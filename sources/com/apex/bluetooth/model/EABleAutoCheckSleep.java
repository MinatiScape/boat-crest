package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleAutoCheckSleep {
    public int begin_hour;
    public int begin_minute;
    public int end_hour;
    public int end_minute;
    public int week_cycle_bit;

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

    public int getWeek_cycle_bit() {
        return this.week_cycle_bit;
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

    public void setWeek_cycle_bit(int i) {
        this.week_cycle_bit = i;
    }

    public String toString() {
        return "EABleAutoCheckSleep{week_cycle_bit=" + this.week_cycle_bit + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + '}';
    }
}
