package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleAgps {
    public byte[] agpsData;
    public AgpsType agpsType;

    /* loaded from: classes.dex */
    public enum AgpsType {
        beidou,
        galileo,
        gps,
        glonass,
        qzss
    }

    public byte[] getAgpsData() {
        return this.agpsData;
    }

    public AgpsType getAgpsType() {
        return this.agpsType;
    }

    public void setAgpsData(byte[] bArr) {
        this.agpsData = bArr;
    }

    public void setAgpsType(AgpsType agpsType) {
        this.agpsType = agpsType;
    }
}
