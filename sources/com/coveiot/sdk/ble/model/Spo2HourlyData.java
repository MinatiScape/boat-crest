package com.coveiot.sdk.ble.model;

import java.util.ArrayList;
/* loaded from: classes9.dex */
public class Spo2HourlyData extends HourlyData {
    public double avgSpo2PerHour;
    public int maxSpo2PerHour;
    public int minSpo2PerHour;
    public ArrayList<Integer> spo2MinuteData = new ArrayList<>();

    public double getAvgSpo2PerHour() {
        this.avgSpo2PerHour = 0.0d;
        if (this.spo2MinuteData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.spo2MinuteData.size(); i2++) {
                this.avgSpo2PerHour += this.spo2MinuteData.get(i2).intValue();
                if (this.spo2MinuteData.get(i2).intValue() != 0) {
                    i++;
                }
            }
            if (i > 0) {
                this.avgSpo2PerHour /= i;
            } else {
                this.avgSpo2PerHour = 0.0d;
            }
        }
        return this.avgSpo2PerHour;
    }

    public int getMaxSpo2PerHour() {
        if (this.spo2MinuteData.size() > 0) {
            this.maxSpo2PerHour = this.spo2MinuteData.get(0).intValue();
            for (int i = 0; i < this.spo2MinuteData.size(); i++) {
                if (this.maxSpo2PerHour < this.spo2MinuteData.get(i).intValue()) {
                    this.maxSpo2PerHour = this.spo2MinuteData.get(i).intValue();
                }
            }
        }
        return this.maxSpo2PerHour;
    }

    public int getMinSpo2PerHour() {
        if (this.spo2MinuteData.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.spo2MinuteData.size()) {
                    break;
                } else if (this.spo2MinuteData.get(i).intValue() > 0) {
                    this.minSpo2PerHour = this.spo2MinuteData.get(i).intValue();
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < this.spo2MinuteData.size(); i2++) {
                if (this.minSpo2PerHour > this.spo2MinuteData.get(i2).intValue() && this.spo2MinuteData.get(i2).intValue() != 0) {
                    this.minSpo2PerHour = this.spo2MinuteData.get(i2).intValue();
                }
            }
        }
        return this.minSpo2PerHour;
    }

    public ArrayList<Integer> getSpo2MinuteData() {
        return this.spo2MinuteData;
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

    public void setSpo2MinuteData(ArrayList<Integer> arrayList) {
        this.spo2MinuteData = arrayList;
    }
}
