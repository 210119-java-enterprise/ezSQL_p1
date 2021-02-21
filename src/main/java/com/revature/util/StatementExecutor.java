package com.revature.util;

import com.revature.reflectors.ColumnField;
import com.revature.reflectors.Metamodel;
import com.revature.repos.TableRepository;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StatementExecutor {
    private Validator valid;
    private StatementBuilder sb;
    Session session;
    private TableRepository tableRepository;

    public StatementExecutor(Session session) {
        this.session = session;
    }

    public <T> void Insert(T obj) throws IllegalAccessException {
        String sql = "";
        Metamodel<Class<?>> meta = session.getClass(obj.getClass());
        List<ColumnField> activeFields = meta.getColumns();
        int numValues = 0;
        int temp = 0;

        sb.insert();
        sb.putThat(meta.getEntityName() + "(");
        for (ColumnField cf : activeFields){
            //sb.addInsertable();
            numValues++;
            sb.putThat(cf.getName());
        }
        sb.putThat(") ");
        sb.values();
        for (ColumnField cf : activeFields){
            //sb.addInsertable();
            sb.putThat(cf.getField().get(obj).toString());
        }
        sb.putThat(");");

        tableRepository.save(sb.getQuery());
    }

    public


}
