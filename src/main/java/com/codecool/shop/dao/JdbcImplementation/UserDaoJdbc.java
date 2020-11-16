package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements AbstractDao<User> {

    private DataSource dataSource;
    public UserDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.connect();
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getBy(int id) {
        return null;
    }
}
