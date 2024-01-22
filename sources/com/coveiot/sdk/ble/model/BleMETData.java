package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class BleMETData {
    public float MET_DATA;
    public float pace_percentage;

    public BleMETData(float f, float f2) {
        this.pace_percentage = f;
        this.MET_DATA = f2;
    }

    public byte[] getDataBytes() {
        return new byte[]{(byte) this.pace_percentage, (byte) (this.MET_DATA * 10.0f)};
    }
}
