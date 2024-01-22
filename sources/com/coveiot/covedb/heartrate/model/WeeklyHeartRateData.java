package com.coveiot.covedb.heartrate.model;
/* loaded from: classes8.dex */
public class WeeklyHeartRateData {

    /* renamed from: a  reason: collision with root package name */
    public String f6952a;
    public int avg_heart_rate;
    public String mac_address;
    public int maxHeartRate;
    public int minHeartRate;
    public String year;

    public int getAvgHeartRate() {
        return this.avg_heart_rate;
    }

    public String getMacAddress() {
        return this.mac_address;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public int getMinHeartRate() {
        return this.minHeartRate;
    }

    public String getWeek() {
        return this.f6952a;
    }

    public void setAvgHeartRate(int i) {
        this.avg_heart_rate = i;
    }

    public void setMacAddress(String str) {
        this.mac_address = str;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public void setMinHeartRate(int i) {
        this.minHeartRate = i;
    }

    public void setWeek(String str) {
        this.f6952a = str;
    }
}
