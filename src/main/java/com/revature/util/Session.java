package com.revature.util;

import com.revature.reflectors.Metamodel;

import java.sql.Connection;

/**
 * Session information pertains to information unique to each individual logging into the given Database.
 */
public class Session {

    private Connection connection;
    private static MetamodelManager metamodelManager;
    private Metamodel<?> metamodel;

    public Session(Connection connection, MetamodelManager metamodelManager){
        if (connection == null)
            throw new ExceptionInInitializerError("Session created with null connection.");

        this.connection = connection;
        this.metamodelManager = metamodelManager;
    }

    public static MetamodelManager getMetamodelManager() {
        return metamodelManager;
    }

    public static void setMetamodelManager(MetamodelManager metamodelManager) {
        Session.metamodelManager = metamodelManager;
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void addAnnotatedClass(Class c){
        metamodelManager.addMetaModel(Metamodel.of(c));
        metamodel = Metamodel.of(c);
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
