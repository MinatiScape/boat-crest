package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SRegisterDeviceRequest implements Serializable {
    private SDeviceInfo deviceInfo;
    private long endUserId;

    public SDeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public long getEndUserId() {
        return this.endUserId;
    }

    public void setDeviceInfo(SDeviceInfo sDeviceInfo) {
        this.deviceInfo = sDeviceInfo;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }
}
