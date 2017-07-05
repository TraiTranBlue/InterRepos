package main;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import dbmanager.MongodDBManager;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
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
        mongodDBManager.getCollection(MongodDBManager.USERS_COLLECTION)
                .find()
//                .projection(Projections.fields(Projections.include("qty"), Projections.excludeId()))
                .forEach(documentBlock);
        mongodDBManager.getCollection(MongodDBManager.USERS_COLLECTION)
                .updateOne(Filters.and(Filters.regex("name", "*Dan*")), new Document("$set", new Document("age", 1000).append("name", "Tran Van Dan")));
        mongodDBManager.getCollection(MongodDBManager.USERS_COLLECTION)
                .find()
//                .projection(Projections.fields(Projections.include("qty"), Projections.excludeId()))
                .forEach(documentBlock);
        Document document = new Document();
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }
}
