package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class DailyCalorieData {
    public List<CalorieHourlyData> calorieHourlyDataList;
    public String date;
    public int interval;

    public String getDate() {
        return this.date;
    }

    public List<CalorieHourlyData> getHourlyDataList() {
        return this.calorieHourlyDataList;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setHourlyDataList(List<CalorieHourlyData> list) {
        this.calorieHourlyDataList = list;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public String toString() {
        return "CalorieData{date='" + this.date + "', interval=" + this.interval + ", calorieHourlyDataList=" + this.calorieHourlyDataList + '}';
    }
}
