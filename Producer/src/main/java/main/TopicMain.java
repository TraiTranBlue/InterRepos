package main;

import topics.BroadCastTopic;
import topics.ReceiverErrorTopic;
import topics.ReceiverInfoTopic;
import topics.ReceiverWarningTopic;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public class TopicMain {
    public static void main (String[] args){
        ReceiverInfoTopic.newInstance().receiverMessage();
        ReceiverErrorTopic.newInstance().receiverMessage();
        ReceiverWarningTopic.newInstance().receiverMessage();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                BroadCastTopic.newInstance().sendMessagmeByExchanges("Sending message " + (i++));
            }
        }, 0, 4000);
    }
}
