package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;
/* loaded from: classes7.dex */
public final class n2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f8590a = Logger.getLogger(n2.class.getName());
    public static final Unsafe b;
    public static final Class<?> c;
    public static final boolean d;
    public static final boolean e;
    public static final d f;
    public static final boolean g;
    public static final boolean h;
    public static final long i;
    public static final long j;
    public static final boolean k;

    /* loaded from: classes7.dex */
    public static final class a extends d {
        public a(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void b(long j, byte b) {
            Memory.pokeByte((int) (j & (-1)), b);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void c(Object obj, long j, double d) {
            f(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void d(Object obj, long j, float f) {
            e(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void g(Object obj, long j, boolean z) {
            if (n2.k) {
                n2.r(obj, j, z);
            } else {
                n2.t(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void h(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (j2 & (-1)), bArr, (int) j, (int) j3);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void i(Object obj, long j, byte b) {
            if (n2.k) {
                n2.d(obj, j, b);
            } else {
                n2.q(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final boolean l(Object obj, long j) {
            return n2.k ? n2.P(obj, j) : n2.Q(obj, j);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final float m(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final double n(Object obj, long j) {
            return Double.longBitsToDouble(k(obj, j));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final byte o(Object obj, long j) {
            return n2.k ? n2.N(obj, j) : n2.O(obj, j);
        }
    }

    /* loaded from: classes7.dex */
    public static final class b extends d {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void b(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void c(Object obj, long j, double d) {
            f(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void d(Object obj, long j, float f) {
            e(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void g(Object obj, long j, boolean z) {
            if (n2.k) {
                n2.r(obj, j, z);
            } else {
                n2.t(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void h(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void i(Object obj, long j, byte b) {
            if (n2.k) {
                n2.d(obj, j, b);
            } else {
                n2.q(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final boolean l(Object obj, long j) {
            return n2.k ? n2.P(obj, j) : n2.Q(obj, j);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final float m(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final double n(Object obj, long j) {
            return Double.longBitsToDouble(k(obj, j));
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final byte o(Object obj, long j) {
            return n2.k ? n2.N(obj, j) : n2.O(obj, j);
        }
    }

    /* loaded from: classes7.dex */
    public static final class c extends d {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void b(long j, byte b) {
            this.f8591a.putByte(j, b);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void c(Object obj, long j, double d) {
            this.f8591a.putDouble(obj, j, d);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void d(Object obj, long j, float f) {
            this.f8591a.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void g(Object obj, long j, boolean z) {
            this.f8591a.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void h(byte[] bArr, long j, long j2, long j3) {
            this.f8591a.copyMemory(bArr, n2.i + j, (Object) null, j2, j3);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final void i(Object obj, long j, byte b) {
            this.f8591a.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final boolean l(Object obj, long j) {
            return this.f8591a.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final float m(Object obj, long j) {
            return this.f8591a.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final double n(Object obj, long j) {
            return this.f8591a.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.clearcut.n2.d
        public final byte o(Object obj, long j) {
            return this.f8591a.getByte(obj, j);
        }
    }

    /* loaded from: classes7.dex */
    public static abstract class d {

        /* renamed from: a  reason: collision with root package name */
        public Unsafe f8591a;

        public d(Unsafe unsafe) {
            this.f8591a = unsafe;
        }

        public final long a(Field field) {
            return this.f8591a.objectFieldOffset(field);
        }

        public abstract void b(long j, byte b);

        public abstract void c(Object obj, long j, double d);

        public abstract void d(Object obj, long j, float f);

        public final void e(Object obj, long j, int i) {
            this.f8591a.putInt(obj, j, i);
        }

        public final void f(Object obj, long j, long j2) {
            this.f8591a.putLong(obj, j, j2);
        }

        public abstract void g(Object obj, long j, boolean z);

        public abstract void h(byte[] bArr, long j, long j2, long j3);

        public abstract void i(Object obj, long j, byte b);

        public final int j(Object obj, long j) {
            return this.f8591a.getInt(obj, j);
        }

        public final long k(Object obj, long j) {
            return this.f8591a.getLong(obj, j);
        }

        public abstract boolean l(Object obj, long j);

        public abstract float m(Object obj, long j);

        public abstract double n(Object obj, long j);

        public abstract byte o(Object obj, long j);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b9  */
    static {
        /*
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            java.lang.Class<double[]> r1 = double[].class
            java.lang.Class<float[]> r2 = float[].class
            java.lang.Class<long[]> r3 = long[].class
            java.lang.Class<int[]> r4 = int[].class
            java.lang.Class<boolean[]> r5 = boolean[].class
            java.lang.Class<com.google.android.gms.internal.clearcut.n2> r6 = com.google.android.gms.internal.clearcut.n2.class
            java.lang.String r6 = r6.getName()
            java.util.logging.Logger r6 = java.util.logging.Logger.getLogger(r6)
            com.google.android.gms.internal.clearcut.n2.f8590a = r6
            sun.misc.Unsafe r6 = z()
            com.google.android.gms.internal.clearcut.n2.b = r6
            java.lang.Class r7 = com.google.android.gms.internal.clearcut.m.c()
            com.google.android.gms.internal.clearcut.n2.c = r7
            java.lang.Class r7 = java.lang.Long.TYPE
            boolean r7 = G(r7)
            com.google.android.gms.internal.clearcut.n2.d = r7
            java.lang.Class r8 = java.lang.Integer.TYPE
            boolean r8 = G(r8)
            com.google.android.gms.internal.clearcut.n2.e = r8
            r9 = 0
            if (r6 != 0) goto L39
        L37:
            r7 = r9
            goto L54
        L39:
            boolean r10 = com.google.android.gms.internal.clearcut.m.b()
            if (r10 == 0) goto L4f
            if (r7 == 0) goto L47
            com.google.android.gms.internal.clearcut.n2$b r7 = new com.google.android.gms.internal.clearcut.n2$b
            r7.<init>(r6)
            goto L54
        L47:
            if (r8 == 0) goto L37
            com.google.android.gms.internal.clearcut.n2$a r7 = new com.google.android.gms.internal.clearcut.n2$a
            r7.<init>(r6)
            goto L54
        L4f:
            com.google.android.gms.internal.clearcut.n2$c r7 = new com.google.android.gms.internal.clearcut.n2$c
            r7.<init>(r6)
        L54:
            com.google.android.gms.internal.clearcut.n2.f = r7
            boolean r6 = B()
            com.google.android.gms.internal.clearcut.n2.g = r6
            boolean r6 = A()
            com.google.android.gms.internal.clearcut.n2.h = r6
            java.lang.Class<byte[]> r6 = byte[].class
            int r6 = E(r6)
            long r6 = (long) r6
            com.google.android.gms.internal.clearcut.n2.i = r6
            E(r5)
            F(r5)
            E(r4)
            F(r4)
            E(r3)
            F(r3)
            E(r2)
            F(r2)
            E(r1)
            F(r1)
            E(r0)
            F(r0)
            java.lang.reflect.Field r0 = C()
            long r0 = n(r0)
            com.google.android.gms.internal.clearcut.n2.j = r0
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r1 = "value"
            java.lang.reflect.Field r0 = p(r0, r1)
            if (r0 == 0) goto Lac
            java.lang.Class r1 = r0.getType()
            java.lang.Class<char[]> r2 = char[].class
            if (r1 != r2) goto Lac
            r9 = r0
        Lac:
            n(r9)
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto Lb9
            r0 = 1
            goto Lba
        Lb9:
            r0 = 0
        Lba:
            com.google.android.gms.internal.clearcut.n2.k = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.n2.<clinit>():void");
    }

    public static boolean A() {
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
            if (m.b()) {
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
            Logger logger = f8590a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    public static boolean B() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (C() == null) {
                return false;
            }
            if (m.b()) {
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
            Logger logger = f8590a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(valueOf.length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    public static Field C() {
        Field p;
        if (!m.b() || (p = p(Buffer.class, "effectiveDirectAddress")) == null) {
            Field p2 = p(Buffer.class, "address");
            if (p2 == null || p2.getType() != Long.TYPE) {
                return null;
            }
            return p2;
        }
        return p;
    }

    public static int E(Class<?> cls) {
        if (h) {
            return f.f8591a.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static int F(Class<?> cls) {
        if (h) {
            return f.f8591a.arrayIndexScale(cls);
        }
        return -1;
    }

    public static boolean G(Class<?> cls) {
        if (m.b()) {
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

    public static int H(Object obj, long j2) {
        return f.j(obj, j2);
    }

    public static long I(Object obj, long j2) {
        return f.k(obj, j2);
    }

    public static boolean J(Object obj, long j2) {
        return f.l(obj, j2);
    }

    public static float K(Object obj, long j2) {
        return f.m(obj, j2);
    }

    public static double L(Object obj, long j2) {
        return f.n(obj, j2);
    }

    public static Object M(Object obj, long j2) {
        return f.f8591a.getObject(obj, j2);
    }

    public static byte N(Object obj, long j2) {
        return (byte) (H(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3)));
    }

    public static byte O(Object obj, long j2) {
        return (byte) (H(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3)));
    }

    public static boolean P(Object obj, long j2) {
        return N(obj, j2) != 0;
    }

    public static boolean Q(Object obj, long j2) {
        return O(obj, j2) != 0;
    }

    public static byte a(byte[] bArr, long j2) {
        return f.o(bArr, i + j2);
    }

    public static long b(Field field) {
        return f.a(field);
    }

    public static void c(long j2, byte b2) {
        f.b(j2, b2);
    }

    public static void d(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int H = H(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        g(obj, j3, ((255 & b2) << i2) | (H & (~(255 << i2))));
    }

    public static void e(Object obj, long j2, double d2) {
        f.c(obj, j2, d2);
    }

    public static void f(Object obj, long j2, float f2) {
        f.d(obj, j2, f2);
    }

    public static void g(Object obj, long j2, int i2) {
        f.e(obj, j2, i2);
    }

    public static void h(Object obj, long j2, long j3) {
        f.f(obj, j2, j3);
    }

    public static void i(Object obj, long j2, Object obj2) {
        f.f8591a.putObject(obj, j2, obj2);
    }

    public static void j(Object obj, long j2, boolean z) {
        f.g(obj, j2, z);
    }

    public static void k(byte[] bArr, long j2, byte b2) {
        f.i(bArr, i + j2, b2);
    }

    public static void l(byte[] bArr, long j2, long j3, long j4) {
        f.h(bArr, j2, j3, j4);
    }

    public static long n(Field field) {
        d dVar;
        if (field == null || (dVar = f) == null) {
            return -1L;
        }
        return dVar.a(field);
    }

    public static long o(ByteBuffer byteBuffer) {
        return f.k(byteBuffer, j);
    }

    public static Field p(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void q(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        g(obj, j3, ((255 & b2) << i2) | (H(obj, j3) & (~(255 << i2))));
    }

    public static void r(Object obj, long j2, boolean z) {
        d(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void t(Object obj, long j2, boolean z) {
        q(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static boolean x() {
        return h;
    }

    public static boolean y() {
        return g;
    }

    public static Unsafe z() {
        try {
            return (Unsafe) AccessController.doPrivileged(new o2());
        } catch (Throwable unused) {
            return null;
        }
    }
}
