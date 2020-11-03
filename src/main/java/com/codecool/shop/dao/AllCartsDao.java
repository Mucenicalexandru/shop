package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.CartDaoMem;

import java.util.List;

public interface AllCartsDao {
    void add(CartDaoMem cart);
    void remove(int cartId);
    List<CartDaoMem> getAll();
}
