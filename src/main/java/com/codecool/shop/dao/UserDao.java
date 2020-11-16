package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    User findByEmail(String email);
}
