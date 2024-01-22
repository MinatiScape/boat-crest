package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes10.dex */
public final class j4 {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f9983a = Logger.getLogger(j4.class.getName());
    public static final Unsafe b;
    public static final Class<?> c;
    public static final boolean d;
    public static final boolean e;
    public static final d f;
    public static final boolean g;
    public static final boolean h;
    public static final long i;
    public static final boolean j;

    /* loaded from: classes10.dex */
    public static final class a extends d {
        public a(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void a(Object obj, long j, double d) {
            c(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void b(Object obj, long j, float f) {
            e(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void d(Object obj, long j, boolean z) {
            if (j4.j) {
                j4.l(obj, j, z);
            } else {
                j4.n(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void f(Object obj, long j, byte b) {
            if (j4.j) {
                j4.b(obj, j, b);
            } else {
                j4.j(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final boolean i(Object obj, long j) {
            return j4.j ? j4.J(obj, j) : j4.K(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final float j(Object obj, long j) {
            return Float.intBitsToFloat(g(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final double k(Object obj, long j) {
            return Double.longBitsToDouble(h(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final byte l(Object obj, long j) {
            return j4.j ? j4.H(obj, j) : j4.I(obj, j);
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends d {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void a(Object obj, long j, double d) {
            this.f9984a.putDouble(obj, j, d);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void b(Object obj, long j, float f) {
            this.f9984a.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void d(Object obj, long j, boolean z) {
            this.f9984a.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void f(Object obj, long j, byte b) {
            this.f9984a.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final boolean i(Object obj, long j) {
            return this.f9984a.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final float j(Object obj, long j) {
            return this.f9984a.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final double k(Object obj, long j) {
            return this.f9984a.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final byte l(Object obj, long j) {
            return this.f9984a.getByte(obj, j);
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends d {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void a(Object obj, long j, double d) {
            c(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void b(Object obj, long j, float f) {
            e(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void d(Object obj, long j, boolean z) {
            if (j4.j) {
                j4.l(obj, j, z);
            } else {
                j4.n(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final void f(Object obj, long j, byte b) {
            if (j4.j) {
                j4.b(obj, j, b);
            } else {
                j4.j(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final boolean i(Object obj, long j) {
            return j4.j ? j4.J(obj, j) : j4.K(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final float j(Object obj, long j) {
            return Float.intBitsToFloat(g(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final double k(Object obj, long j) {
            return Double.longBitsToDouble(h(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.j4.d
        public final byte l(Object obj, long j) {
            return j4.j ? j4.H(obj, j) : j4.I(obj, j);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public Unsafe f9984a;

        public d(Unsafe unsafe) {
            this.f9984a = unsafe;
        }

        public abstract void a(Object obj, long j, double d);

        public abstract void b(Object obj, long j, float f);

        public final void c(Object obj, long j, long j2) {
            this.f9984a.putLong(obj, j, j2);
        }

        public abstract void d(Object obj, long j, boolean z);

        public final void e(Object obj, long j, int i) {
            this.f9984a.putInt(obj, j, i);
        }

        public abstract void f(Object obj, long j, byte b);

        public final int g(Object obj, long j) {
            return this.f9984a.getInt(obj, j);
        }

        public final long h(Object obj, long j) {
            return this.f9984a.getLong(obj, j);
        }

        public abstract boolean i(Object obj, long j);

        public abstract float j(Object obj, long j);

        public abstract double k(Object obj, long j);

        public abstract byte l(Object obj, long j);
    }

    static {
        Unsafe v = v();
        b = v;
        c = e1.b();
        boolean B = B(Long.TYPE);
        d = B;
        boolean B2 = B(Integer.TYPE);
        e = B2;
        d dVar = null;
        if (v != null) {
            if (!e1.a()) {
                dVar = new b(v);
            } else if (B) {
                dVar = new c(v);
            } else if (B2) {
                dVar = new a(v);
            }
        }
        f = dVar;
        g = x();
        h = w();
        i = s(byte[].class);
        s(boolean[].class);
        z(boolean[].class);
        s(int[].class);
        z(int[].class);
        s(long[].class);
        z(long[].class);
        s(float[].class);
        z(float[].class);
        s(double[].class);
        z(double[].class);
        s(Object[].class);
        z(Object[].class);
        Field y = y();
        if (y != null && dVar != null) {
            dVar.f9984a.objectFieldOffset(y);
        }
        j = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static int A(Object obj, long j2) {
        return f.g(obj, j2);
    }

    public static boolean B(Class<?> cls) {
        if (e1.a()) {
            try {
                Class<?> cls2 = c;
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
        return false;
    }

    public static long C(Object obj, long j2) {
        return f.h(obj, j2);
    }

    public static boolean D(Object obj, long j2) {
        return f.i(obj, j2);
    }

    public static float E(Object obj, long j2) {
        return f.j(obj, j2);
    }

    public static double F(Object obj, long j2) {
        return f.k(obj, j2);
    }

    public static Object G(Object obj, long j2) {
        return f.f9984a.getObject(obj, j2);
    }

    public static byte H(Object obj, long j2) {
        return (byte) (A(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3)));
    }

    public static byte I(Object obj, long j2) {
        return (byte) (A(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3)));
    }

    public static boolean J(Object obj, long j2) {
        return H(obj, j2) != 0;
    }

    public static boolean K(Object obj, long j2) {
        return I(obj, j2) != 0;
    }

    public static byte a(byte[] bArr, long j2) {
        return f.l(bArr, i + j2);
    }

    public static void b(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int A = A(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        k(obj, j3, ((255 & b2) << i2) | (A & (~(255 << i2))));
    }

    public static void c(Object obj, long j2, double d2) {
        f.a(obj, j2, d2);
    }

    public static void d(Object obj, long j2, float f2) {
        f.b(obj, j2, f2);
    }

    public static void e(Object obj, long j2, long j3) {
        f.c(obj, j2, j3);
    }

    public static void f(Object obj, long j2, Object obj2) {
        f.f9984a.putObject(obj, j2, obj2);
    }

    public static void g(Object obj, long j2, boolean z) {
        f.d(obj, j2, z);
    }

    public static void h(byte[] bArr, long j2, byte b2) {
        f.f(bArr, i + j2, b2);
    }

    public static Field i(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void j(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        k(obj, j3, ((255 & b2) << i2) | (A(obj, j3) & (~(255 << i2))));
    }

    public static void k(Object obj, long j2, int i2) {
        f.e(obj, j2, i2);
    }

    public static void l(Object obj, long j2, boolean z) {
        b(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void n(Object obj, long j2, boolean z) {
        j(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static <T> T r(Class<T> cls) {
        try {
            return (T) b.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int s(Class<?> cls) {
        if (h) {
            return f.f9984a.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static boolean t() {
        return h;
    }

    public static boolean u() {
        return g;
    }

    public static Unsafe v() {
        try {
            return (Unsafe) AccessController.doPrivileged(new l4());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean w() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getInt", Object.class, cls2);
            cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
            cls.getMethod("getLong", Object.class, cls2);
            cls.getMethod("putLong", Object.class, cls2, cls2);
            cls.getMethod("getObject", Object.class, cls2);
            cls.getMethod("putObject", Object.class, cls2, Object.class);
            if (e1.a()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, cls2);
            cls.getMethod("putByte", Object.class, cls2, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, cls2);
            cls.getMethod("putBoolean", Object.class, cls2, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, cls2);
            cls.getMethod("putFloat", Object.class, cls2, Float.TYPE);
            cls.getMethod("getDouble", Object.class, cls2);
            cls.getMethod("putDouble", Object.class, cls2, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger = f9983a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    public static boolean x() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (y() == null) {
                return false;
            }
            if (e1.a()) {
                return true;
            }
            cls.getMethod("getByte", cls2);
            cls.getMethod("putByte", cls2, Byte.TYPE);
            cls.getMethod("getInt", cls2);
            cls.getMethod("putInt", cls2, Integer.TYPE);
            cls.getMethod("getLong", cls2);
            cls.getMethod("putLong", cls2, cls2);
            cls.getMethod("copyMemory", cls2, cls2, cls2);
            cls.getMethod("copyMemory", Object.class, cls2, Object.class, cls2, cls2);
            return true;
        } catch (Throwable th) {
            Logger logger = f9983a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    public static Field y() {
        Field i2;
        if (!e1.a() || (i2 = i(Buffer.class, "effectiveDirectAddress")) == null) {
            Field i3 = i(Buffer.class, "address");
            if (i3 == null || i3.getType() != Long.TYPE) {
                return null;
            }
            return i3;
        }
        return i2;
    }

    public static int z(Class<?> cls) {
        if (h) {
            return f.f9984a.arrayIndexScale(cls);
        }
        return -1;
    }
}
