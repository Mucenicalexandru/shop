package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public interface CartDao {
    void add(Product product, int userId);
    void update(Product product,int userId, int quantity, int totalPrice);
    void remove(Product product, int userId);
    void clearCart();
    List<Product> getAll();
    List<Integer> getAll(int userId);
    HashMap<Integer, Integer> getQuantity();
    int getCartId();
    int getQuantity(Product product, int userId);

}
