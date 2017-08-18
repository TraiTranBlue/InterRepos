package traitv.com.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.sun.mail.iap.Response;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import traitv.com.dbmanager.MongoContans;
import traitv.com.dbmanager.MongodDBManager;
import traitv.com.dbmanager.models.User;
import traitv.com.dbmanager.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by cpu11118-local on 21/07/2017.
 */
public class Users extends HttpServlet {

    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setStatus(Response.OK);
        if (req.getParameter("name") != null) {
            List<Document> users = UserService.getInstance().getListUserWithName(req.getParameter("name"));
            JSONObject result = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for(Document document : users){
                JSONObject object = new JSONObject(document.toJson());
                jsonArray.put(object);
            }
            result.put("result", jsonArray);
            result.put("size", users.size());
            writer.print(result.toString());
        } else if (req.getParameter("id") != null) {
            try {
                Document user = UserService.getInstance().getUser(req.getParameter("id"));
                if (user != null) {
                    writer.print(user.toJson());
                    System.out.println(user.getObjectId("_id").toString());
                } else
                    writer.print("NULL");
            }catch (RuntimeException e){
                e.printStackTrace();
                writer.print("Server ERRORRRRRRRRRRRRRRRRRRR");
            }

        } else {
            writer.print(false);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(Response.OK);
        System.out.println("PSOT =========");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            if(jb.length() != 0){
                JSONObject jsonObject = new JSONObject(jb.toString());
                System.out.println(jsonObject.toString() + "sdadsaadsdsa");
                writer.print(jsonObject);
            }else {
                writer.println("[{\n" +
                        "  \"id\": 1,\n" +
                        "  \"first_name\": \"Karisa\",\n" +
                        "  \"last_name\": \"Barnicott\",\n" +
                        "  \"email\": \"kbarnicott0@ftc.gov\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"ip_address\": \"70.27.148.135\"\n" +
                        "}, {\n" +
                        "  \"id\": 2,\n" +
                        "  \"first_name\": \"Amitie\",\n" +
                        "  \"last_name\": \"Haddow\",\n" +
                        "  \"email\": \"ahaddow1@google.ca\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"ip_address\": \"63.232.132.178\"\n" +
                        "}, {\n" +
                        "  \"id\": 3,\n" +
                        "  \"first_name\": \"Harp\",\n" +
                        "  \"last_name\": \"Dubble\",\n" +
                        "  \"email\": \"hdubble2@sourceforge.net\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"36.74.219.150\"\n" +
                        "}, {\n" +
                        "  \"id\": 4,\n" +
                        "  \"first_name\": \"Carly\",\n" +
                        "  \"last_name\": \"Engall\",\n" +
                        "  \"email\": \"cengall3@google.co.uk\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"255.109.76.107\"\n" +
                        "}, {\n" +
                        "  \"id\": 5,\n" +
                        "  \"first_name\": \"Lorianna\",\n" +
                        "  \"last_name\": \"Vreede\",\n" +
                        "  \"email\": \"lvreede4@tiny.cc\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"ip_address\": \"133.44.21.59\"\n" +
                        "}, {\n" +
                        "  \"id\": 6,\n" +
                        "  \"first_name\": \"Calypso\",\n" +
                        "  \"last_name\": \"Saiger\",\n" +
                        "  \"email\": \"csaiger5@etsy.com\",\n" +
                        "  \"gender\": \"Female\",\n" +
                        "  \"ip_address\": \"181.97.223.231\"\n" +
                        "}, {\n" +
                        "  \"id\": 7,\n" +
                        "  \"first_name\": \"Matteo\",\n" +
                        "  \"last_name\": \"Tremlett\",\n" +
                        "  \"email\": \"mtremlett6@nifty.com\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"125.17.175.181\"\n" +
                        "}, {\n" +
                        "  \"id\": 8,\n" +
                        "  \"first_name\": \"Briano\",\n" +
                        "  \"last_name\": \"Pardy\",\n" +
                        "  \"email\": \"bpardy7@craigslist.org\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"196.57.150.167\"\n" +
                        "}, {\n" +
                        "  \"id\": 9,\n" +
                        "  \"first_name\": \"Bjorn\",\n" +
                        "  \"last_name\": \"Lepard\",\n" +
                        "  \"email\": \"blepard8@constantcontact.com\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"151.85.135.134\"\n" +
                        "}, {\n" +
                        "  \"id\": 10,\n" +
                        "  \"first_name\": \"Timmie\",\n" +
                        "  \"last_name\": \"Wreath\",\n" +
                        "  \"email\": \"twreath9@diigo.com\",\n" +
                        "  \"gender\": \"Male\",\n" +
                        "  \"ip_address\": \"96.226.32.184\"\n" +
                        "}]");
                writer.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
            /*report an error*/ }
    }
}
