package com.codecool.shop.dao.memoryImplementation;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {

    //TODO cartId will be equal to userId
    private int cartId;
    private static List<Product> cart = new ArrayList<>();
    private static CartDaoMem instance = null;
    private HashMap<Integer,Integer> quantity = new HashMap<>();

    public static CartDaoMem getInstance(){
        if(instance == null){
            instance = new CartDaoMem();
        }
        return instance;
    }

    /**
     * As an user, when we add the first product in the cart, we will set the cartId equal to the userId
     * @param product
     * @param userId
     */
    @Override
    public void add(Product product, int userId){
        if(cart.size() == 0){
            cart.add(product);
            this.cartId = userId;
        }else{
            cart.add(product);
        }

    }

    @Override
    public void update(Product product,int userId, int quantity, int totalPrice) {

    }


    @Override
    public void remove(Product product, int UserId){
        cart.remove(product);
    }

    @Override
    public void clearCart(){
        cart = new ArrayList<>();
        quantity = new HashMap<>();
    }

    @Override
    public List<Product> getAll(){
        return cart;
    }

    @Override
    public List<Integer> getAll(int userId) {
        return null;
    }

    @Override
    public HashMap<Integer, Integer> getQuantity() {
        return quantity;
    }

    @Override
    public int getCartId() {
        return cartId;
    }

    @Override
    public int getQuantity(Product product, int userId) {
        return 0;
    }


}
