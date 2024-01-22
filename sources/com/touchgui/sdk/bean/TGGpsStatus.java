package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGGpsStatus {
    private int runStatus;
    private int validHours;

    public int getRunStatus() {
        return this.runStatus;
    }

    public int getValidHours() {
        return this.validHours;
    }

    public void setRunStatus(int i) {
        this.runStatus = i;
    }

    public void setValidHours(int i) {
        this.validHours = i;
    }

    public String toString() {
        return "GpsStatus{runStatus=" + this.runStatus + ", validHours=" + this.validHours + '}';
    }
}
