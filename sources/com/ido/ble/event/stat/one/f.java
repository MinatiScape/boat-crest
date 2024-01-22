package com.ido.ble.event.stat.one;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
class f {

    /* renamed from: a  reason: collision with root package name */
    public static List<com.ido.ble.event.stat.one.faildata.c> f12220a = new ArrayList();

    public static void a() {
        f12220a.clear();
        for (com.ido.ble.event.stat.one.faildata.c cVar : com.ido.ble.event.stat.one.faildata.a.b().a()) {
            if (cVar != null && !TextUtils.isEmpty(cVar.c())) {
                f12220a.add(cVar);
                com.ido.ble.event.stat.one.faildata.b bVar = new com.ido.ble.event.stat.one.faildata.b();
                bVar.c(cVar.a());
                bVar.a(Long.parseLong(cVar.c()));
                e.a(bVar);
            }
        }
    }

    public static void a(com.ido.ble.g.a.b bVar) {
        if (bVar == null || bVar.b() == 0) {
            return;
        }
        com.ido.ble.event.stat.one.faildata.c cVar = new com.ido.ble.event.stat.one.faildata.c();
        cVar.b(bVar.b() + "");
        cVar.a(bVar.a());
        com.ido.ble.event.stat.one.faildata.a.b().a(cVar);
    }

    public static void b(com.ido.ble.g.a.b bVar) {
        if (bVar == null || bVar.b() == 0) {
            return;
        }
        ArrayList<com.ido.ble.event.stat.one.faildata.c> arrayList = new ArrayList();
        arrayList.addAll(f12220a);
        for (com.ido.ble.event.stat.one.faildata.c cVar : arrayList) {
            if (cVar != null && !TextUtils.isEmpty(cVar.c())) {
                String c = cVar.c();
                if (c.equals(bVar.b() + "")) {
                    f12220a.remove(cVar);
                    com.ido.ble.event.stat.one.faildata.a.b().a(cVar.c());
                }
            }
        }
    }
}
