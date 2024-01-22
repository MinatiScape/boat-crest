package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthTemperature {
    public int avg_temperature;
    public int day;
    public int hour;
    public List<HealthTemperatureItem> items;
    public int max_temperature;
    public int min_temperature;
    public int minute;
    public int month;
    public int sec;
    public int start_time;
    public int year;

    public String toString() {
        return "HealthTemperature{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", sec=" + this.sec + ", start_time=" + this.start_time + ", avg_temperature=" + this.avg_temperature + ", max_temperature=" + this.max_temperature + ", min_temperature=" + this.min_temperature + ", items=" + this.items + '}';
    }
}
