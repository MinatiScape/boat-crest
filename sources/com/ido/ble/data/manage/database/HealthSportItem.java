package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthSportItem {
    private static final long serialVersionUID = 1;
    public int activeTime;
    public int calory;
    private long dId;
    private Date date;
    public int day;
    public int distance;
    public int month;
    private Long sportDataId;
    public int stepCount;
    public int year;

    public HealthSportItem() {
    }

    public HealthSportItem(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, Date date) {
        this.sportDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.stepCount = i4;
        this.activeTime = i5;
        this.calory = i6;
        this.distance = i7;
        this.date = date;
    }

    public int getActiveTime() {
        return this.activeTime;
    }

    public int getCalory() {
        return this.calory;
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

    public int getDistance() {
        return this.distance;
    }

    public int getMonth() {
        return this.month;
    }

    public Long getSportDataId() {
        return this.sportDataId;
    }

    public int getStepCount() {
        return this.stepCount;
    }

    public int getYear() {
        return this.year;
    }

    public void setActiveTime(int i) {
        this.activeTime = i;
    }

    public void setCalory(int i) {
        this.calory = i;
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

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSportDataId(Long l) {
        this.sportDataId = l;
    }

    public void setStepCount(int i) {
        this.stepCount = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthSportItem{sportDataId=" + this.sportDataId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", stepCount=" + this.stepCount + ", activeTime=" + this.activeTime + ", calory=" + this.calory + ", distance=" + this.distance + ", date=" + this.date + '}';
    }
}
