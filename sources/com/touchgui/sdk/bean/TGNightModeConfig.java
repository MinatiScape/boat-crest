package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGNightModeConfig {
    private boolean on;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public int getStopHour() {
        return this.stopHour;
    }

    public int getStopMinute() {
        return this.stopMinute;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setOn(boolean z) {
        this.on = z;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setStopHour(int i) {
        this.stopHour = i;
    }

    public void setStopMinute(int i) {
        this.stopMinute = i;
    }
}
