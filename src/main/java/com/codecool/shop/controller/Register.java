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

@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String town = request.getParameter("town");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        // connect to DB
        try {
            UserDaoJdbc usersDaoDB = new UserDaoJdbc(Connector.connect());
            usersDaoDB.add(new User(firstName,lastName,country,address,Integer.parseInt(postcode), town, Integer.parseInt(phone),email,password));
            request.getSession().setAttribute("user", firstName);
            request.getSession().setAttribute("userID",usersDaoDB.findByEmail(email).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/index");

    }
}
