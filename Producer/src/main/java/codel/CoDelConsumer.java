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
        connectionFactory.setHost("172.17.0.1");
        connectionFactory.setUsername("myuser");
        connectionFactory.setPassword("mypass");
        connectionFactory.setVirtualHost("/");
        final Connection conn = connectionFactory.newConnection();
        final Channel chan = conn.createChannel();
        final QueueingConsumerCoDel consumer = new QueueingConsumerCoDel(chan,
                true, 200, 500);
        //final QueueingConsumer consumer = new QueueingConsumer(chan);
        final String queue = chan.queueDeclare("myqueue", false, false, true,
                null).getQueue();
        chan.basicQos(5);
        chan.basicConsume(queue, consumer);
//        chan.basicConsume(queue, consumer2);
        //sending message
        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                try {
                    i++;
                    chan.basicPublish("", "myqueue", false, null, ("Tran Van Trai  " + i).getBytes());
                    System.out.println("Send TVT " + i);
                    if (i == 300) {
                        timer.cancel();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 100);
        long[] sojourns = new long[1000];
        int idx = 0, sum = 0;
        long now = System.currentTimeMillis(), then = now;
        while (true) {
            QueueingConsumerCoDel.Delivery d = consumer.nextDelivery();
            System.out.println(new String(d.getBody(), "UTF-8") + "consumer 1");
//            sum -= sojourns[idx];
//            sojourns[idx] = d.sojournTime;
//            sum += sojourns[idx];
//            idx += 1;
//            if (idx == sojourns.length) {
//                idx = 0;
//                now = System.currentTimeMillis();
//                System.out.println(""
//                        + (sojourns.length / ((now - then) / 1000.0D)) + "Hz: "
//                        + sum / (double) sojourns.length);
//                then = now;
//            }
            Thread.sleep(500);
            chan.basicAck(d.getEnvelope().getDeliveryTag(), false);
        }
    }
}
