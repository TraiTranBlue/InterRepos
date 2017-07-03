package dbmanager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import utils.MyContans;

import java.util.List;

/**
 * Created by cpu11118-local on 15/06/2017.
 */
public class MongodDBManager {
    public static final String USERS_COLLECTION = "Users";
    private static MongodDBManager mongodDBManager;

    private MongoClient mongodClient;
    private MongoDatabase mongoDatabase;

    public static MongodDBManager newInstance() {
        if(mongodDBManager == null){
            mongodDBManager = new MongodDBManager();
        }
        return mongodDBManager;
    }


    public MongoClient getConnectDB(){
        if(mongodClient == null){
            mongodClient = new MongoClient(new MongoClientURI(MyContans.URL_SERVER));
            mongoDatabase = mongodClient.getDatabase(MyContans.DATA_NAME);
            System.out.println("Address connect at path :" + mongodClient.getConnectPoint());
            System.out.println("Address DB name :" + mongodClient.getDatabase(MyContans.DATA_NAME).getName());
        }
        return mongodClient;
    }


    public void inserOne(String collectionName, Document document){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(document);
        System.out.println("Insert one document into " + collection.getNamespace());
    }

    public void inserMany(String collectionName, List<Document> documents, InsertManyOptions manyOptions){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(documents, manyOptions);
        System.out.println("Insert many document into " + collection);
    }

    public void inserMany(String collectionName, List<Document> documents){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(documents);
        System.out.println("Insert many document into " + collection);
    }
}
