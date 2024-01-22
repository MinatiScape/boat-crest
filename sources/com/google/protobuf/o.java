package com.google.protobuf;
/* loaded from: classes11.dex */
public final class o {

    /* renamed from: a  reason: collision with root package name */
    public static final l<?> f11748a = new n();
    public static final l<?> b = c();

    public static l<?> a() {
        l<?> lVar = b;
        if (lVar != null) {
            return lVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static l<?> b() {
        return f11748a;
    }

    public static l<?> c() {
        try {
            int i = m.b;
            return (l) m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
