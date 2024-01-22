package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.logs.LogTool;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
final class j {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SET_PARA] [EnterDfuModeHandler] json is null");
            EnterDfuModeCallback.a(EnterDfuModeCallback.DfuError.PARA_OTHER);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [EnterDfuModeHandler] json is" + d);
        int i3 = -1;
        try {
            i3 = new JSONObject(d).optInt("err_flag");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SET_PARA] [EnterDfuModeHandler]" + e.getMessage());
        }
        if (i3 == 0) {
            EnterDfuModeCallback.a();
        } else {
            EnterDfuModeCallback.a(i3 == 1 ? EnterDfuModeCallback.DfuError.LOW_BATTERY : i3 == 2 ? EnterDfuModeCallback.DfuError.DEVICE_NOT_SUPPORT : i3 == 3 ? EnterDfuModeCallback.DfuError.PARA_ERROR : EnterDfuModeCallback.DfuError.PARA_OTHER);
        }
    }

    public static boolean a(int i) {
        return i == 400 || i == 401;
    }
}
