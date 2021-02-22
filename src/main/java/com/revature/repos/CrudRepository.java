package com.revature.repos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Repository for use with CRUD methods. Coupled slightly
 * with TableRepository, where methods are implemented for
 * generic use.
 */
public interface CrudRepository<T> {

    void save(String sql);
    ResultSet findAll(String sql);
    ResultSet findById(String sql);
    boolean update(String sql);
    boolean deleteById(String sql);

}
