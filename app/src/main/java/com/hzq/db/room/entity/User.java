package com.hzq.db.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description: 嵌套对象
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @Embedded(prefix = "add_")
    public Address address;
}
