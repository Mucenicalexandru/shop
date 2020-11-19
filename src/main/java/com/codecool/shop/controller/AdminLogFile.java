package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
//import com.codecool.shop.dao.memoryImplementation.OrderDaoMem;
import com.codecool.shop.dao.JdbcImplementation.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/logfile"})
public class AdminLogFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        try {
            OrderDaoJdbc orderDaoJdbc = new OrderDaoJdbc();
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();

            List<Order> orderList = orderDaoJdbc.getAll();
            List<Product> productList = productDaoJdbc.getAll();
            List<User> userList = userDaoJdbc.getAll();

            context.setVariable("orderList", orderList);
            context.setVariable("productList", productList);
            context.setVariable("userList", userList);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        engine.process("admin/logfile.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("search");
        System.out.println("Searched Name is : " + name);
        resp.sendRedirect("/logfile");

        //TODO Edit order
    }
}
