package dbmanager;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.connection.ClusterSettings;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.MyContans;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cpu11118-local on 04/07/2017.
 */
public class MongoDBAsynManager {
    private static MongoDBAsynManager manager;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public static MongoDBAsynManager newInstance(){
        if(manager == null){
            manager = new MongoDBAsynManager();
        }
        return manager;
    }

    public MongoDBAsynManager() {
        ClusterSettings settings = ClusterSettings.builder()
                .hosts(Arrays.asList(new ServerAddress("127.0.0.1:27017")))
                .build();
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .clusterSettings(settings)
                .build();
        mongoClient = MongoClients.create(clientSettings);
//        mongoClient = MongoClients.create(new ConnectionString(MyContans.URL_SERVER));
        mongoDatabase = mongoClient.getDatabase(MyContans.DATA_NAME);
        System.out.println(mongoDatabase.getName() + " Async");
    }

    public void insertOne(String collectionName, Document document, SingleResultCallback<Void> callback){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(document, callback);
    }

    public void insertMany(String collectionName, List<Document> documents, SingleResultCallback<Void> callback){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(documents, callback);
    }

    public<T> void findOne(String collectionName, SingleResultCallback<T> callback){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find().first(callback);
    }

    public<T, E> void findAll(String collectionName, Block<T> block, SingleResultCallback<E> callback){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find().forEach(block, callback);
    }

    public <T, E> void findMany(String collectionName, Bson filters, Block<T> block, SingleResultCallback<E> callback){
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find(filters).forEach(block, callback);
    }
}
