package com.revature.util;

import com.revature.reflectors.Metamodel;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Session information pertains to information unique to each individual logging into the given Database.
 */
public class Session {

    private Connection connection;
    private List<Metamodel<Class<?>>> metamodelList;
    private static ConnectionFactory connectionFactory;
    private Session mySession;
    private StatementExecutor statementExecutor;

    public Session(Connection connection){
        if (connection == null)
            throw new ExceptionInInitializerError("Session created with null connection.");

        this.connection = connection;
        statementExecutor = new StatementExecutor(this);
        metamodelList = new LinkedList<>();


    }

    public void addAnnotatedClass(Class c){
        metamodelList.add(Metamodel.of(c));
    }

    public List<Metamodel<Class<?>>> getMetamodelList() {
        return metamodelList;
    }

    public Metamodel<?> getClass(Object obj){
        for (Metamodel<?> metamodel : metamodelList)
            if (!obj.getClass().getName().equals(metamodel.getClassName()))
                return metamodel;

        return null;
    }

    public <T> int add (T obj) throws IllegalAccessException {
        if (obj.getClass() == null){
            throw new NullPointerException(" The object provided does not have a class, or is null.");
        }
        Metamodel<?> metamodel = getClass(obj.getClass());

        statementExecutor.insert(metamodel, obj);
        return 1;
    }

    public <T> void remove (T obj) throws IllegalAccessException {
        if (obj.getClass() == null){
            throw new NullPointerException(" The object provided does not have a class, or is null.");
        }
        Metamodel<?> metamodel = getClass(obj.getClass());

        statementExecutor.remove(metamodel, obj);
    }

    public <T> boolean update (T objOld, T objNew) throws Exception {
        if (objNew == objOld)
            throw new Exception("Objects passed are the same.");

        Metamodel<?> metamodel = getClass(objNew.getClass());

        return statementExecutor.update(objOld, objNew, metamodel);
    }

    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

}
