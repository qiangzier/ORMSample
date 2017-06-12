package com.hzq.ormsample.helper;

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
    private int type;
    public AppDatabaseHelper(int type){
        this.type = type;
        mAppDatabase = DatabaseCreator.getInstance().getAppDatabase();
        mProductDao = new ProductDao();
    }

    public List<? extends Product> getAllProducts(){
        if(type == 1){
            return mProductDao.getAllUser();
        }else {
            return mAppDatabase.productDao().queryProducts();
        }
    }

    public Product getProductById(long id){
        if(type == 1){
            return mProductDao.getUserById(id);
        }else {
            return mAppDatabase.productDao().getProductById(id);
        }
    }

    public void insertAll(List<ProductEntity> entities){
        if(entities == null || entities.size() == 0) return;
        for (ProductEntity product : entities) {
            if(type == 1){
                mProductDao.insertProduct(convertProduct(product));
            } else {
                mAppDatabase.productDao().insertAll(product);
            }
        }
    }

    public void insert(ProductEntity product){
        if(type == 1){
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
        if(type == 1){
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
        if(type == 1) {
            mProductDao.deleteProduct(convertProduct(entity));
        }else {
            mAppDatabase.productDao().deleteAll(entity);
        }
    }

    public void delete(long id){
        if(id != 0){
//            ProductEntity entity = new ProductEntity();
//            entity.id = id;
//            mAppDatabase.productDao().deleteAll(entity);
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
