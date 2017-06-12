package com.hzq.db.greendao.dao;

import com.hzq.db.greendao.DBManager;
import com.hzq.db.greendao.entity.ProductEntity;
import com.hzq.db.greendao.sysdao.ProductEntityDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public class ProductDao {
    private ProductEntityDao mProductDao;
    public ProductDao(){
        mProductDao = DBManager.getIntence().getDaoSession().getProductEntityDao();
    }

    public void insertProduct(ProductEntity user){
        mProductDao.insert(user);
    }

    public void insertUserList(List<ProductEntity> users){
        if(users == null || users.size() == 0) return;
        mProductDao.insertInTx(users);
    }

    public void deleteProduct(ProductEntity user){
        mProductDao.delete(user);
    }

    public void updateProduct(ProductEntity user){
        mProductDao.update(user);
    }

    public List<ProductEntity> getAllUser(){
        QueryBuilder<ProductEntity> userQueryBuilder = mProductDao.queryBuilder();
        return userQueryBuilder.list();
    }

    public List<ProductEntity> getAllUser(int price){
        QueryBuilder<ProductEntity> builder = mProductDao.queryBuilder();
        builder.where(ProductEntityDao.Properties.Price.gt(price)).orderAsc(ProductEntityDao.Properties.Price);
        return builder.list();
    }

    public ProductEntity getUserById(long id){
        return mProductDao.loadByRowId(id);
    }
}
