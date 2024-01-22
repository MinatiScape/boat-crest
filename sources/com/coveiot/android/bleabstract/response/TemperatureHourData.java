package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class TemperatureHourData extends TempHourData {
    public double avgTemperaturePerHour;
    public double maxTemperaturePerHour;
    public double minTemperaturePerHour;

    public double getAvgTemperaturePerHour() {
        return this.avgTemperaturePerHour;
    }

    public double getMaxTemperaturePerHour() {
        return this.maxTemperaturePerHour;
    }

    public double getMinTemperaturePerHour() {
        return this.minTemperaturePerHour;
    }

    public void setAvgTemperaturePerHour(double d) {
        this.avgTemperaturePerHour = d;
    }

    public void setMaxTemperaturePerHour(double d) {
        this.maxTemperaturePerHour = d;
    }

    public void setMinTemperaturePerHour(double d) {
        this.minTemperaturePerHour = d;
    }
}
