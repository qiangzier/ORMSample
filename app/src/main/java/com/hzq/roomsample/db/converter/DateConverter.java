package com.hzq.roomsample.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}
