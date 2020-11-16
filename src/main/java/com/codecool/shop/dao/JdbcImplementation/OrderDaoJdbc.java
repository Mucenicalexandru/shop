package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoJdbc implements AbstractDao<Order> {

    private DataSource dataSource;
    public OrderDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.connect();
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<Order> getBy(int id) {
        return null;
    }
}
