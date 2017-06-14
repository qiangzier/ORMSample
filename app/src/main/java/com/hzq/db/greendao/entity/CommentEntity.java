package com.hzq.db.greendao.entity;

import com.hzq.ormsample.model.Comment;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

@Entity
public class CommentEntity implements Comment{

    @Id
    private int id;
    private long productId;
    private String text;
    private Date postedAt;

    @Generated(hash = 1786354762)
    public CommentEntity(int id, long productId, String text, Date postedAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.postedAt = postedAt;
    }

    @Generated(hash = 339855539)
    public CommentEntity() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Date getPostedAt() {
        return postedAt;
    }

    @Override
    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }
}
