package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class t4 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f8931a;
    public static final Class<?> b;
    public static final boolean c;
    public static final boolean d;
    public static final s4 e;
    public static final boolean f;
    public static final boolean g;
    public static final long h;
    public static final boolean i;

    /* JADX WARN: Removed duplicated region for block: B:33:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x013b  */
    static {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.t4.<clinit>():void");
    }

    public static boolean A(Class<?> cls) {
        int i2 = f2.f8907a;
        try {
            Class<?> cls2 = b;
            Class<?> cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class<?> cls4 = Integer.TYPE;
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

    public static boolean B(Object obj, long j) {
        return e.g(obj, j);
    }

    public static boolean C() {
        return g;
    }

    public static boolean D() {
        return f;
    }

    public static int E(Class<?> cls) {
        if (g) {
            return e.h(cls);
        }
        return -1;
    }

    public static int a(Class<?> cls) {
        if (g) {
            return e.i(cls);
        }
        return -1;
    }

    public static Field b() {
        int i2 = f2.f8907a;
        Field c2 = c(Buffer.class, "effectiveDirectAddress");
        if (c2 == null) {
            Field c3 = c(Buffer.class, "address");
            if (c3 == null || c3.getType() != Long.TYPE) {
                return null;
            }
            return c3;
        }
        return c2;
    }

    public static Field c(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void d(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        s4 s4Var = e;
        int j3 = s4Var.j(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        s4Var.n(obj, j2, ((255 & b2) << i2) | (j3 & (~(255 << i2))));
    }

    public static void e(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        s4 s4Var = e;
        int i2 = (((int) j) & 3) << 3;
        s4Var.n(obj, j2, ((255 & b2) << i2) | (s4Var.j(obj, j2) & (~(255 << i2))));
    }

    public static double f(Object obj, long j) {
        return e.a(obj, j);
    }

    public static float g(Object obj, long j) {
        return e.b(obj, j);
    }

    public static int h(Object obj, long j) {
        return e.j(obj, j);
    }

    public static long i(Object obj, long j) {
        return e.k(obj, j);
    }

    public static <T> T j(Class<T> cls) {
        try {
            return (T) f8931a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static Object k(Object obj, long j) {
        return e.m(obj, j);
    }

    public static Unsafe l() {
        try {
            return (Unsafe) AccessController.doPrivileged(new p4());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ void m(Throwable th) {
        Logger logger = Logger.getLogger(t4.class.getName());
        Level level = Level.WARNING;
        String valueOf = String.valueOf(th);
        StringBuilder sb = new StringBuilder(valueOf.length() + 71);
        sb.append("platform method missing - proto runtime falling back to safer methods: ");
        sb.append(valueOf);
        logger.logp(level, "com.google.protobuf.UnsafeUtil", "logMissingMethod", sb.toString());
    }

    public static void r(Object obj, long j, boolean z) {
        e.c(obj, j, z);
    }

    public static void s(byte[] bArr, long j, byte b2) {
        e.d(bArr, h + j, b2);
    }

    public static void t(Object obj, long j, double d2) {
        e.e(obj, j, d2);
    }

    public static void u(Object obj, long j, float f2) {
        e.f(obj, j, f2);
    }

    public static void v(Object obj, long j, int i2) {
        e.n(obj, j, i2);
    }

    public static void w(Object obj, long j, long j2) {
        e.o(obj, j, j2);
    }

    public static void x(Object obj, long j, Object obj2) {
        e.p(obj, j, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean y(Object obj, long j) {
        return ((byte) ((e.j(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean z(Object obj, long j) {
        return ((byte) ((e.j(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }
}
