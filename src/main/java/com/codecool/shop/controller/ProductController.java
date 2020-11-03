package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/", "/index"})
public class ProductController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemsNumber = 0;

        //TODO each user has an id. When we add the first product, the cart will have an id = userId
        //TODO for the moment is dummy data, user id -> Session
        //TODO so each cart will have an id equal to user id, when user check out, we will remove his cart by searching with id
        int userId = 1;

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();
        HashMap<Integer, Integer> quantity = cart.getQuantity();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        String categoryId = req.getParameter("categoryId");
        String supplierId = req.getParameter("supplierId");
        String resetFilters = req.getParameter("resetFilters");


        if(quantity.size()>0){
            for(Object value: quantity.values()){
                itemsNumber+= (int)value;
            }
        }
        req.getSession().setAttribute("itemsNumber", itemsNumber);

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("cartSize", cart.getAll().size());
        context.setVariable("itemsNumber", itemsNumber);

        if(categoryId != null && Integer.parseInt(categoryId) >= 1){
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(categoryId))));
        } else if (supplierId != null && Integer.parseInt(supplierId) >= 1) {
            context.setVariable("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(supplierId))));
        } else if (resetFilters != null){
            context.setVariable("products", productDataStore.getAll());
        } else {
            context.setVariable("products", productDataStore.getAll());
        }

        String productToAddId = req.getParameter("productId");
        if(productToAddId != null){
            cart.add(productDataStore.find(Integer.parseInt(productToAddId)), userId);
        }


        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonPressed = req.getParameter("button");
        int productId = Integer.parseInt(req.getParameter("productId"));


        //TODO for the moment is dummy data, user id -> Session
        int userId = 1;


        ProductDao productDataStore = ProductDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();
        HashMap<Integer, Integer> quantity1 = cart.getQuantity();

        if(buttonPressed.equals("add")){
            if(!cart.getAll().contains(productDataStore.find(productId))){
                cart.add(productDataStore.find(productId), userId);
                quantity1.put(productId, 1);
            }else{
                int a = quantity1.get(productId) + 1;
                quantity1.replace(productId, a);
            }
        }

        resp.sendRedirect("/index");
    }
}
