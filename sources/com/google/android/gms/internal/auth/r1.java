package com.google.android.gms.internal.auth;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes6.dex */
public final class r1<T> implements z1<T> {
    public static final int[] m = new int[0];
    public static final Unsafe n = u2.g();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8537a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final zzfw e;
    public final boolean f;
    public final int[] g;
    public final int h;
    public final int i;
    public final h1 j;
    public final m2 k;
    public final m1 l;

    public r1(int[] iArr, Object[] objArr, int i, int i2, zzfw zzfwVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, t1 t1Var, h1 h1Var, m2 m2Var, y0 y0Var, m1 m1Var, byte[] bArr) {
        this.f8537a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.f = z;
        this.g = iArr2;
        this.h = i3;
        this.i = i4;
        this.j = h1Var;
        this.k = m2Var;
        this.e = zzfwVar;
        this.l = m1Var;
    }

    public static int B(int i) {
        return (i >>> 20) & 255;
    }

    public static long D(Object obj, long j) {
        return ((Long) u2.f(obj, j)).longValue();
    }

    public static Field a(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    public static boolean m(Object obj, int i, z1 z1Var) {
        return z1Var.d(u2.f(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static zzgz p(Object obj) {
        zzeu zzeuVar = (zzeu) obj;
        zzgz zzgzVar = zzeuVar.zzc;
        if (zzgzVar == zzgz.zza()) {
            zzgz b = zzgz.b();
            zzeuVar.zzc = b;
            return b;
        }
        return zzgzVar;
    }

    public static r1 q(Class cls, o1 o1Var, t1 t1Var, h1 h1Var, m2 m2Var, y0 y0Var, m1 m1Var) {
        if (o1Var instanceof y1) {
            return r((y1) o1Var, t1Var, h1Var, m2Var, y0Var, m1Var);
        }
        k2 k2Var = (k2) o1Var;
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0385  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.auth.r1 r(com.google.android.gms.internal.auth.y1 r34, com.google.android.gms.internal.auth.t1 r35, com.google.android.gms.internal.auth.h1 r36, com.google.android.gms.internal.auth.m2 r37, com.google.android.gms.internal.auth.y0 r38, com.google.android.gms.internal.auth.m1 r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.r1.r(com.google.android.gms.internal.auth.y1, com.google.android.gms.internal.auth.t1, com.google.android.gms.internal.auth.h1, com.google.android.gms.internal.auth.m2, com.google.android.gms.internal.auth.y0, com.google.android.gms.internal.auth.m1):com.google.android.gms.internal.auth.r1");
    }

    public static int s(Object obj, long j) {
        return ((Integer) u2.f(obj, j)).intValue();
    }

    public final int A(int i, int i2) {
        int length = (this.f8537a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.f8537a[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final int C(int i) {
        return this.f8537a[i + 1];
    }

    public final zzex E(int i) {
        int i2 = i / 3;
        return (zzex) this.b[i2 + i2 + 1];
    }

    public final z1 F(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        z1 z1Var = (z1) this.b[i3];
        if (z1Var != null) {
            return z1Var;
        }
        z1 b = w1.a().b((Class) this.b[i3 + 1]);
        this.b[i3] = b;
        return b;
    }

    public final Object G(int i) {
        int i2 = i / 3;
        return this.b[i2 + i2];
    }

    public final void b(Object obj, Object obj2, int i) {
        long C = C(i) & ErrorCode.ERR_UNKNOWN;
        if (k(obj2, i)) {
            Object f = u2.f(obj, C);
            Object f2 = u2.f(obj2, C);
            if (f != null && f2 != null) {
                u2.p(obj, C, zzez.d(f, f2));
                h(obj, i);
            } else if (f2 != null) {
                u2.p(obj, C, f2);
                h(obj, i);
            }
        }
    }

    public final void c(Object obj, Object obj2, int i) {
        int C = C(i);
        int i2 = this.f8537a[i];
        long j = C & ErrorCode.ERR_UNKNOWN;
        if (n(obj2, i2, i)) {
            Object f = n(obj, i2, i) ? u2.f(obj, j) : null;
            Object f2 = u2.f(obj2, j);
            if (f != null && f2 != null) {
                u2.p(obj, j, zzez.d(f, f2));
                i(obj, i2, i);
            } else if (f2 != null) {
                u2.p(obj, j, f2);
                i(obj, i2, i);
            }
        }
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final boolean d(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.h) {
            int i6 = this.g[i5];
            int i7 = this.f8537a[i6];
            int C = C(i6);
            int i8 = this.f8537a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = n.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & C) != 0 && !l(obj, i6, i, i2, i10)) {
                return false;
            }
            int B = B(C);
            if (B != 9 && B != 17) {
                if (B != 27) {
                    if (B == 60 || B == 68) {
                        if (n(obj, i7, i6) && !m(obj, C, F(i6))) {
                            return false;
                        }
                    } else if (B != 49) {
                        if (B == 50 && !((zzfq) u2.f(obj, C & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            zzfp zzfpVar = (zzfp) G(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) u2.f(obj, C & ErrorCode.ERR_UNKNOWN);
                if (list.isEmpty()) {
                    continue;
                } else {
                    z1 F = F(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!F.d(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (l(obj, i6, i, i2, i10) && !m(obj, C, F(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final boolean e(Object obj, Object obj2) {
        boolean h;
        int length = this.f8537a.length;
        for (int i = 0; i < length; i += 3) {
            int C = C(i);
            long j = C & ErrorCode.ERR_UNKNOWN;
            switch (B(C)) {
                case 0:
                    if (j(obj, obj2, i) && Double.doubleToLongBits(u2.a(obj, j)) == Double.doubleToLongBits(u2.a(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (j(obj, obj2, i) && Float.floatToIntBits(u2.b(obj, j)) == Float.floatToIntBits(u2.b(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (j(obj, obj2, i) && u2.d(obj, j) == u2.d(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (j(obj, obj2, i) && u2.d(obj, j) == u2.d(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (j(obj, obj2, i) && u2.d(obj, j) == u2.d(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (j(obj, obj2, i) && u2.t(obj, j) == u2.t(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (j(obj, obj2, i) && b2.h(u2.f(obj, j), u2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (j(obj, obj2, i) && b2.h(u2.f(obj, j), u2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (j(obj, obj2, i) && b2.h(u2.f(obj, j), u2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (j(obj, obj2, i) && u2.d(obj, j) == u2.d(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (j(obj, obj2, i) && u2.c(obj, j) == u2.c(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (j(obj, obj2, i) && u2.d(obj, j) == u2.d(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (j(obj, obj2, i) && b2.h(u2.f(obj, j), u2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    h = b2.h(u2.f(obj, j), u2.f(obj2, j));
                    break;
                case 50:
                    h = b2.h(u2.f(obj, j), u2.f(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long z = z(i) & ErrorCode.ERR_UNKNOWN;
                    if (u2.c(obj, z) == u2.c(obj2, z) && b2.h(u2.f(obj, j), u2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!h) {
                return false;
            }
        }
        return this.k.a(obj).equals(this.k.a(obj2));
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void f(Object obj, byte[] bArr, int i, int i2, m0 m0Var) throws IOException {
        if (this.f) {
            v(obj, bArr, i, i2, m0Var);
        } else {
            o(obj, bArr, i, i2, 0, m0Var);
        }
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void g(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.f8537a.length; i += 3) {
            int C = C(i);
            long j = 1048575 & C;
            int i2 = this.f8537a[i];
            switch (B(C)) {
                case 0:
                    if (k(obj2, i)) {
                        u2.l(obj, j, u2.a(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (k(obj2, i)) {
                        u2.m(obj, j, u2.b(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (k(obj2, i)) {
                        u2.o(obj, j, u2.d(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (k(obj2, i)) {
                        u2.o(obj, j, u2.d(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (k(obj2, i)) {
                        u2.o(obj, j, u2.d(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (k(obj2, i)) {
                        u2.k(obj, j, u2.t(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (k(obj2, i)) {
                        u2.p(obj, j, u2.f(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    b(obj, obj2, i);
                    break;
                case 10:
                    if (k(obj2, i)) {
                        u2.p(obj, j, u2.f(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (k(obj2, i)) {
                        u2.o(obj, j, u2.d(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (k(obj2, i)) {
                        u2.n(obj, j, u2.c(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (k(obj2, i)) {
                        u2.o(obj, j, u2.d(obj2, j));
                        h(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    b(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.j.b(obj, obj2, j);
                    break;
                case 50:
                    b2.i(this.l, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (n(obj2, i2, i)) {
                        u2.p(obj, j, u2.f(obj2, j));
                        i(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    c(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (n(obj2, i2, i)) {
                        u2.p(obj, j, u2.f(obj2, j));
                        i(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    c(obj, obj2, i);
                    break;
            }
        }
        b2.f(this.k, obj, obj2);
    }

    public final void h(Object obj, int i) {
        int z = z(i);
        long j = 1048575 & z;
        if (j == 1048575) {
            return;
        }
        u2.n(obj, j, (1 << (z >>> 20)) | u2.c(obj, j));
    }

    public final void i(Object obj, int i, int i2) {
        u2.n(obj, z(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    public final boolean j(Object obj, Object obj2, int i) {
        return k(obj, i) == k(obj2, i);
    }

    public final boolean k(Object obj, int i) {
        int z = z(i);
        long j = z & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (u2.c(obj, j) & (1 << (z >>> 20))) != 0;
        }
        int C = C(i);
        long j2 = C & ErrorCode.ERR_UNKNOWN;
        switch (B(C)) {
            case 0:
                return Double.doubleToRawLongBits(u2.a(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(u2.b(obj, j2)) != 0;
            case 2:
                return u2.d(obj, j2) != 0;
            case 3:
                return u2.d(obj, j2) != 0;
            case 4:
                return u2.c(obj, j2) != 0;
            case 5:
                return u2.d(obj, j2) != 0;
            case 6:
                return u2.c(obj, j2) != 0;
            case 7:
                return u2.t(obj, j2);
            case 8:
                Object f = u2.f(obj, j2);
                if (f instanceof String) {
                    return !((String) f).isEmpty();
                } else if (f instanceof zzee) {
                    return !zzee.zzb.equals(f);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return u2.f(obj, j2) != null;
            case 10:
                return !zzee.zzb.equals(u2.f(obj, j2));
            case 11:
                return u2.c(obj, j2) != 0;
            case 12:
                return u2.c(obj, j2) != 0;
            case 13:
                return u2.c(obj, j2) != 0;
            case 14:
                return u2.d(obj, j2) != 0;
            case 15:
                return u2.c(obj, j2) != 0;
            case 16:
                return u2.d(obj, j2) != 0;
            case 17:
                return u2.f(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean l(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return k(obj, i);
        }
        return (i3 & i4) != 0;
    }

    public final boolean n(Object obj, int i, int i2) {
        return u2.c(obj, (long) (z(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0344, code lost:
        if (r0 != r20) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0346, code lost:
        r15 = r28;
        r14 = r29;
        r12 = r30;
        r13 = r32;
        r11 = r33;
        r9 = r34;
        r1 = r17;
        r2 = r19;
        r3 = r22;
        r5 = r23;
        r6 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x035e, code lost:
        r7 = r33;
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x037f, code lost:
        if (r0 != r15) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03a1, code lost:
        if (r0 != r15) goto L76;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int o(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.auth.m0 r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1112
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.r1.o(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.auth.m0):int");
    }

    public final int t(Object obj, byte[] bArr, int i, int i2, int i3, long j, m0 m0Var) throws IOException {
        Unsafe unsafe = n;
        Object G = G(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzfq) object).zze()) {
            zzfq zzb = zzfq.zza().zzb();
            m1.a(zzb, object);
            unsafe.putObject(obj, j, zzb);
        }
        zzfp zzfpVar = (zzfp) G;
        throw null;
    }

    public final int u(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, m0 m0Var) throws IOException {
        Unsafe unsafe = n;
        long j2 = this.f8537a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(n0.n(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(n0.b(bArr, i))));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int m2 = n0.m(bArr, i, m0Var);
                    unsafe.putObject(obj, j, Long.valueOf(m0Var.b));
                    unsafe.putInt(obj, j2, i4);
                    return m2;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int j3 = n0.j(bArr, i, m0Var);
                    unsafe.putObject(obj, j, Integer.valueOf(m0Var.f8531a));
                    unsafe.putInt(obj, j2, i4);
                    return j3;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(n0.n(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(n0.b(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int m3 = n0.m(bArr, i, m0Var);
                    unsafe.putObject(obj, j, Boolean.valueOf(m0Var.b != 0));
                    unsafe.putInt(obj, j2, i4);
                    return m3;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int j4 = n0.j(bArr, i, m0Var);
                    int i9 = m0Var.f8531a;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !y2.d(bArr, j4, j4 + i9)) {
                        throw zzfa.zzb();
                    } else {
                        unsafe.putObject(obj, j, new String(bArr, j4, i9, zzez.f8562a));
                        j4 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return j4;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int d = n0.d(F(i8), bArr, i, i2, m0Var);
                    Object object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, m0Var.c);
                    } else {
                        unsafe.putObject(obj, j, zzez.d(object, m0Var.c));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return d;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int a2 = n0.a(bArr, i, m0Var);
                    unsafe.putObject(obj, j, m0Var.c);
                    unsafe.putInt(obj, j2, i4);
                    return a2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int j5 = n0.j(bArr, i, m0Var);
                    int i10 = m0Var.f8531a;
                    zzex E = E(i8);
                    if (E != null && !E.zza()) {
                        p(obj).d(i3, Long.valueOf(i10));
                    } else {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    }
                    return j5;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int j6 = n0.j(bArr, i, m0Var);
                    unsafe.putObject(obj, j, Integer.valueOf(zzei.zzb(m0Var.f8531a)));
                    unsafe.putInt(obj, j2, i4);
                    return j6;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int m4 = n0.m(bArr, i, m0Var);
                    unsafe.putObject(obj, j, Long.valueOf(zzei.zzc(m0Var.b)));
                    unsafe.putInt(obj, j2, i4);
                    return m4;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int c = n0.c(F(i8), bArr, i, i2, (i3 & (-8)) | 4, m0Var);
                    Object object2 = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, m0Var.c);
                    } else {
                        unsafe.putObject(obj, j, zzez.d(object2, m0Var.c));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return c;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02d9, code lost:
        if (r0 != r5) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02db, code lost:
        r15 = r31;
        r14 = r32;
        r12 = r33;
        r13 = r35;
        r11 = r36;
        r10 = r19;
        r1 = r20;
        r2 = r23;
        r6 = r26;
        r7 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02f1, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0322, code lost:
        if (r0 != r15) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0345, code lost:
        if (r0 != r15) goto L135;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int v(java.lang.Object r32, byte[] r33, int r34, int r35, com.google.android.gms.internal.auth.m0 r36) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 944
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.r1.v(java.lang.Object, byte[], int, int, com.google.android.gms.internal.auth.m0):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0152  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:119:0x021a -> B:120:0x021b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x014f -> B:67:0x0150). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x01cc -> B:100:0x01cd). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int w(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.auth.m0 r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.r1.w(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.auth.m0):int");
    }

    public final int x(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return A(i, 0);
    }

    public final int y(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return A(i, i2);
    }

    public final int z(int i) {
        return this.f8537a[i + 2];
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final int zza(Object obj) {
        int i;
        int zzc;
        int length = this.f8537a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int C = C(i3);
            int i4 = this.f8537a[i3];
            long j = 1048575 & C;
            int i5 = 37;
            switch (B(C)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzez.zzc(Double.doubleToLongBits(u2.a(obj, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(u2.b(obj, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzez.zzc(u2.d(obj, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzez.zzc(u2.d(obj, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzez.zzc(u2.d(obj, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzez.zza(u2.t(obj, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) u2.f(obj, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object f = u2.f(obj, j);
                    if (f != null) {
                        i5 = f.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = u2.f(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzez.zzc(u2.d(obj, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = u2.c(obj, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzez.zzc(u2.d(obj, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object f2 = u2.f(obj, j);
                    if (f2 != null) {
                        i5 = f2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    zzc = u2.f(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = u2.f(obj, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(Double.doubleToLongBits(((Double) u2.f(obj, j)).doubleValue()));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(((Float) u2.f(obj, j)).floatValue());
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(D(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(D(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(D(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zza(((Boolean) u2.f(obj, j)).booleanValue());
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) u2.f(obj, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = u2.f(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = u2.f(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(D(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = s(obj, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzez.zzc(D(obj, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (n(obj, i4, i3)) {
                        i = i2 * 53;
                        zzc = u2.f(obj, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return (i2 * 53) + this.k.a(obj).hashCode();
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final Object zzd() {
        return ((zzeu) this.e).zzi(4, null, null);
    }

    @Override // com.google.android.gms.internal.auth.z1
    public final void zze(Object obj) {
        int i;
        int i2 = this.h;
        while (true) {
            i = this.i;
            if (i2 >= i) {
                break;
            }
            long C = C(this.g[i2]) & ErrorCode.ERR_UNKNOWN;
            Object f = u2.f(obj, C);
            if (f != null) {
                ((zzfq) f).zzc();
                u2.p(obj, C, f);
            }
            i2++;
        }
        int length = this.g.length;
        while (i < length) {
            this.j.a(obj, this.g[i]);
            i++;
        }
        this.k.e(obj);
    }
}
