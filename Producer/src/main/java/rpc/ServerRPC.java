package rpc;

import com.rabbitmq.client.*;
import utils.MyContans;

import java.io.IOException;

/**
 * Created by cpu11118-local on 21/06/2017.
 */
public class ServerRPC extends ConnectRPC{

    public ServerRPC() {
        super();
    }

    public void serverListen(){
        try {
            final Channel channel = connection.createChannel();
            channel.basicQos(1);
            channel.queueDeclare(MyContans.RPC_QUEUE_NAME, false, false, false, null);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        int param = Integer.valueOf(new String(body, "UTF-8").trim());
                        String responeQueue = properties.getReplyTo();
                        //handle param
                        int result = handleValue(param);
                        channel.basicPublish("", responeQueue
                                , new AMQP.BasicProperties()
                                        .builder()
                                        .correlationId(properties.getCorrelationId())
                                        .build()
                                , String.valueOf(result).getBytes());
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume(MyContans.RPC_QUEUE_NAME, false, consumer );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int handleValue(int param) {
        if (param == 0)
            return 0;
        if(param == 1)
            return 1;
        return handleValue(param - 1) + handleValue(param - 2);
    }
}
