package topics;

import base.ReceiverBase;
import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public class ReceiverErrorTopic extends ReceiverBase{
    private static ReceiverErrorTopic receiver;

    public static ReceiverErrorTopic newInstance(){
        if (receiver == null){
            receiver = new ReceiverErrorTopic();
        }
        return receiver;
    }

    public ReceiverErrorTopic() {
        super();
    }

    public void receiverMessage(){
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(MyContans.TOPIC_EXCHANGE_NAME, MyContans.TOPIC_EXCHANGE);
            String nameQueue = channel.queueDeclare().getQueue();
            channel.queueBind(nameQueue, MyContans.TOPIC_EXCHANGE_NAME, MyContans.keyRountingTopic[1]);

            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[==> TOPIC ERROR] Receiving " + message + " with key " + envelope.getRoutingKey());
                }
            };
            channel.basicConsume(nameQueue, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
