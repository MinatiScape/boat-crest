package com.google.android.gms.internal.vision;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes10.dex */
public final class k3 {
    public static final k3 c = new k3();
    public final ConcurrentMap<Class<?>, o3<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final r3 f9988a = new u2();

    public static k3 b() {
        return c;
    }

    public final <T> o3<T> a(Class<T> cls) {
        zzgt.c(cls, "messageType");
        o3<T> o3Var = (o3<T>) this.b.get(cls);
        if (o3Var == null) {
            o3<T> a2 = this.f9988a.a(cls);
            zzgt.c(cls, "messageType");
            zzgt.c(a2, "schema");
            o3<T> o3Var2 = (o3<T>) this.b.putIfAbsent(cls, a2);
            return o3Var2 != null ? o3Var2 : a2;
        }
        return o3Var;
    }

    public final <T> o3<T> c(T t) {
        return a(t.getClass());
    }
}
