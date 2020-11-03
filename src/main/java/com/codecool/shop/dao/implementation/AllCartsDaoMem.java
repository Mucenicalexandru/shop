package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AllCartsDao;

import java.util.ArrayList;
import java.util.List;

public class AllCartsDaoMem implements AllCartsDao {


    private static List<CartDaoMem> allCarts = new ArrayList<>();
    private static AllCartsDaoMem instance = null;


    public static AllCartsDaoMem getInstance(){
        if(instance == null){
            instance = new AllCartsDaoMem();

        }
        return instance;
    }


    @Override
    public void add(CartDaoMem cart) {
        allCarts.add(cart);
    }

    /**
     * When the user pays his cart, from the list of carts we remove his cart (by userId which == cartId)
     * @param cartId
     */
    @Override
    public void remove(int cartId) {
        allCarts.forEach(cartDaoMem ->{
            if(cartDaoMem.getCartId() == cartId){
                allCarts.remove(cartDaoMem);
            }
        });
    }

    @Override
    public List<CartDaoMem> getAll() {
        return allCarts;
    }


}
