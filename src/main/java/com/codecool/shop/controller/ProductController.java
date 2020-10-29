package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/", "/index"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemsNumber = 0;
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        HashMap<Integer, Integer> quantity = cartDataStore.getQuantity();


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

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("cartSize", cartDataStore.getAll().size());
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
            cartDataStore.add(productDataStore.find(Integer.parseInt(productToAddId)));
        }

        engine.process("product/index.html", context, resp.getWriter());
    }

}
