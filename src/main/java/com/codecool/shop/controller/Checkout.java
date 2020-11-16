package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.memoryImplementation.CartDaoMem;
import com.codecool.shop.dao.memoryImplementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> countries = new ArrayList<>();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String finalPrice = String.valueOf(req.getSession().getAttribute("finalPrice"));

        String[] locales = Locale.getISOCountries();
        for(String country : locales){
            Locale obj = new Locale("", country);
            countries.add(obj.getDisplayCountry());
        }

        context.setVariable("finalPrice", finalPrice);
        context.setVariable("countries", countries);

        engine.process("cart/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String finalPrice = String.valueOf(req.getSession().getAttribute("finalPrice"));


        CartDao cart = CartDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        UUID uuid = UUID.randomUUID();
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String country = req.getParameter("country");
        String address = req.getParameter("address");
        String postcode = req.getParameter("postcode");
        String town = req.getParameter("town");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        List<Product> orderedProducts = cart.getAll();
        HashMap<Integer, Integer> quantitiesOrdered = cart.getQuantity();


        Order order = new Order(uuid, firstName, lastName, country, address, postcode, town, phone, email, orderedProducts, quantitiesOrdered, finalPrice);


        //TODO object to json using gson
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try {
            FileWriter fileWriter = new FileWriter("resources/order.json");
            gson.toJson(order, fileWriter);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        orderDataStore.add(order);

        resp.sendRedirect("/payment");
    }
}
