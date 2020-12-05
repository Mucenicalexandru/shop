package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.CartDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.OrderDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
        HttpSession session = req.getSession();
        Order order;
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        try {
            //connect to DB
            OrderDaoJdbc orderDaoJdbc = new OrderDaoJdbc();
            CartDaoJdbc cartDaoJdbc = new CartDaoJdbc();

            int userId = (int) req.getSession().getAttribute("userId");


            //when checkout we add the order to DB
            for(Integer productId : cart.getProductsInCart()){
                order = new Order(userId, productId, cart.getDict().get(productId));
                orderDaoJdbc.add(order);
            }

            //if cart from DB contains saved items and user pays them, then remove them
            if(cartDaoJdbc.getProductIdByUserId(userId).size() > 0){
                cartDaoJdbc.removeProductsByUserId(userId);
            }

            session.setAttribute("itemsInCart", 0);


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        resp.sendRedirect("/payment");
    }
}
