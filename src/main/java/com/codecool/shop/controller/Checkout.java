package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("cart/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String country = req.getParameter("country");
        String address = req.getParameter("address");
        String postcode = req.getParameter("postcode");
        String town = req.getParameter("town");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        List<Product> orderedProducts = cartDataStore.getAll();
        String totalAmount = "500 USD";

        Order order = new Order(orderDataStore.getAll().size(), firstName, lastName, country, address, postcode, town, phone, email, orderedProducts, totalAmount);
        orderDataStore.add(order);

        resp.sendRedirect("/payment");

//        engine.process("cart/order.html", context, resp.getWriter());

    }
}
