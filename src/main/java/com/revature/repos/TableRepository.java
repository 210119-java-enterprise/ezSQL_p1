package com.revature.repos;

import java.util.List;

public class TableRepository<T> implements CrudRepository {

    private final String base = "Select (?) " +
                                "FROM (?) ";

    private String sql;

    @Override
    public void save(Object newObj) {

    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public boolean update(Object updateObj) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
