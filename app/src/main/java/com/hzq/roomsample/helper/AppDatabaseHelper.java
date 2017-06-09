package com.hzq.roomsample.helper;

import android.content.Context;

import com.hzq.roomsample.db.AppDatabase;
import com.hzq.roomsample.db.DatabaseCreator;
import com.hzq.roomsample.db.entity.ProductEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description: 隔离数据库与业务层
 */

public class AppDatabaseHelper {
    AppDatabase mAppDatabase;
    public AppDatabaseHelper(Context context){
        mAppDatabase = DatabaseCreator.getInstance(context).getAppDatabase();
    }

    public List<ProductEntity> getAllProducts(){
        return mAppDatabase.productDao().queryProducts();
    }

    public ProductEntity getProductById(long id){
        return mAppDatabase.productDao().getProductById(id);
    }

    public void insertAll(List<ProductEntity> products){
        for (ProductEntity product : products) {
            mAppDatabase.productDao().insertAll(product);
        }
    }

    public void updateAll(List<ProductEntity> entities){
        for (ProductEntity entity : entities) {
            mAppDatabase.productDao().updateAll(entity);
        }
    }

    public void deleteAll(List<ProductEntity> entities){
        for (ProductEntity entity : entities) {
            mAppDatabase.productDao().deleteAll(entity);
        }
    }




}
