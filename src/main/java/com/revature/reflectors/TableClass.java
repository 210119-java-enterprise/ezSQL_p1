package com.revature.reflectors;

import com.revature.annotations.Table;

public class TableClass {
    private Class clazz;

    public TableClass(Class clazz){
        if (clazz.getAnnotation(Table.class) == null){
            throw new IllegalStateException("Table invalid. " + getName() + " is not properly annotated.");
        }
        this.clazz = clazz;
    }

    public String getName() {
        return clazz.getName();
    }

    public Class<?> getType() {
        return clazz.getComponentType();
    }

    public String getTableName() {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return table.tableName();
    }

}
