package com.revature.util;

import com.revature.reflectors.Metamodel;

public class StatementBuilder {

    private String query = "";
    private final String select = "SELECT ";
    private final String from = "FROM ";
    private final String update = "UPDATE ";
    private final String delete = "DELETE ";
    private final String insert = "INSERT INTO ";
    private final String where = "WHERE ";
    private final String values = "VALUES ";

    public StatementBuilder(Metamodel<?> metamodel, Object o){}

    public String getQuery(){
        return query;
    }

    public void putThat(String that) { query += that; }

    public void selectAll(){
        query = select + " * " + from + " (?) ";
    }

    public void addConditional(String condition){
        query += condition;
    }

    public void addInsertable() { query += " (?) "; }

    public void select() { query = select; }

    public void from(){ query += from; }

    public void update(){ query += update; }

    public void delete(){ query += delete; }

    public void insert(){ query += insert; }

    public void where(){ query += where; }

    public void values(){ query += values; }

}
