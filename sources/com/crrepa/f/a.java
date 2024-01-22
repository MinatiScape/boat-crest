package com.crrepa.f;

import com.crrepa.ble.conn.listener.CRPA2DPConnectStateListener;
/* loaded from: classes9.dex */
public class a {
    public static byte[] a() {
        return d1.a(-12, null);
    }

    public static byte[] a(CRPA2DPConnectStateListener.A2DPConnectState a2DPConnectState) {
        return d1.a(-11, new byte[]{a2DPConnectState.getValue()});
    }
}
