package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/order"})
public class OrderConfirmation extends HttpServlet {

    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        context.setVariable("cart", cart);
        HttpSession session = req.getSession();

        try {
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            User user = userDaoJdbc.find((int)req.getSession().getAttribute("userId"));
            context.setVariable("user", user);
            context.setVariable("date", date);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        cart.removeAllProducts();
        session.setAttribute("totalOrderAmount", 0);



        engine.process("cart/order.html", context, resp.getWriter());


    }

}
