package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao {

    void addProduct(Product product, User user);
    List<Integer> getProductIdByUserId(int userId);
}
