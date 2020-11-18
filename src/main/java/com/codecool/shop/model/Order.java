package com.codecool.shop.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Order {

    private UUID uuid;
    long millis=System.currentTimeMillis();
    private java.sql.Date date;
    private int userId;
    private int productId;

    public Order (User user, Product product){
        this.uuid = UUID.randomUUID();
        this.date = new java.sql.Date(millis);
        this.userId = user.getId();
        this.productId = product.getId();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
