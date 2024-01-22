package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthGpsV3 {
    public int data_interval;
    public int day;
    public int hour;
    public List<HealthGpsItemV3> items;
    public int minute;
    public int month;
    public int second;
    @Deprecated
    public List<String> stringItems;
    public int year;

    public String toString() {
        return "HealthGpsV3{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", data_interval=" + this.data_interval + ", items=" + this.items + '}';
    }
}
