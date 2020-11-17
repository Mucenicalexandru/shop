package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        context.setVariable("cart", cart.getProductsInCart());

        int totalPrice = (int) req.getSession().getAttribute("totalOrderAmount");
        context.setVariable("totalOrderAmount", totalPrice);

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



//        context.setVariable("quantity" ,cart.getQuantity());
//
//        int totalPriceToSend = (int) req.getSession().getAttribute("totalPrice");
//        context.setVariable("totalOrderAmount", totalPriceToSend);
//        req.getSession().setAttribute("finalPrice", totalPriceToSend);

        engine.process("cart/cart.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int productId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession();
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
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

            switch (buttonPressed) {
                case "+":
                    cart.addProduct(productDaoJdbc.find(productId));
                    break;
//                case "-":
//                    if (quantityNumber == 1) {
//                        cart.remove(productDataStore.find(productId));
//                        quantity.remove(productId);
//                    } else {
//                        quantityNumber--;
//                        quantity.replace(productId, quantityNumber);
//                    }
//                    break;
                case "remove":
                    cart.getProductsInCart().remove(productDaoJdbc.find(productId));
                    session.setAttribute("cart", cart);
                    break;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }



        resp.sendRedirect("/cart");
    }
}
