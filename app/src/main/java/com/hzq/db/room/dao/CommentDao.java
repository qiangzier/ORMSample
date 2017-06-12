package com.hzq.db.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hzq.db.room.entity.CommentEntity;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

@Dao
public interface CommentDao {
    @Query("select * from comments")
    List<CommentEntity> queryComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntity> commentEntities);

    @Query("select * from comments where id = :commentId")
    CommentEntity getCommentById(long commentId);
}
