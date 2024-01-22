package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthHeartRateItem {
    public int HeartRaveValue;
    private long dId;
    private Date date;
    public int day;
    private Long heartRateDataId;
    public int month;
    public int offsetMinute;
    public int year;

    public HealthHeartRateItem() {
    }

    public HealthHeartRateItem(Long l, long j, int i, int i2, int i3, int i4, int i5, Date date) {
        this.heartRateDataId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.offsetMinute = i4;
        this.HeartRaveValue = i5;
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

    public Long getHeartRateDataId() {
        return this.heartRateDataId;
    }

    public int getHeartRaveValue() {
        return this.HeartRaveValue;
    }

    public int getMonth() {
        return this.month;
    }

    public int getOffsetMinute() {
        return this.offsetMinute;
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

    public void setHeartRateDataId(Long l) {
        this.heartRateDataId = l;
    }

    public void setHeartRaveValue(int i) {
        this.HeartRaveValue = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setOffsetMinute(int i) {
        this.offsetMinute = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthHeartRateItem{heartRateDataId=" + this.heartRateDataId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", offsetMinute=" + this.offsetMinute + ", HeartRaveValue=" + this.HeartRaveValue + ", date=" + this.date + '}';
    }
}
