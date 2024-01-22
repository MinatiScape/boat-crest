package com.ido.ble.data.manage.database;

import java.util.Date;
/* loaded from: classes11.dex */
public class HealthBloodPressed {
    private Long bloodPressedId;
    private long dId;
    private Date date;
    public int day;
    public int max_bp;
    @Deprecated
    private int minute_offset;
    public int month;
    public int sleep_avg_bp;
    public int year;

    public HealthBloodPressed() {
    }

    public HealthBloodPressed(Long l, long j, int i, int i2, int i3, int i4, int i5, int i6, Date date) {
        this.bloodPressedId = l;
        this.dId = j;
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.max_bp = i4;
        this.minute_offset = i5;
        this.sleep_avg_bp = i6;
        this.date = date;
    }

    public Long getBloodPressedId() {
        return this.bloodPressedId;
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

    public int getMax_bp() {
        return this.max_bp;
    }

    public int getMinute_offset() {
        return this.minute_offset;
    }

    public int getMonth() {
        return this.month;
    }

    public int getSleep_avg_bp() {
        return this.sleep_avg_bp;
    }

    public int getYear() {
        return this.year;
    }

    public void setBloodPressedId(Long l) {
        this.bloodPressedId = l;
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

    public void setMax_bp(int i) {
        this.max_bp = i;
    }

    public void setMinute_offset(int i) {
        this.minute_offset = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSleep_avg_bp(int i) {
        this.sleep_avg_bp = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthBloodPressed{bloodPressedId=" + this.bloodPressedId + ", dId=" + this.dId + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", max_bp=" + this.max_bp + ", minute_offset=" + this.minute_offset + ", sleep_avg_bp=" + this.sleep_avg_bp + ", date=" + this.date + '}';
    }
}
