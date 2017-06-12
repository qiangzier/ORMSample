package com.hzq.ormsample.model;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public interface Product {
    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    double getPrice();

    void setPrice(double price);
}
