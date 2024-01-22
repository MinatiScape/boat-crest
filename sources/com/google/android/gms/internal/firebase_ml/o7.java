package com.google.android.gms.internal.firebase_ml;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes7.dex */
public final class o7<T> implements c8<T> {
    public static final int[] q = new int[0];
    public static final Unsafe r = b.L();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8711a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final zzyk e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int[] i;
    public final int j;
    public final int k;
    public final s7 l;
    public final a7 m;
    public final u8<?, ?> n;
    public final o6<?> o;
    public final k7 p;

    public o7(int[] iArr, Object[] objArr, int i, int i2, zzyk zzykVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, s7 s7Var, a7 a7Var, u8<?, ?> u8Var, o6<?> o6Var, k7 k7Var) {
        this.f8711a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        boolean z3 = zzykVar instanceof zzwz;
        this.g = z;
        this.f = o6Var != null && o6Var.d(zzykVar);
        this.h = false;
        this.i = iArr2;
        this.j = i3;
        this.k = i4;
        this.l = s7Var;
        this.m = a7Var;
        this.n = u8Var;
        this.o = o6Var;
        this.e = zzykVar;
        this.p = k7Var;
    }

    public static List<?> L(Object obj, long j) {
        return (List) b.z(obj, j);
    }

    public static <T> double M(T t, long j) {
        return ((Double) b.z(t, j)).doubleValue();
    }

    public static <T> float N(T t, long j) {
        return ((Float) b.z(t, j)).floatValue();
    }

    public static <T> int O(T t, long j) {
        return ((Integer) b.z(t, j)).intValue();
    }

    public static <T> long P(T t, long j) {
        return ((Long) b.z(t, j)).longValue();
    }

    public static <T> boolean Q(T t, long j) {
        return ((Boolean) b.z(t, j)).booleanValue();
    }

    public static <UT, UB> int c(u8<UT, UB> u8Var, T t) {
        return u8Var.d(u8Var.e(t));
    }

