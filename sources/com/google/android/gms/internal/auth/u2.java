package com.google.android.gms.internal.auth;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes6.dex */
public final class u2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f8541a;
    public static final Class b;
    public static final boolean c;
    public static final t2 d;
    public static final boolean e;
    public static final boolean f;
    public static final boolean g;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012f  */
    static {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.u2.<clinit>():void");
    }

    public static double a(Object obj, long j) {
        return d.a(obj, j);
    }

    public static float b(Object obj, long j) {
        return d.b(obj, j);
    }

    public static int c(Object obj, long j) {
        return d.i(obj, j);
    }

    public static long d(Object obj, long j) {
        return d.j(obj, j);
    }

    public static Object e(Class cls) {
        try {
            return f8541a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static Object f(Object obj, long j) {
        return d.l(obj, j);
    }

    public static Unsafe g() {
        try {
            return (Unsafe) AccessController.doPrivileged(new q2());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ void h(Throwable th) {
        Logger.getLogger(u2.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    public static /* synthetic */ void i(Object obj, long j, boolean z) {
        long j2 = (-4) & j;
        t2 t2Var = d;
        int i = t2Var.i(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        t2Var.m(obj, j2, ((z ? 1 : 0) << i2) | ((~(255 << i2)) & i));
    }

    public static /* synthetic */ void j(Object obj, long j, boolean z) {
        long j2 = (-4) & j;
        t2 t2Var = d;
        int i = t2Var.i(obj, j2);
        int i2 = (((int) j) & 3) << 3;
        t2Var.m(obj, j2, ((z ? 1 : 0) << i2) | ((~(255 << i2)) & i));
    }

    public static void k(Object obj, long j, boolean z) {
        d.c(obj, j, z);
    }

    public static void l(Object obj, long j, double d2) {
        d.d(obj, j, d2);
    }

    public static void m(Object obj, long j, float f2) {
        d.e(obj, j, f2);
    }

    public static void n(Object obj, long j, int i) {
        d.m(obj, j, i);
    }

    public static void o(Object obj, long j, long j2) {
        d.n(obj, j, j2);
    }

    public static void p(Object obj, long j, Object obj2) {
        d.o(obj, j, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean q(Object obj, long j) {
        return ((byte) ((d.i(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean r(Object obj, long j) {
        return ((byte) ((d.i(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean s(Class cls) {
        int i = l0.f8529a;
        try {
            Class cls2 = b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean t(Object obj, long j) {
        return d.f(obj, j);
    }

    public static boolean u() {
        return f;
    }

    public static boolean v() {
        return e;
    }

    public static int w(Class cls) {
        if (f) {
            return d.g(cls);
        }
        return -1;
    }

    public static int x(Class cls) {
        if (f) {
            return d.h(cls);
        }
        return -1;
    }

    public static Field y() {
        int i = l0.f8529a;
        Field z = z(Buffer.class, "effectiveDirectAddress");
        if (z == null) {
            Field z2 = z(Buffer.class, "address");
            if (z2 == null || z2.getType() != Long.TYPE) {
                return null;
            }
            return z2;
        }
        return z;
    }

    public static Field z(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
