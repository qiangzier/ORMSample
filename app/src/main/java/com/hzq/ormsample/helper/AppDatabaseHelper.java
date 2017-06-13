package com.hzq.ormsample.helper;

import android.util.Log;

import com.hzq.db.greendao.dao.ProductDao;
import com.hzq.db.room.AppDatabase;
import com.hzq.db.room.DatabaseCreator;
import com.hzq.db.room.entity.ProductEntity;
import com.hzq.ormsample.model.Product;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description: 隔离数据库与业务层
 */

public class AppDatabaseHelper {
    AppDatabase mAppDatabase;
    ProductDao mProductDao;
    private static final String TAG = AppDatabaseHelper.class.getSimpleName();
    public AppDatabaseHelper(){
        mAppDatabase = DatabaseCreator.getInstance().getAppDatabase();
        mProductDao = new ProductDao();
    }

    public int getDbType(){
        int type = DB.INSTANCE.getType();
        if(type == 1){
            Log.d(TAG,"current use greenDao..."+String.valueOf(DB.INSTANCE.getType()));
        }else if(type == 2){
            Log.d(TAG,"current use ORMLite..."+String.valueOf(DB.INSTANCE.getType()));
        }else{
            Log.d(TAG,"current use ROOM..."+String.valueOf(DB.INSTANCE.getType()));
        }
        return type;
    }

    public List<? extends Product> getAllProducts(){
        if(getDbType() == 1){
            return mProductDao.getAllUser();
        }else {
            return mAppDatabase.productDao().queryProducts();
        }
    }

    public Product getProductById(long id){
        if(getDbType() == 1){
            return mProductDao.getUserById(id);
        }else {
            return mAppDatabase.productDao().getProductById(id);
        }
    }

    public void insertAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
        for (ProductEntity product : entities) {
            if(getDbType() == 1){
                mProductDao.insertProduct(convertProduct(product));
            } else {
                mAppDatabase.productDao().insertAll(product);
            }
        }
    }

    public void insert(ProductEntity product){
        if(getDbType() == 1){
            mProductDao.insertProduct(convertProduct(product));
        }else {
            mAppDatabase.productDao().insertAll(product);
        }
    }

    public void updateAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
        for (ProductEntity entity : entities) {
            mAppDatabase.productDao().updateAll(entity);
        }
    }

    public void updateProduct(ProductEntity entity){
        if(getDbType() == 1){
            mProductDao.updateProduct(convertProduct(entity));
        }else {
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
        if(getDbType() == 1) {
            mProductDao.deleteProduct(convertProduct(entity));
        }else {
            mAppDatabase.productDao().deleteAll(entity);
        }
    }

    public void delete(Long id){
        if(id != 0){
            ProductEntity entity = new ProductEntity();
            entity.setId(id);
            if(getDbType() == 1){
                mProductDao.deleteProduct(convertProduct(entity));
            }else {
                mAppDatabase.productDao().deleteAll(entity);
            }
        }
    }

    public void deleteAll(){
//        deleteAll(getAllProducts());
    }

    private com.hzq.db.greendao.entity.ProductEntity convertProduct(ProductEntity entity){
        com.hzq.db.greendao.entity.ProductEntity pe = new com.hzq.db.greendao.entity.ProductEntity();
        pe.setId(entity.getId());
        pe.setName(entity.getName());
        pe.setDescription(entity.getDescription());
        pe.setPrice(entity.getPrice());
        return pe;
    }
}
