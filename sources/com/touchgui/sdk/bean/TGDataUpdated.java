package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGDataUpdated {
    private boolean alarmUpdated1;
    private boolean alarmUpdated2;
    private boolean firmwareError;
    private int notifyType;
    private int notifyType2;
    private boolean raiseWristUpdated;
    private boolean requestSyncWeather;
    private int type;

    public int getNotifyType() {
        return this.notifyType;
    }

    public int getNotifyType2() {
        return this.notifyType2;
    }

    public int getType() {
        return this.type;
    }

    public boolean isAlarmUpdated1() {
        return this.alarmUpdated1;
    }

    public boolean isAlarmUpdated2() {
        return this.alarmUpdated2;
    }

    public boolean isFirmwareError() {
        return this.firmwareError;
    }

    public boolean isRaiseWristUpdated() {
        return this.raiseWristUpdated;
    }

    public boolean isRequestSyncWeather() {
        return this.requestSyncWeather;
    }

    public void setAlarmUpdated1(boolean z) {
        this.alarmUpdated1 = z;
    }

    public void setAlarmUpdated2(boolean z) {
        this.alarmUpdated2 = z;
    }

    public void setFirmwareError(boolean z) {
        this.firmwareError = z;
    }

    public void setNotifyType(int i) {
        this.notifyType = i;
    }

    public void setNotifyType2(int i) {
        this.notifyType2 = i;
    }

    public void setRaiseWristUpdated(boolean z) {
        this.raiseWristUpdated = z;
    }

    public void setRequestSyncWeather(boolean z) {
        this.requestSyncWeather = z;
    }

    public void setType(int i) {
        this.type = i;
    }
}
