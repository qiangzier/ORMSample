package com.hzq.db.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hzq.db.room.entity.ProductEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

@Dao
public interface ProductDao {

    @Query("select * from products")
    List<ProductEntity> queryProducts();

    @Query("select * from products where id = :productId")
    ProductEntity getProductById(long productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ProductEntity... entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(ProductEntity... entity);

    @Delete
    void deleteAll(ProductEntity... entity);

}
