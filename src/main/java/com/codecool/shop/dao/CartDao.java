package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface CartDao {

    void addProduct(Integer productId, Integer userId, Integer quantity);
    List<Integer> getProductIdByUserId(int userId);
}
