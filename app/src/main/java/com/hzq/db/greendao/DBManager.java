package com.hzq.db.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hzq.db.greendao.migration.MigrationHelper;
import com.hzq.db.greendao.sysdao.CommentEntityDao;
import com.hzq.db.greendao.sysdao.DaoMaster;
import com.hzq.db.greendao.sysdao.DaoSession;
import com.hzq.db.greendao.sysdao.ProductEntityDao;

import org.greenrobot.greendao.database.Database;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description: greenDao数据库框架管理类，使用时需要调用DBManager.init(context)初始化
 */

public class DBManager {
    private static final String DB_NAME = "test_db";
    private static DBManager dbManager;
    private static AppSQLiteOpenHelper openHelper;
    private static DaoSession daoSession;
    private static Database mDatabase;

    private DBManager(){
    }

    public static DBManager getIntence(){
        if(dbManager == null){
            synchronized (DBManager.class){
                if(dbManager == null){
                    dbManager = new DBManager();
                }
            }
        }
        return dbManager;
    }

    public static void init(Context context){
        openHelper = new AppSQLiteOpenHelper(context, DB_NAME,null);
        mDatabase = openHelper.getWritableDb();
        daoSession = new DaoMaster(mDatabase).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public static class AppSQLiteOpenHelper extends DaoMaster.OpenHelper{

        public AppSQLiteOpenHelper(Context context, String name) {
            super(context, name);
        }

        public AppSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            //数据库迁移操作
            MigrationHelper.getInstance().migrate(db,ProductEntityDao.class, CommentEntityDao.class);
        }
    }
}
