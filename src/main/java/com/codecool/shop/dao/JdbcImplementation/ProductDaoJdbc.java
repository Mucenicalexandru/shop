package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private DataSource dataSource;
    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO products (name, description, default_price, default_currency, category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().getSymbol());
            statement.setInt(5, product.getProductCategory().getId());
            statement.setInt(6, product.getSupplier().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products\n" +
                    "INNER JOIN product_category ON products.category_id = product_category.id\n" +
                    "INNER JOIN product_supplier ON products.supplier_id = product_supplier.id\n" +
                    "WHERE products.id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return null;
            }

            Supplier supplier = new Supplier(rs.getString(13), rs.getString(14));
            ProductCategory category = new ProductCategory(rs.getString(8), rs.getString(9), rs.getString(9));
            Product product = new Product(rs.getString(2), rs.getFloat(4), rs.getString(5), rs.getString(3), category, supplier);


            return product;

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

}
