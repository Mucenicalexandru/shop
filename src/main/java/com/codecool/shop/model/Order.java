package com.codecool.shop.model;

import java.util.HashMap;
import java.util.List;

public class Order {

    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String postcode;
    private String town;
    private String phoneNumber;
    private String email;
    private List<Product> orderedProducts;
    private HashMap<Integer, Integer> orderedQuantities;
    private String totalAmount;

    public Order(int id, String firstName, String lastName, String country, String address, String postcode, String town, String phoneNumber, String email, List<Product> orderedProducts, HashMap<Integer, Integer> orderedQuantities, String totalAmount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.address = address;
        this.postcode = postcode;
        this.town = town;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orderedProducts = orderedProducts;
        this.orderedQuantities = orderedQuantities;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public HashMap<Integer, Integer> getOrderedQuantities() {
        return orderedQuantities;
    }

    public void setOrderedQuantities(HashMap<Integer, Integer> quantitiesOrdered) {
        this.orderedQuantities = quantitiesOrdered;
    }
}
