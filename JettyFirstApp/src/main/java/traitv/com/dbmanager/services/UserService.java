package traitv.com.dbmanager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import traitv.com.dbmanager.MongoContans;
import traitv.com.dbmanager.models.User;

import javax.swing.text.Document;
import java.io.IOException;


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

    public User getUserContainName(String nameUser) throws IOException {
        Object result = getMongodDBManager().getCollection(MongoContans.USERS_COLLECTION)
                .find(Filters.regex("name", nameUser)).first();

        return null;
    }

}
