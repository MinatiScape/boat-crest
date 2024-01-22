package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.DeviceLogClearPara;
/* loaded from: classes11.dex */
public class e {
    public static void a() {
        u.d(5019);
    }

    public static void a(DeviceLogClearPara deviceLogClearPara) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(deviceLogClearPara)), 173);
    }
}
