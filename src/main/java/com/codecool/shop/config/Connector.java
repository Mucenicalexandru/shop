package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static Connector instance = new Connector();

    private Connector(){};

    public static Connector getInstance(){
        return instance;
    }

    public static DataSource connect() throws SQLException, IOException {
        String propFileName = "resources/connection.properties";
        InputStream input = new FileInputStream(propFileName);
        Properties properties = new Properties();
        properties.load(input);


        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(properties.getProperty("database"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

}
