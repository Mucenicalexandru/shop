package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
//import com.codecool.shop.dao.memoryImplementation.CartDaoMem;
//import com.codecool.shop.dao.memoryImplementation.OrderDaoMem;
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

@WebServlet(urlPatterns = {"/order"})
public class OrderConfirmation extends HttpServlet {

    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String finalPrice = String.valueOf(req.getSession().getAttribute("finalPrice"));
//        OrderDao orderDataStore = OrderDaoMem.getInstance();
//        CartDao cart = CartDaoMem.getInstance();
//
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        cart.clearCart();
//
//        context.setVariable("lastOrder", orderDataStore.getLast());
//        context.setVariable("date", formatter.format(date));
//        context.setVariable("finalPrice", finalPrice);


        engine.process("cart/order.html", context, resp.getWriter());


    }

}
