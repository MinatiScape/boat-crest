package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class DailyCalorieData {

    /* renamed from: a  reason: collision with root package name */
    public String f3423a;
    public int b;
    public List<CalorieHourlyData> c;

    public String getDate() {
        return this.f3423a;
    }

    public List<CalorieHourlyData> getHourlyDataList() {
        return this.c;
    }

    public int getInterval() {
        return this.b;
    }

    public void setDate(String str) {
        this.f3423a = str;
    }

    public void setHourlyDataList(List<CalorieHourlyData> list) {
        this.c = list;
    }

    public void setInterval(int i) {
        this.b = i;
    }

    public String toString() {
        return "CalorieData{date='" + this.f3423a + "', interval=" + this.b + ", hourlyDataList=" + this.c + '}';
    }
}