    public static int n(byte[] bArr, int i, int i2, zzaan zzaanVar, Class<?> cls, v5 v5Var) throws IOException {
        switch (r7.f8726a[zzaanVar.ordinal()]) {
            case 1:
                int k = w5.k(bArr, i, v5Var);
                v5Var.c = Boolean.valueOf(v5Var.b != 0);
                return k;
            case 2:
                return w5.q(bArr, i, v5Var);
            case 3:
                v5Var.c = Double.valueOf(w5.m(bArr, i));
                return i + 8;
            case 4:
            case 5:
                v5Var.c = Integer.valueOf(w5.h(bArr, i));
                return i + 4;
            case 6:
            case 7:
                v5Var.c = Long.valueOf(w5.l(bArr, i));
                return i + 8;
            case 8:
                v5Var.c = Float.valueOf(w5.o(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int i3 = w5.i(bArr, i, v5Var);
                v5Var.c = Integer.valueOf(v5Var.f8744a);
                return i3;
            case 12:
            case 13:
                int k2 = w5.k(bArr, i, v5Var);
                v5Var.c = Long.valueOf(v5Var.b);
                return k2;
            case 14:
                return w5.g(x7.c().b(cls), bArr, i, i2, v5Var);
            case 15:
                int i4 = w5.i(bArr, i, v5Var);
                v5Var.c = Integer.valueOf(zzwh.zzda(v5Var.f8744a));
                return i4;
            case 16:
                int k3 = w5.k(bArr, i, v5Var);
                v5Var.c = Long.valueOf(zzwh.zzv(v5Var.b));
                return k3;
            case 17:
                return w5.p(bArr, i, v5Var);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.android.gms.internal.firebase_ml.o7<T> o(java.lang.Class<T> r33, com.google.android.gms.internal.firebase_ml.l7 r34, com.google.android.gms.internal.firebase_ml.s7 r35, com.google.android.gms.internal.firebase_ml.a7 r36, com.google.android.gms.internal.firebase_ml.u8<?, ?> r37, com.google.android.gms.internal.firebase_ml.o6<?> r38, com.google.android.gms.internal.firebase_ml.k7 r39) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.o(java.lang.Class, com.google.android.gms.internal.firebase_ml.l7, com.google.android.gms.internal.firebase_ml.s7, com.google.android.gms.internal.firebase_ml.a7, com.google.android.gms.internal.firebase_ml.u8, com.google.android.gms.internal.firebase_ml.o6, com.google.android.gms.internal.firebase_ml.k7):com.google.android.gms.internal.firebase_ml.o7");
    }

    public static Field q(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public static void r(int i, Object obj, p pVar) throws IOException {
        if (obj instanceof String) {
            pVar.o(i, (String) obj);
        } else {
            pVar.n(i, (zzvv) obj);
        }
    }

    public static <UT, UB> void t(u8<UT, UB> u8Var, T t, p pVar) throws IOException {
        u8Var.c(u8Var.e(t), pVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean y(Object obj, int i, c8 c8Var) {
        return c8Var.j(b.z(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static zzzz z(Object obj) {
        zzwz zzwzVar = (zzwz) obj;
        zzzz zzzzVar = zzwzVar.zzclj;
        if (zzzzVar == zzzz.zzwz()) {
            zzzz f = zzzz.f();
            zzwzVar.zzclj = f;
            return f;
        }
        return zzzzVar;
    }

    public final void A(T t, int i) {
        int J = J(i);
        long j = 1048575 & J;
        if (j == 1048575) {
            return;
        }
        b.e(t, j, (1 << (J >>> 20)) | b.r(t, j));
    }

    public final void B(T t, int i, int i2) {
        b.e(t, J(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x048f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(T r18, com.google.android.gms.internal.firebase_ml.p r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.C(java.lang.Object, com.google.android.gms.internal.firebase_ml.p):void");
    }

    public final void D(T t, T t2, int i) {
        int I = I(i);
        int i2 = this.f8711a[i];
        long j = I & ErrorCode.ERR_UNKNOWN;
        if (w(t2, i2, i)) {
            Object z = b.z(t, j);
            Object z2 = b.z(t2, j);
            if (z != null && z2 != null) {
                b.g(t, j, zzxd.d(z, z2));
                B(t, i2, i);
            } else if (z2 != null) {
                b.g(t, j, z2);
                B(t, i2, i);
            }
        }
    }

    public final boolean E(T t, T t2, int i) {
        return v(t, i) == v(t2, i);
    }

    public final c8 F(int i) {
        int i2 = (i / 3) << 1;
        c8 c8Var = (c8) this.b[i2];
        if (c8Var != null) {
            return c8Var;
        }
        c8<T> b = x7.c().b((Class) this.b[i2 + 1]);
        this.b[i2] = b;
        return b;
    }

    public final Object G(int i) {
        return this.b[(i / 3) << 1];
    }

    public final zzxe H(int i) {
        return (zzxe) this.b[((i / 3) << 1) + 1];
    }

    public final int I(int i) {
        return this.f8711a[i + 1];
    }

    public final int J(int i) {
        return this.f8711a[i + 2];
    }

    public final int K(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return S(i, 0);
    }

    public final int R(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return S(i, i2);
    }

    public final int S(int i, int i2) {
        int length = (this.f8711a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.f8711a[i4];
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

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final int a(T t) {
        int i;
        int zzaf;
        int length = this.f8711a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int I = I(i3);
            int i4 = this.f8711a[i3];
            long j = 1048575 & I;
            int i5 = 37;
            switch ((I & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(Double.doubleToLongBits(b.w(t, j)));
                    i2 = i + zzaf;
                    break;
                case 1:
                    i = i2 * 53;
                    zzaf = Float.floatToIntBits(b.u(t, j));
                    i2 = i + zzaf;
                    break;
                case 2:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(b.s(t, j));
                    i2 = i + zzaf;
                    break;
                case 3:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(b.s(t, j));
                    i2 = i + zzaf;
                    break;
                case 4:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 5:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(b.s(t, j));
                    i2 = i + zzaf;
                    break;
                case 6:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 7:
                    i = i2 * 53;
                    zzaf = zzxd.zzaz(b.t(t, j));
                    i2 = i + zzaf;
                    break;
                case 8:
                    i = i2 * 53;
                    zzaf = ((String) b.z(t, j)).hashCode();
                    i2 = i + zzaf;
                    break;
                case 9:
                    Object z = b.z(t, j);
                    if (z != null) {
                        i5 = z.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzaf = b.z(t, j).hashCode();
                    i2 = i + zzaf;
                    break;
                case 11:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 12:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 13:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 14:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(b.s(t, j));
                    i2 = i + zzaf;
                    break;
                case 15:
                    i = i2 * 53;
                    zzaf = b.r(t, j);
                    i2 = i + zzaf;
                    break;
                case 16:
                    i = i2 * 53;
                    zzaf = zzxd.zzaf(b.s(t, j));
                    i2 = i + zzaf;
                    break;
                case 17:
                    Object z2 = b.z(t, j);
                    if (z2 != null) {
                        i5 = z2.hashCode();
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
                    zzaf = b.z(t, j).hashCode();
                    i2 = i + zzaf;
                    break;
                case 50:
                    i = i2 * 53;
                    zzaf = b.z(t, j).hashCode();
                    i2 = i + zzaf;
                    break;
                case 51:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(Double.doubleToLongBits(M(t, j)));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = Float.floatToIntBits(N(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(P(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(P(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(P(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaz(Q(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = ((String) b.z(t, j)).hashCode();
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = b.z(t, j).hashCode();
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = b.z(t, j).hashCode();
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(P(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = O(t, j);
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = zzxd.zzaf(P(t, j));
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (w(t, i4, i3)) {
                        i = i2 * 53;
                        zzaf = b.z(t, j).hashCode();
                        i2 = i + zzaf;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.n.e(t).hashCode();
        return this.f ? (hashCode * 53) + this.o.e(t).hashCode() : hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_ml.b.w(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_ml.b.w(r11, r6))) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.firebase_ml.e8.v(com.google.android.gms.internal.firebase_ml.b.z(r10, r6), com.google.android.gms.internal.firebase_ml.b.z(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.gms.internal.firebase_ml.e8.v(com.google.android.gms.internal.firebase_ml.b.z(r10, r6), com.google.android.gms.internal.firebase_ml.b.z(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.s(r10, r6) == com.google.android.gms.internal.firebase_ml.b.s(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.s(r10, r6) == com.google.android.gms.internal.firebase_ml.b.s(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.gms.internal.firebase_ml.e8.v(com.google.android.gms.internal.firebase_ml.b.z(r10, r6), com.google.android.gms.internal.firebase_ml.b.z(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.gms.internal.firebase_ml.e8.v(com.google.android.gms.internal.firebase_ml.b.z(r10, r6), com.google.android.gms.internal.firebase_ml.b.z(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.gms.internal.firebase_ml.e8.v(com.google.android.gms.internal.firebase_ml.b.z(r10, r6), com.google.android.gms.internal.firebase_ml.b.z(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.t(r10, r6) == com.google.android.gms.internal.firebase_ml.b.t(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.s(r10, r6) == com.google.android.gms.internal.firebase_ml.b.s(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.r(r10, r6) == com.google.android.gms.internal.firebase_ml.b.r(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.s(r10, r6) == com.google.android.gms.internal.firebase_ml.b.s(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.gms.internal.firebase_ml.b.s(r10, r6) == com.google.android.gms.internal.firebase_ml.b.s(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_ml.b.u(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_ml.b.u(r11, r6))) goto L85;
     */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.b(java.lang.Object, java.lang.Object):boolean");
    }

    public final int d(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, v5 v5Var) throws IOException {
        int k;
        Unsafe unsafe = r;
        long j2 = this.f8711a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(w5.m(bArr, i)));
                    k = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(w5.o(bArr, i)));
                    k = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    k = w5.k(bArr, i, v5Var);
                    unsafe.putObject(t, j, Long.valueOf(v5Var.b));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    k = w5.i(bArr, i, v5Var);
                    unsafe.putObject(t, j, Integer.valueOf(v5Var.f8744a));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(w5.l(bArr, i)));
                    k = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(w5.h(bArr, i)));
                    k = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    k = w5.k(bArr, i, v5Var);
                    unsafe.putObject(t, j, Boolean.valueOf(v5Var.b != 0));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int i9 = w5.i(bArr, i, v5Var);
                    int i10 = v5Var.f8744a;
                    if (i10 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !e.h(bArr, i9, i9 + i10)) {
                        throw zzxk.zzvj();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, i9, i10, zzxd.f8814a));
                        i9 += i10;
                    }
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int g = w5.g(F(i8), bArr, i, i2, v5Var);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, v5Var.c);
                    } else {
                        unsafe.putObject(t, j, zzxd.d(object, v5Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return g;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    k = w5.q(bArr, i, v5Var);
                    unsafe.putObject(t, j, v5Var.c);
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int i11 = w5.i(bArr, i, v5Var);
                    int i12 = v5Var.f8744a;
                    zzxe H = H(i8);
                    if (H != null && !H.zzb(i12)) {
                        z(t).c(i3, Long.valueOf(i12));
                        return i11;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(i12));
                    k = i11;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    k = w5.i(bArr, i, v5Var);
                    unsafe.putObject(t, j, Integer.valueOf(zzwh.zzda(v5Var.f8744a)));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    k = w5.k(bArr, i, v5Var);
                    unsafe.putObject(t, j, Long.valueOf(zzwh.zzv(v5Var.b)));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    k = w5.f(F(i8), bArr, i, i2, (i3 & (-8)) | 4, v5Var);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, v5Var.c);
                    } else {
                        unsafe.putObject(t, j, zzxd.d(object2, v5Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            default:
                return i;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final void e(T t) {
        int i;
        int i2 = this.j;
        while (true) {
            i = this.k;
            if (i2 >= i) {
                break;
            }
            long I = I(this.i[i2]) & ErrorCode.ERR_UNKNOWN;
            Object z = b.z(t, I);
            if (z != null) {
                b.g(t, I, this.p.i(z));
            }
            i2++;
        }
        int length = this.i.length;
        while (i < length) {
            this.m.b(t, this.i[i]);
            i++;
        }
        this.n.k(t);
        if (this.f) {
            this.o.g(t);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02d1, code lost:
        if (r0 == r5) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02d5, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r18;
        r10 = r20;
        r1 = r25;
        r6 = r27;
        r7 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x031a, code lost:
        if (r0 == r15) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x033d, code lost:
        if (r0 == r15) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x033f, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [int] */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.firebase_ml.v5 r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 956
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.f(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase_ml.v5):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a2a  */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(T r14, com.google.android.gms.internal.firebase_ml.p r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.g(java.lang.Object, com.google.android.gms.internal.firebase_ml.p):void");
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final void h(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f8711a.length; i += 3) {
            int I = I(i);
            long j = 1048575 & I;
            int i2 = this.f8711a[i];
            switch ((I & 267386880) >>> 20) {
                case 0:
                    if (v(t2, i)) {
                        b.c(t, j, b.w(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (v(t2, i)) {
                        b.d(t, j, b.u(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (v(t2, i)) {
                        b.f(t, j, b.s(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (v(t2, i)) {
                        b.f(t, j, b.s(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (v(t2, i)) {
                        b.f(t, j, b.s(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (v(t2, i)) {
                        b.h(t, j, b.t(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (v(t2, i)) {
                        b.g(t, j, b.z(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    u(t, t2, i);
                    break;
                case 10:
                    if (v(t2, i)) {
                        b.g(t, j, b.z(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (v(t2, i)) {
                        b.f(t, j, b.s(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (v(t2, i)) {
                        b.e(t, j, b.r(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (v(t2, i)) {
                        b.f(t, j, b.s(t2, j));
                        A(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    u(t, t2, i);
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
                    this.m.a(t, t2, j);
                    break;
                case 50:
                    e8.g(this.p, t, t2, j);
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
                    if (w(t2, i2, i)) {
                        b.g(t, j, b.z(t2, j));
                        B(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    D(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (w(t2, i2, i)) {
                        b.g(t, j, b.z(t2, j));
                        B(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    D(t, t2, i);
                    break;
            }
        }
        e8.h(this.n, t, t2);
        if (this.f) {
            e8.f(this.o, t, t2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final int i(T t) {
        int i;
        int i2;
        int i3;
        boolean z;
        long j;
        int zzd;
        int zzb;
        int zzp;
        int i4;
        int W;
        int Q;
        int zzdf;
        int zzdh;
        int zzb2;
        int Q2;
        int zzdf2;
        int zzdh2;
        int i5 = 267386880;
        int i6 = 0;
        if (this.g) {
            Unsafe unsafe = r;
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.f8711a.length) {
                int I = I(i7);
                int i9 = (I & i5) >>> 20;
                int i10 = this.f8711a[i7];
                long j2 = I & ErrorCode.ERR_UNKNOWN;
                int i11 = (i9 < zzww.zzcke.id() || i9 > zzww.zzckr.id()) ? 0 : this.f8711a[i7 + 2] & ErrorCode.ERR_UNKNOWN;
                switch (i9) {
                    case 0:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzb(i10, 0.0d);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 1:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzb(i10, 0.0f);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 2:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzd(i10, b.s(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 3:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zze(i10, b.s(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 4:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzl(i10, b.r(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 5:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzg(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 6:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzo(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 7:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzb(i10, true);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 8:
                        if (v(t, i7)) {
                            Object z2 = b.z(t, j2);
                            if (z2 instanceof zzvv) {
                                zzb2 = zzwi.zzc(i10, (zzvv) z2);
                                break;
                            } else {
                                zzb2 = zzwi.zzc(i10, (String) z2);
                                break;
                            }
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 9:
                        if (v(t, i7)) {
                            zzb2 = e8.m(i10, b.z(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 10:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzc(i10, (zzvv) b.z(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 11:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzm(i10, b.r(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 12:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzq(i10, b.r(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 13:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzp(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 14:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzh(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 15:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzn(i10, b.r(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 16:
                        if (v(t, i7)) {
                            zzb2 = zzwi.zzf(i10, b.s(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 17:
                        if (v(t, i7)) {
                            zzb2 = zzwi.f(i10, (zzyk) b.z(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 18:
                        zzb2 = e8.X(i10, L(t, j2), false);
                        break;
                    case 19:
                        zzb2 = e8.W(i10, L(t, j2), false);
                        break;
                    case 20:
                        zzb2 = e8.L(i10, L(t, j2), false);
                        break;
                    case 21:
                        zzb2 = e8.N(i10, L(t, j2), false);
                        break;
                    case 22:
                        zzb2 = e8.T(i10, L(t, j2), false);
                        break;
                    case 23:
                        zzb2 = e8.X(i10, L(t, j2), false);
                        break;
                    case 24:
                        zzb2 = e8.W(i10, L(t, j2), false);
                        break;
                    case 25:
                        zzb2 = e8.d0(i10, L(t, j2), false);
                        break;
                    case 26:
                        zzb2 = e8.n(i10, L(t, j2));
                        break;
                    case 27:
                        zzb2 = e8.o(i10, L(t, j2), F(i7));
                        break;
                    case 28:
                        zzb2 = e8.q(i10, L(t, j2));
                        break;
                    case 29:
                        zzb2 = e8.U(i10, L(t, j2), false);
                        break;
                    case 30:
                        zzb2 = e8.R(i10, L(t, j2), false);
                        break;
                    case 31:
                        zzb2 = e8.W(i10, L(t, j2), false);
                        break;
                    case 32:
                        zzb2 = e8.X(i10, L(t, j2), false);
                        break;
                    case 33:
                        zzb2 = e8.V(i10, L(t, j2), false);
                        break;
                    case 34:
                        zzb2 = e8.P(i10, L(t, j2), false);
                        break;
                    case 35:
                        Q2 = e8.Q((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 36:
                        Q2 = e8.O((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 37:
                        Q2 = e8.y((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 38:
                        Q2 = e8.A((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 39:
                        Q2 = e8.G((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 40:
                        Q2 = e8.Q((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 41:
                        Q2 = e8.O((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 42:
                        Q2 = e8.S((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 43:
                        Q2 = e8.J((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 44:
                        Q2 = e8.E((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 45:
                        Q2 = e8.O((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 46:
                        Q2 = e8.Q((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 47:
                        Q2 = e8.M((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 48:
                        Q2 = e8.C((List) unsafe.getObject(t, j2));
                        if (Q2 > 0) {
                            if (this.h) {
                                unsafe.putInt(t, i11, Q2);
                            }
                            zzdf2 = zzwi.zzdf(i10);
                            zzdh2 = zzwi.zzdh(Q2);
                            zzb2 = zzdf2 + zzdh2 + Q2;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 49:
                        zzb2 = e8.r(i10, L(t, j2), F(i7));
                        break;
                    case 50:
                        zzb2 = this.p.g(i10, b.z(t, j2), G(i7));
                        break;
                    case 51:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzb(i10, 0.0d);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 52:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzb(i10, 0.0f);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 53:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzd(i10, P(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 54:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zze(i10, P(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 55:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzl(i10, O(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 56:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzg(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 57:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzo(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 58:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzb(i10, true);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 59:
                        if (w(t, i10, i7)) {
                            Object z3 = b.z(t, j2);
                            if (z3 instanceof zzvv) {
                                zzb2 = zzwi.zzc(i10, (zzvv) z3);
                                break;
                            } else {
                                zzb2 = zzwi.zzc(i10, (String) z3);
                                break;
                            }
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 60:
                        if (w(t, i10, i7)) {
                            zzb2 = e8.m(i10, b.z(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 61:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzc(i10, (zzvv) b.z(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 62:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzm(i10, O(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 63:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzq(i10, O(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 64:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzp(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 65:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzh(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 66:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzn(i10, O(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 67:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.zzf(i10, P(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 68:
                        if (w(t, i10, i7)) {
                            zzb2 = zzwi.f(i10, (zzyk) b.z(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    default:
                        i7 += 3;
                        i5 = 267386880;
                }
                i8 += zzb2;
                i7 += 3;
                i5 = 267386880;
            }
            return i8 + c(this.n, t);
        }
        Unsafe unsafe2 = r;
        int i12 = 1048575;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < this.f8711a.length) {
            int I2 = I(i13);
            int[] iArr = this.f8711a;
            int i16 = iArr[i13];
            int i17 = (I2 & 267386880) >>> 20;
            if (i17 <= 17) {
                int i18 = iArr[i13 + 2];
                int i19 = i18 & ErrorCode.ERR_UNKNOWN;
                i2 = 1 << (i18 >>> 20);
                if (i19 != i12) {
                    i15 = unsafe2.getInt(t, i19);
                    i12 = i19;
                }
                i = i18;
            } else {
                i = (!this.h || i17 < zzww.zzcke.id() || i17 > zzww.zzckr.id()) ? 0 : this.f8711a[i13 + 2] & ErrorCode.ERR_UNKNOWN;
                i2 = 0;
            }
            long j3 = I2 & ErrorCode.ERR_UNKNOWN;
            switch (i17) {
                case 0:
                    i3 = 0;
                    z = false;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        i14 += zzwi.zzb(i16, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        z = false;
                        i14 += zzwi.zzb(i16, 0.0f);
                        break;
                    }
                    z = false;
                case 2:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzwi.zzd(i16, unsafe2.getLong(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 3:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzwi.zze(i16, unsafe2.getLong(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 4:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzwi.zzl(i16, unsafe2.getInt(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 5:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzwi.zzg(i16, 0L);
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 6:
                    if ((i15 & i2) != 0) {
                        i3 = 0;
                        i14 += zzwi.zzo(i16, 0);
                        z = false;
                        j = 0;
                        break;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                case 7:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzb(i16, true);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 8:
                    if ((i15 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzvv) {
                            zzb = zzwi.zzc(i16, (zzvv) object);
                        } else {
                            zzb = zzwi.zzc(i16, (String) object);
                        }
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 9:
                    if ((i15 & i2) != 0) {
                        zzb = e8.m(i16, unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 10:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzc(i16, (zzvv) unsafe2.getObject(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 11:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzm(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 12:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzq(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 13:
                    if ((i15 & i2) != 0) {
                        zzp = zzwi.zzp(i16, 0);
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 14:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzh(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 15:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzn(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 16:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.zzf(i16, unsafe2.getLong(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 17:
                    if ((i15 & i2) != 0) {
                        zzb = zzwi.f(i16, (zzyk) unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 18:
                    zzb = e8.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 19:
                    i4 = 0;
                    W = e8.W(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 20:
                    i4 = 0;
                    W = e8.L(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 21:
                    i4 = 0;
                    W = e8.N(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 22:
                    i4 = 0;
                    W = e8.T(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 23:
                    i4 = 0;
                    W = e8.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 24:
                    i4 = 0;
                    W = e8.W(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 25:
                    i4 = 0;
                    W = e8.d0(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 26:
                    zzb = e8.n(i16, (List) unsafe2.getObject(t, j3));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 27:
                    zzb = e8.o(i16, (List) unsafe2.getObject(t, j3), F(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 28:
                    zzb = e8.q(i16, (List) unsafe2.getObject(t, j3));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 29:
                    zzb = e8.U(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 30:
                    i4 = 0;
                    W = e8.R(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 31:
                    i4 = 0;
                    W = e8.W(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 32:
                    i4 = 0;
                    W = e8.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 33:
                    i4 = 0;
                    W = e8.V(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 34:
                    i4 = 0;
                    W = e8.P(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += W;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 35:
                    Q = e8.Q((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 36:
                    Q = e8.O((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 37:
                    Q = e8.y((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 38:
                    Q = e8.A((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 39:
                    Q = e8.G((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 40:
                    Q = e8.Q((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 41:
                    Q = e8.O((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 42:
                    Q = e8.S((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 43:
                    Q = e8.J((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 44:
                    Q = e8.E((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 45:
                    Q = e8.O((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 46:
                    Q = e8.Q((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 47:
                    Q = e8.M((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 48:
                    Q = e8.C((List) unsafe2.getObject(t, j3));
                    if (Q > 0) {
                        if (this.h) {
                            unsafe2.putInt(t, i, Q);
                        }
                        zzdf = zzwi.zzdf(i16);
                        zzdh = zzwi.zzdh(Q);
                        zzp = zzdf + zzdh + Q;
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 49:
                    zzb = e8.r(i16, (List) unsafe2.getObject(t, j3), F(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 50:
                    zzb = this.p.g(i16, unsafe2.getObject(t, j3), G(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 51:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzb(i16, 0.0d);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 52:
                    if (w(t, i16, i13)) {
                        zzp = zzwi.zzb(i16, 0.0f);
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 53:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzd(i16, P(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 54:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zze(i16, P(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 55:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzl(i16, O(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 56:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzg(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 57:
                    if (w(t, i16, i13)) {
                        zzp = zzwi.zzo(i16, 0);
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 58:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzb(i16, true);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 59:
                    if (w(t, i16, i13)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzvv) {
                            zzb = zzwi.zzc(i16, (zzvv) object2);
                        } else {
                            zzb = zzwi.zzc(i16, (String) object2);
                        }
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 60:
                    if (w(t, i16, i13)) {
                        zzb = e8.m(i16, unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 61:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzc(i16, (zzvv) unsafe2.getObject(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 62:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzm(i16, O(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 63:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzq(i16, O(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 64:
                    if (w(t, i16, i13)) {
                        zzp = zzwi.zzp(i16, 0);
                        i14 += zzp;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 65:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzh(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 66:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzn(i16, O(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 67:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.zzf(i16, P(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 68:
                    if (w(t, i16, i13)) {
                        zzb = zzwi.f(i16, (zzyk) unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                default:
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
            }
            i13 += 3;
            i6 = i3;
        }
        int i20 = i6;
        int c = i14 + c(this.n, t);
        if (this.f) {
            p6<?> e = this.o.e(t);
            for (int i21 = i20; i21 < e.f8717a.n(); i21++) {
                Map.Entry<?, Object> k = e.f8717a.k(i21);
                i20 += p6.l((zzwt) k.getKey(), k.getValue());
            }
            for (Map.Entry<?, Object> entry : e.f8717a.o()) {
                i20 += p6.l((zzwt) entry.getKey(), entry.getValue());
            }
            return c + i20;
        }
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.firebase_ml.c8] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.firebase_ml.c8] */
    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final boolean j(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.j) {
                return !this.f || this.o.e(t).c();
            }
            int i6 = this.i[i5];
            int i7 = this.f8711a[i6];
            int I = I(i6);
            int i8 = this.f8711a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = r.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (((268435456 & I) != 0) && !x(t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & I) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (w(t, i7, i6) && !y(t, I, F(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> e = this.p.e(b.z(t, I & ErrorCode.ERR_UNKNOWN));
                            if (!e.isEmpty()) {
                                if (this.p.f(G(i6)).c.zzxi() == zzaaq.MESSAGE) {
                                    c8<T> c8Var = 0;
                                    Iterator<?> it = e.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (c8Var == null) {
                                            c8Var = x7.c().b(next.getClass());
                                        }
                                        boolean j = c8Var.j(next);
                                        c8Var = c8Var;
                                        if (!j) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) b.z(t, I & ErrorCode.ERR_UNKNOWN);
                if (!list.isEmpty()) {
                    ?? F = F(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!F.j(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (x(t, i6, i, i2, i10) && !y(t, I, F(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01e8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:115:0x0233 -> B:116:0x0234). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x016b -> B:67:0x016c). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x01e5 -> B:96:0x01e6). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int k(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.firebase_ml.v5 r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1126
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.k(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.firebase_ml.v5):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <K, V> int l(T t, byte[] bArr, int i, int i2, int i3, long j, v5 v5Var) throws IOException {
        Unsafe unsafe = r;
        Object G = G(i3);
        Object object = unsafe.getObject(t, j);
        if (this.p.j(object)) {
            Object h = this.p.h(G);
            this.p.c(h, object);
            unsafe.putObject(t, j, h);
            object = h;
        }
        i7<?, ?> f = this.p.f(G);
        Map<?, ?> d = this.p.d(object);
        int i4 = w5.i(bArr, i, v5Var);
        int i5 = v5Var.f8744a;
        if (i5 >= 0 && i5 <= i2 - i4) {
            int i6 = i5 + i4;
            Object obj = (K) f.b;
            Object obj2 = (V) f.d;
            while (i4 < i6) {
                int i7 = i4 + 1;
                int i8 = bArr[i4];
                if (i8 < 0) {
                    i7 = w5.d(i8, bArr, i7, v5Var);
                    i8 = v5Var.f8744a;
                }
                int i9 = i7;
                int i10 = i8 >>> 3;
                int i11 = i8 & 7;
                if (i10 != 1) {
                    if (i10 == 2 && i11 == f.c.zzxj()) {
                        i4 = n(bArr, i9, i2, f.c, f.d.getClass(), v5Var);
                        obj2 = v5Var.c;
                    }
                    i4 = w5.a(i8, bArr, i9, i2, v5Var);
                } else if (i11 == f.f8691a.zzxj()) {
                    i4 = n(bArr, i9, i2, f.f8691a, null, v5Var);
                    obj = (K) v5Var.c;
                } else {
                    i4 = w5.a(i8, bArr, i9, i2, v5Var);
                }
            }
            if (i4 == i6) {
                d.put(obj, obj2);
                return i6;
            }
            throw zzxk.zzvi();
        }
        throw zzxk.zzve();
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x04ce, code lost:
        if (r6 == r11) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x04d0, code lost:
        r26.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x04d6, code lost:
        r5 = null;
        r13 = r9.j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x04dd, code lost:
        if (r13 >= r9.k) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x04df, code lost:
        r1 = r9.i[r13];
        r6 = r9.n;
        r2 = r9.f8711a[r1];
        r0 = com.google.android.gms.internal.firebase_ml.b.z(r12, r9.I(r1) & r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x04f3, code lost:
        if (r0 != null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x04f6, code lost:
        r4 = r9.H(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x04fa, code lost:
        if (r4 != null) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x04fd, code lost:
        r5 = p(r1, r2, r9.p.d(r0), r4, r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0509, code lost:
        r5 = (com.google.android.gms.internal.firebase_ml.zzzz) r5;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x050e, code lost:
        if (r5 == null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0510, code lost:
        r9.n.i(r12, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0515, code lost:
        if (r7 != 0) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0519, code lost:
        if (r8 != r31) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0520, code lost:
        throw com.google.android.gms.internal.firebase_ml.zzxk.zzvi();
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0523, code lost:
        if (r8 > r31) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0525, code lost:
        if (r10 != r7) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0527, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x052c, code lost:
        throw com.google.android.gms.internal.firebase_ml.zzxk.zzvi();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m(T r28, byte[] r29, int r30, int r31, int r32, com.google.android.gms.internal.firebase_ml.v5 r33) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.o7.m(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase_ml.v5):int");
    }

    @Override // com.google.android.gms.internal.firebase_ml.c8
    public final T newInstance() {
        return (T) this.l.a(this.e);
    }

    public final <K, V, UT, UB> UB p(int i, int i2, Map<K, V> map, zzxe zzxeVar, UB ub, u8<UT, UB> u8Var) {
        i7<?, ?> f = this.p.f(G(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzxeVar.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = u8Var.l();
                }
                f6 zzcy = zzvv.zzcy(zzyc.a(f, next.getKey(), next.getValue()));
                try {
                    zzyc.b(zzcy.b(), f, next.getKey(), next.getValue());
                    u8Var.b(ub, i2, zzcy.a());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final <K, V> void s(p pVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            pVar.i(i, this.p.f(G(i2)), this.p.e(obj));
        }
    }

    public final void u(T t, T t2, int i) {
        long I = I(i) & ErrorCode.ERR_UNKNOWN;
        if (v(t2, i)) {
            Object z = b.z(t, I);
            Object z2 = b.z(t2, I);
            if (z != null && z2 != null) {
                b.g(t, I, zzxd.d(z, z2));
                A(t, i);
            } else if (z2 != null) {
                b.g(t, I, z2);
                A(t, i);
            }
        }
    }

    public final boolean v(T t, int i) {
        int J = J(i);
        long j = J & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (b.r(t, j) & (1 << (J >>> 20))) != 0;
        }
        int I = I(i);
        long j2 = I & ErrorCode.ERR_UNKNOWN;
        switch ((I & 267386880) >>> 20) {
            case 0:
                return b.w(t, j2) != 0.0d;
            case 1:
                return b.u(t, j2) != 0.0f;
            case 2:
                return b.s(t, j2) != 0;
            case 3:
                return b.s(t, j2) != 0;
            case 4:
                return b.r(t, j2) != 0;
            case 5:
                return b.s(t, j2) != 0;
            case 6:
                return b.r(t, j2) != 0;
            case 7:
                return b.t(t, j2);
            case 8:
                Object z = b.z(t, j2);
                if (z instanceof String) {
                    return !((String) z).isEmpty();
                } else if (z instanceof zzvv) {
                    return !zzvv.zzchp.equals(z);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return b.z(t, j2) != null;
            case 10:
                return !zzvv.zzchp.equals(b.z(t, j2));
            case 11:
                return b.r(t, j2) != 0;
            case 12:
                return b.r(t, j2) != 0;
            case 13:
                return b.r(t, j2) != 0;
            case 14:
                return b.s(t, j2) != 0;
            case 15:
                return b.r(t, j2) != 0;
            case 16:
                return b.s(t, j2) != 0;
            case 17:
                return b.z(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean w(T t, int i, int i2) {
        return b.r(t, (long) (J(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    public final boolean x(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return v(t, i);
        }
        return (i3 & i4) != 0;
    }
}
