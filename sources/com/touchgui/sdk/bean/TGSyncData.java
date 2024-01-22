package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGSyncData {
    private int activityCount;
    private int breathTrainCount;
    private int gpsCount;
    private int heartRateCount;
    private int pressCount;
    private int spo2Count;
    private int swimCount;
    private int totalSize;

    public int getActivityCount() {
        return this.activityCount;
    }

    public int getBreathTrainCount() {
        return this.breathTrainCount;
    }

    public int getGpsCount() {
        return this.gpsCount;
    }

    public int getHeartRateCount() {
        return this.heartRateCount;
    }

    public int getPressCount() {
        return this.pressCount;
    }

    public int getSpo2Count() {
        return this.spo2Count;
    }

    public int getSwimCount() {
        return this.swimCount;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setActivityCount(int i) {
        this.activityCount = i;
    }

    public void setBreathTrainCount(int i) {
        this.breathTrainCount = i;
    }

    public void setGpsCount(int i) {
        this.gpsCount = i;
    }

    public void setHeartRateCount(int i) {
        this.heartRateCount = i;
    }

    public void setPressCount(int i) {
        this.pressCount = i;
    }

    public void setSpo2Count(int i) {
        this.spo2Count = i;
    }

    public void setSwimCount(int i) {
        this.swimCount = i;
    }

    public void setTotalSize(int i) {
        this.totalSize = i;
    }

    public String toString() {
        return "SyncData{totalSize=" + this.totalSize + ", activityCount=" + this.activityCount + ", gpsCount=" + this.gpsCount + ", spo2Count=" + this.spo2Count + ", swimCount=" + this.swimCount + ", pressCount=" + this.pressCount + ", heartRateCount=" + this.heartRateCount + '}';
    }
}
