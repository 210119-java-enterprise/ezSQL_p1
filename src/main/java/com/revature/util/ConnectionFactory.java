package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private Properties props = new Properties();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private ConnectionFactory (Path properties){
        try {
            props.load(new FileReader(String.valueOf(properties)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConnectionFactory establishConnection(Path properties){
        ConnectionFactory connect = new ConnectionFactory(properties);

        return connect;
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
