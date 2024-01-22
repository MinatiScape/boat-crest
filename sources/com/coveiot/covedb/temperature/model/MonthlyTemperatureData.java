package com.coveiot.covedb.temperature.model;
/* loaded from: classes8.dex */
public class MonthlyTemperatureData {

    /* renamed from: a  reason: collision with root package name */
    public String f6990a;
    public String mac_address;
    public double temperature_avg;
    public double temperature_high;
    public double temperature_low;
    public String year;

    public String getMacAddress() {
        return this.mac_address;
    }

    public String getMonth() {
        return this.f6990a;
    }

    public double getTemperature_avg() {
        return this.temperature_avg;
    }

    public double getTemperature_high() {
        return this.temperature_high;
    }

    public double getTemperature_low() {
        return this.temperature_low;
    }

    public String getYear() {
        return this.year;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setMonth(String str) {
        this.f6990a = str;
    }

    public void setTemperature_avg(double d) {
        this.temperature_avg = d;
    }

    public void setTemperature_high(double d) {
        this.temperature_high = d;
    }

    public void setTemperature_low(double d) {
        this.temperature_low = d;
    }

    public void setYear(String str) {
        this.year = str;
    }
}
