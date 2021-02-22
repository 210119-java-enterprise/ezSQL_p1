package com.revature.reflectors;

import com.revature.annotations.PrimaryKey;

import java.lang.reflect.Field;

/**
 * IdField organizes meta information related to Fields tagged with @PrimaryKey by the user.
 * This will allow StatementExecutor to parse information by pulling field names, types, annotations, etc.
 * TODO: Implement alongside Primary Keys.
 */
public class IdField {

    private Field field;

    public IdField(Field field){
        if (field.getAnnotation(PrimaryKey.class) == null){
            throw new IllegalStateException("Cannot create IdField object! Provided field, "
                                            + getName() + "is not annotated with @PrimaryKey");
        }
        this.field = field;
    }

    public String getName(){
        return field.getName();
    }

    public Class<?> getType(){
        return field.getType();
    }

    public String getColumnName(){
        return field.getAnnotation(PrimaryKey.class).columnName();
    }
}
