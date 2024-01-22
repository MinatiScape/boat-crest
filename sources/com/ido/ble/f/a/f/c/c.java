package com.ido.ble.f.a.f.c;

import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class c {
    public static void a() {
        if (com.ido.ble.f.a.f.b.e().d()) {
            return;
        }
        if (b.O().N()) {
            LogTool.d("OldSpDataMigrateHandler", "start handle ...");
            b();
            LogTool.d("OldSpDataMigrateHandler", "handle end.");
        }
        com.ido.ble.f.a.f.b.e().a(true);
    }

    private static void a(String str) {
        com.ido.ble.bluetooth.f.c.g().f(str);
        com.ido.ble.bluetooth.f.c.g().a(a.e().d());
        com.ido.ble.bluetooth.f.c.g().c(a.e().b());
        com.ido.ble.bluetooth.f.c.g().d(a.e().c());
    }

    private static void b() {
        BLEDevice s = b.O().s();
        if (s == null) {
            return;
        }
        com.ido.ble.f.a.f.b.e().a(s);
        if (TextUtils.isEmpty(s.mDeviceAddress)) {
            return;
        }
        b(s.mDeviceAddress);
        a(s.mDeviceAddress);
    }

    private static void b(String str) {
        com.ido.ble.f.a.f.a.g0().e(str);
        com.ido.ble.f.a.f.a.g0().a(b.O().K());
        com.ido.ble.f.a.f.a.g0().a(b.O().H());
        com.ido.ble.f.a.f.a.g0().a(b.O().c());
        com.ido.ble.f.a.f.a.g0().a(b.O().m());
        com.ido.ble.f.a.f.a.g0().a(b.O().o());
        com.ido.ble.f.a.f.a.g0().a(b.O().g());
        com.ido.ble.f.a.f.a.g0().a(b.O().I());
        com.ido.ble.f.a.f.a.g0().a(b.O().f());
        com.ido.ble.f.a.f.a.g0().a(b.O().e());
        com.ido.ble.f.a.f.a.g0().a(b.O().v());
        com.ido.ble.f.a.f.a.g0().a(b.O().l());
        com.ido.ble.f.a.f.a.g0().a(b.O().q());
        com.ido.ble.f.a.f.a.g0().a(b.O().p());
        com.ido.ble.f.a.f.a.g0().a(b.O().J());
        com.ido.ble.f.a.f.a.g0().a(b.O().z());
        com.ido.ble.f.a.f.a.g0().b(b.O().y());
        com.ido.ble.f.a.f.a.g0().a(b.O().k());
        com.ido.ble.f.a.f.a.g0().c(b.O().A());
        com.ido.ble.f.a.f.a.g0().d(b.O().L());
        com.ido.ble.f.a.f.a.g0().a(b.O().B());
        com.ido.ble.f.a.f.a.g0().a(b.O().j());
        com.ido.ble.f.a.f.a.g0().a(b.O().D());
        com.ido.ble.f.a.f.a.g0().a(b.O().E());
        com.ido.ble.f.a.f.a.g0().a(b.O().u());
        com.ido.ble.f.a.f.a.g0().d(b.O().t());
        com.ido.ble.f.a.f.a.g0().d(b.O().G());
        com.ido.ble.f.a.f.a.g0().b(b.O().r());
        com.ido.ble.f.a.f.a.g0().a(b.O().h());
        com.ido.ble.f.a.f.a.g0().a(b.O().n());
        com.ido.ble.f.a.f.a.g0().c(b.O().C());
        com.ido.ble.f.a.f.a.g0().e(com.ido.ble.f.a.f.a.g0().f0());
        com.ido.ble.f.a.f.a.g0().a(b.O().w());
        com.ido.ble.f.a.f.a.g0().a(b.O().x());
        com.ido.ble.f.a.f.a.g0().a(b.O().i());
        com.ido.ble.f.a.f.a.g0().a(b.O().F());
    }
}
