package com.crrepa.ble.conn.callback;
/* loaded from: classes9.dex */
public interface CRPDeviceDfuStatusCallback {
    public static final int DEVICE_STATUS_DFU = 1;
    public static final int DEVICE_STATUS_NORMAL = 0;
    public static final int DEVICE_STATUS_NULL = 2;

    void onDeviceDfuStatus(int i);
}
