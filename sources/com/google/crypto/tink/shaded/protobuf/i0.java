package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes10.dex */
public final class i0 {
    public static final i0 c = new i0();
    public final ConcurrentMap<Class<?>, n0<?>> b = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    public final o0 f10982a = new t();

    public static i0 a() {
        return c;
    }

    public <T> void b(T t, l0 l0Var, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        e(t).h(t, l0Var, extensionRegistryLite);
    }

    public n0<?> c(Class<?> cls, n0<?> n0Var) {
        Internal.b(cls, "messageType");
        Internal.b(n0Var, "schema");
        return this.b.putIfAbsent(cls, n0Var);
    }

    public <T> n0<T> d(Class<T> cls) {
        Internal.b(cls, "messageType");
        n0<T> n0Var = (n0<T>) this.b.get(cls);
        if (n0Var == null) {
            n0<T> a2 = this.f10982a.a(cls);
            n0<T> n0Var2 = (n0<T>) c(cls, a2);
            return n0Var2 != null ? n0Var2 : a2;
        }
        return n0Var;
    }

    public <T> n0<T> e(T t) {
        return d(t.getClass());
    }
}
