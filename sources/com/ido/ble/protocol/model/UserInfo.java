package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class UserInfo implements Serializable {
    public static final int FEMALE = 1;
    public static final int MALE = 0;
    private static final long serialVersionUID = 1;
    public int day;
    public int gender;
    public int height;
    public int month;
    public int weight;
    public int year;

    public UserInfo cloneNew() {
        UserInfo userInfo = new UserInfo();
        userInfo.day = this.day;
        userInfo.month = this.month;
        userInfo.year = this.year;
        userInfo.gender = this.gender;
        userInfo.height = this.height;
        userInfo.weight = this.weight;
        return userInfo;
    }

    public String toString() {
        return "UserInfo{height=" + this.height + ", weight=" + this.weight + ", gender=" + this.gender + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + '}';
    }
}
