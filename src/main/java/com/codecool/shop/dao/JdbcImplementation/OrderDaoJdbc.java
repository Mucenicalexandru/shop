package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class OrderDaoJdbc implements AbstractDao<Order> {

    private DataSource dataSource;
    public OrderDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.connect();
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO orders(date, user_id, product_id, quantity) VALUES (?, ?, ?, ?)";

        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, order.getDate());
            statement.setInt(2, order.getUserId());
            statement.setInt(3, order.getProductId());
            statement.setInt(4, 1);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
//            order.setId(resultSet.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
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
