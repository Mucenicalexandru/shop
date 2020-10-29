package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartDaoMem implements CartDao {

    private List<Product> shoppingCart = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;
    HashMap<Integer,Integer> quantity = new HashMap<>();

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        shoppingCart.add(product);
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(Product product) {
        shoppingCart.remove(product);
    }

    @Override
    public HashMap<Integer, Integer> getQuantity() {
        return quantity;
    }

    @Override
    public List<Product> getAll() {
        return shoppingCart;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
