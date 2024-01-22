package com.google.android.gms.internal.clearcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes7.dex */
public final class m1 {
    public static final m1 c = new m1();

    /* renamed from: a  reason: collision with root package name */
    public final s1 f8588a;
    public final ConcurrentMap<Class<?>, r1<?>> b = new ConcurrentHashMap();

    public m1() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        s1 s1Var = null;
        for (int i = 0; i <= 0; i++) {
            s1Var = c(strArr[0]);
            if (s1Var != null) {
                break;
            }
        }
        this.f8588a = s1Var == null ? new x0() : s1Var;
    }

    public static m1 a() {
        return c;
    }

    public static s1 c(String str) {
        try {
            return (s1) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> r1<T> b(Class<T> cls) {
        zzci.d(cls, "messageType");
        r1<T> r1Var = (r1<T>) this.b.get(cls);
        if (r1Var == null) {
            r1<T> a2 = this.f8588a.a(cls);
            zzci.d(cls, "messageType");
            zzci.d(a2, "schema");
            r1<T> r1Var2 = (r1<T>) this.b.putIfAbsent(cls, a2);
            return r1Var2 != null ? r1Var2 : a2;
        }
        return r1Var;
    }

    public final <T> r1<T> d(T t) {
        return b(t.getClass());
    }
}
