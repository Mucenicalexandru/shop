package com.codecool.shop.controller;

import com.codecool.shop.config.Connector;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //user info
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDaoJdbc usersDaoDB = new UserDaoJdbc();
            User user = usersDaoDB.findByEmail(email);

            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("user", user.getFirstName());
                request.getSession().setAttribute("userId", user.getId());
                response.sendRedirect("/index");
            } else {
                System.out.println("the password dose not match");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
