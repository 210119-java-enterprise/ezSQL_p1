package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    private Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private ConnectionFactory (){
        try {
            props.load(new FileReader(String.valueOf("src/main/resources/application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("admin-usr"),
                    props.getProperty("admin-pw")
            );
        } catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

}
