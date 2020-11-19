package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart{

    private int id; //userId
    private int productId;
//    private int quantity;
    private List<Integer> productsInCart;
    private HashMap<Integer, Integer> quantities;


    public Cart() {
        productsInCart = new ArrayList<>();
        quantities = new HashMap<>();
    }

    public void addProduct(Product product){
        if(productsInCart.contains(product.getId())){
            quantities.put(product.getId(), quantities.get(product.getId())+1);
        }else{
            productsInCart.add(product.getId());
            quantities.put(product.getId(), 1);
            this.productId = product.getId();
        }
    }

    public int getQuantity(int productId){
        return quantities.get(productId);
    }

    public HashMap<Integer, Integer> getDict(){
        return quantities;
    }


    public List<Integer> getProductsInCart(){
        return productsInCart;
    }

    public void removeProduct(Integer productId){
        if(productsInCart.contains(productId)){
            productsInCart.remove(productId);
        }
    }

    public void removeAllProducts(){
        productsInCart = new ArrayList<>();
        quantities = new HashMap<>();
    }

//    public double getTotalPrice(){
//        double totalPrice = 0;
//
//        for(Integer productId : productsInCart){
//            totalPrice += product.getDefaultPrice();
//        }
//
//        return totalPrice;
//    }

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

//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productId=" + productId +
                ", productsInCart=" + productsInCart +
                ", quantities=" + quantities +
                '}';
    }

}
