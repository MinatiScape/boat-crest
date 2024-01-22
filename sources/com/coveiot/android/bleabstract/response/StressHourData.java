package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class StressHourData extends HourData {
    public int g;
    public int h;
    public double i;

    public double getAvgStress() {
        return this.i;
    }

    public int getMaxStress() {
        return this.h;
    }

    public int getMinStress() {
        return this.g;
    }

    public void setAvgStress(double d) {
        this.i = d;
    }

    public void setMaxStress(int i) {
        this.h = i;
    }

    public void setMinStress(int i) {
        this.g = i;
    }
}
