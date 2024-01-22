package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGWashConfig {
    private boolean enable;
    private int interval;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    public int getInterval() {
        return this.interval;
    }

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

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setInterval(int i) {
        this.interval = i;
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
