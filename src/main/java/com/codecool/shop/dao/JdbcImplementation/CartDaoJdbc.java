package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.Cart;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CartDaoJdbc implements AbstractDao<Cart> {

    private DataSource dataSource;
    public CartDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.getInstance().connect();
    }

    @Override
    public void add(Cart cart) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?,?,?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, cart.getId());
            statement.setInt(2, cart.getProductId());
            statement.setInt(3, cart.getQuantity());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

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
