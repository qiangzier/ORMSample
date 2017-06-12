package com.hzq.db.greendao.dao;

import com.hzq.db.greendao.DBManager;
import com.hzq.db.greendao.entity.User;
import com.hzq.db.greendao.sysdao.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author: hezhiqiang
 * @date: 2017/6/12
 * @version:
 * @description:
 */

public class UserDaoOprater {
    private UserDao mUserDao;
    public UserDaoOprater(){
        mUserDao = DBManager.getIntence().getDaoSession().getUserDao();
    }

    public void insertUser(User user){
        mUserDao.insert(user);
    }

    public void insertUserList(List<User> users){
        if(users == null || users.size() == 0) return;
        mUserDao.insertInTx(users);
    }

    public void deleteUser(User user){
        mUserDao.delete(user);
    }

    public void updateUser(User user){
        mUserDao.update(user);
    }

    public List<User> getAllUser(){
        QueryBuilder<User> userQueryBuilder = mUserDao.queryBuilder();
        return userQueryBuilder.list();
    }

    public List<User> getAllUser(int age){
        QueryBuilder<User> builder = mUserDao.queryBuilder();
        builder.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        return builder.list();
    }
}
