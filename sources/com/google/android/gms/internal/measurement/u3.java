package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes8.dex */
public final class u3 {
    public static final u3 c = new u3();
    public final ConcurrentMap<Class<?>, x3<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final y3 f8933a = new j3();

    public static u3 a() {
        return c;
    }

    public final <T> x3<T> b(Class<T> cls) {
        zzkh.c(cls, "messageType");
        x3<T> x3Var = (x3<T>) this.b.get(cls);
        if (x3Var == null) {
            x3Var = this.f8933a.zza(cls);
            zzkh.c(cls, "messageType");
            zzkh.c(x3Var, "schema");
            x3 putIfAbsent = this.b.putIfAbsent(cls, x3Var);
            if (putIfAbsent != null) {
                return putIfAbsent;
            }
        }
        return x3Var;
    }
}
