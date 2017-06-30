package producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.MyContans;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 15/06/2017.
 */
public class ProducerHellWorld {
    private ConnectionFactory connectionFactory;

    private static ProducerHellWorld producerHellWorld;

    public ProducerHellWorld() {
        this.connectionFactory = new ConnectionFactory();
        this.connectionFactory.setHost("172.17.0.2");
//        this.connectionFactory.setUsername("guest");
//        this.connectionFactory.setPassword("guest");
//        this.connectionFactory.setPort(MyContans.PROT);
//        this.connectionFactory.setVirtualHost("none");
    }

    public static ProducerHellWorld newInstance(){
        if(producerHellWorld == null){
            producerHellWorld = new ProducerHellWorld();
        }
        return producerHellWorld;
    }

    /**
     * Method send message to consumer
     * @param message
     */
    public void sendMessage(String message){
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
//            channel.queueDeclare(MyContans.QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", MyContans.QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Producer send message : " + message);
            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
