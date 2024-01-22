package com.coveiot.android.bleabstract.models;

import java.util.List;
/* loaded from: classes2.dex */
public class CalorieHourlyData {

    /* renamed from: a  reason: collision with root package name */
    public String f3403a;
    public String b;
    public String c;
    public List<Float> d;
    public Float e;

    public CalorieHourlyData(String str, String str2, String str3, List<Float> list, Float f) {
        this.f3403a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
        this.e = f;
    }

    public Float getCalorieValue() {
        return this.e;
    }

    public List<Float> getCodeValues() {
        return this.d;
    }

    public String getDate() {
        return this.f3403a;
    }

    public String getEndHour() {
        return this.c;
    }

    public String getStartHour() {
        return this.b;
    }

    public void setCalorieValue(Float f) {
        this.e = f;
    }

    public void setCodeValues(List<Float> list) {
        this.d = list;
    }

    public void setDate(String str) {
        this.f3403a = str;
    }

    public void setEndHour(String str) {
        this.c = str;
    }

    public void setStartHour(String str) {
        this.b = str;
    }
}
