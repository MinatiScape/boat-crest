package com.ido.ble.event.stat.one;

import android.content.Context;
/* loaded from: classes11.dex */
class h extends com.ido.ble.common.d {
    private static final String c = "LOG_STAT_PARAS";
    private static final String d = "last_ip";
    private static h e;

    public static final h c() {
        if (e == null) {
            h hVar = new h();
            e = hVar;
            hVar.a(com.ido.ble.common.e.a());
        }
        return e;
    }

    public void a(Context context) {
        super.a(context, c);
    }

    public String b() {
        return a(d, "");
    }

    public void c(String str) {
        b(d, str);
    }
}
