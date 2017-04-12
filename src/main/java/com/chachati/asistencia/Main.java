package com.chachati.asistencia;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.chachati.asistencia.utils.PropertyConfiguration;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Loading configuration from properties file...");
        PropertyConfiguration sp = PropertyConfiguration.getInstance();

        logger.info("Starting embedded web server...");
        // 1. Creating the server on specified port
        Server server = new Server(Integer.valueOf(sp.getProperty("jetty.server.port")));

        // 2. Creating the WebAppContext for the created content
        WebAppContext ctx = new WebAppContext();
        ctx.setResourceBase("src/main/webapp");
        ctx.setContextPath("/jetty-jsp-example");

        // 3. Including the JSTL jars for the webapp.
        ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");

        // 4. Enabling the Annotation based configuration
        org.eclipse.jetty.webapp.Configuration.ClassList classlist =
                org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        // 5. Setting the handler and starting the Server
        server.setHandler(ctx);
        server.start();
        server.join();

        logger.info("Embedded web server started successfully...");
    }
}
