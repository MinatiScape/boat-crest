package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.BloodPressureMeasurePara;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class b {
    @Deprecated
    public static void a() {
    }

    @Deprecated
    public static void a(long j, long j2) {
    }

    public static void b() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 502, 0, 0);
    }

    public static void c() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 500, 0, 0);
    }

    @Deprecated
    public static void d() {
    }

    public static void e() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 503, 0, 0);
    }

    public static void f() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 501, 0, 0);
    }

    @Deprecated
    public static void g() {
    }

    public static void h() {
        BloodPressureMeasurePara bloodPressureMeasurePara = new BloodPressureMeasurePara();
        bloodPressureMeasurePara.flag = 3;
        u.b(com.ido.ble.common.c.d(new Gson().toJson(bloodPressureMeasurePara)), 127);
    }

    @Deprecated
    public static void i() {
    }

    public static void j() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 504, 0, 0);
        LogTool.d(com.ido.ble.logs.a.f12307a, "CmdAppControlDeviceWrapper->startFindDevice()");
    }

    public static void k() {
        BloodPressureMeasurePara bloodPressureMeasurePara = new BloodPressureMeasurePara();
        bloodPressureMeasurePara.flag = 1;
        u.b(com.ido.ble.common.c.d(new Gson().toJson(bloodPressureMeasurePara)), 127);
    }

    public static void l() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 505, 0, 0);
    }

    public static void m() {
        BloodPressureMeasurePara bloodPressureMeasurePara = new BloodPressureMeasurePara();
        bloodPressureMeasurePara.flag = 2;
        u.b(com.ido.ble.common.c.d(new Gson().toJson(bloodPressureMeasurePara)), 127);
    }
}
