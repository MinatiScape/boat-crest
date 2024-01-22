package com.coveiot.coveaccess.device.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class IOTUserDeviceReq implements Serializable {
    public String appTrackerId;
    private BleDeviceInfo deviceInfo;
    private Boolean registerInDvcMgmt;

    public IOTUserDeviceReq(BleDeviceInfo bleDeviceInfo) {
        this.deviceInfo = bleDeviceInfo;
    }

    public String getAppTrackerId() {
        return this.appTrackerId;
    }

    public BleDeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public Boolean getRegisterInDvcMgmt() {
        return this.registerInDvcMgmt;
    }

    public void setAppTrackerId(String str) {
        this.appTrackerId = str;
    }

    public void setRegisterInDvcMgmt(Boolean bool) {
        this.registerInDvcMgmt = bool;
    }
}
