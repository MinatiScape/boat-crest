package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthBloodPressedItem {
    private Long bloodPressedItemId;
    private long dId;
    private Date date;
    public int day;
    public int dias_blood;
    public int month;
    public int offset;
    public int sys_blood;
    public int year;

    public HealthBloodPressedItem() {
    }

    public HealthBloodPressedItem(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, Date date) {
        this.bloodPressedItemId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.offset = i4;
        this.dias_blood = i5;
        this.sys_blood = i6;
        this.date = date;
    }

    public Long getBloodPressedItemId() {
        return this.bloodPressedItemId;
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

    public int getDias_blood() {
        return this.dias_blood;
    }

    public int getMonth() {
        return this.month;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getSys_blood() {
        return this.sys_blood;
    }

    public int getYear() {
        return this.year;
    }

    public void setBloodPressedItemId(Long l) {
        this.bloodPressedItemId = l;
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

    public void setDias_blood(int i) {
        this.dias_blood = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setSys_blood(int i) {
        this.sys_blood = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthBloodPressedItem{bloodPressedItemId=" + this.bloodPressedItemId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", offset=" + this.offset + ", dias_blood=" + this.dias_blood + ", sys_blood=" + this.sys_blood + ", date=" + this.date + '}';
    }
}
