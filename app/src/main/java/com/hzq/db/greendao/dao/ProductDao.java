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

    /**
     * insert product
     * @param entity
     */
    public void insertProduct(ProductEntity entity){
        mProductDao.insert(entity);
    }

    public void insertUserList(List<ProductEntity> entitys){
        if(entitys == null || entitys.size() == 0) return;
        mProductDao.insertInTx(entitys);
    }

    /**
     * delete product
     * @param entity
     */
    public void deleteProduct(ProductEntity entity){
        mProductDao.delete(entity);
    }

    /**
     * update product
     * @param entity
     */
    public void updateProduct(ProductEntity entity){
        mProductDao.update(entity);
    }

    /**
     * 获取所有product
     * @return
     */
    public List<ProductEntity> getAllUser(){
        QueryBuilder<ProductEntity> userQueryBuilder = mProductDao.queryBuilder();
        return userQueryBuilder.list();
    }
    /**
     * 获取所有product
     * @return
     */
    public List<ProductEntity> getALLUser1(){
        return mProductDao.loadAll();
    }

    public List<ProductEntity> getAllUser(int price){
        QueryBuilder<ProductEntity> builder = mProductDao.queryBuilder();
        builder.where(ProductEntityDao.Properties.Price.gt(price)).orderAsc(ProductEntityDao.Properties.Price);
        return builder.list();
    }

    /**
     * 根据id获取product
     * @return
     */
    public ProductEntity getUserById(long id){
        return mProductDao.load(id);
    }

    /**
     * 执行sql
     * @param sql
     */
    public void execSql(String sql){
        DBManager.getIntence().getDaoSession().getDatabase().execSQL(sql);
    }
}
