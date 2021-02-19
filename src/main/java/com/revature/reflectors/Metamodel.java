package com.revature.reflectors;

import com.revature.annotations.Column;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Metamodel<T> {

    private final Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    public static <T> Metamodel of(Class<T> clazz){
        if (clazz.getAnnotation(Table.class) == null){
            throw new IllegalStateException("Cannot create Metamodel object! Provided class, "
                    + clazz.getName() + "is not annotated with @Entity");
        }
        return new Metamodel<T>(clazz);
    }

    private Metamodel(Class<T> clazz){
        this.clazz = clazz;
    }

    public String getClassName() {
        return clazz.getName();
    }

    public String getSimpleClassName(){
        return clazz.getSimpleName();
    }

    public IdField getPrimaryKey(){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null){
                return new IdField(field);
            }
        }
        throw new RuntimeException("Did not find a field annotated with @PrimaryKey in: " + clazz.getName());
    }

    public List<String> getColumnNames(){
        List<String> columnNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            Column column = f.getAnnotation(Column.class);
            if (column != null){
                columnNames.add(f.getName());
            }
        }

        if (columnNames.isEmpty())
            throw new RuntimeException("There were no columns to be found in: " + clazz.getName());

        return columnNames;
    }

    public List<ColumnField> getColumns(){
        List<ColumnField> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            Column column = f.getAnnotation(Column.class);
            PrimaryKey primaryKey = f.getAnnotation(PrimaryKey.class);
            if (column != null){
                columns.add(new ColumnField(f));
            } else if (primaryKey != null){
                columns.add(new ColumnField(f));
            }
        }

        if (columns.isEmpty())
            throw new RuntimeException("There were no columns to be found in: " + clazz.getName());

        return columns;
    }

    public Class<?> getClassOfColumn(String columnName){
        for (ColumnField columnField : this.getColumns()){
            if (columnField.getName().equals(columnName))
                return columnField.getType();
        }
        if (getPrimaryKey().getColumnName().equals(columnName))
            return getPrimaryKey().getType();

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metamodel<?> metamodel = (Metamodel<?>) o;
        return clazz.equals(metamodel.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz);
    }
}
