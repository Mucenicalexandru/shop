package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.Cart;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CartDaoJdbc implements AbstractDao<Cart> {

    private DataSource dataSource;
    public CartDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.connect();
    }

    @Override
    public void add(Cart cart) {

    }

    @Override
    public Cart find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Cart> getAll() {
        return null;
    }

    @Override
    public List<Cart> getBy(int id) {
        return null;
    }
}
