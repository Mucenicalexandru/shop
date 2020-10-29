package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/removeFromCart"})
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // catch information from front
        int productId = Integer.parseInt(req.getParameter("productId"));
        String minusButton = req.getParameter("MinusButton");

        // Connect to DB
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();
        HashMap<Integer, Integer> quantity = cartDataStore.getQuantity();

        int quantityNumber = quantity.get(productId);

        //Remove product from shopping cart
        if(minusButton!=null){
            if(quantityNumber==1){
                cartDataStore.remove(productDataStore.find(productId));
                quantity.remove(productId);
            }else{
                quantityNumber--;
                quantity.replace(productId,quantityNumber);
            }
        }else {
            cartDataStore.remove(productDataStore.find(productId));
            quantity.remove(productId);
        }

        //Redirect to shopping cart
        resp.sendRedirect("/cart");

    }
}
