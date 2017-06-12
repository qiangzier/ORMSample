package com.hzq.ormsample;

import android.app.Application;

import com.hzq.db.greendao.DBManager;
import com.hzq.db.room.DatabaseCreator;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public class ORMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseCreator.init(this);
        DBManager.init(this);
    }
}
