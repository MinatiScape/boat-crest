package com.ido.ble.dfu.nodic.firmware;

import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
class d {
    public static CheckNewVersionResponse a(String str) {
        LogTool.d(b.f12207a, str);
        try {
            return (CheckNewVersionResponse) k.c(str, CheckNewVersionResponse.class);
        } catch (Exception e) {
            LogTool.b(b.f12207a, e.toString());
            return null;
        }
    }
}
