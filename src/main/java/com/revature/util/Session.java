package com.revature.util;

import java.sql.Connection;

/**
 * Session information pertains to information unique to each individual logging into the given Database.
 */
public class Session {

    private Connection connection;

    public Session(Connection connection){
        if (connection == null)
            throw new ExceptionInInitializerError("Impossible");

        this.connection = connection;
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public int add (Class<?> clazz){
        return 1;
    }

    public void remove (Class<?> clazz){

    }

    public void update (Class<?> clazz){

    }

    public Class<?> read (Class<?> clazz){

        return null;
    }

}
