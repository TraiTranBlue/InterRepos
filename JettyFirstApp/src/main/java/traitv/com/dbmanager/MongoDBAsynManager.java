package traitv.com.dbmanager;

import com.mongodb.Block;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.*;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ServerSettings;
import com.mongodb.connection.SocketSettings;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cpu11118-local on 04/07/2017.
 */
public class MongoDBAsynManager {
    private static MongoDBAsynManager manager;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public static MongoDBAsynManager newInstance() {
        if (manager == null) {
            try {
                manager = new MongoDBAsynManager();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return manager;
    }

    public MongoDBAsynManager() throws UnknownHostException {
        ClusterSettings settings = ClusterSettings.builder()
                .hosts(Arrays.asList(new ServerAddress("127.0.0.1:27017")))
                .serverSelectionTimeout(60, TimeUnit.SECONDS)
                .build();
        SocketSettings socketSettings = SocketSettings.builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .clusterSettings(settings)
                .socketSettings(socketSettings)
                .build();
        mongoClient = MongoClients.create(clientSettings);
        mongoDatabase = mongoClient.getDatabase(MongoContans.DATA_NAME);
        System.out.println(mongoDatabase.getName() + " Async");
    }

    public MongoCollection getCollection(String collectionName) throws MongoException {
        return mongoDatabase.getCollection(collectionName);
    }

    public void insertOne(String collectionName, Document document, SingleResultCallback<Void> callback)
            throws MongoException {
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(document, callback);
    }

    public void insertMany(String collectionName, List<Document> documents, SingleResultCallback<Void> callback)
            throws MongoException {
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(documents, callback);
    }

    public <T> void findOne(String collectionName, SingleResultCallback<T> callback)
            throws MongoException {
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find().first(callback);
    }

    public <T> void findAll(String collectionName, Block<T> block, SingleResultCallback<Void> callback)
            throws MongoException {
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find().forEach(block, callback);
    }

    public <T> void findMany(String collectionName, Bson filters, Block<T> block, SingleResultCallback<Void> callback)
            throws MongoException {
        MongoCollection collection = mongoDatabase.getCollection(collectionName);
        collection.find(filters).forEach(block, callback);
    }
}
