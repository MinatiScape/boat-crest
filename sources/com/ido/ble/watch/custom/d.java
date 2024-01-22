package com.ido.ble.watch.custom;

import com.ido.ble.common.k;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.watch.custom.model.WatchPlateFileMakeConfig;
import com.ido.ble.watch.custom.model.WatchPlateOperation;
/* loaded from: classes11.dex */
public class d {
    public static void a() {
        u.d(5006);
    }

    public static void a(WatchPlateFileMakeConfig watchPlateFileMakeConfig) {
        u.b(com.ido.ble.common.c.b(k.a(watchPlateFileMakeConfig)), 5009);
    }

    public static void a(WatchPlateOperation watchPlateOperation) {
        u.b(com.ido.ble.common.c.b(k.a(watchPlateOperation)), 5008);
    }

    public static void b() {
        u.d(com.veryfit.multi.nativeprotocol.b.J3);
    }

    public static void c() {
        u.d(5007);
    }
}
