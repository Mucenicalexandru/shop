package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String buttonPressed= req.getParameter("button");

        // connect to DB
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        List <Product> shoppingCartWithDuplicates = cartDataStore.getAll();
        HashMap<Integer, Integer> quantity = cartDataStore.getQuantity();

        if (!shoppingCartWithDuplicates.contains(productDataStore.find(productId))) {
            cartDataStore.add(productDataStore.find(productId));
            quantity.put(productId, 1);
            System.out.println("if " + quantity);
        } else {
            int a = quantity.get(productId) + 1;
            quantity.replace(productId, a);

        }

        if (buttonPressed != null) {
            resp.sendRedirect("/cart");
        } else {
            resp.sendRedirect("/index");
        }
    }
}
