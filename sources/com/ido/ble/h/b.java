package com.ido.ble.h;

import com.ido.ble.common.k;
import com.ido.ble.custom.MakeGpsFileConfig;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.protocol.handler.u;
/* loaded from: classes11.dex */
class b {
    public static int a() {
        return u.c();
    }

    public static int a(int i) {
        return u.c(i);
    }

    public static int a(ConfigGPS configGPS) {
        return u.b(com.ido.ble.common.c.b(k.a(configGPS)), 155);
    }

    public static int a(ConnParam connParam) {
        return u.b(com.ido.ble.common.c.b(k.a(connParam)), 157);
    }

    public static int a(ControlGps controlGps) {
        return u.b(com.ido.ble.common.c.b(k.a(controlGps)), 156);
    }

    public static int a(GpsHotStartParam gpsHotStartParam) {
        return u.b(com.ido.ble.common.c.b(k.a(gpsHotStartParam)), 158);
    }

    public static int a(byte[] bArr, int i) {
        return u.a(bArr, i);
    }

    public static void a(MakeGpsFileConfig makeGpsFileConfig) {
        u.b(com.ido.ble.common.c.b(k.a(makeGpsFileConfig)), (int) com.veryfit.multi.nativeprotocol.b.d4);
    }

    public static int b() {
        return u.b((int) com.veryfit.multi.nativeprotocol.b.u5, 313);
    }

    public static int c() {
        return u.b((int) com.veryfit.multi.nativeprotocol.b.u5, 312);
    }

    public static int d() {
        return u.b((int) com.veryfit.multi.nativeprotocol.b.u5, 314);
    }

    public static boolean e() {
        return u.f();
    }

    public static int f() {
        return u.q();
    }

    public static int g() {
        return u.s();
    }

    public static int h() {
        return u.z();
    }

    public static int i() {
        return u.C();
    }
}
