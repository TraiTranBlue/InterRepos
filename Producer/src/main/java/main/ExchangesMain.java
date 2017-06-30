package main;

import exchanges.BroadCast;
import exchanges.Receiver;
import exchanges.ReceiverError;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cpu11118-local on 20/06/2017.
 */
public class ExchangesMain {
    public static void main(String[] args){
        Receiver.newInstance().receiverMessage();
        ReceiverError.newInstance().receiverMessage();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                BroadCast.newInstance().sendMessagmeByExchanges(" Message " + (i++));
            }
        }, 0, 4000);
    }
}
