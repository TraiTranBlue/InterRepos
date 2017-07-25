package traitv.com.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import traitv.com.hanlder.HelloHandler;
import traitv.com.servlets.HelloServlet;
import traitv.com.servlets.V3;

import java.io.File;

/**
 * Created by cpu11118-local on 24/07/2017.
 */
public class ConnectorJettyServer {

    @Override
    protected void finalize() throws Throwable {
        System.out.print("Destroyed Http Jetty Server");
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("127.0.0.1");
        connector.setPort(8080);
        connector.setIdleTimeout(30000);
        server.addConnector(connector);

        // Create a resource handler for static content.
        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setResourceBase("./webapps/static/");
        staticResourceHandler.setDirectoriesListed(true);

        // Create context handler for static resource handler.
        ContextHandler staticContextHandler = new ContextHandler();
        staticContextHandler.setContextPath("/static");
        staticContextHandler.setHandler(staticResourceHandler);

        // Create WebAppContext for JSP files.
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setDescriptor("WEB-INF/web.xml");
        webAppContext.setContextPath("/jsp");
        webAppContext.setResourceBase("./web/pages");
        File warFile = new File(
                "/home/cpu11118-local/JETTY/jetty-distribution-9.4.6.v20170531/demo-base/webapps/test.war" );
        if (!warFile.exists())
        {
            throw new RuntimeException( "Unable to find WAR File: "
                    + warFile.getAbsolutePath() );
        }
        webAppContext.setWar( warFile.getAbsolutePath() );
        webAppContext.setExtractWAR(true);

        // This webapp will use jsps and jstl. We need to enable the
        // AnnotationConfiguration in order to correctly
        // set up the jsp container
//        Configuration.ClassList classlist = Configuration.ClassList
//                .setServerDefault( server );
//        classlist.addBefore(
//                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
//                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        // Set the ContainerIncludeJarPattern so that jetty examines these
        // container-path jars for tlds, web-fragments etc.
        // If you omit the jar that contains the jstl .tlds, the jsp engine will
        // scan for them instead.
        webAppContext.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );
        webAppContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*taglibs.*\\.jar$");
        webAppContext.setParentLoaderPriority(true);
        // ??? THIS DOES NOT STOP DIR LISTING OF ./webapps/jsp/ ???
        webAppContext.setInitParameter("dirAllowed", "false");

        // Create servlet context handler for main servlet.
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(new ServletHolder(new HelloServlet()), "/profile/mono");
        servletContextHandler.addServlet(new ServletHolder(new V3()), "/v3");

        //Hello handler
        HelloHandler helloHanlder = new HelloHandler();

        // Create a handler list to store our static, jsp and servlet context handlers.
        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[] { staticContextHandler, webAppContext, servletContextHandler });
        handlers.setHandlers(new Handler[]{helloHanlder});
        // Add the handlers to the server and start jetty.
        server.setHandler(handlers);
        server.start();
        server.join();


    }
}
