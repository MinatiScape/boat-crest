package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
class e {
    public static void a() {
        LogTool.d(c.f12275a, "clearLog...");
        byte[] bArr = new byte[20];
        bArr[0] = 33;
        bArr[1] = 7;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    public static boolean a(byte[] bArr) {
        return bArr[2] == 85;
    }

    public static void b() {
        LogTool.d(c.f12275a, "getLog...");
        byte[] bArr = new byte[20];
        bArr[0] = 33;
        bArr[1] = 6;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    public static boolean b(byte[] bArr) {
        return bArr[0] == 33 && bArr[1] == 7;
    }

    public static void c() {
        LogTool.d(c.f12275a, "openLog...");
        byte[] bArr = new byte[20];
        bArr[0] = com.crrepa.c.a.K1;
        bArr[1] = -10;
        com.ido.ble.bluetooth.a.b(bArr);
    }

    public static boolean c(byte[] bArr) {
        return bArr[0] == 33 && bArr[1] == 6;
    }

    public static boolean d(byte[] bArr) {
        return bArr[0] == -14 && bArr[1] == -10;
    }
}
