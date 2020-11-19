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
import java.util.Optional;



@WebServlet(urlPatterns = {"/", "/index"})
public class ProductController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        int itemsNumber = 0;
        if(req.getSession().getAttribute("userId") != null){
            for(Integer quantity : cart.getDict().values()){
                itemsNumber += quantity;
                session.setAttribute("itemsInCart", itemsNumber);
            }
        }


        context.setVariable("itemsNumber", itemsNumber);

        String categoryId = req.getParameter("categoryId");
        String supplierId = req.getParameter("supplierId");

        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

            context.setVariable("categories", productCategoryDaoJdbc.getAll());
            context.setVariable("suppliers", supplierDaoJdbc.getAll());

            if(categoryId != null && Integer.parseInt(categoryId) >= 1){
                context.setVariable("products", productDaoJdbc.getByCategory(Integer.parseInt(categoryId)));
            } else if (supplierId != null && Integer.parseInt(supplierId) >= 1){
                context.setVariable("products", productDaoJdbc.getBySupplier(Integer.parseInt(supplierId)));
            }else{
                context.setVariable("products", productDaoJdbc.getAll());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int productId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession();


        try {
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

            if(buttonPressed.equals("add")){
                Product product = productDaoJdbc.find(productId);
                Cart cart = (Cart) req.getSession().getAttribute("cart");
                cart.addProduct(product);
                session.setAttribute("itemsInCart", cart.getProductsInCart().size());
                int totalPrice = 0;
                for(Integer prodId : cart.getProductsInCart()){
                    totalPrice += productDaoJdbc.find(prodId).getDefaultPrice() * cart.getDict().get(prodId);
                }
                session.setAttribute("totalOrderAmount", totalPrice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/index");
    }
}

