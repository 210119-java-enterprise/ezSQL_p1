package com.revature.util;

import com.revature.reflectors.Metamodel;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;

/**
 * Session information pertains to information unique to each individual logging into the given Database.
 */
public class Session {

    private Connection connection;
    private List<Metamodel<Class<?>>> metamodelList;
    private static ConnectionFactory connectionFactory;
    private Session mySession;

    public Session(Path p){
        connectionFactory.startConnection(p);
        if (connection == null)
            throw new ExceptionInInitializerError("Session created with null connection.");

        this.connection = connection;
    }

    public void addAnnotatedClass(Class c){
        metamodelList.add(Metamodel.of(c));
    }

    public List<Metamodel<Class<?>>> getMetamodelList() {
        return metamodelList;
    }

    public Metamodel<Class<?>> getClass(Class<?> clazz){
        for (Metamodel<Class<?>> metamodel : metamodelList)
            if (metamodel.getClassName().equals(clazz.getSimpleName()))
                return metamodel;

        return null;
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

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

}
