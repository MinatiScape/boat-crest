package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleNotDisturb {
    public int begin_hour;
    public int begin_minute;
    public int end_hour;
    public int end_minute;
    public int sw;
    public int watch_sw;

    public int getBegin_hour() {
        return this.begin_hour;
    }

    public int getBegin_minute() {
        return this.begin_minute;
    }

    public int getEnd_hour() {
        return this.end_hour;
    }

    public int getEnd_minute() {
        return this.end_minute;
    }

    public int getSw() {
        return this.sw;
    }

    public int getWatch_sw() {
        return this.watch_sw;
    }

    public void setBegin_hour(int i) {
        this.begin_hour = i;
    }

    public void setBegin_minute(int i) {
        this.begin_minute = i;
    }

    public void setEnd_hour(int i) {
        this.end_hour = i;
    }

    public void setEnd_minute(int i) {
        this.end_minute = i;
    }

    public void setSw(int i) {
        this.sw = i;
    }

    public void setWatch_sw(int i) {
        this.watch_sw = i;
    }

    public String toString() {
        return "EABleNotDisturb{sw=" + this.sw + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", watch_sw=" + this.watch_sw + '}';
    }
}
