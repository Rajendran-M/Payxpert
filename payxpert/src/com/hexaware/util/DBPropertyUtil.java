package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    static String url;
    static String user;
    static String password;

    public static void getConnection() {
        // Load properties file from classpath
        InputStream input = DBPropertyUtil.class.getResourceAsStream("db.properties");
        Properties properties = new Properties();

        try {
            if (input == null) {
                System.err.println("Unable to find db.properties file in classpath.");
                return;
            }

            properties.load(input);

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
