package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleSedentariness {
    public int begin_hour;
    public int begin_minute;
    public int end_hour;
    public int end_minute;
    public int interval;
    public int noon_begin_hour;
    public int noon_begin_minute;
    public int noon_end_hour;
    public int noon_end_minute;
    public int noon_sw;
    public int step_threshold;
    public int sw;
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

    public int getInterval() {
        return this.interval;
    }

    public int getNoon_begin_hour() {
        return this.noon_begin_hour;
    }

    public int getNoon_begin_minute() {
        return this.noon_begin_minute;
    }

    public int getNoon_end_hour() {
        return this.noon_end_hour;
    }

    public int getNoon_end_minute() {
        return this.noon_end_minute;
    }

    public int getNoon_sw() {
        return this.noon_sw;
    }

    public int getStep_threshold() {
        return this.step_threshold;
    }

    public int getSw() {
        return this.sw;
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

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setNoon_begin_hour(int i) {
        this.noon_begin_hour = i;
    }

    public void setNoon_begin_minute(int i) {
        this.noon_begin_minute = i;
    }

    public void setNoon_end_hour(int i) {
        this.noon_end_hour = i;
    }

    public void setNoon_end_minute(int i) {
        this.noon_end_minute = i;
    }

    public void setNoon_sw(int i) {
        this.noon_sw = i;
    }

    public void setStep_threshold(int i) {
        this.step_threshold = i;
    }

    public void setSw(int i) {
        this.sw = i;
    }

    public void setWeek_cycle_bit(int i) {
        this.week_cycle_bit = i;
    }

    public String toString() {
        return "EABleSedentariness{interval=" + this.interval + ", week_cycle_bit=" + this.week_cycle_bit + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", step_threshold=" + this.step_threshold + ", sw=" + this.sw + '}';
    }
}
