package rpc;

import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by cpu11118-local on 21/06/2017.
 */
public class ClientPRC extends ConnectRPC {
    private String correlationId;
    private String responseQueueName;

    public ClientPRC() {
        super();
    }

    public String call(String param){
        correlationId = UUID.randomUUID().toString();
        try {
            final Channel channel = connection.createChannel();
            responseQueueName = channel.queueDeclare().getQueue();
            channel.basicPublish("", MyContans.RPC_QUEUE_NAME,
                    new AMQP.BasicProperties().builder()
                            .correlationId(correlationId)
                            .replyTo(responseQueueName)
                            .build()
                    , param.getBytes());
            final ArrayBlockingQueue<String> respone = new ArrayBlockingQueue<String>(1);
            channel.basicConsume(responseQueueName,false, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    if(properties.getCorrelationId().equalsIgnoreCase(correlationId)){
                        respone.offer(new String(body, "UTF-8"));
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });

            return respone.take();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
