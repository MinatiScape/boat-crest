package com.google.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes11.dex */
public final class n0 {
    public static final n0 c = new n0();
    public final ConcurrentMap<Class<?>, s0<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final t0 f11747a = new v();

    public static n0 a() {
        return c;
    }

    public <T> void b(T t, q0 q0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        e(t).h(t, q0Var, extensionRegistryLite);
    }

    public s0<?> c(Class<?> cls, s0<?> s0Var) {
        Internal.checkNotNull(cls, "messageType");
        Internal.checkNotNull(s0Var, "schema");
        return this.b.putIfAbsent(cls, s0Var);
    }

    public <T> s0<T> d(Class<T> cls) {
        Internal.checkNotNull(cls, "messageType");
        s0<T> s0Var = (s0<T>) this.b.get(cls);
        if (s0Var == null) {
            s0<T> a2 = this.f11747a.a(cls);
            s0<T> s0Var2 = (s0<T>) c(cls, a2);
            return s0Var2 != null ? s0Var2 : a2;
        }
        return s0Var;
    }

    public <T> s0<T> e(T t) {
        return d(t.getClass());
    }
}
