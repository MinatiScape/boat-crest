package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthPressure {
    private Long Id;
    private long dId;
    public Date date;
    public int day;
    public int month;
    public int startTime;
    public int year;

    public HealthPressure() {
    }

    public HealthPressure(Long l, long j, int i, int i2, int i3, int i4, Date date) {
        this.Id = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.startTime = i4;
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

    public Long getId() {
        return this.Id;
    }

    public int getMonth() {
        return this.month;
    }

    public int getStartTime() {
        return this.startTime;
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

    public void setId(Long l) {
        this.Id = l;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
