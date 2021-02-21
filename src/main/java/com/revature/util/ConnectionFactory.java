package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = null;
    private static Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private ConnectionFactory (Path p){
        try {
            props.load(new FileReader(String.valueOf(p)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startConnection(Path p){
        connectionFactory = new ConnectionFactory(p);
    }

    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public static Connection getConnection() {
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
