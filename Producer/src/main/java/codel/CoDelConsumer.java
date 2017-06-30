package codel;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 28/06/2017.
 */
public class CoDelConsumer {

    public static void main(String[] args) throws IOException,
            ShutdownSignalException, ConsumerCancelledException,
            InterruptedException, TimeoutException {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.17.0.2");
        final Connection conn = connectionFactory.newConnection();
        final Channel chan = conn.createChannel();
        final QueueingConsumerCoDel consumer = new QueueingConsumerCoDel(chan,
                false, 100, 500){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println(new String(body, "UTF-8"));
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                chan.basicAck(envelope.getDeliveryTag(), false);
//            }
        };

        final QueueingConsumerCoDel consumer2 = new QueueingConsumerCoDel(chan,
                true, 100, 500){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("===>>" + new String(body, "UTF-8"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                chan.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //final QueueingConsumer consumer = new QueueingConsumer(chan);
        final String queue = chan.queueDeclare("myqueue", false, false, true,
                null).getQueue();
        chan.basicQos(2);
        chan.basicConsume(queue, consumer);
//        chan.basicConsume(queue, consumer2);
        //sending message
        final Timer timer = new Timer();

//        timer.schedule(new TimerTask() {
//            int i = 0;
//            @Override
//            public void run() {
//                try {
//                    i++;
//                    chan.basicPublish("", "myqueue", false, null, ("Tran Van Trai  " + i).getBytes());
//                    System.out.println("Send TVT " + i);
//                    if(i == 20){
//                        timer.cancel();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 100);
        long[] sojourns = new long[1000];
        int idx = 0, sum = 0;
        long now = System.currentTimeMillis(), then = now;

        while (true) {
            QueueingConsumerCoDel.Delivery d = consumer.nextDelivery();
            System.out.println(new String(d.getBody(), "UTF-8"));
            sum -= sojourns[idx];
            sojourns[idx] = d.sojournTime;
            sum += sojourns[idx];
            idx += 1;
            if (idx == sojourns.length) {
                idx = 0;
                now = System.currentTimeMillis();
                System.out.println(""
                        + (sojourns.length / ((now - then) / 1000.0D)) + "Hz: "
                        + sum / (double) sojourns.length);
                then = now;
            }
            Thread.sleep(500);
            chan.basicAck(d.getEnvelope().getDeliveryTag(), false);
        }
    }
}
