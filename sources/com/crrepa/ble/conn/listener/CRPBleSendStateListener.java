package com.crrepa.ble.conn.listener;
/* loaded from: classes9.dex */
public interface CRPBleSendStateListener {
    public static final int STATE_FAIL = 2;
    public static final int STATE_SENDING = 0;
    public static final int STATE_SUCCESS = 1;

    void onSendStateChange(int i);
}
