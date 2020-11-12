package com.codecool.shop.controller;

import com.codecool.shop.config.DatabaseManager;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.JdbcImplementation.CartDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.memoryImplementation.ProductDaoMem;
import com.codecool.shop.dao.memoryImplementation.CartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDao cart = CartDaoMem.getInstance();
        HashMap <Integer, Integer> quantityRegister = cart.getQuantity();
        List<Product> productsInShoppingCart = new ArrayList<>();

//        AtomicReference<Float> totalPriceToSend = new AtomicReference<Float>((float) 0);

//        cart.getAll().forEach(product -> {
//            totalPriceToSend.updateAndGet(v -> v + (product.getDefaultPrice() *  (int)quantityRegister.get(product.getId())));
//        });


        try {
            CartDaoJdbc cartDaoDB = new CartDaoJdbc(DatabaseManager.connect());
            ProductDaoJdbc productDaoDB = new ProductDaoJdbc(DatabaseManager.connect());
            cartDaoDB.getAll((Integer) req.getSession().getAttribute("userID")).forEach(productId->{
                productsInShoppingCart.add(productDaoDB.find(productId));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        context.setVariable("cart", cart.getAll());
        context.setVariable("cart", productsInShoppingCart);
        context.setVariable("quantity" ,cart.getQuantity());

        int totalPriceToSend = (int) req.getSession().getAttribute("totalPrice");
        context.setVariable("totalOrderAmount", totalPriceToSend);
        req.getSession().setAttribute("finalPrice", totalPriceToSend);

        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int productId = Integer.parseInt(req.getParameter("productId"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();
        HashMap<Integer, Integer> quantity = cart.getQuantity();

        //TODO for the moment is dummy data, user id -> Session
        int userId = 1;
        List<Product> shoppingCartWithDuplicates = new ArrayList<>();

        try {
            CartDaoJdbc cartDaoDB = new CartDaoJdbc(DatabaseManager.connect());
            ProductDaoJdbc productDaoDB = new ProductDaoJdbc(DatabaseManager.connect());
            cartDaoDB.getAll((Integer) req.getSession().getAttribute("userID")).forEach(productID->{
                shoppingCartWithDuplicates.add(productDaoDB.find(productID));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }



        int quantityNumber = quantity.get(productId);

        switch (buttonPressed) {
            case "+":
                if (!shoppingCartWithDuplicates.contains(productDataStore.find(productId))) {
                    cart.add(productDataStore.find(productId), userId);
                    quantity.put(productId, 1);
                } else {
                    int a = quantity.get(productId) + 1;
                    quantity.replace(productId, a);
                }
                break;
            case "-":
                if (quantityNumber == 1) {
//                    cart.remove(productDataStore.find(productId));
                    quantity.remove(productId);
                } else {
                    quantityNumber--;
                    quantity.replace(productId, quantityNumber);
                }
                break;
            case "remove":
//                cart.remove(productDataStore.find(productId));
                quantity.remove(productId);
                break;
        }

        resp.sendRedirect("/cart");
    }
}
