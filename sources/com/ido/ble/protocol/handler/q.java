package com.ido.ble.protocol.handler;

import com.ido.ble.callback.RebootCallback;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
final class q {
    public static void a(int i, int i2, int i3) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device reboot] error = " + i2);
        RebootCallback.a(i2 == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    public static void a(int i, byte[] bArr, int i2) {
    }

    public static boolean a(int i) {
        return i == 403;
    }
}
