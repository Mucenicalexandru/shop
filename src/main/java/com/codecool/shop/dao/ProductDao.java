package com.codecool.shop.dao;

import java.util.List;

public interface ProductDao<T> {

    List<T> getBySupplier(int supplierId);
    List<T> getByCategory(int categoryId);
}
