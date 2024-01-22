package com.google.protobuf;

import java.util.Set;
/* loaded from: classes11.dex */
public final class v implements t0 {
    public static final b0 b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final b0 f11754a;

    /* loaded from: classes11.dex */
    public static class a implements b0 {
        @Override // com.google.protobuf.b0
        public a0 a(Class<?> cls) {
            throw new IllegalStateException("This should never be called.");
        }

        @Override // com.google.protobuf.b0
        public boolean b(Class<?> cls) {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static class b implements b0 {

        /* renamed from: a  reason: collision with root package name */
        public b0[] f11755a;

        public b(b0... b0VarArr) {
            this.f11755a = b0VarArr;
        }

        @Override // com.google.protobuf.b0
        public a0 a(Class<?> cls) {
            b0[] b0VarArr;
            for (b0 b0Var : this.f11755a) {
                if (b0Var.b(cls)) {
                    return b0Var.a(cls);
                }
            }
            throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
        }

        @Override // com.google.protobuf.b0
        public boolean b(Class<?> cls) {
            for (b0 b0Var : this.f11755a) {
                if (b0Var.b(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    public v() {
        this(b());
    }

    public static b0 b() {
        return new b(q.c(), c());
    }

    public static b0 c() {
        try {
            Set<String> set = i.f11733a;
            return (b0) i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    public static boolean d(a0 a0Var) {
        return a0Var.getSyntax() == ProtoSyntax.PROTO2;
    }

    public static <T> s0<T> e(Class<T> cls, a0 a0Var) {
        if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
            if (d(a0Var)) {
                return d0.Q(cls, a0Var, j0.b(), t.b(), u0.O(), o.b(), z.b());
            }
            return d0.Q(cls, a0Var, j0.b(), t.b(), u0.O(), null, z.b());
        } else if (d(a0Var)) {
            return d0.Q(cls, a0Var, j0.a(), t.a(), u0.I(), o.a(), z.a());
        } else {
            return d0.Q(cls, a0Var, j0.a(), t.a(), u0.J(), null, z.a());
        }
    }

    @Override // com.google.protobuf.t0
    public <T> s0<T> a(Class<T> cls) {
        u0.K(cls);
        a0 a2 = this.f11754a.a(cls);
        if (a2.a()) {
            if (GeneratedMessageLite.class.isAssignableFrom(cls)) {
                return e0.l(u0.O(), o.b(), a2.b());
            }
            return e0.l(u0.I(), o.a(), a2.b());
        }
        return e(cls, a2);
    }

    public v(b0 b0Var) {
        this.f11754a = (b0) Internal.checkNotNull(b0Var, "messageInfoFactory");
    }
}
