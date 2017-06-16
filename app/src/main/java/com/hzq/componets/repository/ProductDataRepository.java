package com.hzq.componets.repository;

import android.arch.lifecycle.LiveData;

import com.hzq.db.room.DatabaseCreator;
import com.hzq.db.room.entity.ProductEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/15
 * @version:
 * @description:
 */

public class ProductDataRepository {

    public LiveData<List<ProductEntity>> getProducts(){
        return DatabaseCreator.getInstance().getAppDatabase().productDao().queryLiveProducts();
    }

    public LiveData<ProductEntity> getProduct(long productId){
        return DatabaseCreator.getInstance().getAppDatabase().productDao().getLiveProductById(productId);
    }

    public LiveData<Boolean> isCreatedDatabase(){
        return DatabaseCreator.getInstance().getCreatedDatabase();
    }

    public Boolean deleteProduct(long productId){
        try {
            ProductEntity entity = new ProductEntity();
            entity.setId(productId);
            DatabaseCreator.getInstance().getAppDatabase().productDao().deleteAll(entity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
