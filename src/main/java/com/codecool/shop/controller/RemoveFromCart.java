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

@WebServlet(urlPatterns = {"/removeItemFromCart"})
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // catch information from front
        String productId = req.getParameter("productId");

        // Connect to DB
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        //Remove product from shopping cart
        cartDataStore.remove(productDataStore.find(Integer.parseInt(productId)));
        cartDataStore.getQuantity().remove(Integer.parseInt(productId));

        //Redirect to shopping cart
        resp.sendRedirect("/cart");

    }
}
