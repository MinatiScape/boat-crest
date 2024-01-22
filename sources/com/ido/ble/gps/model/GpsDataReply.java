package com.ido.ble.gps.model;

import java.util.List;
/* loaded from: classes11.dex */
public class GpsDataReply {
    public int data_interval;
    public long date;
    public int day;
    public int hour;
    public List<String> items;
    public int minute;
    public int month;
    public int second;
    public int year;

    public int getData_interval() {
        return this.data_interval;
    }

    public long getDate() {
        return this.date;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public List<String> getItems() {
        return this.items;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getMonth() {
        return this.month;
    }

    public int getSecond() {
        return this.second;
    }

    public int getYear() {
        return this.year;
    }

    public void setData_interval(int i) {
        this.data_interval = i;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public void setItems(List<String> list) {
        this.items = list;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String toString() {
        return "HealthGpsReply{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", data_interval=" + this.data_interval + ", items=" + this.items + ", date=" + this.date + '}';
    }
}
