package com.hzq.db.greendao.entity;

import com.hzq.ormsample.model.Product;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

@Entity
public class ProductEntity implements Product {

    @Id
    private long id;
    private String name;
    private String description;
    private double price;

    @Generated(hash = 1251424648)
    public ProductEntity(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Generated(hash = 27353230)
    public ProductEntity() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
