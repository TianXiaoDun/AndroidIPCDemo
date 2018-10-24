package com.txd.androidipcdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <com.txd.androidipcdemo.bean>
 * Created by XiaoDun on 2018/10/23.
 * Feature:
 */

public class UserParcelable implements Parcelable {
    private int userId;
    private String userName;
    private boolean isMale;
    
    public UserParcelable(int userId, String userName, boolean isMale) {
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
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(userName);
        parcel.writeInt(isMale ? 1 : 0);
    }
    
    public static final Parcelable.Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel parcel) {
            return new UserParcelable(parcel);
        }
        
        @Override
        public UserParcelable[] newArray(int i) {
            return new UserParcelable[i];
        }
    };
    
    private UserParcelable(Parcel parcel) {
        userId = parcel.readInt();
        userName = parcel.readString();
        isMale = parcel.readInt() == 1;
    }
    
    @Override
    public String toString() {
        return "UserParcelable{" +
               "userId=" + userId +
               ", userName='" + userName + '\'' +
               ", isMale=" + isMale +
               '}';
    }
}
