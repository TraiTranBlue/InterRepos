package receiver;

import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 19/06/2017.
 */
public class ConsumerComplexTask {
    private static ConsumerComplexTask consumerHelloWorld;

    private ConnectionFactory connectionFactory;

    public ConsumerComplexTask() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost("172.17.0.2");
//        this.connectionFactory.setUsername("guest");
//        this.connectionFactory.setPassword("guest");
//        this.connectionFactory.setVirtualHost("/");
//        this.connectionFactory.setPort(MyContans.PROT);
    }

    public static ConsumerComplexTask newInstance(){
        if(consumerHelloWorld == null){
            consumerHelloWorld = new ConsumerComplexTask();
        }
        return consumerHelloWorld;
    }

    public void subscribeMessage(final String nameTask){
        try {
            Connection connection = this.connectionFactory.newConnection();
            System.out.println("Waiting for messaging complex task "+ nameTask +"....");
            final Channel channel = connection.createChannel();
            channel.queueDeclare(MyContans.QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                    try {
                        String message = new String(bytes, "UTF-8");
                        System.out.println("[==>] Received " + nameTask +" '" + message + "'");
                        doWorker();
                    } finally {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            channel.basicConsume(MyContans.QUEUE_NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    private void doWorker(){
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
