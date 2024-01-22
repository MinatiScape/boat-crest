package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGStressConfig {
    public static final int INTERVAL_05 = 5;
    public static final int INTERVAL_10 = 10;
    public static final int INTERVAL_20 = 20;
    public static final int INTERVAL_30 = 30;
    public static final int MODE_AUTO = 136;
    public static final int MODE_CLOSE = 0;
    private boolean hasRange;
    private int interval;
    private int mode;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    public int getInterval() {
        return this.interval;
    }

    public int getMode() {
        return this.mode;
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

    public void setHasRange(boolean z) {
        this.hasRange = z;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setMode(int i) {
        this.mode = i;
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
