package com.google.protobuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;
/* loaded from: classes11.dex */
public final class a1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f11716a = J();
    public static final Class<?> b = com.google.protobuf.b.b();
    public static final boolean c = r(Long.TYPE);
    public static final boolean d = r(Integer.TYPE);
    public static final e e = G();
    public static final boolean f = b0();
    public static final boolean g = a0();
    public static final long h = m(byte[].class);
    public static final long i;
    public static final boolean j;

    /* loaded from: classes11.dex */
    public static class a implements PrivilegedExceptionAction<Unsafe> {
        @Override // java.security.PrivilegedExceptionAction
        /* renamed from: a */
        public Unsafe run() throws Exception {
            java.lang.reflect.Field[] declaredFields;
            for (java.lang.reflect.Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }
    }

    /* loaded from: classes11.dex */
    public static final class b extends e {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.a1.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public boolean e(Object obj, long j) {
            return a1.j ? a1.v(obj, j) : a1.w(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public byte f(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public byte g(Object obj, long j) {
            return a1.j ? a1.z(obj, j) : a1.A(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public double h(Object obj, long j) {
            return Double.longBitsToDouble(l(obj, j));
        }

        @Override // com.google.protobuf.a1.e
        public float i(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.protobuf.a1.e
        public long k(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public Object n(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.a1.e
        public void p(Object obj, long j, boolean z) {
            if (a1.j) {
                a1.P(obj, j, z);
            } else {
                a1.Q(obj, j, z);
            }
        }

        @Override // com.google.protobuf.a1.e
        public void q(long j, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public void r(Object obj, long j, byte b) {
            if (a1.j) {
                a1.T(obj, j, b);
            } else {
                a1.U(obj, j, b);
            }
        }

        @Override // com.google.protobuf.a1.e
        public void s(Object obj, long j, double d) {
            v(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.protobuf.a1.e
        public void t(Object obj, long j, float f) {
            u(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.protobuf.a1.e
        public boolean y() {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static final class c extends e {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.a1.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public boolean e(Object obj, long j) {
            return a1.j ? a1.v(obj, j) : a1.w(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public byte f(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public byte g(Object obj, long j) {
            return a1.j ? a1.z(obj, j) : a1.A(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public double h(Object obj, long j) {
            return Double.longBitsToDouble(l(obj, j));
        }

        @Override // com.google.protobuf.a1.e
        public float i(Object obj, long j) {
            return Float.intBitsToFloat(j(obj, j));
        }

        @Override // com.google.protobuf.a1.e
        public long k(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public Object n(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.a1.e
        public void p(Object obj, long j, boolean z) {
            if (a1.j) {
                a1.P(obj, j, z);
            } else {
                a1.Q(obj, j, z);
            }
        }

        @Override // com.google.protobuf.a1.e
        public void q(long j, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.a1.e
        public void r(Object obj, long j, byte b) {
            if (a1.j) {
                a1.T(obj, j, b);
            } else {
                a1.U(obj, j, b);
            }
        }

        @Override // com.google.protobuf.a1.e
        public void s(Object obj, long j, double d) {
            v(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.protobuf.a1.e
        public void t(Object obj, long j, float f) {
            u(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.protobuf.a1.e
        public boolean y() {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static final class d extends e {
        public d(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.a1.e
        public void c(long j, byte[] bArr, long j2, long j3) {
            this.f11717a.copyMemory((Object) null, j, bArr, a1.h + j2, j3);
        }

        @Override // com.google.protobuf.a1.e
        public void d(byte[] bArr, long j, long j2, long j3) {
            this.f11717a.copyMemory(bArr, a1.h + j, (Object) null, j2, j3);
        }

        @Override // com.google.protobuf.a1.e
        public boolean e(Object obj, long j) {
            return this.f11717a.getBoolean(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public byte f(long j) {
            return this.f11717a.getByte(j);
        }

        @Override // com.google.protobuf.a1.e
        public byte g(Object obj, long j) {
            return this.f11717a.getByte(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public double h(Object obj, long j) {
            return this.f11717a.getDouble(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public float i(Object obj, long j) {
            return this.f11717a.getFloat(obj, j);
        }

        @Override // com.google.protobuf.a1.e
        public long k(long j) {
            return this.f11717a.getLong(j);
        }

        @Override // com.google.protobuf.a1.e
        public Object n(java.lang.reflect.Field field) {
            return m(this.f11717a.staticFieldBase(field), this.f11717a.staticFieldOffset(field));
        }

        @Override // com.google.protobuf.a1.e
        public void p(Object obj, long j, boolean z) {
            this.f11717a.putBoolean(obj, j, z);
        }

        @Override // com.google.protobuf.a1.e
        public void q(long j, byte b) {
            this.f11717a.putByte(j, b);
        }

        @Override // com.google.protobuf.a1.e
        public void r(Object obj, long j, byte b) {
            this.f11717a.putByte(obj, j, b);
        }

        @Override // com.google.protobuf.a1.e
        public void s(Object obj, long j, double d) {
            this.f11717a.putDouble(obj, j, d);
        }

        @Override // com.google.protobuf.a1.e
        public void t(Object obj, long j, float f) {
            this.f11717a.putFloat(obj, j, f);
        }

        @Override // com.google.protobuf.a1.e
        public boolean x() {
            if (super.x()) {
                try {
                    Class<?> cls = this.f11717a.getClass();
                    Class<?> cls2 = Long.TYPE;
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
                    a1.M(th);
                    return false;
                }
            }
            return false;
        }

        @Override // com.google.protobuf.a1.e
        public boolean y() {
            if (super.y()) {
                try {
                    Class<?> cls = this.f11717a.getClass();
                    Class<?> cls2 = Long.TYPE;
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
                    a1.M(th);
                    return false;
                }
            }
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class e {

        /* renamed from: a  reason: collision with root package name */
        public Unsafe f11717a;

        public e(Unsafe unsafe) {
            this.f11717a = unsafe;
        }

        public final int a(Class<?> cls) {
            return this.f11717a.arrayBaseOffset(cls);
        }

        public final int b(Class<?> cls) {
            return this.f11717a.arrayIndexScale(cls);
        }

        public abstract void c(long j, byte[] bArr, long j2, long j3);

        public abstract void d(byte[] bArr, long j, long j2, long j3);

        public abstract boolean e(Object obj, long j);

        public abstract byte f(long j);

        public abstract byte g(Object obj, long j);

        public abstract double h(Object obj, long j);

        public abstract float i(Object obj, long j);

        public final int j(Object obj, long j) {
            return this.f11717a.getInt(obj, j);
        }

        public abstract long k(long j);

        public final long l(Object obj, long j) {
            return this.f11717a.getLong(obj, j);
        }

        public final Object m(Object obj, long j) {
            return this.f11717a.getObject(obj, j);
        }

        public abstract Object n(java.lang.reflect.Field field);

        public final long o(java.lang.reflect.Field field) {
            return this.f11717a.objectFieldOffset(field);
        }

        public abstract void p(Object obj, long j, boolean z);

        public abstract void q(long j, byte b);

        public abstract void r(Object obj, long j, byte b);

        public abstract void s(Object obj, long j, double d);

        public abstract void t(Object obj, long j, float f);

        public final void u(Object obj, long j, int i) {
            this.f11717a.putInt(obj, j, i);
        }

        public final void v(Object obj, long j, long j2) {
            this.f11717a.putLong(obj, j, j2);
        }

        public final void w(Object obj, long j, Object obj2) {
            this.f11717a.putObject(obj, j, obj2);
        }

        public boolean x() {
            Unsafe unsafe = this.f11717a;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("getInt", Object.class, cls2);
                cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
                cls.getMethod("getLong", Object.class, cls2);
                cls.getMethod("putLong", Object.class, cls2, cls2);
                cls.getMethod("getObject", Object.class, cls2);
                cls.getMethod("putObject", Object.class, cls2, Object.class);
                return true;
            } catch (Throwable th) {
                a1.M(th);
                return false;
            }
        }

        public boolean y() {
            Unsafe unsafe = this.f11717a;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                return a1.b() != null;
            } catch (Throwable th) {
                a1.M(th);
                return false;
            }
        }
    }

    static {
        m(boolean[].class);
        n(boolean[].class);
        m(int[].class);
        n(int[].class);
        m(long[].class);
        n(long[].class);
        m(float[].class);
        n(float[].class);
        m(double[].class);
        n(double[].class);
        m(Object[].class);
        n(Object[].class);
        i = t(o());
        j = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static byte A(Object obj, long j2) {
        return (byte) ((D(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255);
    }

    public static double B(Object obj, long j2) {
        return e.h(obj, j2);
    }

    public static float C(Object obj, long j2) {
        return e.i(obj, j2);
    }

    public static int D(Object obj, long j2) {
        return e.j(obj, j2);
    }

    public static long E(long j2) {
        return e.k(j2);
    }

    public static long F(Object obj, long j2) {
        return e.l(obj, j2);
    }

    public static e G() {
        Unsafe unsafe = f11716a;
        if (unsafe == null) {
            return null;
        }
        if (com.google.protobuf.b.c()) {
            if (c) {
                return new c(unsafe);
            }
            if (d) {
                return new b(unsafe);
            }
            return null;
        }
        return new d(unsafe);
    }

    public static Object H(Object obj, long j2) {
        return e.m(obj, j2);
    }

    public static Object I(java.lang.reflect.Field field) {
        return e.n(field);
    }

    public static Unsafe J() {
        try {
            return (Unsafe) AccessController.doPrivileged(new a());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean K() {
        return g;
    }

    public static boolean L() {
        return f;
    }

    public static void M(Throwable th) {
        Logger logger = Logger.getLogger(a1.class.getName());
        Level level = Level.WARNING;
        logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static long N(java.lang.reflect.Field field) {
        return e.o(field);
    }

    public static void O(Object obj, long j2, boolean z) {
        e.p(obj, j2, z);
    }

    public static void P(Object obj, long j2, boolean z) {
        T(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void Q(Object obj, long j2, boolean z) {
        U(obj, j2, z ? (byte) 1 : (byte) 0);
    }

    public static void R(long j2, byte b2) {
        e.q(j2, b2);
    }

    public static void S(byte[] bArr, long j2, byte b2) {
        e.r(bArr, h + j2, b2);
    }

    public static void T(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int D = D(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        X(obj, j3, ((255 & b2) << i2) | (D & (~(255 << i2))));
    }

    public static void U(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        X(obj, j3, ((255 & b2) << i2) | (D(obj, j3) & (~(255 << i2))));
    }

    public static void V(Object obj, long j2, double d2) {
        e.s(obj, j2, d2);
    }

    public static void W(Object obj, long j2, float f2) {
        e.t(obj, j2, f2);
    }

    public static void X(Object obj, long j2, int i2) {
        e.u(obj, j2, i2);
    }

    public static void Y(Object obj, long j2, long j3) {
        e.v(obj, j2, j3);
    }

    public static void Z(Object obj, long j2, Object obj2) {
        e.w(obj, j2, obj2);
    }

    public static boolean a0() {
        e eVar = e;
        if (eVar == null) {
            return false;
        }
        return eVar.x();
    }

    public static /* synthetic */ java.lang.reflect.Field b() {
        return o();
    }

    public static boolean b0() {
        e eVar = e;
        if (eVar == null) {
            return false;
        }
        return eVar.y();
    }

    public static long k(ByteBuffer byteBuffer) {
        return e.l(byteBuffer, i);
    }

    public static <T> T l(Class<T> cls) {
        try {
            return (T) f11716a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int m(Class<?> cls) {
        if (g) {
            return e.a(cls);
        }
        return -1;
    }

    public static int n(Class<?> cls) {
        if (g) {
            return e.b(cls);
        }
        return -1;
    }

    public static java.lang.reflect.Field o() {
        java.lang.reflect.Field s;
        if (!com.google.protobuf.b.c() || (s = s(Buffer.class, "effectiveDirectAddress")) == null) {
            java.lang.reflect.Field s2 = s(Buffer.class, "address");
            if (s2 == null || s2.getType() != Long.TYPE) {
                return null;
            }
            return s2;
        }
        return s;
    }

    public static void p(long j2, byte[] bArr, long j3, long j4) {
        e.c(j2, bArr, j3, j4);
    }

    public static void q(byte[] bArr, long j2, long j3, long j4) {
        e.d(bArr, j2, j3, j4);
    }

    public static boolean r(Class<?> cls) {
        if (com.google.protobuf.b.c()) {
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
        return false;
    }

    public static java.lang.reflect.Field s(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long t(java.lang.reflect.Field field) {
        e eVar;
        if (field == null || (eVar = e) == null) {
            return -1L;
        }
        return eVar.o(field);
    }

    public static boolean u(Object obj, long j2) {
        return e.e(obj, j2);
    }

    public static boolean v(Object obj, long j2) {
        return z(obj, j2) != 0;
    }

    public static boolean w(Object obj, long j2) {
        return A(obj, j2) != 0;
    }

    public static byte x(long j2) {
        return e.f(j2);
    }

    public static byte y(byte[] bArr, long j2) {
        return e.g(bArr, h + j2);
    }

    public static byte z(Object obj, long j2) {
        return (byte) ((D(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255);
    }
}
