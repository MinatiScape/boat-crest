package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPBleConnectionStateListener {
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;

    void onConnectionStateChange(int i);
}
