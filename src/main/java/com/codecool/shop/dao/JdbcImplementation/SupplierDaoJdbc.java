package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc implements AbstractDao<Supplier> {

    private DataSource dataSource;
    public SupplierDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.getInstance().connect();
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        String sql = "SELECT * FROM product_supplier WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                supplier = new Supplier(name,
                        description
                );
                supplier.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    @Override
    public List<Supplier> getBy(int id) {
        return null;
    }
}
