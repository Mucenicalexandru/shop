package com.codecool.shop.controller;


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

@WebServlet(urlPatterns = {"/register"})
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //get user info
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String town = request.getParameter("town");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        //The default log_rounds is 10, and the valid range is 4 to 31.
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(10));

        //build the User
        User user = new User(firstName, lastName, country, address, postcode, town, phone, email, password);


        try {
            //connect to DB
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            if(userDaoJdbc.findByEmail(email) != null){
                boolean emailAlreadyRegistered = true;
            }else{
                userDaoJdbc.add(user);
                int userId = userDaoJdbc.findByEmail(email).getId();
                session.setAttribute("user", firstName);
                session.setAttribute("userId", userId);
                Cart cart = new Cart();
                cart.setId(user.getId());
                session.setAttribute("cart", cart);
                session.setAttribute("itemsNumber", cart.getProductsInCart().size());
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        response.sendRedirect("/index");

    }
}
