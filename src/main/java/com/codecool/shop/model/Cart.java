package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart{

    private int id; //userId
    private int productId;
    private int quantity;
    private List<Product> productsInCart;

    public Cart() {
    }

    public void addProduct(Product product){
        productsInCart.add(product);
    }

    public List<Product> getProductsInCart(){
        return productsInCart;
    }

    public void removeProduct(Product product){
        if(productsInCart.contains(product)){
            productsInCart.remove(product);
        }
    }

    public void removeAllProducts(){
        productsInCart = new ArrayList<>();
    }

    public double getTotalPrice(){
        double totalPrice = 0;

        for(Product product : productsInCart){
            totalPrice += product.getDefaultPrice();
        }

        return totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
