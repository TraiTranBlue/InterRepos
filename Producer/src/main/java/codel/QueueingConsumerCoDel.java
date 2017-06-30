package codel;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.utility.Utility;
/**
 * Created by cpu11118-local on 28/06/2017.
 */
public class QueueingConsumerCoDel extends DefaultConsumer {

    public static final long DEFAULT_TARGET_DELAY_MS = 200;
    public static final long DEFAULT_INTERVAL_MS = 20;
    public static final boolean DEFAULT_REQUEUE = true;

    // Marker object used to signal the queue is in shutdown mode.
    // It is only there to wake up consumers. The canonical representation
    // of shutting down is the presence of _shutdown.
    // Invariant: This is never on _queue unless _shutdown != null.
    private static final Delivery POISON = new Delivery(null, null, null);

    private static final Delivery NULL_DELIVERY = new Delivery(null, null, null);

    private final BlockingQueue<Delivery> _queue;

    // When this is non-null the queue is in shutdown mode and nextDelivery
    // should
    // throw a shutdown signal exception.
    private volatile ShutdownSignalException _shutdown;
    private volatile ConsumerCancelledException _cancelled;

    private long _firstAboveTime = 0;
    private long _dropNextAt = 0;
    private long _dropCount = 0;
    private boolean _dropping = false;
    private final Channel _chan;
    private final boolean _requeue;
    private final long _targetDelay;
    private final long _interval;

    public QueueingConsumerCoDel(Channel ch) {
        this(ch, new LinkedBlockingQueue<Delivery>(), DEFAULT_REQUEUE,
                DEFAULT_TARGET_DELAY_MS, DEFAULT_INTERVAL_MS);
    }

    public QueueingConsumerCoDel(Channel ch, boolean requeue) {
        this(ch, new LinkedBlockingQueue<Delivery>(), requeue,
                DEFAULT_TARGET_DELAY_MS, DEFAULT_INTERVAL_MS);
    }

    public QueueingConsumerCoDel(Channel ch, boolean requeue, long targetDelay,
                                 long interval) {
        this(ch, new LinkedBlockingQueue<Delivery>(), requeue, targetDelay,
                interval);
    }

    public QueueingConsumerCoDel(Channel ch, BlockingQueue<Delivery> q) {
        this(ch, q, DEFAULT_REQUEUE, DEFAULT_TARGET_DELAY_MS,
                DEFAULT_INTERVAL_MS);
    }

    public QueueingConsumerCoDel(Channel ch, BlockingQueue<Delivery> q,
                                 boolean requeue, long targetDelay, long interval) {
        super(ch);
        _queue = q;
        _chan = ch;
        _requeue = requeue;
        _targetDelay = targetDelay;
        _interval = interval;
    }

