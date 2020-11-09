package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface CartDao {
    void add(Product product, int userId);
    void remove(Product product);
    void clearCart();
    List<Product> getAll();
    HashMap<Integer, Integer> getQuantity();
    int getCartId();

}
