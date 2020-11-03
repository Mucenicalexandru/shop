package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index/details"})
public class ProductDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        ProductDao productDataStore = ProductDaoMem.getInstance();
        String productId = req.getParameter("id");
        String itemsNumber = String.valueOf(req.getSession().getAttribute("itemsNumber"));

        context.setVariable("product", productDataStore.find(Integer.parseInt(productId)));
        context.setVariable("itemsNumber", itemsNumber);

        engine.process("product/details.html", context, resp.getWriter());
    }

}
