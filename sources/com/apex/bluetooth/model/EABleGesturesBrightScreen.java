package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleGesturesBrightScreen {
    public int begin_hour;
    public int begin_minute;
    public BrightScreenSwitch brightScreenSwitch;
    public int end_hour;
    public int end_minute;

    /* loaded from: classes.dex */
    public enum BrightScreenSwitch {
        close(0),
        all_day(1),
        select_time(2);
        
        public int value;

        BrightScreenSwitch(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getBegin_hour() {
        return this.begin_hour;
    }

    public int getBegin_minute() {
        return this.begin_minute;
    }

    public BrightScreenSwitch getBrightScreenSwitch() {
        return this.brightScreenSwitch;
    }

    public int getEnd_hour() {
        return this.end_hour;
    }

    public int getEnd_minute() {
        return this.end_minute;
    }

    public void setBegin_hour(int i) {
        this.begin_hour = i;
    }

    public void setBegin_minute(int i) {
        this.begin_minute = i;
    }

    public void setBrightScreenSwitch(BrightScreenSwitch brightScreenSwitch) {
        this.brightScreenSwitch = brightScreenSwitch;
    }

    public void setEnd_hour(int i) {
        this.end_hour = i;
    }

    public void setEnd_minute(int i) {
        this.end_minute = i;
    }

    public String toString() {
        return "EABleGesturesBrightScreen{brightScreenSwitch=" + this.brightScreenSwitch + ", begin_hour=" + this.begin_hour + ", begin_minute=" + this.begin_minute + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + '}';
    }
}
