package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGUnitConfig {
    public static final int CELSIUS = 1;
    public static final int FAHRENHEIT = 2;
    public static final int HOUR12 = 2;
    public static final int HOUR24 = 1;
    public static final int IMPERIAL_SYSTEM = 2;
    public static final int METRIC_SYSTEM = 1;
    private int distance;
    private int language;
    private int strideRun;
    private int strideWalk;
    private int temp;
    private int timeMode;
    private int weight;

    public int getDistance() {
        return this.distance;
    }

    public int getLanguage() {
        return this.language;
    }

    public int getStrideRun() {
        return this.strideRun;
    }

    public int getStrideWalk() {
        return this.strideWalk;
    }

    public int getTemp() {
        return this.temp;
    }

    public int getTimeMode() {
        return this.timeMode;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setLanguage(int i) {
        this.language = i;
    }

    public void setStrideRun(int i) {
        this.strideRun = i;
    }

    public void setStrideWalk(int i) {
        this.strideWalk = i;
    }

    public void setTemp(int i) {
        this.temp = i;
    }

    public void setTimeMode(int i) {
        this.timeMode = i;
    }

    public void setWeight(int i) {
        this.weight = i;
    }
}
