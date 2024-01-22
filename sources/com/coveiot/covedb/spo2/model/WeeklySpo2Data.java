package com.coveiot.covedb.spo2.model;
/* loaded from: classes8.dex */
public class WeeklySpo2Data {

    /* renamed from: a  reason: collision with root package name */
    public String f6980a;
    public String mac_address;
    public double spo2_avg;
    public double spo2_high;
    public double spo2_low;
    public String year;

    public String getMacAddress() {
        return this.mac_address;
    }

    public double getSpo2_avg() {
        return this.spo2_avg;
    }

    public double getSpo2_high() {
        return this.spo2_high;
    }

    public double getSpo2_low() {
        return this.spo2_low;
    }

    public String getWeek() {
        return this.f6980a;
    }

    public String getYear() {
        return this.year;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setSpo2_avg(double d) {
        this.spo2_avg = d;
    }

    public void setSpo2_high(double d) {
        this.spo2_high = d;
    }

    public void setSpo2_low(double d) {
        this.spo2_low = d;
    }

    public void setWeek(String str) {
        this.f6980a = str;
    }

    public void setYear(String str) {
        this.year = str;
    }
}
