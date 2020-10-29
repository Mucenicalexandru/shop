package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@WebServlet(urlPatterns = {"/order"})
public class OrderConfirmation extends HttpServlet {

    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String finalPrice = String.valueOf(req.getSession().getAttribute("finalPrice"));
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        cartDataStore.clearCart();

        context.setVariable("lastOrder", orderDataStore.getLast());
        context.setVariable("date", formatter.format(date));
        context.setVariable("finalPrice", finalPrice);


        engine.process("cart/order.html", context, resp.getWriter());

        //TODO when we press the order number, all the products from that order to appear
    }

}
