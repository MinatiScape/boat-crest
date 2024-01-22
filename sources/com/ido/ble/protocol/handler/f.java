package com.ido.ble.protocol.handler;

import com.ido.ble.callback.DeviceLogCallBack;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class f {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 173) {
            a(bArr, i2);
        } else if (i != 5019) {
        } else {
            b(bArr, i2);
        }
    }

    private static void a(byte[] bArr, int i) {
        String str;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            str = "[GET_PARA] [handleClearHeatLogStatus] clear failed";
        } else {
            str = "[GET_PARA] [handleClearHeatLogStatus] jsonString is " + com.ido.ble.common.c.d(bArr);
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, str);
    }

    public static boolean a(int i) {
        return i == 173 || i == 5019;
    }

    private static void b(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceParaCallBack.onGetSupportSportInfoV3(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleHeatLog] jsonString is " + d);
        DeviceLogCallBack.a(d);
    }
}
