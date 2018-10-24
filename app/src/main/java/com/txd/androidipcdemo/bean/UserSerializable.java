package com.txd.androidipcdemo.bean;

import java.io.Serializable;

/**
 * <com.txd.androidipcdemo.bean>
 * Created by XiaoDun on 2018/10/23.
 * Feature:
 */

public class UserSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private boolean isMale;
    
    public UserSerializable(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public boolean isMale() {
        return isMale;
    }
    
    public void setMale(boolean male) {
        isMale = male;
    }
    
    @Override
    public String toString() {
        return "UserSerializable{" +
               "userId=" + userId +
               ", userName='" + userName + '\'' +
               ", isMale=" + isMale +
               '}';
    }
}
