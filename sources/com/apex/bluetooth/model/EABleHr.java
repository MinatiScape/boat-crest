package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleHr {
    public int max_hr;
    public int min_hr;
    public int sw;

    public int getMax_hr() {
        return this.max_hr;
    }

    public int getMin_hr() {
        return this.min_hr;
    }

    public int getSw() {
        return this.sw;
    }

    public void setMax_hr(int i) {
        this.max_hr = i;
    }

    public void setMin_hr(int i) {
        this.min_hr = i;
    }

    public void setSw(int i) {
        this.sw = i;
    }

    public String toString() {
        return "EABleHr{sw=" + this.sw + ", max_hr=" + this.max_hr + ", min_hr=" + this.min_hr + '}';
    }
}
