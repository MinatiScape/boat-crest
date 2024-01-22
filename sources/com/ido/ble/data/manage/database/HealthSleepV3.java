package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthSleepV3 {
    public int awrr_status;
    public int breath_quality;
    public int data_type;
    public int deep_count;
    public int deep_mins;
    public int fall_asleep_day;
    public int fall_asleep_hour;
    public int fall_asleep_minte;
    public int fall_asleep_month;
    public int fall_asleep_year;
    public int get_up_day;
    public int get_up_hour;
    public int get_up_minte;
    public int get_up_month;
    public int get_up_year;
    public List<HealthSleepV3Item> items;
    public int light_count;
    public int light_mins;
    public int rem_count;
    public int rem_mins;
    public int sleep_score;
    public int total_sleep_time_mins;
    public int wake_count;
    public int wake_mins;

    public String toString() {
        return "HealthSleepV3{data_type=" + this.data_type + ", fall_asleep_year=" + this.fall_asleep_year + ", fall_asleep_month=" + this.fall_asleep_month + ", fall_asleep_day=" + this.fall_asleep_day + ", fall_asleep_hour=" + this.fall_asleep_hour + ", fall_asleep_minte=" + this.fall_asleep_minte + ", get_up_year=" + this.get_up_year + ", get_up_month=" + this.get_up_month + ", get_up_day=" + this.get_up_day + ", get_up_hour=" + this.get_up_hour + ", get_up_minte=" + this.get_up_minte + ", total_sleep_time_mins=" + this.total_sleep_time_mins + ", wake_mins=" + this.wake_mins + ", light_mins=" + this.light_mins + ", rem_mins=" + this.rem_mins + ", deep_mins=" + this.deep_mins + ", wake_count=" + this.wake_count + ", light_count=" + this.light_count + ", rem_count=" + this.rem_count + ", deep_count=" + this.deep_count + ", awrr_status=" + this.awrr_status + ", sleep_score=" + this.sleep_score + ", breath_quality=" + this.breath_quality + ", items=" + this.items + '}';
    }
}
