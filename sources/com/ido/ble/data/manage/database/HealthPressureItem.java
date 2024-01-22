package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthPressureItem {
    public Long Id;
    public long dId;
    public Date date;
    public int day;
    public int month;
    public int offset;
    public int value;
    public int year;

    public HealthPressureItem() {
    }

    public HealthPressureItem(Long l, long j, int i, int i2, int i3, int i4, int i5, Date date) {
        this.Id = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.offset = i4;
        this.value = i5;
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

    public int getOffset() {
        return this.offset;
    }

    public int getValue() {
        return this.value;
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

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
