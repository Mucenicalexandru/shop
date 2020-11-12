package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private DataSource dataSource;
    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_category (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_category WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return null;
            }

            ProductCategory category = new ProductCategory(rs.getString(1), rs.getString(3), rs.getString(2));

            return category;

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product_category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while removing a product from cart. " + throwable, throwable);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        ArrayList<ProductCategory> categories = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM product_category";
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ProductCategory category = new ProductCategory(rs.getString("name"), rs.getString("description"), rs.getString("department"));
                categories.add(category);
            }
            return categories;

        } catch (SQLException throwable){
            throw new RuntimeException("Error while trying to get all items in cart." + throwable, throwable);
        }
    }
}
