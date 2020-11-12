package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;
    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_supplier (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_supplier WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return null;
            }

            Supplier supplier = new Supplier(rs.getString(1), rs.getString(2));
            return supplier;

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product_supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while removing a product from cart. " + throwable, throwable);
        }
    }

    @Override
    public List<Supplier> getAll() {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM product_supplier";
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Supplier supplier = new Supplier(rs.getString("name"), rs.getString("description"));
                suppliers.add(supplier);
            }
            return suppliers;

        } catch (SQLException throwable){
            throw new RuntimeException("Error while trying to get all items in cart." + throwable, throwable);
        }
    }
}
