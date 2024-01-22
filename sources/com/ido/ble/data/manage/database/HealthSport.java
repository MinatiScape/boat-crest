package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthSport {
    private static final long serialVersionUID = 1;
    private long dId;
    private Date date;
    public int day;
    public int keyword;
    public int month;
    private Long sportDataId;
    private int startTime;
    private int timeSpace;
    public int totalActiveTime;
    public int totalCalory;
    public int totalDistance;
    public int totalStepCount;
    public int year;

    public HealthSport() {
    }

    public HealthSport(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Date date) {
        this.sportDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.totalStepCount = i4;
        this.totalCalory = i5;
        this.totalDistance = i6;
        this.totalActiveTime = i7;
        this.startTime = i8;
        this.timeSpace = i9;
        this.date = date;
    }

    public long getDId() {
        return this.dId;
    }

    public Date getDate() {
        return this.date;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public Long getSportDataId() {
        return this.sportDataId;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getTimeSpace() {
        return this.timeSpace;
    }

    public int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public int getTotalCalory() {
        return this.totalCalory;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public int getTotalStepCount() {
        return this.totalStepCount;
    }

    public int getYear() {
        return this.year;
    }

    public void setDId(long j) {
        this.dId = j;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSportDataId(Long l) {
        this.sportDataId = l;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setTimeSpace(int i) {
        this.timeSpace = i;
    }

    public void setTotalActiveTime(int i) {
        this.totalActiveTime = i;
    }

    public void setTotalCalory(int i) {
        this.totalCalory = i;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public void setTotalStepCount(int i) {
        this.totalStepCount = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthSport{sportDataId=" + this.sportDataId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", totalStepCount=" + this.totalStepCount + ", totalCalory=" + this.totalCalory + ", totalDistance=" + this.totalDistance + ", totalActiveTime=" + this.totalActiveTime + ", startTime=" + this.startTime + ", timeSpace=" + this.timeSpace + ", date=" + this.date + '}';
    }
}
