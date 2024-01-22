package com.ido.ble.bluetooth.f;

import android.content.Context;
import android.text.TextUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class c extends com.ido.ble.common.d {
    private static final String d = "bind_info_";
    private static final String e = "bind_device_address";
    private static final String f = "is_bind";
    private static final String g = "bind_auth";
    private static final String h = "encrypted_auth";
    private static final String i = "default";
    private static c j;
    private String c;

    private c() {
    }

    public static c g() {
        if (j == null) {
            BLEDevice c = com.ido.ble.f.a.f.b.e().c();
            j = new c();
            if (c == null || TextUtils.isEmpty(c.mDeviceAddress)) {
                j.a(com.ido.ble.common.e.a(), "default");
            } else {
                j.a(com.ido.ble.common.e.a(), c.mDeviceAddress);
            }
            LogTool.d("DeviceSharedPreferences", "sp_name = " + j.c);
        }
        return j;
    }

    public static c g(String str) {
        c cVar = new c();
        cVar.a(com.ido.ble.common.e.a(), str);
        LogTool.d("DeviceSharedPreferences", "[createInstanceWithMacAddress] sp_name = " + cVar.c);
        return cVar;
    }

    @Override // com.ido.ble.common.d
    public void a(Context context, String str) {
        String str2 = d + str;
        this.c = str2;
        super.a(context, str2);
    }

    public void a(boolean z) {
        LogTool.d("DeviceSharedPreferences", "setIsBind" + z + ",sp_name = " + j.c);
        b(f, z);
    }

    public void b() {
        LogTool.d("DeviceSharedPreferences", "clearDataIfUnbind" + j.c);
        b(f);
        b(g);
        b(e);
    }

    public String c() {
        return a(g, "");
    }

    public void c(String str) {
        LogTool.d("DeviceSharedPreferences", "setBindAuth" + str + ",sp_name = " + j.c);
        b(g, str);
    }

    public String d() {
        return a(e, "");
    }

    public void d(String str) {
        b(e, str);
    }

    public String e() {
        LogTool.d("DeviceSharedPreferences", "getEncryptedAuth,sp_name = " + j.c);
        return a(h, "");
    }

    public void e(String str) {
        LogTool.d("DeviceSharedPreferences", "setEncryptedAuth" + str + ",sp_name = " + j.c);
        b(h, str);
    }

    public void f(String str) {
        j.a(com.ido.ble.common.e.a(), str);
        LogTool.d("DeviceSharedPreferences", "switchToDevice = " + j.c);
    }

    public boolean f() {
        return a(f, false);
    }
}
