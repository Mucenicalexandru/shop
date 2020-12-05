package com.codecool.shop.config;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;

@WebListener
public class Initializer implements ServletContextListener {

    public Initializer() throws FileNotFoundException {
    }



    @Override
    public void contextInitialized(ServletContextEvent sce) {



    }
}
