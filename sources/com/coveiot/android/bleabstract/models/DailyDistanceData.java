package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class DailyDistanceData {

    /* renamed from: a  reason: collision with root package name */
    public String f3424a;
    public int b;
    public List<DistanceHourlyData> c;

    public String getDate() {
        return this.f3424a;
    }

    public List<DistanceHourlyData> getDistanceHourlyDataList() {
        return this.c;
    }

    public int getInterval() {
        return this.b;
    }

    public void setDate(String str) {
        this.f3424a = str;
    }

    public void setDistanceHourlyDataList(List<DistanceHourlyData> list) {
        this.c = list;
    }

    public void setInterval(int i) {
        this.b = i;
    }
}
