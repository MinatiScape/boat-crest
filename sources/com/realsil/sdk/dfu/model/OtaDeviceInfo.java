package com.realsil.sdk.dfu.model;
/* loaded from: classes12.dex */
public final class OtaDeviceInfo extends DeviceInfo {
    public OtaDeviceInfo() {
    }

    public OtaDeviceInfo(int i) {
        this(0, i);
    }

    public OtaDeviceInfo(int i, int i2) {
        this.protocolType = i;
        setMode(i2);
    }
}
