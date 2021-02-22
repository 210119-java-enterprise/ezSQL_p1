package com.revature.reflectors;

import com.revature.annotations.Column;

import java.lang.reflect.Field;

/**
 * ColumnField organizes meta information related to Fields tagged with @Column by the user.
 * This will allow StatementExecutor to parse information by pulling field names, types, annotations, etc.
 */
public class ColumnField {

    private Field field;

    /**
     * Generic Constructor. Will not allow null Columns.
     * @param field The field marked by the Column annotation.
     */
    public ColumnField(Field field){
        if (field.getAnnotation(Column.class) == null){
            throw new IllegalStateException("Column invalid. " + getName() + " is not properly annotated.");
        }
        this.field = field;
        field.setAccessible(true);
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
