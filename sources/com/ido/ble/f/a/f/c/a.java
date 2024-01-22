package com.ido.ble.f.a.f.c;

import android.content.Context;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
@Deprecated
/* loaded from: classes11.dex */
class a extends d {
    private static final String c = "BLE_CONNECT_CONFIG";
    private static final String d = "bind_device_address";
    private static final String e = "is_bind";
    private static final String f = "bind_auth";
    private static a g;

    private a() {
    }

    public static final a e() {
        if (g == null) {
            a aVar = new a();
            g = aVar;
            aVar.a(e.a());
        }
        return g;
    }

    public void a(Context context) {
        super.a(context, c);
    }

    public void a(boolean z) {
        b(e, z);
    }

    public String b() {
        return a(f, "");
    }

    public String c() {
        return a(d, "");
    }

    public void c(String str) {
        b(f, str);
    }

    public void d(String str) {
        b(d, str);
    }

    public boolean d() {
        return a(e, false);
    }
}
