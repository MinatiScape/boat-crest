package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class StressData implements Serializable {
    public String dwmValue;
    public Double stressAvg;
    public int stressLow;
    public int stressMax;
    public String type;
    public int year;

    public StressData(int i, int i2, Double d, String str) {
        this.stressMax = i2;
        this.stressLow = i;
        this.stressAvg = d;
        this.dwmValue = str;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public Double getStressAvg() {
        return this.stressAvg;
    }

    public int getStressLow() {
        return this.stressLow;
    }

    public int getStressMax() {
        return this.stressMax;
    }

    public String getType() {
        return this.type;
    }

    public int getYear() {
        return this.year;
    }

    public void setDwmValue(String str) {
        this.dwmValue = str;
    }

    public void setStressAvg(Double d) {
        this.stressAvg = d;
    }

    public void setStressLow(int i) {
        this.stressLow = i;
    }

    public void setStressMax(int i) {
        this.stressMax = i;
    }

    public StressData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
