package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class PeriodicSpo2Data implements Serializable {
    public String dwmValue;
    public Double spo2Avg;
    public Integer spo2High;
    public Integer spo2Low;
    public String type;
    public int year;

    public PeriodicSpo2Data(Integer num, Integer num2, Double d, String str) {
        this.spo2High = num2;
        this.spo2Low = num;
        this.spo2Avg = d;
        this.dwmValue = str;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public Double getSpo2Avg() {
        return this.spo2Avg;
    }

    public Integer getSpo2High() {
        return this.spo2High;
    }

    public Integer getSpo2Low() {
        return this.spo2Low;
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

    public void setSpo2Avg(Double d) {
        this.spo2Avg = d;
    }

    public void setSpo2High(Integer num) {
        this.spo2High = num;
    }

    public void setSpo2Low(Integer num) {
        this.spo2Low = num;
    }

    public PeriodicSpo2Data setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
