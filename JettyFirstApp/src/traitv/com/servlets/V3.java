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
@WebServlet("/profile/mysite")

public class V3 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("OK");
        System.out.println(req.getParameter("value1"));
        System.out.println(req.getParameter("value2"));

        PrintWriter writer = resp.getWriter();
        writer.println(req.getParameter("value1"));
        writer.println(req.getParameter("value2"));
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
