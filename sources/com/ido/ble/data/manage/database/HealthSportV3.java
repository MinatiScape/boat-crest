package com.ido.ble.data.manage.database;

import java.util.List;
/* loaded from: classes11.dex */
public class HealthSportV3 {
    public int day;
    public int hour;
    public int item_count;
    public List<HealthSportV3Item> items;
    public int minute;
    public int month;
    public int per_minute;
    public int second;
    public int start_time;
    public int total_active_time;
    public int total_activity_calories;
    public int total_distances;
    public int total_rest_activity_calories;
    public int total_step;
    public List<Integer> type;
    public int walk_goal_time;
    public List<Integer> wear_flag_array;
    public int year;

    public String toString() {
        return "HealthSportV3{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", start_time=" + this.start_time + ", per_minute=" + this.per_minute + ", total_step=" + this.total_step + ", total_rest_activity_calories=" + this.total_rest_activity_calories + ", total_distances=" + this.total_distances + ", total_active_time=" + this.total_active_time + ", total_activity_calories=" + this.total_activity_calories + ", item_count=" + this.item_count + ", wear_flag_array=" + this.wear_flag_array + ", type=" + this.type + ", items=" + this.items + ", walk_goal_time=" + this.walk_goal_time + '}';
    }
}
