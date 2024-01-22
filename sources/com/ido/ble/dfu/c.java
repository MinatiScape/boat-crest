package com.ido.ble.dfu;

import com.ido.ble.protocol.model.BasicInfo;
/* loaded from: classes11.dex */
public class c {
    public static void a() {
        BasicInfo h = com.ido.ble.f.a.f.a.g0().h();
        if (h == null || h.platform != 10) {
            com.ido.ble.dfu.e.a.d().a();
        }
    }

    public static void a(BleDFUConfig bleDFUConfig) {
        if (bleDFUConfig.getPlatform() == -1) {
            BasicInfo h = com.ido.ble.f.a.f.a.f(bleDFUConfig.getMacAddress()).h();
            if (h != null && h.platform == 10) {
                return;
            }
        } else if (bleDFUConfig.getPlatform() != 0) {
            bleDFUConfig.getPlatform();
            return;
        }
        com.ido.ble.dfu.e.a.d().a(bleDFUConfig);
    }

    public static boolean b() {
        return com.ido.ble.dfu.e.a.d().b();
    }
}
