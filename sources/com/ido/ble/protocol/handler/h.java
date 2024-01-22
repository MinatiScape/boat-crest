package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class h {
    public static void a(int i, int i2, int i3, int i4) {
    }

    public static void a(byte[] bArr, int i, int i2) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i2)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_SET_COMMON] [handleCallBackJsonData] err = " + i2);
            DeviceResponseCommonCallBack.onResponse(i, null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_SET_COMMON] [handleCallBackJsonData] json is null");
            DeviceResponseCommonCallBack.onResponse(i, null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_SET_COMMON] [handleCallBackJsonData] json is" + d);
        DeviceResponseCommonCallBack.onResponse(i, d);
    }
}
