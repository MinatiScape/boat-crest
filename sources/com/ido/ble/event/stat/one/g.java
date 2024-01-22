package com.ido.ble.event.stat.one;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
/* loaded from: classes11.dex */
public class g {
    public static com.ido.ble.g.a.a a() {
        return a("");
    }

    public static com.ido.ble.g.a.a a(long j) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.q);
        aVar.a("duration", j + "");
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a a(long j, String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, "dfu");
        aVar.a(d.H, j + "");
        aVar.a(d.B, str);
        aVar.a(d.A, d.P);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a a(long j, String str, String str2, String str3) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.n);
        aVar.a(d.A, d.P);
        aVar.a(d.F, str2);
        aVar.a(d.B, str);
        aVar.a(d.D, j + "");
        if (!TextUtils.isEmpty(b(j))) {
            aVar.a(d.E, b(j));
        }
        aVar.a(d.G, str3);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a a(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.s);
        aVar.a(d.A, d.Q);
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a a(String str, String str2) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, str);
        aVar.a(d.A, d.Q);
        aVar.a(d.B, str2 + a.b());
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a a(boolean z) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.r);
        aVar.a(d.A, z ? d.P : d.Q);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a b() {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.s);
        aVar.a(d.A, d.P);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a b(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.p);
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            str = "手机蓝牙开关关闭";
        }
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }

    private static String b(long j) {
        if (j < 0) {
            return null;
        }
        return j <= 5 ? "0~5s" : j <= 10 ? "5~10s" : j <= 15 ? "10~15s" : j <= 20 ? "15~20s" : j <= 30 ? "20~30s" : j <= 60 ? "30~60s" : j <= 120 ? "60~120s" : j <= 180 ? "120~180s" : ">180s";
    }

    public static com.ido.ble.g.a.a c() {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.m);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a c(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, "connect_failed_cause");
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a d() {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.t);
        aVar.a(d.A, d.P);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a d(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.n);
        aVar.a(d.A, d.Q);
        aVar.a(d.F, str);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a e(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.z);
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a f(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, "dfu");
        aVar.a(d.A, d.Q);
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a g(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.y);
        aVar.a(d.I, str);
        aVar.a(d.J, d.L);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a h(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.y);
        aVar.a(d.I, str);
        aVar.a(d.J, d.K);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a i(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, str);
        aVar.a(d.A, d.P);
        a.a(aVar);
        return aVar;
    }

    public static com.ido.ble.g.a.a j(String str) {
        com.ido.ble.g.a.a aVar = new com.ido.ble.g.a.a();
        aVar.a(d.l, d.t);
        aVar.a(d.A, d.Q);
        aVar.a(d.B, str);
        a.a(aVar);
        return aVar;
    }
}
