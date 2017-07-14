package main;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dbmanager.MongoContans;
import dbmanager.MongoDBAsynManager;
import dbmanager.MongodDBManager;
import org.bson.*;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

/**
 *
 * Created by cpu11118-local on 30/06/2017.
 */
public class MongoApplication {

    public static void main(String[] args){
        Block<Document> block = new Block<Document>() {
            public void apply(Document document) {
                System.out.println(document.toJson());
            }
        };
        MongodDBManager mongodDBManager = MongodDBManager.newInstance();
        MongoDBAsynManager mongoDBAsynManager = MongoDBAsynManager.newInstance();
        Bson filter = Filters.elemMatch("instock", Filters.and(Filters.in("warehouse", new String[]{"A", "B"}),
                Filters.gte("qty", 40.0), Filters.lte("qty", 60.0)));



        BasicDBObject andFilter = new BasicDBObject();
        andFilter.put("warehouse", new BasicDBObject("$in", Arrays.asList("A", "B")));
        andFilter.put("qty", new BasicDBObject("$gte", 40.0).append("$lte", 60.0));
        BasicDBObject object = new BasicDBObject("$elemMatch", andFilter);
        Bson query = new BasicDBObject("instock", object);


        Bson queryFilter = Filters.and(Filters.or(Filters.in("qty", new Object[]{25, 10, 50}), Filters.in("tags", new String[]{"red"})),
                Filters.eq("item", "notebook"));

        mongoDBAsynManager.getCollection(MongoContans.INVENTORY_COLLECTION).find(queryFilter).forEach(block, new SingleResultCallback<Void>() {
            public void onResult(Void aVoid, Throwable throwable) {

            }
        });


        System.out.println(mongodDBManager.getCollection(MongoContans.INVENTORY_COLLECTION).count(query));

        Scanner scanner = new Scanner(System.in);
        int exit = scanner.nextInt();
        while (exit != 1){
            System.out.println("Please type 1 in order to exit program : ");
            exit = scanner.nextInt();
        }
        System.exit(0);
    }
}
