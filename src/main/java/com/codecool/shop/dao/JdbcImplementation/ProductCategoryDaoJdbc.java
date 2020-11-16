package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJdbc implements AbstractDao<ProductCategory> {

    private DataSource dataSource;
    public ProductCategoryDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.getInstance().connect();
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory category = null;
        String sql = "SELECT * FROM product_category WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String department = resultSet.getString("department");

                category = new ProductCategory(name,
                        description,
                        department
                );
                category.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public List<ProductCategory> getBy(int id) {
        return null;
    }
}
