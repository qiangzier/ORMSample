package com.hzq.ormsample.model;

import java.util.Date;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public interface Comment {
    int getId();

    void setId(int id);

    long getProductId();

    void setProductId(long productId);

    String getText();

    void setText(String text);

    Date getPostedAt();

    void setPostedAt(Date postedAt);
}
