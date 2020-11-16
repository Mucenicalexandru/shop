package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.AbstractDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements AbstractDao<User>, UserDao {

    private DataSource dataSource;
    public UserDaoJdbc() throws IOException, SQLException {
        this.dataSource = Connector.connect();
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO registered_users(first_name, last_name, country, address, postcode, town, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection()) {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getCountry());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPostcode());
            statement.setString(6, user.getTown());
            statement.setString(7, user.getPhone());
            statement.setString(8, user.getEmail());
            statement.setString(9, user.getPassword());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getBy(int id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM registered_users WHERE email = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("address"),
                        resultSet.getString("postcode"),
                        resultSet.getString("town"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
