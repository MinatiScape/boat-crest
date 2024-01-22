package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGRaiseWristConfig {
    private boolean hasRange;
    private boolean on;
    private int showSeconds;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    @Deprecated
    public int getShowSeconds() {
        return this.showSeconds;
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

    public boolean isHasRange() {
        return this.hasRange;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setHasRange(boolean z) {
        this.hasRange = z;
    }

    public void setOn(boolean z) {
        this.on = z;
    }

    @Deprecated
    public void setShowSeconds(int i) {
        this.showSeconds = i;
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
