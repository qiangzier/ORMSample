package com.hzq.db.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.hzq.db.room.AppDatabase;
import com.hzq.ormsample.model.Product;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

@Entity(tableName = AppDatabase.PRODUCT_TABLE_NAME,
        indices = {@Index(value = {"id","name"},unique = true)})
public class ProductEntity implements Product{
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private String description;
    private double price;
    @Ignore
    private String testField;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public String getTestField() {
        return testField;
    }

    public void setTestField(String testField) {
        this.testField = testField;
    }
}
