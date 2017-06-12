package com.hzq.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.hzq.db.room.converter.DateConverter;
import com.hzq.db.room.dao.CommentDao;
import com.hzq.db.room.dao.ProductDao;
import com.hzq.db.room.entity.CommentEntity;
import com.hzq.db.room.entity.ProductEntity;
import com.hzq.db.room.entity.User;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description: 数据库管理类
 */

@Database(entities = {ProductEntity.class, CommentEntity.class, User.class},version = 2)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static String DATABASE_NAME = "room-sample-db";

    public abstract ProductDao productDao();

    public abstract CommentDao commentDao();
}
