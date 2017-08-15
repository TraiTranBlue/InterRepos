package traitv.com.dbmanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import traitv.com.dbmanager.MongoContans;
import traitv.com.dbmanager.models.User;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


/**
 * Created by cpu11118-local on 04/07/2017.
 */
public class UserService extends BaseService {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public UserService() {
        super();
    }

    public Document getUser(String idUser) throws RuntimeException{
        try {
            Document result = (Document) getMongodDBManager().getCollection(MongoContans.PROFILE_COLLECTION)
                    .find(Filters.eq("_id", new ObjectId(idUser))).first();
            return result;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param nameUser
     * @return
     */
    public List<Document> getListUserWithName(String nameUser) throws MongoException {
        List<Document> users = new ArrayList();
        MongoCursor cursor = getMongodDBManager().getCollection(MongoContans.PROFILE_COLLECTION)
                .find(Filters.regex("fullname", ".*" + nameUser + ".*", "i")).iterator();
        while (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            users.add(document);
        }
        return users;
    }

}
