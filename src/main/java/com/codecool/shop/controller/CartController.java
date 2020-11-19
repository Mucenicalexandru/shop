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
        context.setVariable("totalOrderAmount", totalPrice);

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
//        CartDao cart = CartDaoMem.getInstance();
//        HashMap <Integer, Integer> quantityRegister = cart.getQuantity();
//        List<Product> productsInShoppingCart = new ArrayList<>();

//        AtomicReference<Float> totalPriceToSend = new AtomicReference<Float>((float) 0);

//        cart.getAll().forEach(product -> {
//            totalPriceToSend.updateAndGet(v -> v + (product.getDefaultPrice() *  (int)quantityRegister.get(product.getId())));
//        });


//        try {
//            CartDaoJdbc cartDaoDB = new CartDaoJdbc(Connector.connect());
//            ProductDaoJdbc productDaoDB = new ProductDaoJdbc(Connector.connect());
//            cartDaoDB.getAll((Integer) req.getSession().getAttribute("userID")).forEach(productId->{
//                productsInShoppingCart.add(productDaoDB.find(productId));
//            });
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



        context.setVariable("quantity" ,cart.getDict());
//
//        int totalPriceToSend = (int) req.getSession().getAttribute("totalPrice");
//        context.setVariable("totalOrderAmount", totalPriceToSend);
//        req.getSession().setAttribute("finalPrice", totalPriceToSend);

        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int productId = 0;
        int userId = (int) req.getSession().getAttribute("userId");
        Cart cart = (Cart) req.getSession().getAttribute("cart");


//        if (saveButton.equals("save")){
//            try {
//                SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
//                ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
//                ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);
//                CartDaoJdbc cartDaoJdbc = new CartDaoJdbc();
//                UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
//                User user = userDaoJdbc.find(userId);
//
//                for(Integer prodId : cart.getProductsInCart()){
//                    cartDaoJdbc.addProduct(productDaoJdbc.find(prodId), user);
//                }
//            } catch (SQLException throwable) {
//                throwable.printStackTrace();
//            }
//        }

//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        CartDao cart = CartDaoMem.getInstance();
//        HashMap<Integer, Integer> quantity = cart.getQuantity();
//
//        //TODO for the moment is dummy data, user id -> Session
//        int userId = 1;
//        List<Product> shoppingCartWithDuplicates = new ArrayList<>();
//
//        try {
//            CartDaoJdbc cartDaoDB = new CartDaoJdbc(Connector.connect());
//            ProductDaoJdbc productDaoDB = new ProductDaoJdbc(Connector.connect());
//            cartDaoDB.getAll((Integer) req.getSession().getAttribute("userID")).forEach(productID->{
//                shoppingCartWithDuplicates.add(productDaoDB.find(productID));
//            });
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//        int quantityNumber = quantity.get(productId);
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

            switch (buttonPressed) {
                case "+":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    cart.addProduct(productDaoJdbc.find(productId));
                    break;
                case "-":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    if (cart.getQuantity(productId) == 1) {
                        cart.removeProduct(productId);
                        cart.getDict().remove(productId);
                    } else {
                        cart.getDict().put(productId, cart.getDict().get(productId)-1);
                    }
                    break;
                case "save":
                    System.out.println(cart.getDict());
                    CartDaoJdbc cartDaoJdbc = new CartDaoJdbc();
                    for(Integer prodId : cart.getDict().keySet()){
                        cartDaoJdbc.addProduct(userId, prodId, cart.getDict().get(prodId));
                    }
                    System.out.println("Products saved");
                    break;
                case "remove":
                    productId = Integer.parseInt(req.getParameter("productId"));
                    cart.removeProduct(productId);
                    cart.getDict().remove(productId);
                    break;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }


        resp.sendRedirect("/cart");
    }
}
