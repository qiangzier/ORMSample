package com.hzq.db.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/14
 * @version:
 * @description: 数据库迁移：没写完，感觉这里不太好写
 */

public class RoomMigrationManager {
    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";
    private static final String TAG = RoomMigrationManager.class.getSimpleName();
    private static RoomMigrationManager INSTANCE;
    private static final String PERFIX = "_TEMP";

    private RoomMigrationManager(){}

    public static RoomMigrationManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RoomMigrationManager();
        }
        return INSTANCE;
    }

    private static List<String> getColumns(SupportSQLiteDatabase db,String tableName){
        List<String> results = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1",null);
            results = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
        } catch (Exception e) {
            Log.d(TAG,e.getMessage());
        } finally {
            if(cursor != null)
                cursor.close();
        }
        return results;
    }

    public void migrate(SupportSQLiteDatabase db,String... tableNames){
    }

}
