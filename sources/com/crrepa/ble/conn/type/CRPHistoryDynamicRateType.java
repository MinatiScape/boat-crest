package com.crrepa.ble.conn.type;
/* loaded from: classes9.dex */
public enum CRPHistoryDynamicRateType {
    FIRST_HEART_RATE((byte) 0),
    SECOND_HEART_RATE((byte) 1),
    THIRD_HEART_RATE((byte) 2);
    
    private byte value;

    CRPHistoryDynamicRateType(byte b) {
        this.value = b;
    }

    public byte getValue() {
        return this.value;
    }
}
