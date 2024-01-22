package com.coveiot.sdk.ble.api.model;

import java.util.List;
/* loaded from: classes9.dex */
public class CalorieHourlyData {
    public float calorieValue;
    public List<Float> codeValues;
    public String date;
    public String endHour;
    public String startHour;

    public Float getCalorieValue() {
        return Float.valueOf(this.calorieValue);
    }

    public List<Float> getCodeValues() {
        return this.codeValues;
    }

    public String getDate() {
        return this.date;
    }

    public String getEndHour() {
        return this.endHour;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public void setCalorieValue(Float f) {
        this.calorieValue = f.floatValue();
    }

    public void setCodeValues(List<Float> list) {
        this.codeValues = list;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setEndHour(String str) {
        this.endHour = str;
    }

    public void setStartHour(String str) {
        this.startHour = str;
    }

    public String toString() {
        return "CalorieHourlyData{date='" + this.date + "', startHour='" + this.startHour + "', endHour='" + this.endHour + "', codeValues=" + this.codeValues + ", calorieValue=" + this.calorieValue + '}';
    }
}