    @Override
    public void handleShutdownSignal(String consumerTag,
                                     ShutdownSignalException sig) {
        _shutdown = sig;
        _queue.add(POISON);
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        _cancelled = new ConsumerCancelledException();
        _queue.add(POISON);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {
        checkShutdown();
        this._queue.add(new Delivery(envelope, properties, body));
    }

    /**
     * Check if we are in shutdown mode and if so throw an exception.
     */
    private void checkShutdown() {
        if (_shutdown != null)
            throw Utility.fixStackTrace(_shutdown);
    }

    /**
     * If delivery is not POISON nor null, return it.
     * <p/>
     * If delivery, _shutdown and _cancelled are all null, return null.
     * <p/>
     * If delivery is POISON re-insert POISON into the queue and throw an
     * exception if POISONed for no reason.
     * <p/>
     * Otherwise, if we are in shutdown mode or cancelled, throw a corresponding
     * exception.
     */
    private Delivery handle(Delivery delivery) {
        if (delivery == POISON || delivery == null
                && (_shutdown != null || _cancelled != null)) {
            if (delivery == POISON) {
                _queue.add(POISON);
                if (_shutdown == null && _cancelled == null) {
                    throw new IllegalStateException(
                            "POISON in queue, but null _shutdown and null _cancelled. "
                                    + "This should never happen, please report as a BUG");
                }
            }
            if (null != _shutdown)
                throw Utility.fixStackTrace(_shutdown);
            if (null != _cancelled)
                throw Utility.fixStackTrace(_cancelled);
        }
        return delivery;
    }

    /**
     * Main application-side API: wait for the next message delivery and return
     * it.
     *
     * @return the next message
     * @throws InterruptedException    if an interrupt is received while waiting
     * @throws ShutdownSignalException if the connection is shut down while waiting
     * @throws IOException             if an IOException gets thrown
     */
    public Delivery nextDelivery() throws InterruptedException,
            ShutdownSignalException, ConsumerCancelledException, IOException {
        Delivery d = dequeue(false);
        while (true) {
            if (d == NULL_DELIVERY) {
                _dropping = false;
            }
            if (_dropping) {
                if (!d.dropable) {
                    _dropping = false;
                } else if (d.dequeuedAt >= _dropNextAt) {
                    while (d.dequeuedAt >= _dropNextAt && _dropping) {
                        _chan.basicNack(d.getEnvelope().getDeliveryTag(),
                                false, _requeue);
                        _dropCount++;
                        d = dequeue(false);
                        if (!d.dropable) {
                            _dropping = false;
                        } else {
                            _dropNextAt += controlLaw();
                        }
                    }
                }
            } else if (d.dropable
                    && ((d.dequeuedAt - _dropNextAt < _interval) || (d.dequeuedAt
                    - _firstAboveTime >= _interval))) {
                _chan.basicNack(d.getEnvelope().getDeliveryTag(), false,
                        _requeue);
                _dropping = true;
                if (d.dequeuedAt - _dropNextAt < _interval) {
                    _dropCount = _dropCount > 2 ? _dropCount - 2 : 1;
                } else {
                    _dropCount = 1;
                }
                _dropNextAt = d.dequeuedAt + controlLaw();
                d = NULL_DELIVERY;
            }
            if (d == NULL_DELIVERY) {
                d = dequeue(true);
            } else {
                return d;
            }
        }
    }

    private long controlLaw() {
        return (long) (_interval / Math.sqrt(_dropCount));
    }

    private Delivery dequeue(boolean block) throws InterruptedException {
        Delivery d;
        if (block) {
            d = _queue.take();
        } else {
            d = _queue.poll();
        }
        d = handle(d);
        if (d == null) {
            _firstAboveTime = 0;
            d = NULL_DELIVERY;
        } else {
            d.dequeued();
            if (d.sojournTime < _targetDelay) {
                _firstAboveTime = 0;
            } else {
                if (_firstAboveTime == 0) {
                    _firstAboveTime = d.dequeuedAt + _interval;
                } else if (d.dequeuedAt >= _firstAboveTime) {
                    d.dropable = true;
                }
            }
        }
        return d;
    }

    public static class Delivery {
        private final Envelope _envelope;
        private final AMQP.BasicProperties _properties;
        private final byte[] _body;
        private long enqueuedAt;
        private boolean dropable = false;
        public long sojournTime = 0;
        public long dequeuedAt = 0;

        public Delivery(Envelope envelope, AMQP.BasicProperties properties,
                        byte[] body) {
            _envelope = envelope;
            _properties = properties;
            _body = body;
            enqueuedAt = System.currentTimeMillis();
        }

        private void dequeued() {
            dequeuedAt = System.currentTimeMillis();
            sojournTime = dequeuedAt - enqueuedAt;
        }

        /**
         * Retrieve the message envelope.
         *
         * @return the message envelope
         */
        public Envelope getEnvelope() {
            return _envelope;
        }

        /**
         * Retrieve the message properties.
         *
         * @return the message properties
         */
        public BasicProperties getProperties() {
            return _properties;
        }

        /**
         * Retrieve the message body.
         *
         * @return the message body
         */
        public byte[] getBody() {
            return _body;
        }
    }
}
