package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
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


@WebServlet(urlPatterns = {"/index/details"})
public class ProductDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //parameter from url
        String productId = req.getParameter("id");

        try {
        SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
        ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);

        context.setVariable("product", productDaoJdbc.find(Integer.parseInt(productId)));

        }catch (SQLException e) {
            e.printStackTrace();
        }

        if(req.getSession().getAttribute("userId") != null){
            String itemsNumber = String.valueOf(req.getSession().getAttribute("itemsInCart"));
            context.setVariable("itemsNumber", itemsNumber);
        }else{
            context.setVariable("itemsNumber", 0);
        }


        engine.process("product/details.html", context, resp.getWriter());

    }

}
