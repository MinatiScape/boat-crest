package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class HeartRateData implements Serializable {
    public int avgHr;
    public String dwmValue;
    public int maxHr;
    public int minHr;
    public String type;
    public int year;

    public HeartRateData(int i, int i2, int i3, String str) {
        this.maxHr = i2;
        this.minHr = i;
        this.avgHr = i3;
        this.dwmValue = str;
    }

    public float getAvgHr() {
        return this.avgHr;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public String getType() {
        return this.type;
    }

    public int getYear() {
        return this.year;
    }

    public void setAvgHr(int i) {
        this.avgHr = i;
    }

    public void setDwmValue(String str) {
        this.dwmValue = str;
    }

    public void setMaxHr(int i) {
        this.maxHr = i;
    }

    public void setMinHr(int i) {
        this.minHr = i;
    }

    public HeartRateData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
