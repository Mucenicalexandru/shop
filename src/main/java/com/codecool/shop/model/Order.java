package com.codecool.shop.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Order {

    long millis=System.currentTimeMillis();
    private java.sql.Date date;

    private int userId;
    private int productId;
    private int quantity;

    public Order (Integer userId, Integer productId, Integer quantity){
        this.date = new java.sql.Date(millis);
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
