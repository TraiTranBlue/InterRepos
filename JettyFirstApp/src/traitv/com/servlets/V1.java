
package traitv.com.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cpu11118-local on 21/07/2017.
 */
@WebServlet(name = "V1", urlPatterns = {"v1"})
public class V1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType( "text/html" );
        PrintWriter pw = res.getWriter();

        // create 32K buffer
        res.setBufferSize( 32 * 1024 );

        pw.println( "everything we send now is buffered " );
        pw.println( "at the server until we reach the " );
        pw.println( "buffer size limit or we do a flush!" );
        pw.flush();
        // check to see if anything has been sent to the client
        if( res.isCommitted() ) {
            pw.println( "<br>damned, something has been sent" );
        }
        else {
            // clear the buffer, so what has gone before is lost
//            res.resetBuffer();

            pw.println( "we are going to clear this as well" );

            // clears buffer, status codes and headers
//            res.reset();

            // we've blanked the ContentType header so lets add it back
            res.setContentType( "text/html" );

            pw.println( "<br>but we are going to see this" );
        }

        // send what we have now
//        res.flushBuffer();

        pw.println( "<br>and this will get flushed at the end of the method" );
        pw.println( "<br>current response buffer size is: " + res.getBufferSize());

    }
}
