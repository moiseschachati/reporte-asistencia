package com.chachati.asistencia.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import java.net.URL;
import java.net.URLClassLoader;

public class PropertyConfiguration extends HttpServlet{
    /**
     * 
     */
    private static final long serialVersionUID = 5174856060131442424L;
    InputStream inputStream;
    private static PropertyConfiguration instance = null;
    private Properties p;

    public PropertyConfiguration() {
        p = new Properties();
        String propFileName = "config.properties";
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();
        
        String path = urls[0].toString().substring(urls[0].toString().indexOf("file:/")+6,urls[0].toString().lastIndexOf('/'));

        try {            
            inputStream = new FileInputStream(path+"/config.properties");
            if (inputStream != null) {
                p.load(inputStream);
            } else {
                System.out.println("inside else");
                inputStream = new FileInputStream("/"+path+"/config.properties");
                if (inputStream != null) {
                    p.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }
        }
    }

    public static PropertyConfiguration getInstance() {

        if (instance == null) {

            instance = new PropertyConfiguration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }
}
