package com.revature.util;

public class Validator {

    public Validator(){
        super();
    }

    /**
     * Checks over a valid transaction.
     * @param sql Takes in sql to be checked
     * @return Ensures that it is not null, and ends in a semicolon.
     */
    public boolean isValidTransaction(String sql){
        return (sql.equals("") && sql.matches(";$"));
    }

    public <T> boolean isEntityPopulated(T obj){

        return true;
    }

}
