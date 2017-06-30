package main;

import dbmanager.MongodDBManager;
import org.bson.Document;

/**
 * Created by cpu11118-local on 30/06/2017.
 */
public class MongoApplication {

    public static void main(String[] args){
        MongodDBManager mongodDBManager = MongodDBManager.newInstance();
        mongodDBManager.getConnectDB();
        Document users = new Document("name", "Tran Van Trai").append("age", 23).append("address", "614 DBP F.11 Q.10");

    }
}
