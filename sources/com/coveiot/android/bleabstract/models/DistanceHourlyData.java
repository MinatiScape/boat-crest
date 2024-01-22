package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class DistanceHourlyData {

    /* renamed from: a  reason: collision with root package name */
    public String f3426a;
    public String b;
    public String c;
    public List<Integer> d;
    public int e;

    public DistanceHourlyData(String str, String str2, String str3, List<Integer> list, int i) {
        this.f3426a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = i;
    }

    public List<Integer> getCodeValues() {
        return this.d;
    }

    public String getDate() {
        return this.f3426a;
    }

    public int getDistanceValue() {
        return this.e;
    }

    public String getEndHour() {
        return this.c;
    }

    public String getStartHour() {
        return this.b;
    }

    public void setCodeValues(List<Integer> list) {
        this.d = list;
    }

    public void setDate(String str) {
        this.f3426a = str;
    }

    public void setDistanceValue(int i) {
        this.e = i;
    }

    public void setEndHour(String str) {
        this.c = str;
    }

    public void setStartHour(String str) {
        this.b = str;
    }
}
