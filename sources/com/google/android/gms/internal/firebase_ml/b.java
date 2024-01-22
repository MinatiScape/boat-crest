package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes7.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f8665a = Logger.getLogger(b.class.getName());
    public static final Unsafe b;
    public static final Class<?> c;
    public static final boolean d;
    public static final boolean e;
    public static final d f;
    public static final boolean g;
    public static final boolean h;
    public static final long i;
    public static final boolean j;

    /* loaded from: classes7.dex */
    public static final class a extends d {
        public a(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void a(Object obj, long j, double d) {
            d(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void b(Object obj, long j, float f) {
            c(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void e(Object obj, long j, boolean z) {
            if (b.j) {
                b.l(obj, j, z);
            } else {
                b.n(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void f(Object obj, long j, byte b) {
            if (b.j) {
                b.b(obj, j, b);
            } else {
                b.k(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final boolean i(Object obj, long j) {
            return b.j ? b.D(obj, j) : b.E(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final float j(Object obj, long j) {
            return Float.intBitsToFloat(g(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final double k(Object obj, long j) {
            return Double.longBitsToDouble(h(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final byte l(Object obj, long j) {
            return b.j ? b.A(obj, j) : b.C(obj, j);
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase_ml.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public static final class C0380b extends d {
        public C0380b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void a(Object obj, long j, double d) {
            this.f8666a.putDouble(obj, j, d);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void b(Object obj, long j, float f) {
            this.f8666a.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void e(Object obj, long j, boolean z) {
            this.f8666a.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void f(Object obj, long j, byte b) {
            this.f8666a.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final boolean i(Object obj, long j) {
            return this.f8666a.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final float j(Object obj, long j) {
            return this.f8666a.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final double k(Object obj, long j) {
            return this.f8666a.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final byte l(Object obj, long j) {
            return this.f8666a.getByte(obj, j);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends d {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void a(Object obj, long j, double d) {
            d(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void b(Object obj, long j, float f) {
            c(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void e(Object obj, long j, boolean z) {
            if (b.j) {
                b.l(obj, j, z);
            } else {
                b.n(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final void f(Object obj, long j, byte b) {
            if (b.j) {
                b.b(obj, j, b);
            } else {
                b.k(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final boolean i(Object obj, long j) {
            return b.j ? b.D(obj, j) : b.E(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final float j(Object obj, long j) {
            return Float.intBitsToFloat(g(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final double k(Object obj, long j) {
            return Double.longBitsToDouble(h(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_ml.b.d
        public final byte l(Object obj, long j) {
            return b.j ? b.A(obj, j) : b.C(obj, j);
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public Unsafe f8666a;

        public d(Unsafe unsafe) {
            this.f8666a = unsafe;
        }

        public abstract void a(Object obj, long j, double d);

        public abstract void b(Object obj, long j, float f);

        public final void c(Object obj, long j, int i) {
            this.f8666a.putInt(obj, j, i);
        }

        public final void d(Object obj, long j, long j2) {
            this.f8666a.putLong(obj, j, j2);
        }

        public abstract void e(Object obj, long j, boolean z);

        public abstract void f(Object obj, long j, byte b);

        public final int g(Object obj, long j) {
            return this.f8666a.getInt(obj, j);
        }

        public final long h(Object obj, long j) {
            return this.f8666a.getLong(obj, j);
        }

        public abstract boolean i(Object obj, long j);

        public abstract float j(Object obj, long j);

        public abstract double k(Object obj, long j);

        public abstract byte l(Object obj, long j);
    }

    static {
        Unsafe L = L();
        b = L;
        c = t5.c();
        boolean B = B(Long.TYPE);
        d = B;
        boolean B2 = B(Integer.TYPE);
        e = B2;
        d dVar = null;
        if (L != null) {
            if (!t5.b()) {
                dVar = new C0380b(L);
            } else if (B) {
                dVar = new c(L);
            } else if (B2) {
                dVar = new a(L);
            }
        }
        f = dVar;
        g = N();
        h = M();
        i = x(byte[].class);
        x(boolean[].class);
        y(boolean[].class);
        x(int[].class);
        y(int[].class);
        x(long[].class);
        y(long[].class);
        x(float[].class);
        y(float[].class);
        x(double[].class);
        y(double[].class);
        x(Object[].class);
        y(Object[].class);
        Field O = O();
        if (O != null && dVar != null) {
            dVar.f8666a.objectFieldOffset(O);
        }
        j = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static byte A(Object obj, long j2) {
        return (byte) (r(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3)));
    }

    public static boolean B(Class<?> cls) {
        if (t5.b()) {
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

    public static byte C(Object obj, long j2) {
        return (byte) (r(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3)));
    }

    public static boolean D(Object obj, long j2) {
        return A(obj, j2) != 0;
    }

    public static boolean E(Object obj, long j2) {
        return C(obj, j2) != 0;
    }

    public static boolean J() {
        return h;
    }

    public static boolean K() {
        return g;
    }

    public static Unsafe L() {
        try {
            return (Unsafe) AccessController.doPrivileged(new com.google.android.gms.internal.firebase_ml.d());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean M() {
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
            if (t5.b()) {
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
            Logger logger = f8665a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    public static boolean N() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (O() == null) {
                return false;
            }
            if (t5.b()) {
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
            Logger logger = f8665a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    public static Field O() {
        Field j2;
        if (!t5.b() || (j2 = j(Buffer.class, "effectiveDirectAddress")) == null) {
            Field j3 = j(Buffer.class, "address");
            if (j3 == null || j3.getType() != Long.TYPE) {
                return null;
            }
            return j3;
        }
        return j2;
    }

    public static byte a(byte[] bArr, long j2) {
        return f.l(bArr, i + j2);
    }

    public static void b(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int r = r(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        e(obj, j3, ((255 & b2) << i2) | (r & (~(255 << i2))));
    }

    public static void c(Object obj, long j2, double d2) {
        f.a(obj, j2, d2);
    }

    public static void d(Object obj, long j2, float f2) {
        f.b(obj, j2, f2);
    }

    public static void e(Object obj, long j2, int i2) {
        f.c(obj, j2, i2);
    }

    public static void f(Object obj, long j2, long j3) {
        f.d(obj, j2, j3);
    }

    public static void g(Object obj, long j2, Object obj2) {
        f.f8666a.putObject(obj, j2, obj2);
    }

    public static void h(Object obj, long j2, boolean z) {
        f.e(obj, j2, z);
    }

    public static void i(byte[] bArr, long j2, byte b2) {
        f.f(bArr, i + j2, b2);
    }

    public static Field j(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void k(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        e(obj, j3, ((255 & b2) << i2) | (r(obj, j3) & (~(255 << i2))));
    }

    public static void l(Object obj, long j2, boolean z) {
        b(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void n(Object obj, long j2, boolean z) {
        k(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static int r(Object obj, long j2) {
        return f.g(obj, j2);
    }

    public static long s(Object obj, long j2) {
        return f.h(obj, j2);
    }

    public static boolean t(Object obj, long j2) {
        return f.i(obj, j2);
    }

    public static float u(Object obj, long j2) {
        return f.j(obj, j2);
    }

    public static <T> T v(Class<T> cls) {
        try {
            return (T) b.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static double w(Object obj, long j2) {
        return f.k(obj, j2);
    }

    public static int x(Class<?> cls) {
        if (h) {
            return f.f8666a.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static int y(Class<?> cls) {
        if (h) {
            return f.f8666a.arrayIndexScale(cls);
        }
        return -1;
    }

    public static Object z(Object obj, long j2) {
        return f.f8666a.getObject(obj, j2);
    }
}
