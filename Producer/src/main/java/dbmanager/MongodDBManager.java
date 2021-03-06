package dbmanager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.List;

/**
 * Created by cpu11118-local on 15/06/2017.
 */
public class MongodDBManager {

    private static MongodDBManager mongodDBManager;

    private MongoClient mongodClient;
    private MongoDatabase mongoDatabase;

    public static MongodDBManager newInstance() {
        if(mongodDBManager == null){
            mongodDBManager = new MongodDBManager();
        }
        return mongodDBManager;
    }

    public MongodDBManager() {
        getConnectDB();
    }

    public MongoClient getConnectDB(){
        if(mongodClient == null){
            mongodClient = new MongoClient(new MongoClientURI(MongoContans.URL_SERVER));
            mongoDatabase = mongodClient.getDatabase(MongoContans.DATA_NAME);
            System.out.println("Address connect at path :" + mongodClient.getConnectPoint());
            System.out.println("Address DB name :" + mongodClient.getDatabase(MongoContans.DATA_NAME).getName());
        }
        return mongodClient;
    }

    public MongoCollection getCollection(String collectionName){
        return mongoDatabase.getCollection(collectionName);
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

    public Document findOne(String collectionName){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        return (Document) collection.find().first();
    }

    public FindIterable findAll(String collectionName){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        return collection.find();
    }

    /**
     * Query document by collection name and param filter
     * @param collectionName
     * @param filter
     * @return FindIterable document
     */
    public FindIterable findMany(String collectionName, Bson filter){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        return collection.find(filter);
    }

    /**
     * Check collection has document
     * @param collectionName
     * @return True if has document other case return false
     */
    public boolean checkCollectionExistsDocument(String collectionName){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        return collection.find().first() != null;
    }

}
