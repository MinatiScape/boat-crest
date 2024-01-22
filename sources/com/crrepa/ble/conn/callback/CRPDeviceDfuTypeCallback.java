package com.crrepa.ble.conn.callback;
/* loaded from: classes9.dex */
public interface CRPDeviceDfuTypeCallback {
    public static final int TYPE_GR_A = 1;
    public static final int TYPE_GR_B = 2;

    void onDfuType(int i);
}
