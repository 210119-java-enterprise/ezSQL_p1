package com.revature.util;

import com.revature.reflectors.Metamodel;

public class StatementBuilder {

    private String query = "";
    private final String select = "SELECT ";
    private final String from = "FROM ";
    private final String update = "UPDATE ";
    private final String delete = "DELETE ";
    private final String insert = "INSERT ";
    private final String where = "WHERE ";
    private Restrictions restriction;

    public StatementBuilder(Metamodel<?> metamodel, Object o){}

    public String getQuery(){
        return query;
    }

    public void selectAll(){
        query = select + " * " + from + " (?) ";
    }

    public void selectConditional(String condition){
        query = select + condition + from + " (?) " + where + condition;
    }


}
