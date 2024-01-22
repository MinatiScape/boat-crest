package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGDeviceInfo {
    public static final int BATTERY_STATUS_CHARGING = 1;
    public static final int BATTERY_STATUS_CHARGING_COMPLETED = 2;
    public static final int BATTERY_STATUS_LOW_POWER = 3;
    public static final int BATTERY_STATUS_NORMAL = 0;
    private int batteryStatus;
    private int bindConfirm;
    private int btStatus;
    private int energe;
    private boolean hasDetailVersion;
    private int id;
    private int pairFlag;
    private int platform;
    private int protocolVersion;
    private int rebootFlag;
    private int resFlag;
    private int runMode;
    private int versionCode;

    public int getBatteryStatus() {
        return this.batteryStatus;
    }

    public int getBindConfirm() {
        return this.bindConfirm;
    }

    public int getBtStatus() {
        return this.btStatus;
    }

    public int getEnerge() {
        return this.energe;
    }

    public int getId() {
        return this.id;
    }

    public int getPairFlag() {
        return this.pairFlag;
    }

    public int getPlatform() {
        return this.platform;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public int getRebootFlag() {
        return this.rebootFlag;
    }

    public int getResFlag() {
        return this.resFlag;
    }

    public int getRunMode() {
        return this.runMode;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean isHasDetailVersion() {
        return this.hasDetailVersion;
    }

    public void setBatteryStatus(int i) {
        this.batteryStatus = i;
    }

    public void setBindConfirm(int i) {
        this.bindConfirm = i;
    }

    public void setBtStatus(int i) {
        this.btStatus = i;
    }

    public void setEnerge(int i) {
        this.energe = i;
    }

    public void setHasDetailVersion(boolean z) {
        this.hasDetailVersion = z;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setPairFlag(int i) {
        this.pairFlag = i;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public void setProtocolVersion(int i) {
        this.protocolVersion = i;
    }

    public void setRebootFlag(int i) {
        this.rebootFlag = i;
    }

    public void setResFlag(int i) {
        this.resFlag = i;
    }

    public void setRunMode(int i) {
        this.runMode = i;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }

    public String toString() {
        return "TGDeviceInfo{id=" + this.id + ", versionCode=" + this.versionCode + ", runMode=" + this.runMode + ", batteryStatus=" + this.batteryStatus + ", energe=" + this.energe + ", pairFlag=" + this.pairFlag + ", rebootFlag=" + this.rebootFlag + ", hasDetailVersion=" + this.hasDetailVersion + ", bindConfirm=" + this.bindConfirm + ", platform=" + this.platform + ", resFlag=" + this.resFlag + ", protocolVersion=" + this.protocolVersion + ", btStatus=" + this.btStatus + '}';
    }
}
