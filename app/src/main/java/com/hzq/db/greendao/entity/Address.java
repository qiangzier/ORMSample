package com.hzq.db.greendao.entity;

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
public class Address {
    @Id
    public Long id;
    public String street;
    public String state;
    public String city;
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1224093568)
    public Address(Long id, String street, String state, String city) {
        this.id = id;
        this.street = street;
        this.state = state;
        this.city = city;
    }
    @Generated(hash = 388317431)
    public Address() {
    }
}
