package com.ido.ble.h;

import com.ido.ble.custom.MakeGpsFileConfig;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
/* loaded from: classes11.dex */
public class a {
    public static int a() {
        return b.a();
    }

    public static int a(int i) {
        return b.a(i);
    }

    public static int a(byte[] bArr, int i) {
        return b.a(bArr, i);
    }

    public static void a(MakeGpsFileConfig makeGpsFileConfig) {
        b.a(makeGpsFileConfig);
    }

    public static void a(ConfigGPS configGPS) {
        b.a(configGPS);
    }

    public static void a(ConnParam connParam) {
        b.a(connParam);
    }

    public static void a(ControlGps controlGps) {
        b.a(controlGps);
    }

    public static void a(GpsHotStartParam gpsHotStartParam) {
        b.a(gpsHotStartParam);
    }

    public static void b() {
        b.b();
    }

    public static void c() {
        b.c();
    }

    public static void d() {
        b.d();
    }

    public static boolean e() {
        return b.e();
    }

    public static void f() {
        ConfigGPS v = com.ido.ble.f.a.f.a.g0().v();
        if (v == null) {
            v = new ConfigGPS();
            v.startMode = 2;
            v.operationMode = 1;
            v.cycleMS = 1000;
            v.gnsValue = 1;
        }
        a(v);
    }

    public static void g() {
        LogTool.d(com.ido.ble.logs.a.j, "start sync gps data...");
        GpsCallBack.g();
        if (b.f() != 0) {
            GpsCallBack.e();
        }
    }

    public static int h() {
        return b.g();
    }

    public static void i() {
        LogTool.d(com.ido.ble.logs.a.j, "stop sync gps data...");
        u.x();
    }

    public static int j() {
        return b.h();
    }

    public static int k() {
        return b.i();
    }
}
