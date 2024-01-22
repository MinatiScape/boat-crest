package com.crrepa.t;

import com.crrepa.ble.trans.upgrade.bean.FirmwareVersionInfo;
import com.crrepa.u.b;
/* loaded from: classes9.dex */
public class a {
    public static String a() {
        return b.b().a(com.crrepa.u.a.f7842a, "");
    }

    public static void a(FirmwareVersionInfo firmwareVersionInfo) {
        b b = b.b();
        b.b(com.crrepa.u.a.c, firmwareVersionInfo.getVersion());
        b.b(com.crrepa.u.a.b, firmwareVersionInfo.getUrl());
        b.b(com.crrepa.u.a.d, firmwareVersionInfo.getMd5());
        if (firmwareVersionInfo.getTp_bin() > 0) {
            b.b(com.crrepa.u.a.e, firmwareVersionInfo.getTp_bin_path());
            b.b(com.crrepa.u.a.f, firmwareVersionInfo.getTp_bin_md5());
            b.b(com.crrepa.u.a.g, firmwareVersionInfo.getTp_bin_offset());
        }
    }

    public static String b() {
        return b.b().a(com.crrepa.u.a.d, "");
    }

    public static void b(String str) {
        c(str);
    }

    public static String c() {
        return b.b().a(com.crrepa.u.a.b, "");
    }

    public static void c(String str) {
        b.b().b(com.crrepa.u.a.f7842a, str);
    }

    public static String d() {
        return b.b().a(com.crrepa.u.a.c, "");
    }

    public static String e() {
        return b.b().a(com.crrepa.u.a.f, "");
    }

    public static int f() {
        return b.b().a(com.crrepa.u.a.g, 0);
    }

    public static String g() {
        return b.b().a(com.crrepa.u.a.e, "");
    }
}
