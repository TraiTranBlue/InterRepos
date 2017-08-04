package topics;

import base.ReceiverBase;
import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public class ReceiverWarningTopic extends ReceiverBase{
    private static ReceiverWarningTopic receiver;

    public static ReceiverWarningTopic newInstance(){
        if (receiver == null){
            receiver = new ReceiverWarningTopic();
        }
        return receiver;
    }

    public ReceiverWarningTopic() {
        super();
    }

    public void receiverMessage() {

    }

    public void receiverMessage(final String key){
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare("fanoutExchange", "fanout");
            channel.queueDeclare("fanoutQueue22", true, false, false, null);
//            String queue = channel.queueDeclare().getQueue();
            channel.queueBind("fanoutQueue22", "fanoutExchange", "");

            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("[==> TOPIC WARNING - " + key +"] Receiving " + message + " with key " + envelope.getRoutingKey());
                }
            };
            channel.basicConsume("fanoutQueue22", true, consumer);
//            GetResponse response = channel.basicGet("topicQueue22", false);
//            if(response == null){
//                System.out.println("Response = NULL");
//            }else {
//                String message = new String(response.getBody(), "UTF-8");
//                System.out.println("[==> TOPIC WARNING] Receiving " + message + " with key " + response.getEnvelope().getRoutingKey());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
