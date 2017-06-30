package rpc;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import utils.MyContans;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cpu11118-local on 21/06/2017.
 */
public abstract class ConnectRPC {
    protected ConnectionFactory connectionFactory;
    protected Connection connection;

    public ConnectRPC() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(MyContans.HOST_RABBITMQ);
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void closeConnect(){
        if(connection != null){
            try {
                connection.close();
                connection = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
