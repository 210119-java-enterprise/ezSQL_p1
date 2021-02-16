package com.revature.repos;

import java.util.List;

public interface CrudRepository<T> {

    void save(T newObj);
    List<T> findAll();
    T findById(int id);
    boolean update(T updateObj);
    boolean deleteById(int id);

}
