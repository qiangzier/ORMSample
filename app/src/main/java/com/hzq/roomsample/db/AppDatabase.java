package com.hzq.roomsample.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.hzq.roomsample.db.converter.DateConverter;
import com.hzq.roomsample.db.dao.CommentDao;
import com.hzq.roomsample.db.dao.ProductDao;
import com.hzq.roomsample.db.entity.CommentEntity;
import com.hzq.roomsample.db.entity.ProductEntity;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description: 数据库管理类
 */

@Database(entities = {ProductEntity.class, CommentEntity.class},version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static String DATABASE_NAME = "room-sample-db";

    public abstract ProductDao productDao();

    public abstract CommentDao commentDao();
}
