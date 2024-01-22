package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class Spo2HourData extends Spo2HourBaseData {
    public double avgSpo2PerHour;
    public int maxSpo2PerHour;
    public int minSpo2PerHour;

    public double getAvgSpo2PerHour() {
        return this.avgSpo2PerHour;
    }

    public int getMaxSpo2PerHour() {
        return this.maxSpo2PerHour;
    }

    public int getMinSpo2PerHour() {
        return this.minSpo2PerHour;
    }

    public void setAvgSpo2PerHour(double d) {
        this.avgSpo2PerHour = d;
    }

    public void setMaxSpo2PerHour(int i) {
        this.maxSpo2PerHour = i;
    }

    public void setMinSpo2PerHour(int i) {
        this.minSpo2PerHour = i;
    }
}
