package com.hzq.roomsample.helper;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.hzq.roomsample.db.AppDatabase;
import com.hzq.roomsample.db.entity.ProductEntity;

import java.util.List;

import static com.hzq.roomsample.db.AppDatabase.DATABASE_NAME;

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description: 隔离数据库与业务层
 */

public class AppDatabaseHelper {
    AppDatabase mAppDatabase;
    public AppDatabaseHelper(Context context){
        // Reset the database to have new data on every run.
        context.deleteDatabase(DATABASE_NAME);
        //创建数据库
        mAppDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,DATABASE_NAME).build();
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

    public void insert(ProductEntity product){
        mAppDatabase.productDao().insertAll(product);
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

    public void delete(ProductEntity entity){
        mAppDatabase.productDao().deleteAll(entity);
    }

    public void delete(long id){
        if(id != 0){
            ProductEntity entity = new ProductEntity();
            entity.id = id;
            mAppDatabase.productDao().deleteAll(entity);
        }
    }

}
