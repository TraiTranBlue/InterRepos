package codel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 10/08/2017.
 */
public class CoDelComsumerSecondary {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.17.0.1");
        connectionFactory.setUsername("myuser");
        connectionFactory.setPassword("mypass");
        connectionFactory.setVirtualHost("/");
        final Connection conn = connectionFactory.newConnection();
        final Channel chan = conn.createChannel();
        final QueueingConsumerCoDel consumer = new QueueingConsumerCoDel(chan,
                true, 200, 500);

        final String queue = chan.queueDeclare("myqueue", false, false, true,
                null).getQueue();
        chan.basicQos(5);
        chan.basicConsume(queue, consumer);
        while (true) {
            QueueingConsumerCoDel.Delivery d = consumer.nextDelivery();
            System.out.println(new String(d.getBody(), "UTF-8") + "consumer 2");
            Thread.sleep(500);
            chan.basicAck(d.getEnvelope().getDeliveryTag(), false);
        }
    }
}
