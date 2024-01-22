package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthNoise {
    public int avg_noise;
    public int day;
    public int hour;
    public int interval_mode;
    public List<HealthNoiseItem> items;
    public int max_noise;
    public int min_noise;
    public int minute;
    public int month;
    public int second;
    public int start_time;
    public int year;

    public String toString() {
        return "HealthNoise{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", avg_noise=" + this.avg_noise + ", max_noise=" + this.max_noise + ", min_noise=" + this.min_noise + ", interval_mode=" + this.interval_mode + ", start_time=" + this.start_time + ", items=" + this.items + '}';
    }
}
