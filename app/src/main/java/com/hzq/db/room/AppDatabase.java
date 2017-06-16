package com.hzq.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.hzq.db.room.converter.DateConverter;
import com.hzq.db.room.dao.CommentDao;
import com.hzq.db.room.dao.ProductDao;
import com.hzq.db.room.entity.CommentEntity;
import com.hzq.db.room.entity.ProductEntity;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description: 数据库管理类
 */

@Database(entities = {ProductEntity.class, CommentEntity.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "room-sample-db";
    public static final String PRODUCT_TABLE_NAME = "products";
    public static final String COMMENT_TABLE_NAME = "comments";

    public abstract ProductDao productDao();

    public abstract CommentDao commentDao();
}
