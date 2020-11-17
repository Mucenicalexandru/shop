package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
//import com.codecool.shop.dao.memoryImplementation.CartDaoMem;
//import com.codecool.shop.dao.memoryImplementation.OrderDaoMem;
import com.codecool.shop.dao.JdbcImplementation.OrderDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> countries = new ArrayList<>();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String finalPrice = String.valueOf(req.getSession().getAttribute("totalOrderAmount"));
        int userId = (int) req.getSession().getAttribute("userId");

        String[] locales = Locale.getISOCountries();
        for(String country : locales){
            Locale obj = new Locale("", country);
            countries.add(obj.getDisplayCountry());
        }

        context.setVariable("finalPrice", finalPrice);
        context.setVariable("countries", countries);

        try {
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            User user = userDaoJdbc.find(userId);
            context.setVariable("user", user);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        engine.process("cart/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        Order order = null;
        String finalPrice = String.valueOf(req.getSession().getAttribute("totalOrderAmount"));

        try {
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            user = userDaoJdbc.find((int)req.getSession().getAttribute("userId"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        try {
            OrderDaoJdbc orderDaoJdbc = new OrderDaoJdbc();

            UUID uuid = UUID.randomUUID();
            order = new Order(uuid,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCountry(),
                    user.getAddress(),
                    user.getPostcode(),
                    user.getTown(),
                    user.getPhone(),
                    user.getEmail(),
                    cart.getProductsInCart(),
                    finalPrice);

            orderDaoJdbc.add(order);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        resp.sendRedirect("/payment");
    }
}
