package com.google.common.hash;

import com.google.common.primitives.Longs;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;
/* loaded from: classes10.dex */
public final class m {

    /* renamed from: a  reason: collision with root package name */
    public static final c f10656a;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class b implements c {
        public static final b INSTANCE = new a("INSTANCE", 0);
        private static final /* synthetic */ b[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends b {
            public a(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.hash.m.b, com.google.common.hash.m.c
            public long getLongLittleEndian(byte[] bArr, int i) {
                return Longs.fromBytes(bArr[i + 7], bArr[i + 6], bArr[i + 5], bArr[i + 4], bArr[i + 3], bArr[i + 2], bArr[i + 1], bArr[i]);
            }

            @Override // com.google.common.hash.m.b
            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                long j2 = 255;
                for (int i2 = 0; i2 < 8; i2++) {
                    bArr[i + i2] = (byte) ((j & j2) >> (i2 * 8));
                    j2 <<= 8;
                }
            }
        }

        private static /* synthetic */ b[] $values() {
            return new b[]{INSTANCE};
        }

        private b(String str, int i) {
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) $VALUES.clone();
        }

        @Override // com.google.common.hash.m.c
        public abstract /* synthetic */ long getLongLittleEndian(byte[] bArr, int i);

        public abstract /* synthetic */ void putLongLittleEndian(byte[] bArr, int i, long j);
    }

    /* loaded from: classes10.dex */
    public interface c {
        long getLongLittleEndian(byte[] bArr, int i);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class d implements c {
        private static final int BYTE_ARRAY_BASE_OFFSET;
        private static final Unsafe theUnsafe;
        public static final d UNSAFE_LITTLE_ENDIAN = new a("UNSAFE_LITTLE_ENDIAN", 0);
        public static final d UNSAFE_BIG_ENDIAN = new b("UNSAFE_BIG_ENDIAN", 1);
        private static final /* synthetic */ d[] $VALUES = $values();

        /* loaded from: classes10.dex */
        public enum a extends d {
            public a(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.hash.m.d, com.google.common.hash.m.c
            public long getLongLittleEndian(byte[] bArr, int i) {
                return d.theUnsafe.getLong(bArr, i + d.BYTE_ARRAY_BASE_OFFSET);
            }

            @Override // com.google.common.hash.m.d
            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                d.theUnsafe.putLong(bArr, i + d.BYTE_ARRAY_BASE_OFFSET, j);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends d {
            public b(String str, int i) {
                super(str, i);
            }

            @Override // com.google.common.hash.m.d, com.google.common.hash.m.c
            public long getLongLittleEndian(byte[] bArr, int i) {
                return Long.reverseBytes(d.theUnsafe.getLong(bArr, i + d.BYTE_ARRAY_BASE_OFFSET));
            }

            @Override // com.google.common.hash.m.d
            public void putLongLittleEndian(byte[] bArr, int i, long j) {
                d.theUnsafe.putLong(bArr, i + d.BYTE_ARRAY_BASE_OFFSET, Long.reverseBytes(j));
            }
        }

        /* loaded from: classes10.dex */
        public class c implements PrivilegedExceptionAction<Unsafe> {
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
                throw new NoSuchFieldError("the Unsafe");
            }
        }

        private static /* synthetic */ d[] $values() {
            return new d[]{UNSAFE_LITTLE_ENDIAN, UNSAFE_BIG_ENDIAN};
        }

        static {
            Unsafe unsafe = getUnsafe();
            theUnsafe = unsafe;
            BYTE_ARRAY_BASE_OFFSET = unsafe.arrayBaseOffset(byte[].class);
            if (unsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        private d(String str, int i) {
        }

        private static Unsafe getUnsafe() {
            try {
                try {
                    return Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                return (Unsafe) AccessController.doPrivileged(new c());
            }
        }

        public static d valueOf(String str) {
            return (d) Enum.valueOf(d.class, str);
        }

        public static d[] values() {
            return (d[]) $VALUES.clone();
        }

        @Override // com.google.common.hash.m.c
        public abstract /* synthetic */ long getLongLittleEndian(byte[] bArr, int i);

        public abstract /* synthetic */ void putLongLittleEndian(byte[] bArr, int i, long j);
    }

    static {
        c cVar = b.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                    cVar = d.UNSAFE_LITTLE_ENDIAN;
                } else {
                    cVar = d.UNSAFE_BIG_ENDIAN;
                }
            }
        } catch (Throwable unused) {
        }
        f10656a = cVar;
    }

    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long b(byte[] bArr, int i) {
        return f10656a.getLongLittleEndian(bArr, i);
    }
}
