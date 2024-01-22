package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class HeartRateHourData extends HourData {
    public int avgHeartRatePerHour;
    public int maxHeartRatePerHour;
    public int minHeartRatePerHour;

    public int getAvgHeartRatePerHour() {
        return this.avgHeartRatePerHour;
    }

    public int getMaxHeartRatePerHour() {
        return this.maxHeartRatePerHour;
    }

    public int getMinHeartRatePerHour() {
        return this.minHeartRatePerHour;
    }

    public void setAvgHeartRatePerHour(int i) {
        this.avgHeartRatePerHour = i;
    }

    public void setMaxHeartRatePerHour(int i) {
        this.maxHeartRatePerHour = i;
    }

    public void setMinHeartRatePerHour(int i) {
        this.minHeartRatePerHour = i;
    }
}
