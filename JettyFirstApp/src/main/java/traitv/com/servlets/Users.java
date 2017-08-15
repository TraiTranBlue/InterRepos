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
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            JSONObject jsonObject = new JSONObject(jb.toString());
            writer.print(jsonObject);
        } catch (Exception e) { /*report an error*/ }
    }
}
