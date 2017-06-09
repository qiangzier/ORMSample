package com.hzq.roomsample.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

@Entity(tableName = "products")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String description;
    public double price;
    @Ignore
    public String testField;
}
