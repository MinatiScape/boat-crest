package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGSportStatusEvent {
    public static final int EVENT_PAUSE = 3;
    public static final int EVENT_READY = 1;
    public static final int EVENT_RESUME = 5;
    public static final int EVENT_START = 2;
    public static final int EVENT_STOP = 4;
    private int event;
    private boolean needAppGpsData;
    private int sportType;

    public int getEvent() {
        return this.event;
    }

    public int getSportType() {
        return this.sportType;
    }

    public boolean isNeedAppGpsData() {
        return this.needAppGpsData;
    }

    public void setEvent(int i) {
        this.event = i;
    }

    public void setNeedAppGpsData(boolean z) {
        this.needAppGpsData = z;
    }

    public void setSportType(int i) {
        this.sportType = i;
    }

    public String toString() {
        return "TGSportStatusEvent{event=" + this.event + ", sportType=" + this.sportType + ", needAppGpsData=" + this.needAppGpsData + '}';
    }
}
