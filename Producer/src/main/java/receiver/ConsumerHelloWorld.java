package receiver;

import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 15/06/2017.
 */
public class ConsumerHelloWorld {

    private static ConsumerHelloWorld consumerHelloWorld;

    private ConnectionFactory connectionFactory;

    public ConsumerHelloWorld() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost(MyContans.LOCALHOST_RABBITMQ);
//        this.connectionFactory.setUsername("guest");
//        this.connectionFactory.setPassword("guest");
//        this.connectionFactory.setVirtualHost("/");
//        this.connectionFactory.setPort(MyContans.PROT);
    }

    public static ConsumerHelloWorld newInstance(){
        if(consumerHelloWorld == null){
            consumerHelloWorld = new ConsumerHelloWorld();
        }
        return consumerHelloWorld;
    }

    public void subscribeMessage(){
        try {
            Connection connection = this.connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(MyContans.QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                    String message = new String(bytes, "UTF-8");
                    System.out.println("[==>] Received '" + message + "'");
                }
            };
            channel.basicConsume(MyContans.QUEUE_NAME, true, consumer);
            System.out.println("Waiting message incoming ...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
