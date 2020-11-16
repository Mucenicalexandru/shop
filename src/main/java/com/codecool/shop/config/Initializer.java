package com.codecool.shop.config;

//import com.codecool.shop.dao.memoryImplementation.ProductCategoryDaoMem;
//import com.codecool.shop.dao.memoryImplementation.ProductDaoMem;
//import com.codecool.shop.dao.memoryImplementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    public Initializer() throws FileNotFoundException {
    }

//    private Connector connector = new Connector();




    @Override
    public void contextInitialized(ServletContextEvent sce) {


//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
//
////        setting up a new supplier
//        Supplier bmw = new Supplier("BWM", "BMW (Bayerische Motoren-Werke) is a German manufacturer of motorcycles and automobiles, founded in 1917 and headquartered in München, Germany.");
//        supplierDataStore.add(bmw);
//
//        Supplier audi = new Supplier("Audi", "Audi AG is a German automobile manufacturer that designs, engineers, produces, markets and distributes luxury vehicles.");
//        supplierDataStore.add(audi);
//
//        Supplier mercedes = new Supplier("Mercedes-Benz", "Mercedes-Benz is both a German automotive marque and, from late 2019 onwards, a subsidiary of Daimler AG. Mercedes-Benz is known for producing luxury vehicles and commercial vehicles.");
//        supplierDataStore.add(mercedes);
//
//        Supplier ducati = new Supplier("Ducati", "Ducati Motor Holding S.p.A. is the motorcycle-manufacturing division of Italian company Ducati, headquartered in Bologna, Italy.");
//        supplierDataStore.add(ducati);
//
//        Supplier lamborghini = new Supplier("Lamborghini", "Automobili Lamborghini S.p.A. is an Italian brand and manufacturer of luxury sports cars and SUVs based in Sant'Agata Bolognese.");
//        supplierDataStore.add(lamborghini);
//
//        Supplier landRover = new Supplier("Range Rover", "The Land Rover Range Rover is both a full-sized luxury sport utility vehicle (SUV) produced by Land Rover, a marque of Jaguar Land Rover");
//        supplierDataStore.add(landRover);
//
//        Supplier porsche = new Supplier("Porsche", "Porsche AG, usually shortened to Porsche AG is a German automobile manufacturer specializing in high-performance sports cars");
//        supplierDataStore.add(porsche);
//
//        Supplier rolls = new Supplier("Rolls Royce", "Porsche AG, usually shortened to Porsche AG is a German automobile manufacturer specializing in high-performance sports cars");
//        supplierDataStore.add(rolls);
//
//
//        //setting up a new product category
//        ProductCategory cars = new ProductCategory("Cars", "Transport", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        ProductCategory motorcycle = new ProductCategory("Motorcycle", "Transport", "Meet the new Iphone 12");
//        productCategoryDataStore.add(cars);
//        productCategoryDataStore.add(motorcycle);
//
//
//        //setting up products and printing it
//        try {
//            productDataStore.add(new Product("BMW 5 Series", 57050, "EUR", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", cars, bmw));
//            productDataStore.add(new Product("BMW 6 Series", 63850, "EUR", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", cars, bmw));
//            productDataStore.add(new Product("BMW 6 Series Coupe", 89400, "EUR", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", cars, bmw));
//
//            productDataStore.add(new Product("Mercedes-Benz S 65 AMG", 105950, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, mercedes));
//            productDataStore.add(new Product("Mercedes-Benz Mercedes-Benz AMG", 249500, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, mercedes));
//            productDataStore.add(new Product("Mercedes-Benz G 63 AMG", 244760, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, mercedes));
//
//            productDataStore.add(new Product("Audi R8 Coupe", 243980, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, audi));
//            productDataStore.add(new Product("Audi RS6", 234900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, audi));
//            productDataStore.add(new Product("Audi RSQ8 quattro", 234900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, audi));
//
//            productDataStore.add(new Product("Ducati Desmosedici 16 RR Racing", 175000, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//            productDataStore.add(new Product("Ducati Superleggera V4", 149000, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//            productDataStore.add(new Product("Ducati 1199 Panigale", 59999, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//
//            productDataStore.add(new Product("Bentley Continental GT V8", 235700, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//            productDataStore.add(new Product("Bentley Bentayga Speed", 244900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//            productDataStore.add(new Product("Bentley Flying Spur", 234600, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", motorcycle, ducati));
//
//            productDataStore.add(new Product("Lamborghini URUS", 240000, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, lamborghini));
//            productDataStore.add(new Product("Lamborghini Huracan EVO", 219900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, lamborghini));
//            productDataStore.add(new Product("Lamborghini Aventador Miura Edition", 433700, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, lamborghini));
//
//            productDataStore.add(new Product("Rolls-Royce Wraith", 189900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, rolls));
//            productDataStore.add(new Product("Rolls-Royce Wraith DAS3", 179700, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, rolls));
//            productDataStore.add(new Product("Rolls-Royce Cullinan", 328300, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, rolls));
//
//            productDataStore.add(new Product("Land Rover Range Rover 5.0 V8 SC", 285000, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, landRover));
//            productDataStore.add(new Product("Land Rover Range Rover 5.0 V8", 221300, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, landRover));
//            productDataStore.add(new Product("Land Rover Range Rover 3.0 V6 ", 211800, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, landRover));
//
//
//            productDataStore.add(new Product("Porsche Cayenne Turbo Coupe 4.0", 171900, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, porsche));
//            productDataStore.add(new Product("Porsche Cayman 718 GTS", 90880, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, porsche));
//            productDataStore.add(new Product("Porsche 718 Boxster GTS 4.0", 88980, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, porsche));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }




//        try {
//            ProductDaoJdbc productDaoJdbc;
//            ProductCategoryDaoJdbc productCategoryDaoJdbc;
//            SupplierDaoJdbc supplierDaoJdbc;
//            supplierDaoJdbc = new SupplierDaoJdbc((DatabaseManager.connect()));
//            productCategoryDaoJdbc = new ProductCategoryDaoJdbc((DatabaseManager.connect()));
//            productDaoJdbc = new ProductDaoJdbc(DatabaseManager.connect());
//            supplierDaoJdbc.add(porsche);
//            productCategoryDaoJdbc.add(cars);
//            productDaoJdbc.add(new Product("Porsche Cayenne Turbo Coupe 4.0", 171900.00f, "EUR", "Lang FINAL EDITION, Magic Skydak, Enter", cars, porsche));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
