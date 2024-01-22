package com.google.android.gms.internal.fitness;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes8.dex */
public final class t3 {
    public static final t3 c = new t3();
    public final ConcurrentMap<Class<?>, x3<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final w3 f8852a = new c3();

    public static t3 a() {
        return c;
    }

    public final <T> x3<T> b(Class<T> cls) {
        zzhc.c(cls, "messageType");
        x3<T> x3Var = (x3<T>) this.b.get(cls);
        if (x3Var == null) {
            x3<T> a2 = this.f8852a.a(cls);
            zzhc.c(cls, "messageType");
            zzhc.c(a2, "schema");
            x3<T> x3Var2 = (x3<T>) this.b.putIfAbsent(cls, a2);
            return x3Var2 != null ? x3Var2 : a2;
        }
        return x3Var;
    }

    public final <T> x3<T> c(T t) {
        return b(t.getClass());
    }
}
