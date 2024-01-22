package com.coveiot.sdk.ble.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class FirmwareCapabilityInfo implements Serializable {
    private byte[] capabilities = new byte[32];

    public byte[] getCapabilities() {
        return this.capabilities;
    }

    public void setCapabilities(byte[] bArr) {
        this.capabilities = bArr;
    }
}
