package com.codecool.shop.dao;

import java.util.List;

public interface OrderDao<T> {

    List<T> getAllByUserId(int userId);
}
