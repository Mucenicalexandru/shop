package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    private ProductDao productDao;
    private CartDao cartDao;
    private OrderDao orderDao;
    private SupplierDao supplierDao;
    private ProductCategoryDao productCategoryDao;

    public DataSource setup() throws SQLException {
        DataSource dataSource = connect();
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        return dataSource;
    }

    public void testSetup() throws SQLException {
        DataSource dataSource = testConnect();
        productDao = new ProductDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "codecool_shop";
        String user = "alex";
        String password = "1234";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public void addProduct(Product product, Supplier supplier, ProductCategory category) throws SQLException {
        supplierDao.add(supplier);
        productCategoryDao.add(category);
        productDao.add(product);
    }

    public Product findProductById(int id){
        return productDao.find(id);
    }


    private DataSource testConnect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "codecoolshop_test";
        String user = "alex";
        String password = "1234";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

}
