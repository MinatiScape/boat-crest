package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class HRVData implements Serializable {
    public String dwmValue;
    public Double hrvAvg;
    public Double hrvBaseLine;
    public Double hrvLow;
    public Double hrvMax;
    public String type;
    public int year;

    public HRVData(Double d, Double d2, Double d3, Double d4, String str) {
        this.hrvMax = d2;
        this.hrvLow = d;
        this.hrvAvg = d3;
        this.dwmValue = str;
        this.hrvBaseLine = d4;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public Double getHrvAvg() {
        return this.hrvAvg;
    }

    public Double getHrvBaseLine() {
        return this.hrvBaseLine;
    }

    public Double getHrvLow() {
        return this.hrvLow;
    }

    public Double getHrvMax() {
        return this.hrvMax;
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

    public void setHrvAvg(Double d) {
        this.hrvAvg = d;
    }

    public void setHrvLow(Double d) {
        this.hrvLow = d;
    }

    public void setHrvMax(Double d) {
        this.hrvMax = d;
    }

    public HRVData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
