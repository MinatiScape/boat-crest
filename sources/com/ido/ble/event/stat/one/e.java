package com.ido.ble.event.stat.one;

import android.text.TextUtils;
/* loaded from: classes11.dex */
public class e {
    public static void a() {
        b();
    }

    public static void a(com.ido.ble.g.a.a aVar) {
        b(aVar, null);
    }

    private static void a(com.ido.ble.g.a.a aVar, IEventStatCallBack iEventStatCallBack) {
        a(aVar, "", iEventStatCallBack);
    }

    private static void a(com.ido.ble.g.a.a aVar, String str, IEventStatCallBack iEventStatCallBack) {
        if (TextUtils.isEmpty(str)) {
            str = h.c().b();
            if (TextUtils.isEmpty(str)) {
                str = " no ip ";
            }
        }
        com.ido.ble.g.a.b bVar = new com.ido.ble.g.a.b("IDO_BLE_LIB", str);
        bVar.a(aVar);
        a(bVar, iEventStatCallBack);
    }

    public static void a(com.ido.ble.g.a.b bVar) {
        a(bVar, (IEventStatCallBack) null);
    }

    private static void a(com.ido.ble.g.a.b bVar, IEventStatCallBack iEventStatCallBack) {
    }

    private static void b() {
    }

    public static void b(com.ido.ble.g.a.a aVar, IEventStatCallBack iEventStatCallBack) {
        a(aVar, iEventStatCallBack);
    }
}
