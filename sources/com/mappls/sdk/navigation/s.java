package com.mappls.sdk.navigation;

import com.mappls.sdk.navigation.apis.NavigationLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
/* loaded from: classes11.dex */
public abstract class s {
    public static ArrayList b = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    public boolean f12951a;

    public static s a() {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (com.mappls.sdk.navigation.tracks.a.class.isInstance(sVar)) {
                return sVar;
            }
        }
        return null;
    }

    public static ArrayList b() {
        ArrayList arrayList = new ArrayList(b.size());
        Iterator it = b.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.f12951a) {
                arrayList.add(sVar);
            }
        }
        return arrayList;
    }

    public abstract void c();

    public void d() {
    }

    public static void a(NavigationApplication navigationApplication) {
        LinkedHashSet g = navigationApplication.k().g();
        b.add(new com.mappls.sdk.navigation.tracks.a(navigationApplication));
        g.add("navigation.monitoring");
        Iterator it = b.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            sVar.c();
            if (g.contains("navigation.monitoring") || sVar.f12951a) {
                try {
                    sVar.f12951a = true;
                } catch (Exception e) {
                    sVar.c();
                    NavigationLogger.e(e, "Plugin initialization failed %s", "navigation.monitoring");
                }
            }
        }
    }
}
