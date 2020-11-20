package com.codecool.shop.controller;

import com.codecool.shop.dao.JdbcImplementation.*;
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
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = null;

        //user info
        String email = request.getParameter("email");

        try {
            UserDaoJdbc usersDaoDB = new UserDaoJdbc();
            CartDaoJdbc cartDaoJdbc = new CartDaoJdbc();
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);
            User user = usersDaoDB.findByEmail(email);
            List<Integer> productIdList = cartDaoJdbc.getProductIdByUserId(user.getId());


            int totalPrice = 0;
            if (BCrypt.checkpw(request.getParameter("password"), user.getPassword())) {
                if (productIdList.size() > 0) {
                    cart = new Cart();
                    for (Integer productId : productIdList) {
                        cart.addProduct(productDaoJdbc.find(productId));
                        cart.getDict().put(productId, cartDaoJdbc.getQuantityByProductId(productId));
                        totalPrice = (int) (totalPrice + productDaoJdbc.find(productId).getDefaultPrice() * cart.getDict().get(productId));
                        session.setAttribute("itemsInCart", cart.getProductsInCart().size());
                        session.setAttribute("cart", cart);
                        session.setAttribute("totalOrderAmount", totalPrice);
                    }
                } else {
                    cart = new Cart();
                    cart.setId(user.getId());
                    session.setAttribute("cart", cart);
                    session.setAttribute("itemsInCart", 0);
                    session.setAttribute("totalOrderAmount", 0);
                    session.setAttribute("itemsInCart", 0);
                }

                session.setAttribute("user", user.getFirstName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("itemsNumber", cart.getProductsInCart().size());
                response.sendRedirect("/index");
            } else {

                System.out.println("the password dose not match");
                response.sendRedirect("/index");

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

}
