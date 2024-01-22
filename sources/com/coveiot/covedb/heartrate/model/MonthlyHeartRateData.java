package com.coveiot.covedb.heartrate.model;
/* loaded from: classes8.dex */
public class MonthlyHeartRateData {

    /* renamed from: a  reason: collision with root package name */
    public String f6951a;
    public int avg_heart_rate;
    public String mac_address;
    public int max_heart_rate;
    public int min_heart_rate;
    public String year;

    public int getAvgHeartRate() {
        return this.avg_heart_rate;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public int getMaxHeartRate() {
        return this.max_heart_rate;
    }

    public int getMinHeartRate() {
        return this.min_heart_rate;
    }

    public String getMonth() {
        return this.f6951a;
    }

    public void setAvgHeartRate(int i) {
        this.avg_heart_rate = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setMaxHeartRate(int i) {
        this.max_heart_rate = i;
    }

    public void setMinHeartRate(int i) {
        this.min_heart_rate = i;
    }

    public void setMonth(String str) {
        this.f6951a = str;
    }
}
