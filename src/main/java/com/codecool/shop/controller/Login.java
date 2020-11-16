package com.codecool.shop.controller;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //user info
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        try {
            UserDaoJdbc usersDaoDB = new UserDaoJdbc();
            User user = usersDaoDB.findByEmail(email);

            if(user.getPassword().equals(password)){
                session.setAttribute("user", user.getFirstName());
                session.setAttribute("userId", user.getId());
                Cart cart = new Cart();
                cart.setId(user.getId());
                session.setAttribute("cart", cart);
                session.setAttribute("itemsNumber", cart.getProductsInCart().size());
                response.sendRedirect("/index");
            } else {
                System.out.println("the password dose not match");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
