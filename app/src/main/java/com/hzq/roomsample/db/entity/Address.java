package com.hzq.roomsample.db.entity;

import android.arch.persistence.room.ColumnInfo;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
