package com.crrepa.ble.conn.callback;
/* loaded from: classes9.dex */
public interface CRPDeviceBondStateCallback {
    public static final byte BONDED = 1;
    public static final byte BONDING = 2;
    public static final byte UNBONDED = 0;

    void onBondState(int i);
}
