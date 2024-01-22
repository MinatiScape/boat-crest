package com.crrepa.ble.conn.type;
/* loaded from: classes9.dex */
public enum CRPBloodOxygenTimeType {
    TODAY(0),
    YESTERDAY(4);
    
    private int value;

    CRPBloodOxygenTimeType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
