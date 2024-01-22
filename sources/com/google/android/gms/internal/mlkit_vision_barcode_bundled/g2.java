package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class g2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f9596a;
    public static final Class b;
    public static final boolean c;
    public static final f2 d;
    public static final boolean e;
    public static final boolean f;
    public static final long g;
    public static final boolean h;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0135  */
    static {
        /*
            Method dump skipped, instructions count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.g2.<clinit>():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean A(Class cls) {
        int i = l.f9602a;
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

    public static boolean B(Object obj, long j) {
        return d.g(obj, j);
    }

    public static boolean C() {
        return f;
    }

    public static boolean D() {
        return e;
    }

    public static int E(Class cls) {
        if (f) {
            return d.f9595a.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static int a(Class cls) {
        if (f) {
            return d.f9595a.arrayIndexScale(cls);
        }
        return -1;
    }

    public static Field b() {
        int i = l.f9602a;
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

    public static Field c(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void d(Object obj, long j, byte b2) {
        f2 f2Var = d;
        long j2 = (-4) & j;
        int i = f2Var.f9595a.getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        f2Var.f9595a.putInt(obj, j2, ((255 & b2) << i2) | (i & (~(255 << i2))));
    }

    public static void e(Object obj, long j, byte b2) {
        f2 f2Var = d;
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        f2Var.f9595a.putInt(obj, j2, ((255 & b2) << i) | (f2Var.f9595a.getInt(obj, j2) & (~(255 << i))));
    }

    public static double f(Object obj, long j) {
        return d.a(obj, j);
    }

    public static float g(Object obj, long j) {
        return d.b(obj, j);
    }

    public static int h(Object obj, long j) {
        return d.f9595a.getInt(obj, j);
    }

    public static long i(Object obj, long j) {
        return d.f9595a.getLong(obj, j);
    }

    public static Object j(Class cls) {
        try {
            return f9596a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static Object k(Object obj, long j) {
        return d.f9595a.getObject(obj, j);
    }

    public static Unsafe l() {
        try {
            return (Unsafe) AccessController.doPrivileged(new c2());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* bridge */ /* synthetic */ void m(Throwable th) {
        Logger.getLogger(g2.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    public static void r(Object obj, long j, boolean z) {
        d.c(obj, j, z);
    }

    public static void s(byte[] bArr, long j, byte b2) {
        d.d(bArr, g + j, b2);
    }

    public static void t(Object obj, long j, double d2) {
        d.e(obj, j, d2);
    }

    public static void u(Object obj, long j, float f2) {
        d.f(obj, j, f2);
    }

    public static void v(Object obj, long j, int i) {
        d.f9595a.putInt(obj, j, i);
    }

    public static void w(Object obj, long j, long j2) {
        d.f9595a.putLong(obj, j, j2);
    }

    public static void x(Object obj, long j, Object obj2) {
        d.f9595a.putObject(obj, j, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean y(Object obj, long j) {
        return ((byte) ((d.f9595a.getInt(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean z(Object obj, long j) {
        return ((byte) ((d.f9595a.getInt(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }
}
