package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product, int userId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (product_id, quantity, total_price, user_id) VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, product.getId());
            st.setInt(2, 1);
            st.setFloat(3,product.getDefaultPrice());
            st.setInt(4, userId);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding a product to the cart. " + throwable, throwable);
        }

    }

    @Override
    public void update(Product product,int userId, int quantity, int totalPrice) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET quantity = ?, total_price = ? WHERE user_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, quantity);
            st.setInt(2, totalPrice);
            st.setInt(3, userId);
            st.setInt(4,product.getId());
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating a product in the cart. " + throwable, throwable);
        }
    }

    @Override
    public void remove(Product product, int userId) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE product_id = ? AND user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,product.getId());
            st.setInt(2,userId);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while removing a product from cart. " + throwable, throwable);
        }

    }

    @Override
    public void clearCart() {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while clearing the cart. " + throwable, throwable);
        }

    }

    @Override
    public List<Product> getAll() {
        return null;
    }


    @Override
    public List<Integer> getAll(int userId) {
        ArrayList<Integer> allItemsInCart = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM cart WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1,userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                allItemsInCart.add(rs.getInt(2));
            }
            return allItemsInCart;

        } catch (SQLException throwable){
            throw new RuntimeException("Error while trying to get all items in cart." + throwable, throwable);
        }
    }

    @Override
    public HashMap<Integer, Integer> getQuantity() {
        return null;
    }

    @Override
    public int getCartId() {
        return 0;
    }

    @Override
    public int getQuantity(Product product, int userId){
        int result = 0;
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT quantity FROM cart WHERE product_id = ? AND user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, product.getId());
            st.setInt(2, userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }
            return result;

        } catch (SQLException throwable){
            throw new RuntimeException("Error while trying to get all items in cart." + throwable, throwable);
        }
    }
}
