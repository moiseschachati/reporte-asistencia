package com.chachati.asistencia.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.chachati.asistencia.utils.PropertyConfiguration;

public class ConnectionProvider {
    private final static String DB_DRIVER_KEY = "asistencia.db.driver";
    private final static String DB_HOST_KEY = "asistencia.db.host";
    private final static String DB_NAME_KEY = "asistencia.db.name";
    private final static String DB_USERNAME_KEY = "asistencia.db.username";
    private final static String DB_PASSWORD_KEY = "asistencia.db.password";
    private final static String DB_PORT_KEY = "asistencia.db.port";
    private final static String DB_URL_PREFIX_KEY = "jdbc:sqlserver://";
    private final static String DB_INSTANCE_KEY = "asistencia.db.instance";
    final static Logger logger = Logger.getLogger(ConnectionProvider.class);

    public static Connection getCon() {
        Connection connection = null;
        PropertyConfiguration sp = PropertyConfiguration.getInstance();
        StringBuilder url = new StringBuilder(DB_URL_PREFIX_KEY);
        try {
            // the sql server driver string
            Class.forName(sp.getProperty(DB_DRIVER_KEY));
            if (!StringUtils.isBlank(sp.getProperty(DB_INSTANCE_KEY))) {
                url.append(sp.getProperty(DB_INSTANCE_KEY)).append(";DatabaseName=").append(sp.getProperty(DB_NAME_KEY));
            } else {
                url.append(sp.getProperty(DB_HOST_KEY)).append(":").append(sp.getProperty(DB_PORT_KEY))
                        .append(";DatabaseName=").append(sp.getProperty(DB_NAME_KEY));
            }

            logger.debug("url: " + url);

            // get the sql server database connection
            connection = DriverManager.getConnection(url.toString(), sp.getProperty(DB_USERNAME_KEY),
                    sp.getProperty(DB_PASSWORD_KEY));

            return connection;
        } catch (ClassNotFoundException e) {
            logger.debug("ClassNotFoundException: " + e);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.debug("Exception: " + e);
            e.printStackTrace();
        }
        return connection;
    }
}
