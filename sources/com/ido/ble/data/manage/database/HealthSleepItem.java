package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthSleepItem {
    private long dId;
    private Date date;
    public int day;
    public int month;
    public int offsetMinute;
    private Long sleepDataId;
    public int sleepStatus;
    public int year;

    public HealthSleepItem() {
    }

    public HealthSleepItem(Long l, long j, int i, int i2, int i3, int i4, int i5, Date date) {
        this.sleepDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.offsetMinute = i4;
        this.sleepStatus = i5;
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

    public int getOffsetMinute() {
        return this.offsetMinute;
    }

    public Long getSleepDataId() {
        return this.sleepDataId;
    }

    public int getSleepStatus() {
        return this.sleepStatus;
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

    public void setOffsetMinute(int i) {
        this.offsetMinute = i;
    }

    public void setSleepDataId(Long l) {
        this.sleepDataId = l;
    }

    public void setSleepStatus(int i) {
        this.sleepStatus = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthSleepItem{, dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", offsetMinute=" + this.offsetMinute + ", sleepStatus=" + this.sleepStatus + ", date=" + this.date + '}';
    }
}
