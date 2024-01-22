package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import java.util.Set;
/* loaded from: classes7.dex */
public final class g7 implements f8 {
    public static final n7 b = new f7();

    /* renamed from: a  reason: collision with root package name */
    public final n7 f8682a;

    public g7() {
        this(new h7(v6.c(), c()));
    }

    public static boolean b(l7 l7Var) {
        return l7Var.b() == zzwz.zzg.zzcmb;
    }

    public static n7 c() {
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            return (n7) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.f8
    public final <T> c8<T> a(Class<T> cls) {
        e8.I(cls);
        l7 b2 = this.f8682a.b(cls);
        if (b2.c()) {
            if (zzwz.class.isAssignableFrom(cls)) {
                return q7.c(e8.a0(), q6.b(), b2.a());
            }
            return q7.c(e8.Y(), q6.c(), b2.a());
        } else if (zzwz.class.isAssignableFrom(cls)) {
            if (b(b2)) {
                return o7.o(cls, b2, t7.b(), a7.d(), e8.a0(), q6.b(), m7.b());
            }
            return o7.o(cls, b2, t7.b(), a7.d(), e8.a0(), null, m7.b());
        } else if (b(b2)) {
            return o7.o(cls, b2, t7.a(), a7.c(), e8.Y(), q6.c(), m7.a());
        } else {
            return o7.o(cls, b2, t7.a(), a7.c(), e8.Z(), null, m7.a());
        }
    }

    public g7(n7 n7Var) {
        this.f8682a = (n7) zzxd.b(n7Var, "messageInfoFactory");
    }
}
