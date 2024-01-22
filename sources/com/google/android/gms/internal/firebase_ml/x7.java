package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes7.dex */
public final class x7 {
    public static final x7 c = new x7();
    public final ConcurrentMap<Class<?>, c8<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final f8 f8750a = new g7();

    public static x7 c() {
        return c;
    }

    public final <T> c8<T> a(T t) {
        return b(t.getClass());
    }

    public final <T> c8<T> b(Class<T> cls) {
        zzxd.b(cls, "messageType");
        c8<T> c8Var = (c8<T>) this.b.get(cls);
        if (c8Var == null) {
            c8<T> a2 = this.f8750a.a(cls);
            zzxd.b(cls, "messageType");
            zzxd.b(a2, "schema");
            c8<T> c8Var2 = (c8<T>) this.b.putIfAbsent(cls, a2);
            return c8Var2 != null ? c8Var2 : a2;
        }
        return c8Var;
    }
}
