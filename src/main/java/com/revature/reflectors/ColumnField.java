package com.revature.reflectors;

import com.revature.annotations.Column;
import com.revature.annotations.Table;

import java.lang.reflect.Field;

public class ColumnField {

    private Field field;

    public ColumnField(Field field){
        if (field.getAnnotation(Table.class) == null){
            throw new IllegalStateException("Table invalid. " + getName() + " is not properly annotated.");
        }
        this.field = field;
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getType() {
        return field.getType();
    }

    public String getColumnName() {
        return field.getAnnotation(Column.class).columnName();
    }

    public Field getField() {
        return field;
    }
}
