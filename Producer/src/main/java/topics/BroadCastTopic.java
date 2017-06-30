package topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.MyContans;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public class BroadCastTopic {
    private static BroadCastTopic broadCast;
    private ConnectionFactory connectionFactory;
    private static String[] keyRounting = new String[]{"info.error.warning", "error", "noinfo.error.warning.#", "error.warning"};
    private Random random = new Random();

    public static BroadCastTopic newInstance(){
        if (broadCast == null){
            broadCast = new BroadCastTopic();
        }
        return broadCast;
    }

    public BroadCastTopic() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(MyContans.LOCALHOST_RABBITMQ);
    }

    public void sendMessagmeByExchanges(String message){
        try {
            String key = generateRoutingKey();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(MyContans.TOPIC_EXCHANGE_NAME, MyContans.TOPIC_EXCHANGE);
            channel.basicPublish(MyContans.TOPIC_EXCHANGE_NAME, key, null, message.getBytes());
            System.out.println("[x] Sending " + message + " with key: " + key);
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public String generateRoutingKey(){
        return keyRounting[random.nextInt(keyRounting.length)];
    }
}
