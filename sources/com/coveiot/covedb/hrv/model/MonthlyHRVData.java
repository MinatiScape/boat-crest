package com.coveiot.covedb.hrv.model;
/* loaded from: classes8.dex */
public class MonthlyHRVData {

    /* renamed from: a  reason: collision with root package name */
    public String f6956a;
    public double baseline;
    public double hrv_avg;
    public double hrv_high;
    public double hrv_low;
    public String mac_address;
    public String year;

    public double getBaseline() {
        return this.baseline;
    }

    public double getHrv_avg() {
        return this.hrv_avg;
    }

    public double getHrv_high() {
        return this.hrv_high;
    }

    public double getHrv_low() {
        return this.hrv_low;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public String getMonth() {
        return this.f6956a;
    }

    public String getYear() {
        return this.year;
    }

    public void setBaseline(double d) {
        this.baseline = d;
    }

    public void setHrv_avg(double d) {
        this.hrv_avg = d;
    }

    public void setHrv_high(double d) {
        this.hrv_high = d;
    }

    public void setHrv_low(double d) {
        this.hrv_low = d;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setMonth(String str) {
        this.f6956a = str;
    }

    public void setYear(String str) {
        this.year = str;
    }
}
