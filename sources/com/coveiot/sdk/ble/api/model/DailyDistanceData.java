package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class DailyDistanceData {
    public String date;
    public List<DistanceHourlyData> distanceHourlyDataList;
    public int interval;

    public String getDate() {
        return this.date;
    }

    public List<DistanceHourlyData> getDistanceHourlyDataList() {
        return this.distanceHourlyDataList;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDistanceHourlyDataList(List<DistanceHourlyData> list) {
        this.distanceHourlyDataList = list;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public String toString() {
        return "DailyDistanceData{date='" + this.date + "', interval=" + this.interval + ", distanceHourlyDataList=" + this.distanceHourlyDataList + '}';
    }
}
