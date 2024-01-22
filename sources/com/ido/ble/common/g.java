package com.ido.ble.common;

import android.text.TextUtils;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12149a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;

    private static String a() {
        long j;
        String str = com.ido.ble.f.a.f.b.e().c().mDeviceAddress;
        if (TextUtils.isEmpty(str)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] getCurrentDeviceUniqueId:macAddress is null.");
            return "";
        }
        try {
            j = Long.parseLong(str.replaceAll("[a-zA-Z:]", ""));
        } catch (Exception e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.c + e.getMessage());
            j = -1L;
        }
        if (j == -1) {
            return "";
        }
        return j + "";
    }

    public static void a(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(com.ido.ble.f.a.f.a.g0().j() + i2);
    }

    public static void b() {
        if (!e.c()) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] is not support fast sync, reset all offset!");
            f();
            g();
            return;
        }
        if (c()) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] isNeedRestOffset = true, reset all offset!");
            f();
        }
        g();
        if (d()) {
            com.ido.ble.i.a.a.p0();
        }
        com.ido.ble.f.a.f.a.g0().a(System.currentTimeMillis());
        com.ido.ble.f.a.f.a.g0().d(a());
    }

    public static void b(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().b(com.ido.ble.f.a.f.a.g0().A() + i2);
    }

    public static void c(int i, int i2) {
        if (h.a(i)) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().d(com.ido.ble.f.a.f.a.g0().Y() + i2);
    }

    private static boolean c() {
        long D = com.ido.ble.f.a.f.a.g0().D();
        return (e() && D != 0 && TimeUtil.isSameDay(D, System.currentTimeMillis())) ? false : true;
    }

    private static boolean d() {
        if (System.currentTimeMillis() - com.ido.ble.f.a.f.a.g0().D() > 7200000) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] The time space of last sync health data was more than 2 hours");
            return true;
        }
        return false;
    }

    private static boolean e() {
        String C = com.ido.ble.f.a.f.a.g0().C();
        return !TextUtils.isEmpty(C) && C.equals(a());
    }

    private static void f() {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] reset all offset!");
        com.ido.ble.f.a.f.a.g0().d(0);
        com.ido.ble.f.a.f.a.g0().b(0);
        com.ido.ble.f.a.f.a.g0().a(0);
    }

    private static void g() {
        com.ido.ble.i.a.a.a(0, com.ido.ble.f.a.f.a.g0().Y());
        com.ido.ble.i.a.a.a(2, com.ido.ble.f.a.f.a.g0().A());
        com.ido.ble.i.a.a.a(3, com.ido.ble.f.a.f.a.g0().j());
    }
}
