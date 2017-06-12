package com.hzq.db.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

@Entity(tableName = "comments",
        foreignKeys = { //外键
            @ForeignKey(entity = ProductEntity.class,
            parentColumns = "id",
            childColumns = "productId",
            onDelete = ForeignKey.CASCADE)},
        indices = { //索引
                @Index(value = "productId")
        }
)
public class CommentEntity {
    @PrimaryKey(autoGenerate = true) //主键自增
    public int id;
    public long productId;
    public String text;
    public Date postedAt;
}
