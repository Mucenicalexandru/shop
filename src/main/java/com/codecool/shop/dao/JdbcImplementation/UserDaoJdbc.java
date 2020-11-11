package com.codecool.shop.dao.JdbcImplementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(User user) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO registered_users(first_name, last_name, country, address, postcode, town, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getCountry());
            st.setString(4, user.getAddress());
            st.setInt(5, user.getPostcode());
            st.setString(6, user.getTown());
            st.setInt(7, user.getPhone());
            st.setString(8, user.getEmail());
            st.setString(9, user.getPassword());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new User. " + throwable, throwable);
        }

    }

    @Override
    public User find(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM registered_users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                return null;
            }
            User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("country"),rs.getString("address"),rs.getInt("postcode"), rs.getString("town"), rs.getInt("phone"),rs.getString("email"),rs.getString("password"));
            user.setId(rs.getInt(1));
            return user;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }
    }

    @Override
    public User findByEmail(String email) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM registered_users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                return null;
            }
            User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("country"),rs.getString("address"),rs.getInt("postcode"), rs.getString("town"), rs.getInt("phone"),rs.getString("email"),rs.getString("password"));
            user.setId(rs.getInt(1));
            return user;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM registered_users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to remove an user. " + throwable, throwable);
        }
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> allUsersRegistered = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT * FROM registered_users";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("country"),rs.getString("address"),rs.getInt("postcode"),rs.getString("town"), rs.getInt("phone"),rs.getString("email"),rs.getString("password"));
                user.setId(rs.getInt(1));
                allUsersRegistered.add(user);
            }
            return allUsersRegistered;

        } catch (SQLException throwable){
            throw new RuntimeException("Error while trying to get all users. " + throwable, throwable);
        }
    }

}
