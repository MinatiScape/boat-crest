package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class CalorieAndDistanceGoal {
    public int calorie;
    public int calorie_max;
    public int calorie_min;
    public int distance;
    public long mid_high_time_goal;
    public int walk_goal_time;

    public String toString() {
        return "CalorieAndDistanceGoal{calorie=" + this.calorie + ", distance=" + this.distance + ", mid_high_time_goal=" + this.mid_high_time_goal + ", calorie_min=" + this.calorie_min + ", calorie_max=" + this.calorie_max + ", walk_goal_time=" + this.walk_goal_time + '}';
    }
}
