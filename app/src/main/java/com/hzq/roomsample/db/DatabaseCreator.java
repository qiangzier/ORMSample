package com.hzq.roomsample.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;

import static com.hzq.roomsample.db.AppDatabase.DATABASE_NAME;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

public class DatabaseCreator {
    private static DatabaseCreator INSTANCE;
    private static final Object LOCK = new Object();

    private AppDatabase appDatabase;
    public static DatabaseCreator getInstance(Context context){
        if(INSTANCE == null){
            synchronized (LOCK){
                if(INSTANCE == null){
                    INSTANCE = new DatabaseCreator(context);
                }
            }
        }
        return INSTANCE;
    }

    @Nullable
    public AppDatabase getAppDatabase(){
        return appDatabase;
    }

    private DatabaseCreator(final Context context){
        // Reset the database to have new data on every run.
        context.deleteDatabase(DATABASE_NAME);
        //创建数据库
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,DATABASE_NAME).build();
    }

}
