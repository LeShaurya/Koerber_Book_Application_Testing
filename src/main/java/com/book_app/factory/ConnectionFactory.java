package com.book_app.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection=null;

    public static Connection getConnection(){
        InputStream is=ConnectionFactory
                .class.getClassLoader().getResourceAsStream("dbconn.properties");

        Properties properties=new Properties();
        try{
            properties.load(is);
        } catch (IOException e) {
            System.out.println("IO Error encountered.");
        }

        String url=properties.getProperty("jdbc.url");
        String username=properties.getProperty("jdbc.username");
        String password=properties.getProperty("jdbc.password");
        String driverName=properties.getProperty("jdbc.drivername");

        try{
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Appropriate Driver not found.");
        }
        try {
            connection= DriverManager.getConnection(url, username,password);
        } catch (SQLException e) {
            System.out.println("Issue with the resource");
        }
        return connection;
    }
}
