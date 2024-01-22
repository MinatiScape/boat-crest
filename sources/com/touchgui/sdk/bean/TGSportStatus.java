package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGSportStatus {
    private boolean needAppGpsData;
    private int sportType;

    public int getSportType() {
        return this.sportType;
    }

    public boolean isNeedAppGpsData() {
        return this.needAppGpsData;
    }

    public void setNeedAppGpsData(boolean z) {
        this.needAppGpsData = z;
    }

    public void setSportType(int i) {
        this.sportType = i;
    }

    public String toString() {
        return "TGSportStatus{sportType=" + this.sportType + ", needAppGpsData=" + this.needAppGpsData + '}';
    }
}
