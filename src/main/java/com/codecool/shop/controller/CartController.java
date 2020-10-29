package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();
        HashMap <Integer, Integer> quantityRegister = cartDataStore.getQuantity();

        AtomicReference<Float> totalPriceToSend = new AtomicReference<Float>((float) 0);

        cartDataStore.getAll().forEach(product -> {
            totalPriceToSend.updateAndGet(v -> v + (product.getDefaultPrice() *  (int)quantityRegister.get(product.getId())));
        });

        context.setVariable("cart", cartDataStore.getAll());
        context.setVariable("quantity" ,cartDataStore.getQuantity());

        context.setVariable("totalOrderAmount", totalPriceToSend);
        req.getSession().setAttribute("finalPrice", totalPriceToSend);

        engine.process("cart/cart.html", context, resp.getWriter());
    }



}
