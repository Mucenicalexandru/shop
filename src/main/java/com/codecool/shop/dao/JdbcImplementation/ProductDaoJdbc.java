package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements AbstractDao<Product>, ProductDao<Product> {

    private DataSource dataSource;
    private AbstractDao supplierDaoJdbc;
    private AbstractDao categoryDaoJdbc;

    public ProductDaoJdbc(SupplierDaoJdbc supplierDaoJdbc, ProductCategoryDaoJdbc productCategoryDaoJdbc) throws IOException, SQLException {
        this.dataSource = Connector.getInstance().connect();
        this.supplierDaoJdbc = supplierDaoJdbc;
        this.categoryDaoJdbc = productCategoryDaoJdbc;

    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    float defaultPrice = resultSet.getFloat("default_price");
                    String defaultCurrency = resultSet.getString("default_currency");
                    int categoryId = resultSet.getInt("category_id");
                    int supplierId = resultSet.getInt("supplier_id");
                    Supplier supplier = (Supplier) supplierDaoJdbc.find(supplierId);
                    ProductCategory category = (ProductCategory) categoryDaoJdbc.find(categoryId);

                    product = new Product(name,
                            defaultPrice,
                            defaultCurrency,
                            description,
                            category,
                            supplier
                    );
                    product.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return product;
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM products";

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String defaultCurrency = resultSet.getString("default_currency");
                int categoryId = resultSet.getInt("category_id");
                int supplierId = resultSet.getInt("supplier_id");

                Supplier supplier = (Supplier) supplierDaoJdbc.find(supplierId);
                ProductCategory category = (ProductCategory) categoryDaoJdbc.find(categoryId);

                product = new Product(name,
                        defaultPrice,
                        defaultCurrency,
                        description,
                        category,
                        supplier);

                product.setId(resultSet.getInt(1));
                productList.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getBy(int id) {
        List<Product> productList = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM products";

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String defaultCurrency = resultSet.getString("default_currency");
                int categoryId = resultSet.getInt("category_id");
                int supplierId = resultSet.getInt("supplier_id");

                Supplier supplier = (Supplier) supplierDaoJdbc.find(supplierId);
                ProductCategory category = (ProductCategory) categoryDaoJdbc.find(categoryId);

                product = new Product(name,
                        defaultPrice,
                        defaultCurrency,
                        description,
                        category,
                        supplier);

                product.setId(resultSet.getInt(1));
                productList.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getBySupplier(int supplierId) {
        List<Product> productList = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM products WHERE supplier_id = ?";

        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, supplierId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String defaultCurrency = resultSet.getString("default_currency");
                int categoryId = resultSet.getInt("category_id");
                Supplier supplier = (Supplier) supplierDaoJdbc.find(supplierId);
                ProductCategory category = (ProductCategory) categoryDaoJdbc.find(categoryId);

                product = new Product(name,
                        defaultPrice,
                        defaultCurrency,
                        description,
                        category,
                        supplier);

                product.setId(resultSet.getInt(1));
                productList.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Product> getByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String defaultCurrency = resultSet.getString("default_currency");
                int supplierId = resultSet.getInt("supplier_id");
                Supplier supplier = (Supplier) supplierDaoJdbc.find(supplierId);
                ProductCategory category = (ProductCategory) categoryDaoJdbc.find(categoryId);

                product = new Product(name,
                        defaultPrice,
                        defaultCurrency,
                        description,
                        category,
                        supplier);

                product.setId(resultSet.getInt(1));
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}