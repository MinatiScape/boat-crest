package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes10.dex */
public final class u0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f10995a = Logger.getLogger(u0.class.getName());
    public static final Unsafe b = G();
    public static final Class<?> c = com.google.crypto.tink.shaded.protobuf.b.b();
    public static final boolean d = p(Long.TYPE);
    public static final boolean e = p(Integer.TYPE);
    public static final e f = E();
    public static final boolean g = X();
    public static final boolean h = W();
    public static final long i = k(byte[].class);
    public static final long j;
    public static final boolean k;

    /* loaded from: classes10.dex */
    public class a implements PrivilegedExceptionAction<Unsafe> {
        @Override // java.security.PrivilegedExceptionAction
        /* renamed from: a */
        public Unsafe run() throws Exception {
            Field[] declaredFields;
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends e {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public boolean e(Object obj, long j) {
            return u0.k ? u0.t(obj, j) : u0.u(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte f(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte g(Object obj, long j) {
            return u0.k ? u0.x(obj, j) : u0.y(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public double h(Object obj, long j) {
            return Double.longBitsToDouble(l(obj, j));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public float i(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public long k(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void o(Object obj, long j, boolean z) {
            if (u0.k) {
                u0.L(obj, j, z);
            } else {
                u0.M(obj, j, z);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void p(long j, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void q(Object obj, long j, byte b) {
            if (u0.k) {
                u0.P(obj, j, b);
            } else {
                u0.Q(obj, j, b);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void r(Object obj, long j, double d) {
            u(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void s(Object obj, long j, float f) {
            t(obj, j, Float.floatToIntBits(f));
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends e {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public boolean e(Object obj, long j) {
            return u0.k ? u0.t(obj, j) : u0.u(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte f(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte g(Object obj, long j) {
            return u0.k ? u0.x(obj, j) : u0.y(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public double h(Object obj, long j) {
            return Double.longBitsToDouble(l(obj, j));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public float i(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public long k(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void o(Object obj, long j, boolean z) {
            if (u0.k) {
                u0.L(obj, j, z);
            } else {
                u0.M(obj, j, z);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void p(long j, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void q(Object obj, long j, byte b) {
            if (u0.k) {
                u0.P(obj, j, b);
            } else {
                u0.Q(obj, j, b);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void r(Object obj, long j, double d) {
            u(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void s(Object obj, long j, float f) {
            t(obj, j, Float.floatToIntBits(f));
        }
    }

    /* loaded from: classes10.dex */
    public static final class d extends e {
        public d(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            this.f10996a.copyMemory((Object) null, j, bArr, u0.i + j2, j3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            this.f10996a.copyMemory(bArr, u0.i + j, (Object) null, j2, j3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public boolean e(Object obj, long j) {
            return this.f10996a.getBoolean(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte f(long j) {
            return this.f10996a.getByte(j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public byte g(Object obj, long j) {
            return this.f10996a.getByte(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public double h(Object obj, long j) {
            return this.f10996a.getDouble(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public float i(Object obj, long j) {
            return this.f10996a.getFloat(obj, j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public long k(long j) {
            return this.f10996a.getLong(j);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void o(Object obj, long j, boolean z) {
            this.f10996a.putBoolean(obj, j, z);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void p(long j, byte b) {
            this.f10996a.putByte(j, b);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void q(Object obj, long j, byte b) {
            this.f10996a.putByte(obj, j, b);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void r(Object obj, long j, double d) {
            this.f10996a.putDouble(obj, j, d);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.u0.e
        public void s(Object obj, long j, float f) {
            this.f10996a.putFloat(obj, j, f);
        }
    }

    /* loaded from: classes10.dex */
    public static abstract class e {

        /* renamed from: a  reason: collision with root package name */
        public Unsafe f10996a;

        public e(Unsafe unsafe) {
            this.f10996a = unsafe;
        }

        public final int a(Class<?> cls) {
            return this.f10996a.arrayBaseOffset(cls);
        }

        public final int b(Class<?> cls) {
            return this.f10996a.arrayIndexScale(cls);
        }

        public abstract void c(long j, byte[] bArr, long j2, long j3);

        public abstract void d(byte[] bArr, long j, long j2, long j3);

        public abstract boolean e(Object obj, long j);

        public abstract byte f(long j);

        public abstract byte g(Object obj, long j);

        public abstract double h(Object obj, long j);

        public abstract float i(Object obj, long j);

        public final int j(Object obj, long j) {
            return this.f10996a.getInt(obj, j);
        }

        public abstract long k(long j);

        public final long l(Object obj, long j) {
            return this.f10996a.getLong(obj, j);
        }

        public final Object m(Object obj, long j) {
            return this.f10996a.getObject(obj, j);
        }

        public final long n(Field field) {
            return this.f10996a.objectFieldOffset(field);
        }

        public abstract void o(Object obj, long j, boolean z);

        public abstract void p(long j, byte b);

        public abstract void q(Object obj, long j, byte b);

        public abstract void r(Object obj, long j, double d);

        public abstract void s(Object obj, long j, float f);

        public final void t(Object obj, long j, int i) {
            this.f10996a.putInt(obj, j, i);
        }

        public final void u(Object obj, long j, long j2) {
            this.f10996a.putLong(obj, j, j2);
        }

        public final void v(Object obj, long j, Object obj2) {
            this.f10996a.putObject(obj, j, obj2);
        }
    }

    static {
        k(boolean[].class);
        l(boolean[].class);
        k(int[].class);
        l(int[].class);
        k(long[].class);
        l(long[].class);
        k(float[].class);
        l(float[].class);
        k(double[].class);
        l(double[].class);
        k(Object[].class);
        l(Object[].class);
        j = r(m());
        k = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static float A(Object obj, long j2) {
        return f.i(obj, j2);
    }

    public static int B(Object obj, long j2) {
        return f.j(obj, j2);
    }

    public static long C(long j2) {
        return f.k(j2);
    }

    public static long D(Object obj, long j2) {
        return f.l(obj, j2);
    }

    public static e E() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return null;
        }
        if (com.google.crypto.tink.shaded.protobuf.b.c()) {
            if (d) {
                return new c(unsafe);
            }
            if (e) {
                return new b(unsafe);
            }
            return null;
        }
        return new d(unsafe);
    }

    public static Object F(Object obj, long j2) {
        return f.m(obj, j2);
    }

    public static Unsafe G() {
        try {
            return (Unsafe) AccessController.doPrivileged(new a());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean H() {
        return h;
    }

    public static boolean I() {
        return g;
    }

    public static long J(Field field) {
        return f.n(field);
    }

    public static void K(Object obj, long j2, boolean z) {
        f.o(obj, j2, z);
    }

    public static void L(Object obj, long j2, boolean z) {
        P(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void M(Object obj, long j2, boolean z) {
        Q(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void N(long j2, byte b2) {
        f.p(j2, b2);
    }

    public static void O(byte[] bArr, long j2, byte b2) {
        f.q(bArr, i + j2, b2);
    }

    public static void P(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int B = B(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        T(obj, j3, ((255 & b2) << i2) | (B & (~(255 << i2))));
    }

    public static void Q(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        T(obj, j3, ((255 & b2) << i2) | (B(obj, j3) & (~(255 << i2))));
    }

    public static void R(Object obj, long j2, double d2) {
        f.r(obj, j2, d2);
    }

    public static void S(Object obj, long j2, float f2) {
        f.s(obj, j2, f2);
    }

    public static void T(Object obj, long j2, int i2) {
        f.t(obj, j2, i2);
    }

    public static void U(Object obj, long j2, long j3) {
        f.u(obj, j2, j3);
    }

    public static void V(Object obj, long j2, Object obj2) {
        f.v(obj, j2, obj2);
    }

    public static boolean W() {
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
            if (com.google.crypto.tink.shaded.protobuf.b.c()) {
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
            Logger logger = f10995a;
            Level level = Level.WARNING;
            logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    public static boolean X() {
        Unsafe unsafe = b;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getLong", Object.class, cls2);
            if (m() == null) {
                return false;
            }
            if (com.google.crypto.tink.shaded.protobuf.b.c()) {
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
            Logger logger = f10995a;
            Level level = Level.WARNING;
            logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
            return false;
        }
    }

    public static long i(ByteBuffer byteBuffer) {
        return f.l(byteBuffer, j);
    }

    public static <T> T j(Class<T> cls) {
        try {
            return (T) b.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int k(Class<?> cls) {
        if (h) {
            return f.a(cls);
        }
        return -1;
    }

    public static int l(Class<?> cls) {
        if (h) {
            return f.b(cls);
        }
        return -1;
    }

    public static Field m() {
        Field q;
        if (!com.google.crypto.tink.shaded.protobuf.b.c() || (q = q(Buffer.class, "effectiveDirectAddress")) == null) {
            Field q2 = q(Buffer.class, "address");
            if (q2 == null || q2.getType() != Long.TYPE) {
                return null;
            }
            return q2;
        }
        return q;
    }

    public static void n(long j2, byte[] bArr, long j3, long j4) {
        f.c(j2, bArr, j3, j4);
    }

    public static void o(byte[] bArr, long j2, long j3, long j4) {
        f.d(bArr, j2, j3, j4);
    }

    public static boolean p(Class<?> cls) {
        if (com.google.crypto.tink.shaded.protobuf.b.c()) {
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

    public static Field q(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long r(Field field) {
        e eVar;
        if (field == null || (eVar = f) == null) {
            return -1L;
        }
        return eVar.n(field);
    }

    public static boolean s(Object obj, long j2) {
        return f.e(obj, j2);
    }

    public static boolean t(Object obj, long j2) {
        return x(obj, j2) != 0;
    }

    public static boolean u(Object obj, long j2) {
        return y(obj, j2) != 0;
    }

    public static byte v(long j2) {
        return f.f(j2);
    }

    public static byte w(byte[] bArr, long j2) {
        return f.g(bArr, i + j2);
    }

    public static byte x(Object obj, long j2) {
        return (byte) ((B(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255);
    }

    public static byte y(Object obj, long j2) {
        return (byte) ((B(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255);
    }

    public static double z(Object obj, long j2) {
        return f.h(obj, j2);
    }
}
