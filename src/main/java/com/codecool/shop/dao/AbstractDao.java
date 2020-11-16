package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<T> {

    //CRUD operations

    void add(T t) throws SQLException;
    T find(int id);
    void remove(int id);
    List<T> getAll();
    List<T> getBy(int id);

}
