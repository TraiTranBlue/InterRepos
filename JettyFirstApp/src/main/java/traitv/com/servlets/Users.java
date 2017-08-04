package traitv.com.servlets;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.sun.mail.iap.Response;
import org.bson.Document;
import org.json.JSONObject;
import traitv.com.dbmanager.MongoContans;
import traitv.com.dbmanager.MongodDBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cpu11118-local on 21/07/2017.
 */
public class Users extends HttpServlet {
    private Gson mGson;
    @Override
    public void init() throws ServletException {
        mGson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        if(req.getParameter("usersId") != null){

        }else {
            writer.println("NULL");
        }
        FindIterable<Document> documents = MongodDBManager.newInstance().findAll(MongoContans.USERS_COLLECTION);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(Response.OK);
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
