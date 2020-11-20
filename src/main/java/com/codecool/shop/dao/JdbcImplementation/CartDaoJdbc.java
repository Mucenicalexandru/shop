package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc implements AbstractDao<Cart>, CartDao {

    private DataSource dataSource;
    public CartDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.getInstance().connect();
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

    @Override
    public void addProduct(Integer userId, Integer productId, Integer quantity) {
        String sql = "INSERT INTO cart(user_id, product_id, quantity) VALUES (?, ?, ?)";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);


            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> getProductIdByUserId(int userId) {
        List<Integer> productIdList = new ArrayList<>();
        int productId = -1;
        String sql = "SELECT * FROM cart WHERE user_id = ?";

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                productId = resultSet.getInt("product_id");
                productIdList.add(productId);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return productIdList;
    }

    @Override
    public void removeProductsByUserId(int userId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to remove an user. " + throwable, throwable);
        }
    }

    @Override
    public int getQuantityByProductId(int productId) {
        int quantity = -1;
        String sql = "SELECT quantity FROM cart WHERE product_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                quantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }
}
