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

    public StatementExecutor() {
        super();
    }

    public <T> void insert(Metamodel<?> meta, T obj) throws IllegalAccessException {
        String sql = "";
        List<ColumnField> activeFields = meta.getColumns();
        sb = new StatementBuilder(meta, obj);
        tableRepository = new TableRepository();
        int numValues = 0;
        int temp = 0;

        sb.insert();
        sb.putThat(meta.getEntityName() + " (");
        for (ColumnField cf : activeFields){
            //sb.addInsertable();
            numValues++;
            sb.putThat(cf.getName());
        }
        sb.putThat(") ");
        sb.values();
        sb.putThat("('");
        for (ColumnField cf : activeFields){
            //sb.addInsertable();
            sb.putThat(cf.getField().get(obj).toString());
        }
        sb.putThat("');");

        tableRepository.save(sb.getQuery());
    }

    public <T> void remove(Metamodel<?> meta, T obj) throws IllegalAccessException {
        String sql = "";
        List<ColumnField> activeFields = meta.getColumns();
        sb = new StatementBuilder(meta, obj);
        tableRepository = new TableRepository();
        int numValues = 0;
        int temp = 0;

        sb.delete();
        sb.from();
        sb.putThat(meta.getEntityName() + " ");
        sb.where();
        for (ColumnField cf : activeFields){
            sb.putThat(cf.getName());
            sb.putThat("= '" + cf.getField().get(obj).toString() + "'");
            break;
        }
        sb.putThat(";");

        tableRepository.save(sb.getQuery());
    }

    public <T> boolean update(T objOld, T objNew, Metamodel<?> meta) throws IllegalAccessException {
        String sql = "";
        List<ColumnField> activeFields = meta.getColumns();
        sb = new StatementBuilder(meta, objNew);
        tableRepository = new TableRepository();
        int numValues = 0;
        int temp = 0;

        sb.update();
        sb.putThat(meta.getEntityName() + " ");
        sb.set();
        for (ColumnField cf : activeFields){
            sb.putThat(cf.getName());
            sb.putThat(" = '" + cf.getField().get(objNew).toString() + "'");
        }
        sb.putThat(" ");
        sb.where();
        for (ColumnField cf : activeFields){
            sb.putThat(cf.getName());
            sb.putThat(" = '" + cf.getField().get(objOld).toString() + "'");
            break;
        }
        sb.putThat(";");

        return tableRepository.update(sb.getQuery());
    }
}
