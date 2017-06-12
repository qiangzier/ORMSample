package com.hzq.db.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.Nullable;

import static com.hzq.db.room.AppDatabase.DATABASE_NAME;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

public class DatabaseCreator {
    private static DatabaseCreator INSTANCE;
    private static final Object LOCK = new Object();

    private static AppDatabase appDatabase;
    public static DatabaseCreator getInstance(){
        if(INSTANCE == null){
            synchronized (LOCK){
                if(INSTANCE == null){
                    INSTANCE = new DatabaseCreator();
                }
            }
        }
        return INSTANCE;
    }

    public static void init(Context context){
        // Reset the database to have new data on every run.
        context.deleteDatabase(DATABASE_NAME);
        //创建数据库
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    @Nullable
    public AppDatabase getAppDatabase(){
        if(appDatabase == null) throw new NullPointerException("数据库连接未初始化");
        return appDatabase;
    }

    private DatabaseCreator(){
    }

    private static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //// TODO: 2017/6/12
        }
    };
}
