package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class TGDevice {
    private String address;
    private int battery;
    private boolean forceOTA;
    @Nullable
    private String name;
    private boolean paired = false;
    private int resFlag;
    private int versionCode;

    public String getAddress() {
        return this.address;
    }

    public int getBattery() {
        return this.battery;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    public int getResFlag() {
        return this.resFlag;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean isForceOTA() {
        return this.forceOTA;
    }

    public boolean isPaired() {
        return this.paired;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public void setForceOTA(boolean z) {
        this.forceOTA = z;
    }

    public void setName(@Nullable String str) {
        this.name = str;
    }

    public void setPaired(boolean z) {
        this.paired = z;
    }

    public void setResFlag(int i) {
        this.resFlag = i;
    }

    public void setVersionCode(int i) {
        this.versionCode = i;
    }
}
