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

//import com.codecool.shop.dao.memoryImplementation.ProductCategoryDaoMem;
//import com.codecool.shop.dao.memoryImplementation.ProductDaoMem;
//import com.codecool.shop.dao.memoryImplementation.SupplierDaoMem;
//import com.codecool.shop.dao.memoryImplementation.CartDaoMem;

@WebServlet(urlPatterns = {"/", "/index"})
public class ProductController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//

//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//        CartDao cart = CartDaoMem.getInstance();
//        HashMap<Integer, Integer> quantity = cart.getQuantity();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());



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


//        if(quantity.size()>0){
//            for(Object value: quantity.values()){
//                itemsNumber+= (int)value;
//            }
//        }
//        req.getSession().setAttribute("itemsNumber", itemsNumber);



//        context.setVariable("categories", productCategoryDataStore.getAll());
//        context.setVariable("suppliers", supplierDataStore.getAll());
//        context.setVariable("cartSize", cart.getAll().size());
//        context.setVariable("itemsNumber", itemsNumber);

//        if(categoryId != null && Integer.parseInt(categoryId) >= 1){
//            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(categoryId))));
//        } else if (supplierId != null && Integer.parseInt(supplierId) >= 1) {
//            context.setVariable("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(supplierId))));
//        } else if (resetFilters != null){
//            context.setVariable("products", productDataStore.getAll());
//        } else {
//            context.setVariable("products", productDataStore.getAll());
//        }



//        String productToAddId = req.getParameter("productId");
//        if(productToAddId != null){
//            cart.add(productDataStore.find(Integer.parseInt(productToAddId)), userId);
//        }


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
                session.setAttribute("itemsNumber", cart.getProductsInCart().size());
                int totalPrice = 0;
                for(Product product1 : cart.getProductsInCart()){
                    totalPrice += product1.getDefaultPrice();
                }
                session.setAttribute("totalOrderAmount", totalPrice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        CartDao cart = CartDaoMem.getInstance();
//        HashMap<Integer, Integer> quantity1 = cart.getQuantity();





//        if(buttonPressed.equals("add")){
//            try {
//
//                //TODO DAO -> Singleton
//                CartDaoJdbc cartDaoDB = new CartDaoJdbc(Connector.connect());
//                ProductDaoJdbc productDaoDB = new ProductDaoJdbc(Connector.connect());
//                ArrayList<Product> productsInShoppingCart = new ArrayList<>();
//
//                cartDaoDB.getAll((Integer) req.getSession().getAttribute("userID")).forEach(productID->{
//                    productsInShoppingCart.add(productDaoDB.find(productId));
//                });
//
//
//                //initial quantity of a given product from a given user
//                int quantity1 = cartDaoDB.getQuantity(productDaoDB.find(productId), userId);
//
//                //if userCart is empty, then just add, else we check for duplicates and we only update the quantity if we find duplicate
//                if(productsInShoppingCart.size() == 0){
//                    cartDaoDB.add(productDaoDB.find(productId), (Integer) req.getSession().getAttribute("userID"));
//                    productsInShoppingCart.add(productDaoDB.find(productId));
//                }else{
//                    for(int i = 0; i < productsInShoppingCart.size(); i++){
//                        if(productsInShoppingCart.get(i).toString().equals(productDaoDB.find(productId).toString())){
//                            int quantityToUpdate = quantity1 + 1;
//                            int totalPrice = (int) (quantityToUpdate * productDaoDB.find(productId).getDefaultPrice());
//                            //TODO to remove
//                            req.getSession().setAttribute("totalPrice", totalPrice);
//                            cartDaoDB.update(productDaoDB.find(productId),(Integer) req.getSession().getAttribute("userID"), quantityToUpdate, totalPrice);
//                        }else{
//                            cartDaoDB.add(productDaoDB.find(productId), (Integer) req.getSession().getAttribute("userID"));
//                            productsInShoppingCart.add(productDaoDB.find(productId));
//                        }
//                    }
//
//                }
//
//
//            } catch (SQLException e) {
//                e.printStackTrace();

//            if(!cart.getAll().contains(productDataStore.find(productId))){
//                cart.add(productDataStore.find(productId), userId);
//                quantity1.put(productId, 1);
//                }
//            }else{
//                int a = quantity1.get(productId) + 1;
//                quantity1.replace(productId, a);
//            }
//            }

        resp.sendRedirect("/index");
    }
}
//    }}
