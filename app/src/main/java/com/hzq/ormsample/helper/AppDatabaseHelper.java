package com.hzq.ormsample.helper;

import com.hzq.db.room.AppDatabase;
import com.hzq.db.room.DatabaseCreator;
import com.hzq.db.room.entity.ProductEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description: 隔离数据库与业务层
 */

public class AppDatabaseHelper {
    AppDatabase mAppDatabase;
    public AppDatabaseHelper(){
        mAppDatabase = DatabaseCreator.getInstance().getAppDatabase();
    }

    public List<ProductEntity> getAllProducts(){
        return mAppDatabase.productDao().queryProducts();
    }

    public ProductEntity getProductById(long id){
        return mAppDatabase.productDao().getProductById(id);
    }

    public void insertAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
        for (ProductEntity product : entities) {
            mAppDatabase.productDao().insertAll(product);
        }
    }

    public void insert(ProductEntity product){
        mAppDatabase.productDao().insertAll(product);
    }

    public void updateAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
        for (ProductEntity entity : entities) {
            mAppDatabase.productDao().updateAll(entity);
        }
    }

    public void deleteAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
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

    public void deleteAll(){
        deleteAll(getAllProducts());
    }

}
