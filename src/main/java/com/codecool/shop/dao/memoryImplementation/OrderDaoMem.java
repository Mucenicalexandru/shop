package com.codecool.shop.dao.memoryImplementation;

import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

//Singleton
public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        data.add(order);
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return data;
    }

    @Override
    public Order getLast(){
        return data.get(data.size()-1);
    }
}
