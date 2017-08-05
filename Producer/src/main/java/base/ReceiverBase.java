package base;

import com.rabbitmq.client.ConnectionFactory;
import utils.MyContans;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public abstract class ReceiverBase implements  Receiver{
    protected ConnectionFactory connectionFactory;

    public ReceiverBase() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("myuser");
        connectionFactory.setPassword("mypass");
        connectionFactory.setVirtualHost("/");
    }
}
