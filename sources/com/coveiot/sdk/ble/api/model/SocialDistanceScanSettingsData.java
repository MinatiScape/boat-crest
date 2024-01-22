package com.coveiot.sdk.ble.api.model;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public class SocialDistanceScanSettingsData {
    private String addressFilter;
    private int rssiFilter;
    private int scanInterval;
    private int scanPeriod;
    private int scanTimeout;
    private int scanWindow;
    private int uuidFilter;

    public String getAddressFilter() {
        return this.addressFilter;
    }

    public int getRssiFilter() {
        return this.rssiFilter;
    }

    public int getScanInterval() {
        return this.scanInterval;
    }

    public int getScanPeriod() {
        return this.scanPeriod;
    }

    public int getScanTimeout() {
        return this.scanTimeout;
    }

    public int getScanWindow() {
        return this.scanWindow;
    }

    public int getUuidFilter() {
        return this.uuidFilter;
    }

    public void setAddressFilter(String str) {
        this.addressFilter = str;
    }

    public void setRssiFilter(int i) {
        this.rssiFilter = i;
    }

    public void setScanInterval(int i) {
        this.scanInterval = i;
    }

    public void setScanPeriod(int i) {
        this.scanPeriod = i;
    }

    public void setScanTimeout(int i) {
        this.scanTimeout = i;
    }

    public void setScanWindow(int i) {
        this.scanWindow = i;
    }

    public void setUuidFilter(int i) {
        this.uuidFilter = i;
    }

    @NonNull
    public String toString() {
        return "scanPeriod: " + this.scanPeriod + ", rssiFilter:" + this.rssiFilter + ", addressFilter:" + this.addressFilter + ", uuidFilter:" + this.uuidFilter + ", scanInterval:" + this.scanInterval + ", scanWindow:" + this.scanWindow + ", scanTimeout:" + this.scanTimeout;
    }
}
