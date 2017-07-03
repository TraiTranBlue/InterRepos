package main;

import com.mongodb.client.MongoCollection;
import dbmanager.MongodDBManager;
import org.bson.Document;

/**
 * Created by cpu11118-local on 30/06/2017.
 */
public class MongoApplication {

    public static void main(String[] args){
        MongodDBManager mongodDBManager = MongodDBManager.newInstance();
        mongodDBManager.getConnectDB();
        Document users = new Document("name", "Tran Van Trai 2222").append("age", 23).append("address", "614 DBP F.11 Q.10");
        mongodDBManager.inserOne(MongodDBManager.USERS_COLLECTION, users);
    }
}
