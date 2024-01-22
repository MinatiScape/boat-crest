package com.coveiot.android.leonardo.dashboard.health.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class TemperatureData implements Serializable {
    public String dwmValue;
    public Double temperatureAvg;
    public Double temperatureHigh;
    public Double temperatureLow;
    public String type;
    public int year;

    public TemperatureData(Double d, Double d2, Double d3, String str) {
        this.temperatureHigh = d2;
        this.temperatureLow = d;
        this.temperatureAvg = d3;
        this.dwmValue = str;
    }

    public String getDwmValue() {
        return this.dwmValue;
    }

    public Double getTemperatureAvg() {
        return this.temperatureAvg;
    }

    public Double getTemperatureHigh() {
        return this.temperatureHigh;
    }

    public Double getTemperatureLow() {
        return this.temperatureLow;
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

    public void setTemperatureAvg(Double d) {
        this.temperatureAvg = d;
    }

    public void setTemperatureHigh(Double d) {
        this.temperatureHigh = d;
    }

    public void setTemperatureLow(Double d) {
        this.temperatureLow = d;
    }

    public TemperatureData setType(String str) {
        this.type = str;
        return this;
    }

    public void setYear(int i) {
        this.year = i;
    }
}
