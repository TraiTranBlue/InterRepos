package traitv.com.dbmanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import traitv.com.dbmanager.MongoContans;
import traitv.com.dbmanager.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


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

    public User getUser(String idUser) throws IOException {
        Document result = (Document) getMongodDBManager().getCollection(MongoContans.PROFILE_COLLECTION)
                .find(Filters.eq("_id", new ObjectId(idUser))).first();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(result.toJson(), User.class);

    }

    /**
     * @param nameUser
     * @return
     */
    public List<User> getListUserWithName(String nameUser) throws IOException {
        List<User> users = new ArrayList<User>();
        MongoCursor cursor = getMongodDBManager().getCollection(MongoContans.USERS_COLLECTION)
                .find(Filters.regex("name", ".*" + nameUser + ".*", "i")).iterator();
        ObjectMapper mapper = new ObjectMapper();
        while (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            users.add(mapper.readValue(document.toJson(), User.class));
        }
        return users;
    }

}
