package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGHeartRateConfig {
    public static final int INTERVAL_05 = 5;
    public static final int INTERVAL_10 = 10;
    public static final int INTERVAL_20 = 20;
    public static final int INTERVAL_30 = 30;
    public static final int MODE_AUTO = 136;
    public static final int MODE_CLOSE = 85;
    public static final int MODE_MANUAL = 170;
    private boolean enableMax;
    private boolean enableMin;
    private boolean hasRange;
    private int interval;
    private int maxHr;
    private int minHr;
    private int mode;
    private int range1 = 98;
    private int range2 = 151;
    private int range3 = 138;
    private int range4 = 158;
    private int range5 = 177;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;

    public int getInterval() {
        return this.interval;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public int getMode() {
        return this.mode;
    }

    public int getRange1() {
        return this.range1;
    }

    public int getRange2() {
        return this.range2;
    }

    public int getRange3() {
        return this.range3;
    }

    public int getRange4() {
        return this.range4;
    }

    public int getRange5() {
        return this.range5;
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

    public boolean isEnableMax() {
        return this.enableMax;
    }

    public boolean isEnableMin() {
        return this.enableMin;
    }

    public boolean isHasRange() {
        return this.hasRange;
    }

    public void setEnableMax(boolean z) {
        this.enableMax = z;
    }

    public void setEnableMin(boolean z) {
        this.enableMin = z;
    }

    public void setHasRange(boolean z) {
        this.hasRange = z;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setMaxHr(int i) {
        this.maxHr = i;
    }

    public void setMinHr(int i) {
        this.minHr = i;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setRange1(int i) {
        this.range1 = i;
    }

    public void setRange2(int i) {
        this.range2 = i;
    }

    public void setRange3(int i) {
        this.range3 = i;
    }

    public void setRange4(int i) {
        this.range4 = i;
    }

    public void setRange5(int i) {
        this.range5 = i;
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
