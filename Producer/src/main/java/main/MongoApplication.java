package main;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.model.Filters;
import dbmanager.MongoDBAsynManager;
import dbmanager.MongodDBManager;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;

/**
 *
 * Created by cpu11118-local on 30/06/2017.
 */
public class MongoApplication {

    public static void main(String[] args){
        MongodDBManager mongodDBManager = MongodDBManager.newInstance();
        mongodDBManager.getConnectDB();
        Block<Document> documentBlock = new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        Bson filter = Filters.and(Filters.eq("item", "journal"), Filters.gte("qty", 15));
        mongodDBManager.getCollection(MongodDBManager.INVENTORY_COLLECTION).find(filter).forEach(documentBlock);
        MongoDBAsynManager.newInstance().findMany(MongodDBManager.INVENTORY_COLLECTION, filter, new Block<Document>() {
                    public void apply(Document document) {
                        System.out.println("===>> Block " + document.toJson());
                    }
                },
                new SingleResultCallback<Void>() {
                    public void onResult(Void document, Throwable throwable) {
                        System.out.println("===>> Operator finished");
                    }
                });
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }
}
