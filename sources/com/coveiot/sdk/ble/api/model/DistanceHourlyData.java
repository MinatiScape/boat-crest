package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class DistanceHourlyData {
    public List<Integer> codeValues;
    public String date;
    public int distanceValue;
    public String endHour;
    public String startHour;

    public List<Integer> getCodeValues() {
        return this.codeValues;
    }

    public String getDate() {
        return this.date;
    }

    public int getDistanceValue() {
        return this.distanceValue;
    }

    public String getEndHour() {
        return this.endHour;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public void setCodeValues(List<Integer> list) {
        this.codeValues = list;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setDistanceValue(int i) {
        this.distanceValue = i;
    }

    public void setEndHour(String str) {
        this.endHour = str;
    }

    public void setStartHour(String str) {
        this.startHour = str;
    }

    public String toString() {
        return "DistanceHourlyData{date='" + this.date + "', startHour='" + this.startHour + "', endHour='" + this.endHour + "', codeValues=" + this.codeValues + ", distanceValue=" + this.distanceValue + '}';
    }
}
