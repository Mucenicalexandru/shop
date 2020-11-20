package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.JdbcImplementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.ProductDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.SupplierDaoJdbc;
import com.codecool.shop.dao.JdbcImplementation.UserDaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/order"})
public class OrderConfirmation extends HttpServlet {

    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Cart cart = (Cart) req.getSession().getAttribute("cart");


        try {
            UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
            SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
            ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
            ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(supplierDaoJdbc, productCategoryDaoJdbc);
            User user = userDaoJdbc.find((int)req.getSession().getAttribute("userId"));
            List<Product> productList = new ArrayList<>(productDaoJdbc.getAll());

            context.setVariable("productList", productList);
            context.setVariable("cart", cart.getProductsInCart());
            context.setVariable("quantities", cart.getDict());
            context.setVariable("user", user);
            context.setVariable("date", date);
            context.setVariable("finalPrice", session.getAttribute("totalOrderAmount"));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }




        cart.removeAllProducts();
        session.setAttribute("totalOrderAmount", 0);



        engine.process("cart/order.html", context, resp.getWriter());


    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Document document = new Document();
//
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream("resources/receipt.pdf"));
//            document.open();
//            document.add(new Paragraph("Receipt : "));
//            document.add(new Phrase("Shipping address : "));
//            document.close();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }
}
