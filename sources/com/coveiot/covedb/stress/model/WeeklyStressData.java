package com.coveiot.covedb.stress.model;
/* loaded from: classes8.dex */
public class WeeklyStressData {

    /* renamed from: a  reason: collision with root package name */
    public String f6986a;
    public String mac_address;
    public double stress_avg;
    public int stress_high;
    public int stress_low;
    public String year;

    public String getMacAddress() {
        return this.mac_address;
    }

    public double getStress_avg() {
        return this.stress_avg;
    }

    public int getStress_high() {
        return this.stress_high;
    }

    public int getStress_low() {
        return this.stress_low;
    }

    public String getWeek() {
        return this.f6986a;
    }

    public String getYear() {
        return this.year;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setStress_avg(double d) {
        this.stress_avg = d;
    }

    public void setStress_high(int i) {
        this.stress_high = i;
    }

    public void setStress_low(int i) {
        this.stress_low = i;
    }

    public void setWeek(String str) {
        this.f6986a = str;
    }

    public void setYear(String str) {
        this.year = str;
    }
}
