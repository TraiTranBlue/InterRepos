
package traitv.com.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cpu11118-local on 21/07/2017.
 */
@WebServlet("hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println(request.getQueryString());
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
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
}
