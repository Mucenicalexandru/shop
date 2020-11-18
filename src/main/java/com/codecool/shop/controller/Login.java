package com.codecool.shop.controller;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
        HttpSession session = request.getSession();

        //user info
        String email = request.getParameter("email");



        try {
            UserDaoJdbc usersDaoDB = new UserDaoJdbc();
            User user = usersDaoDB.findByEmail(email);

            if(BCrypt.checkpw(request.getParameter("password"), user.getPassword()) ){
                Cart cart = new Cart();
                cart.setId(user.getId());

                session.setAttribute("user", user.getFirstName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("cart", cart);
                session.setAttribute("itemsNumber", cart.getProductsInCart().size());
                session.setAttribute("totalOrderAmount", 0);

                response.sendRedirect("/index");
            } else {
                System.out.println("the password dose not match");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
