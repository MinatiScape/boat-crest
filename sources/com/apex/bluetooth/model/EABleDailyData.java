package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDailyData implements Comparable<EABleDailyData> {
    public int average_heart_rate;
    public int calorie;
    public int distance;
    public int duration;
    public int steps;
    public long time_stamp;

    public int getAverage_heart_rate() {
        return this.average_heart_rate;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getSteps() {
        return this.steps;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setAverage_heart_rate(int i) {
        this.average_heart_rate = i;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABleDailyData{time_stamp=" + this.time_stamp + ", steps=" + this.steps + ", calorie=" + this.calorie + ", distance=" + this.distance + ", duration=" + this.duration + ", average_heart_rate=" + this.average_heart_rate + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleDailyData eABleDailyData) {
        if (eABleDailyData != null) {
            if (getTime_stamp() > eABleDailyData.getTime_stamp()) {
                return 1;
            }
            return getTime_stamp() == eABleDailyData.getTime_stamp() ? 0 : -1;
        }
        return 0;
    }
}
