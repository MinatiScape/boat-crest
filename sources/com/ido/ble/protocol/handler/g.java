package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.HornVoice;
/* loaded from: classes11.dex */
final class g {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 577 || i == 580) {
            b(bArr);
        } else if (i != 591) {
        } else {
            a(bArr);
        }
    }

    private static void a(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara != null && deviceChangedPara.dataType == 1) {
            v.a();
        }
    }

    private static void a(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b("DeviceParaChangedHandler", "handleHornJsonData jsonString is null");
            return;
        }
        LogTool.d("DeviceParaChangedHandler", "handleHornJsonData jsonString is " + d);
        com.ido.ble.callback.c.a((HornVoice) new Gson().fromJson(d, (Class<Object>) HornVoice.class));
    }

    public static boolean a(int i) {
        return i == 577 || i == 580 || i == 591;
    }

    private static void b(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b("DeviceParaChangedHandler", "handleJsonData jsonString is null");
            return;
        }
        LogTool.d("DeviceParaChangedHandler", "handleJsonData jsonString is " + d);
        DeviceChangedPara deviceChangedPara = (DeviceChangedPara) new Gson().fromJson(d, (Class<Object>) DeviceChangedPara.class);
        DeviceParaChangedCallBack.onChanged(deviceChangedPara);
        a(deviceChangedPara);
    }
}
