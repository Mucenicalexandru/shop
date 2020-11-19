package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.*;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        int totalPrice = (int) req.getSession().getAttribute("totalOrderAmount");

        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);
            List<Product> productList = new ArrayList<>();

            for(Integer productId : cart.getProductsInCart()){
                productList.add(productDaoJdbc.find(productId));
            }
            context.setVariable("cart", productList);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        context.setVariable("totalOrderAmount", totalPrice);
        context.setVariable("quantity", cart.getDict());


        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int totalPrice = (int) req.getSession().getAttribute("totalOrderAmount");
        HttpSession session = req.getSession();
        int productId = 0;
        int userId = (int) req.getSession().getAttribute("userId");
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

            switch (buttonPressed) {
                case "+":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    cart.addProduct(productDaoJdbc.find(productId));
                    session.setAttribute("cart", cart);
                    totalPrice = (int) (totalPrice + productDaoJdbc.find(productId).getDefaultPrice());
                    session.setAttribute("totalOrderAmount", totalPrice);
                    resp.sendRedirect("/cart");
                    break;
                case "-":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    if (cart.getQuantity(productId) == 1) {
                        cart.removeProduct(productId);
                        cart.getDict().remove(productId);
                    } else {
                        cart.getDict().put(productId, cart.getDict().get(productId)-1);
                    }
                    totalPrice = (int) (totalPrice - productDaoJdbc.find(productId).getDefaultPrice());
                    session.setAttribute("totalOrderAmount", totalPrice);
                    session.setAttribute("cart", cart);
                    resp.sendRedirect("/cart");
                    break;
                case "save":
                    CartDaoJdbc cartDaoJdbc = new CartDaoJdbc();

                    if(cartDaoJdbc.getProductIdByUserId(userId).size() > 0){
                        cartDaoJdbc.removeProductsByUserId(userId);
                    }

                    for(Integer prodId : cart.getDict().keySet()){
                        cartDaoJdbc.addProduct(userId, prodId, cart.getDict().get(prodId));
                    }
                    resp.sendRedirect("/cart");
                    break;
                case "checkout":
                    resp.sendRedirect("/checkout");
                    break;
                case "remove":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    totalPrice = (int) (totalPrice - (productDaoJdbc.find(productId).getDefaultPrice() * cart.getDict().get(productId)));
                    cart.removeProduct(productId);
                    cart.getDict().remove(productId);
                    session.setAttribute("cart", cart);
                    session.setAttribute("totalOrderAmount", totalPrice);
                    resp.sendRedirect("/cart");
                    break;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
