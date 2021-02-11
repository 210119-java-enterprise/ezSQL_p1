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

    public void establishConnection(){

    }
}
