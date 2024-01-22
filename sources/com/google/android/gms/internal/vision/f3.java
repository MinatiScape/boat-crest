package com.google.android.gms.internal.vision;

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
/* loaded from: classes10.dex */
public final class f3<T> implements o3<T> {
    public static final int[] r = new int[0];
    public static final Unsafe s = j4.v();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f9973a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final zzic e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int k;
    public final int l;
    public final i3 m;
    public final r2 n;
    public final g4<?, ?> o;
    public final b2<?> p;
    public final z2 q;

    public f3(int[] iArr, Object[] objArr, int i, int i2, zzic zzicVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, i3 i3Var, r2 r2Var, g4<?, ?> g4Var, b2<?> b2Var, z2 z2Var) {
        this.f9973a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = zzicVar instanceof zzgs;
        this.h = z;
        this.f = b2Var != null && b2Var.g(zzicVar);
        this.i = false;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = i3Var;
        this.n = r2Var;
        this.o = g4Var;
        this.p = b2Var;
        this.e = zzicVar;
        this.q = z2Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean A(Object obj, int i, o3 o3Var) {
        return o3Var.d(j4.G(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static boolean K(int i) {
        return (i & PKIFailureInfo.duplicateCertReq) != 0;
    }

    public static List<?> N(Object obj, long j) {
        return (List) j4.G(obj, j);
    }

    public static <T> double O(T t, long j) {
        return ((Double) j4.G(t, j)).doubleValue();
    }

    public static <T> float P(T t, long j) {
        return ((Float) j4.G(t, j)).floatValue();
    }

    public static <T> int Q(T t, long j) {
        return ((Integer) j4.G(t, j)).intValue();
    }

    public static <T> long R(T t, long j) {
        return ((Long) j4.G(t, j)).longValue();
    }

    public static <T> boolean S(T t, long j) {
        return ((Boolean) j4.G(t, j)).booleanValue();
    }

    public static zzjm V(Object obj) {
        zzgs zzgsVar = (zzgs) obj;
        zzjm zzjmVar = zzgsVar.zzwj;
        if (zzjmVar == zzjm.zzig()) {
            zzjm f = zzjm.f();
            zzgsVar.zzwj = f;
            return f;
        }
        return zzjmVar;
    }

    public static <UT, UB> int j(g4<UT, UB> g4Var, T t) {
        return g4Var.p(g4Var.q(t));
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.android.gms.internal.vision.f3<T> o(java.lang.Class<T> r33, com.google.android.gms.internal.vision.d3 r34, com.google.android.gms.internal.vision.i3 r35, com.google.android.gms.internal.vision.r2 r36, com.google.android.gms.internal.vision.g4<?, ?> r37, com.google.android.gms.internal.vision.b2<?> r38, com.google.android.gms.internal.vision.z2 r39) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.o(java.lang.Class, com.google.android.gms.internal.vision.d3, com.google.android.gms.internal.vision.i3, com.google.android.gms.internal.vision.r2, com.google.android.gms.internal.vision.g4, com.google.android.gms.internal.vision.b2, com.google.android.gms.internal.vision.z2):com.google.android.gms.internal.vision.f3");
    }

    public static Field r(Class<?> cls, String str) {
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

    public static void s(int i, Object obj, x4 x4Var) throws IOException {
        if (obj instanceof String) {
            x4Var.zza(i, (String) obj);
        } else {
            x4Var.o(i, (zzfh) obj);
        }
    }

    public static <UT, UB> void t(g4<UT, UB> g4Var, T t, x4 x4Var) throws IOException {
        g4Var.d(g4Var.q(t), x4Var);
    }

    public final void B(T t, int i) {
        int J = J(i);
        long j = 1048575 & J;
        if (j == 1048575) {
            return;
        }
        j4.k(t, j, (1 << (J >>> 20)) | j4.A(t, j));
    }

    public final void C(T t, int i, int i2) {
        j4.k(t, J(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x048f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void D(T r18, com.google.android.gms.internal.vision.x4 r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.D(java.lang.Object, com.google.android.gms.internal.vision.x4):void");
    }

    public final void E(T t, T t2, int i) {
        int I = I(i);
        int i2 = this.f9973a[i];
        long j = I & ErrorCode.ERR_UNKNOWN;
        if (y(t2, i2, i)) {
            Object G = j4.G(t, j);
            Object G2 = j4.G(t2, j);
            if (G != null && G2 != null) {
                j4.f(t, j, zzgt.d(G, G2));
                C(t, i2, i);
            } else if (G2 != null) {
                j4.f(t, j, G2);
                C(t, i2, i);
            }
        }
    }

    public final o3 F(int i) {
        int i2 = (i / 3) << 1;
        o3 o3Var = (o3) this.b[i2];
        if (o3Var != null) {
            return o3Var;
        }
        o3<T> a2 = k3.b().a((Class) this.b[i2 + 1]);
        this.b[i2] = a2;
        return a2;
    }

    public final Object G(int i) {
        return this.b[(i / 3) << 1];
    }

    public final zzgy H(int i) {
        return (zzgy) this.b[((i / 3) << 1) + 1];
    }

    public final int I(int i) {
        return this.f9973a[i + 1];
    }

    public final int J(int i) {
        return this.f9973a[i + 2];
    }

    public final int L(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return U(i, 0);
    }

    public final boolean M(T t, T t2, int i) {
        return x(t, i) == x(t2, i);
    }

    public final int T(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return U(i, i2);
    }

    public final int U(int i, int i2) {
        int length = (this.f9973a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.f9973a[i4];
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

    @Override // com.google.android.gms.internal.vision.o3
    public final int a(T t) {
        int i;
        int zzab;
        int length = this.f9973a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int I = I(i3);
            int i4 = this.f9973a[i3];
            long j = 1048575 & I;
            int i5 = 37;
            switch ((I & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zzab = zzgt.zzab(Double.doubleToLongBits(j4.F(t, j)));
                    i2 = i + zzab;
                    break;
                case 1:
                    i = i2 * 53;
                    zzab = Float.floatToIntBits(j4.E(t, j));
                    i2 = i + zzab;
                    break;
                case 2:
                    i = i2 * 53;
                    zzab = zzgt.zzab(j4.C(t, j));
                    i2 = i + zzab;
                    break;
                case 3:
                    i = i2 * 53;
                    zzab = zzgt.zzab(j4.C(t, j));
                    i2 = i + zzab;
                    break;
                case 4:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 5:
                    i = i2 * 53;
                    zzab = zzgt.zzab(j4.C(t, j));
                    i2 = i + zzab;
                    break;
                case 6:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 7:
                    i = i2 * 53;
                    zzab = zzgt.zzm(j4.D(t, j));
                    i2 = i + zzab;
                    break;
                case 8:
                    i = i2 * 53;
                    zzab = ((String) j4.G(t, j)).hashCode();
                    i2 = i + zzab;
                    break;
                case 9:
                    Object G = j4.G(t, j);
                    if (G != null) {
                        i5 = G.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzab = j4.G(t, j).hashCode();
                    i2 = i + zzab;
                    break;
                case 11:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 12:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 13:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 14:
                    i = i2 * 53;
                    zzab = zzgt.zzab(j4.C(t, j));
                    i2 = i + zzab;
                    break;
                case 15:
                    i = i2 * 53;
                    zzab = j4.A(t, j);
                    i2 = i + zzab;
                    break;
                case 16:
                    i = i2 * 53;
                    zzab = zzgt.zzab(j4.C(t, j));
                    i2 = i + zzab;
                    break;
                case 17:
                    Object G2 = j4.G(t, j);
                    if (G2 != null) {
                        i5 = G2.hashCode();
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
                    zzab = j4.G(t, j).hashCode();
                    i2 = i + zzab;
                    break;
                case 50:
                    i = i2 * 53;
                    zzab = j4.G(t, j).hashCode();
                    i2 = i + zzab;
                    break;
                case 51:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(Double.doubleToLongBits(O(t, j)));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Float.floatToIntBits(P(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(R(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(R(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(R(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzm(S(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = ((String) j4.G(t, j)).hashCode();
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = j4.G(t, j).hashCode();
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = j4.G(t, j).hashCode();
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(R(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = Q(t, j);
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = zzgt.zzab(R(t, j));
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (y(t, i4, i3)) {
                        i = i2 * 53;
                        zzab = j4.G(t, j).hashCode();
                        i2 = i + zzab;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.o.q(t).hashCode();
        return this.f ? (hashCode * 53) + this.p.h(t).hashCode() : hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.j4.F(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.j4.F(r11, r6))) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.vision.q3.u(com.google.android.gms.internal.vision.j4.G(r10, r6), com.google.android.gms.internal.vision.j4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.gms.internal.vision.q3.u(com.google.android.gms.internal.vision.j4.G(r10, r6), com.google.android.gms.internal.vision.j4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.gms.internal.vision.j4.C(r10, r6) == com.google.android.gms.internal.vision.j4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.gms.internal.vision.j4.C(r10, r6) == com.google.android.gms.internal.vision.j4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.gms.internal.vision.q3.u(com.google.android.gms.internal.vision.j4.G(r10, r6), com.google.android.gms.internal.vision.j4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.gms.internal.vision.q3.u(com.google.android.gms.internal.vision.j4.G(r10, r6), com.google.android.gms.internal.vision.j4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.gms.internal.vision.q3.u(com.google.android.gms.internal.vision.j4.G(r10, r6), com.google.android.gms.internal.vision.j4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.gms.internal.vision.j4.D(r10, r6) == com.google.android.gms.internal.vision.j4.D(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.gms.internal.vision.j4.C(r10, r6) == com.google.android.gms.internal.vision.j4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.gms.internal.vision.j4.A(r10, r6) == com.google.android.gms.internal.vision.j4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.gms.internal.vision.j4.C(r10, r6) == com.google.android.gms.internal.vision.j4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.gms.internal.vision.j4.C(r10, r6) == com.google.android.gms.internal.vision.j4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.j4.E(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.j4.E(r11, r6))) goto L85;
     */
    @Override // com.google.android.gms.internal.vision.o3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.b(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void c(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f9973a.length; i += 3) {
            int I = I(i);
            long j = 1048575 & I;
            int i2 = this.f9973a[i];
            switch ((I & 267386880) >>> 20) {
                case 0:
                    if (x(t2, i)) {
                        j4.c(t, j, j4.F(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (x(t2, i)) {
                        j4.d(t, j, j4.E(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (x(t2, i)) {
                        j4.e(t, j, j4.C(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (x(t2, i)) {
                        j4.e(t, j, j4.C(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (x(t2, i)) {
                        j4.e(t, j, j4.C(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (x(t2, i)) {
                        j4.g(t, j, j4.D(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (x(t2, i)) {
                        j4.f(t, j, j4.G(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    w(t, t2, i);
                    break;
                case 10:
                    if (x(t2, i)) {
                        j4.f(t, j, j4.G(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (x(t2, i)) {
                        j4.e(t, j, j4.C(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (x(t2, i)) {
                        j4.k(t, j, j4.A(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (x(t2, i)) {
                        j4.e(t, j, j4.C(t2, j));
                        B(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    w(t, t2, i);
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
                    this.n.b(t, t2, j);
                    break;
                case 50:
                    q3.h(this.q, t, t2, j);
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
                    if (y(t2, i2, i)) {
                        j4.f(t, j, j4.G(t2, j));
                        C(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    E(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (y(t2, i2, i)) {
                        j4.f(t, j, j4.G(t2, j));
                        C(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    E(t, t2, i);
                    break;
            }
        }
        q3.i(this.o, t, t2);
        if (this.f) {
            q3.g(this.p, t, t2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.o3
    public final boolean d(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.k) {
                return !this.f || this.p.h(t).c();
            }
            int i6 = this.j[i5];
            int i7 = this.f9973a[i6];
            int I = I(i6);
            int i8 = this.f9973a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = s.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (((268435456 & I) != 0) && !z(t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & I) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (y(t, i7, i6) && !A(t, I, F(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 == 50 && !this.q.d(j4.G(t, I & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            this.q.e(G(i6));
                            throw null;
                        }
                    }
                }
                List list = (List) j4.G(t, I & ErrorCode.ERR_UNKNOWN);
                if (!list.isEmpty()) {
                    o3 F = F(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!F.d(list.get(i12))) {
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
            } else if (z(t, i6, i, i2, i10) && !A(t, I, F(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void e(T t) {
        int i;
        int i2 = this.k;
        while (true) {
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long I = I(this.j[i2]) & ErrorCode.ERR_UNKNOWN;
            Object G = j4.G(t, I);
            if (G != null) {
                j4.f(t, I, this.q.f(G));
            }
            i2++;
        }
        int length = this.j.length;
        while (i < length) {
            this.n.c(t, this.j[i]);
            i++;
        }
        this.o.m(t);
        if (this.f) {
            this.p.j(t);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.vision.o3
    public final int f(T t) {
        int i;
        int i2;
        int i3;
        boolean z;
        long j;
        int zzd;
        int zzb;
        int zzo;
        int i4;
        int X;
        int d0;
        int zzbb;
        int zzbd;
        int zzb2;
        int d02;
        int zzbb2;
        int zzbd2;
        int i5 = 267386880;
        int i6 = 0;
        if (this.h) {
            Unsafe unsafe = s;
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.f9973a.length) {
                int I = I(i7);
                int i9 = (I & i5) >>> 20;
                int i10 = this.f9973a[i7];
                long j2 = I & ErrorCode.ERR_UNKNOWN;
                int i11 = (i9 < zzgn.zzuz.id() || i9 > zzgn.zzvm.id()) ? 0 : this.f9973a[i7 + 2] & ErrorCode.ERR_UNKNOWN;
                switch (i9) {
                    case 0:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzb(i10, 0.0d);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 1:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzb(i10, 0.0f);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 2:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzd(i10, j4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 3:
                        if (x(t, i7)) {
                            zzb2 = zzga.zze(i10, j4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 4:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzk(i10, j4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 5:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzg(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 6:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzn(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 7:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzb(i10, true);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 8:
                        if (x(t, i7)) {
                            Object G = j4.G(t, j2);
                            if (G instanceof zzfh) {
                                zzb2 = zzga.zzc(i10, (zzfh) G);
                                break;
                            } else {
                                zzb2 = zzga.zzb(i10, (String) G);
                                break;
                            }
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 9:
                        if (x(t, i7)) {
                            zzb2 = q3.m(i10, j4.G(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 10:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzc(i10, (zzfh) j4.G(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 11:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzl(i10, j4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 12:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzp(i10, j4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 13:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzo(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 14:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzh(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 15:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzm(i10, j4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 16:
                        if (x(t, i7)) {
                            zzb2 = zzga.zzf(i10, j4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 17:
                        if (x(t, i7)) {
                            zzb2 = zzga.g(i10, (zzic) j4.G(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 18:
                        zzb2 = q3.Z(i10, N(t, j2), false);
                        break;
                    case 19:
                        zzb2 = q3.X(i10, N(t, j2), false);
                        break;
                    case 20:
                        zzb2 = q3.L(i10, N(t, j2), false);
                        break;
                    case 21:
                        zzb2 = q3.M(i10, N(t, j2), false);
                        break;
                    case 22:
                        zzb2 = q3.R(i10, N(t, j2), false);
                        break;
                    case 23:
                        zzb2 = q3.Z(i10, N(t, j2), false);
                        break;
                    case 24:
                        zzb2 = q3.X(i10, N(t, j2), false);
                        break;
                    case 25:
                        zzb2 = q3.b0(i10, N(t, j2), false);
                        break;
                    case 26:
                        zzb2 = q3.n(i10, N(t, j2));
                        break;
                    case 27:
                        zzb2 = q3.o(i10, N(t, j2), F(i7));
                        break;
                    case 28:
                        zzb2 = q3.q(i10, N(t, j2));
                        break;
                    case 29:
                        zzb2 = q3.T(i10, N(t, j2), false);
                        break;
                    case 30:
                        zzb2 = q3.P(i10, N(t, j2), false);
                        break;
                    case 31:
                        zzb2 = q3.X(i10, N(t, j2), false);
                        break;
                    case 32:
                        zzb2 = q3.Z(i10, N(t, j2), false);
                        break;
                    case 33:
                        zzb2 = q3.V(i10, N(t, j2), false);
                        break;
                    case 34:
                        zzb2 = q3.N(i10, N(t, j2), false);
                        break;
                    case 35:
                        d02 = q3.d0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 36:
                        d02 = q3.c0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 37:
                        d02 = q3.O((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 38:
                        d02 = q3.Q((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 39:
                        d02 = q3.W((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 40:
                        d02 = q3.d0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 41:
                        d02 = q3.c0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 42:
                        d02 = q3.e0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 43:
                        d02 = q3.Y((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 44:
                        d02 = q3.U((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 45:
                        d02 = q3.c0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 46:
                        d02 = q3.d0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 47:
                        d02 = q3.a0((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 48:
                        d02 = q3.S((List) unsafe.getObject(t, j2));
                        if (d02 > 0) {
                            if (this.i) {
                                unsafe.putInt(t, i11, d02);
                            }
                            zzbb2 = zzga.zzbb(i10);
                            zzbd2 = zzga.zzbd(d02);
                            zzb2 = zzbb2 + zzbd2 + d02;
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 49:
                        zzb2 = q3.r(i10, N(t, j2), F(i7));
                        break;
                    case 50:
                        zzb2 = this.q.a(i10, j4.G(t, j2), G(i7));
                        break;
                    case 51:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzb(i10, 0.0d);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 52:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzb(i10, 0.0f);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 53:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzd(i10, R(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 54:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zze(i10, R(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 55:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzk(i10, Q(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 56:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzg(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 57:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzn(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 58:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzb(i10, true);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 59:
                        if (y(t, i10, i7)) {
                            Object G2 = j4.G(t, j2);
                            if (G2 instanceof zzfh) {
                                zzb2 = zzga.zzc(i10, (zzfh) G2);
                                break;
                            } else {
                                zzb2 = zzga.zzb(i10, (String) G2);
                                break;
                            }
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 60:
                        if (y(t, i10, i7)) {
                            zzb2 = q3.m(i10, j4.G(t, j2), F(i7));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 61:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzc(i10, (zzfh) j4.G(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 62:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzl(i10, Q(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 63:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzp(i10, Q(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 64:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzo(i10, 0);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 65:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzh(i10, 0L);
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 66:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzm(i10, Q(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 67:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.zzf(i10, R(t, j2));
                            break;
                        } else {
                            continue;
                            i7 += 3;
                            i5 = 267386880;
                        }
                    case 68:
                        if (y(t, i10, i7)) {
                            zzb2 = zzga.g(i10, (zzic) j4.G(t, j2), F(i7));
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
            return i8 + j(this.o, t);
        }
        Unsafe unsafe2 = s;
        int i12 = 1048575;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < this.f9973a.length) {
            int I2 = I(i13);
            int[] iArr = this.f9973a;
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
                i = (!this.i || i17 < zzgn.zzuz.id() || i17 > zzgn.zzvm.id()) ? 0 : this.f9973a[i13 + 2] & ErrorCode.ERR_UNKNOWN;
                i2 = 0;
            }
            long j3 = I2 & ErrorCode.ERR_UNKNOWN;
            switch (i17) {
                case 0:
                    i3 = 0;
                    z = false;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        i14 += zzga.zzb(i16, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        z = false;
                        i14 += zzga.zzb(i16, 0.0f);
                        break;
                    }
                    z = false;
                case 2:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzga.zzd(i16, unsafe2.getLong(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 3:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzga.zze(i16, unsafe2.getLong(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 4:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzga.zzk(i16, unsafe2.getInt(t, j3));
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 5:
                    i3 = 0;
                    j = 0;
                    if ((i15 & i2) != 0) {
                        zzd = zzga.zzg(i16, 0L);
                        i14 += zzd;
                    }
                    z = false;
                    break;
                case 6:
                    if ((i15 & i2) != 0) {
                        i3 = 0;
                        i14 += zzga.zzn(i16, 0);
                        z = false;
                        j = 0;
                        break;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                case 7:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzb(i16, true);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 8:
                    if ((i15 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzfh) {
                            zzb = zzga.zzc(i16, (zzfh) object);
                        } else {
                            zzb = zzga.zzb(i16, (String) object);
                        }
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 9:
                    if ((i15 & i2) != 0) {
                        zzb = q3.m(i16, unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 10:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzc(i16, (zzfh) unsafe2.getObject(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 11:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzl(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 12:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzp(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 13:
                    if ((i15 & i2) != 0) {
                        zzo = zzga.zzo(i16, 0);
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 14:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzh(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 15:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzm(i16, unsafe2.getInt(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 16:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.zzf(i16, unsafe2.getLong(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 17:
                    if ((i15 & i2) != 0) {
                        zzb = zzga.g(i16, (zzic) unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 18:
                    zzb = q3.Z(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 19:
                    i4 = 0;
                    X = q3.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 20:
                    i4 = 0;
                    X = q3.L(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 21:
                    i4 = 0;
                    X = q3.M(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 22:
                    i4 = 0;
                    X = q3.R(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 23:
                    i4 = 0;
                    X = q3.Z(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 24:
                    i4 = 0;
                    X = q3.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 25:
                    i4 = 0;
                    X = q3.b0(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 26:
                    zzb = q3.n(i16, (List) unsafe2.getObject(t, j3));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 27:
                    zzb = q3.o(i16, (List) unsafe2.getObject(t, j3), F(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 28:
                    zzb = q3.q(i16, (List) unsafe2.getObject(t, j3));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 29:
                    zzb = q3.T(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 30:
                    i4 = 0;
                    X = q3.P(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 31:
                    i4 = 0;
                    X = q3.X(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 32:
                    i4 = 0;
                    X = q3.Z(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 33:
                    i4 = 0;
                    X = q3.V(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 34:
                    i4 = 0;
                    X = q3.N(i16, (List) unsafe2.getObject(t, j3), false);
                    i14 += X;
                    i3 = i4;
                    z = false;
                    j = 0;
                    break;
                case 35:
                    d0 = q3.d0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 36:
                    d0 = q3.c0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 37:
                    d0 = q3.O((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 38:
                    d0 = q3.Q((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 39:
                    d0 = q3.W((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 40:
                    d0 = q3.d0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 41:
                    d0 = q3.c0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 42:
                    d0 = q3.e0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 43:
                    d0 = q3.Y((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 44:
                    d0 = q3.U((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 45:
                    d0 = q3.c0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 46:
                    d0 = q3.d0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 47:
                    d0 = q3.a0((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 48:
                    d0 = q3.S((List) unsafe2.getObject(t, j3));
                    if (d0 > 0) {
                        if (this.i) {
                            unsafe2.putInt(t, i, d0);
                        }
                        zzbb = zzga.zzbb(i16);
                        zzbd = zzga.zzbd(d0);
                        zzo = zzbb + zzbd + d0;
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 49:
                    zzb = q3.r(i16, (List) unsafe2.getObject(t, j3), F(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 50:
                    zzb = this.q.a(i16, unsafe2.getObject(t, j3), G(i13));
                    i14 += zzb;
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 51:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzb(i16, 0.0d);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 52:
                    if (y(t, i16, i13)) {
                        zzo = zzga.zzb(i16, 0.0f);
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 53:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzd(i16, R(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 54:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zze(i16, R(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 55:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzk(i16, Q(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 56:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzg(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 57:
                    if (y(t, i16, i13)) {
                        zzo = zzga.zzn(i16, 0);
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 58:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzb(i16, true);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 59:
                    if (y(t, i16, i13)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzfh) {
                            zzb = zzga.zzc(i16, (zzfh) object2);
                        } else {
                            zzb = zzga.zzb(i16, (String) object2);
                        }
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 60:
                    if (y(t, i16, i13)) {
                        zzb = q3.m(i16, unsafe2.getObject(t, j3), F(i13));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 61:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzc(i16, (zzfh) unsafe2.getObject(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 62:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzl(i16, Q(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 63:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzp(i16, Q(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 64:
                    if (y(t, i16, i13)) {
                        zzo = zzga.zzo(i16, 0);
                        i14 += zzo;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 65:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzh(i16, 0L);
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 66:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzm(i16, Q(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 67:
                    if (y(t, i16, i13)) {
                        zzb = zzga.zzf(i16, R(t, j3));
                        i14 += zzb;
                    }
                    i3 = 0;
                    z = false;
                    j = 0;
                    break;
                case 68:
                    if (y(t, i16, i13)) {
                        zzb = zzga.g(i16, (zzic) unsafe2.getObject(t, j3), F(i13));
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
        int j4 = i14 + j(this.o, t);
        if (this.f) {
            e2<?> h = this.p.h(t);
            for (int i21 = i20; i21 < h.f9969a.n(); i21++) {
                Map.Entry<?, Object> i22 = h.f9969a.i(i21);
                i20 += e2.m((zzgk) i22.getKey(), i22.getValue());
            }
            for (Map.Entry<?, Object> entry : h.f9969a.o()) {
                i20 += e2.m((zzgk) entry.getKey(), entry.getValue());
            }
            return j4 + i20;
        }
        return j4;
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
    @Override // com.google.android.gms.internal.vision.o3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.vision.f1 r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 956
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.g(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.f1):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a2a  */
    @Override // com.google.android.gms.internal.vision.o3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(T r14, com.google.android.gms.internal.vision.x4 r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.h(java.lang.Object, com.google.android.gms.internal.vision.x4):void");
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final void i(T t, p3 p3Var, zzgd zzgdVar) throws IOException {
        Objects.requireNonNull(zzgdVar);
        g4 g4Var = this.o;
        b2<?> b2Var = this.p;
        e2<?> e2Var = null;
        Object obj = null;
        while (true) {
            try {
                int m = p3Var.m();
                int L = L(m);
                if (L >= 0) {
                    int I = I(L);
                    switch ((267386880 & I) >>> 20) {
                        case 0:
                            j4.c(t, I & ErrorCode.ERR_UNKNOWN, p3Var.readDouble());
                            B(t, L);
                            continue;
                        case 1:
                            j4.d(t, I & ErrorCode.ERR_UNKNOWN, p3Var.readFloat());
                            B(t, L);
                            continue;
                        case 2:
                            j4.e(t, I & ErrorCode.ERR_UNKNOWN, p3Var.B());
                            B(t, L);
                            continue;
                        case 3:
                            j4.e(t, I & ErrorCode.ERR_UNKNOWN, p3Var.z());
                            B(t, L);
                            continue;
                        case 4:
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, p3Var.u());
                            B(t, L);
                            continue;
                        case 5:
                            j4.e(t, I & ErrorCode.ERR_UNKNOWN, p3Var.y());
                            B(t, L);
                            continue;
                        case 6:
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, p3Var.i());
                            B(t, L);
                            continue;
                        case 7:
                            j4.g(t, I & ErrorCode.ERR_UNKNOWN, p3Var.k());
                            B(t, L);
                            continue;
                        case 8:
                            v(t, I, p3Var);
                            B(t, L);
                            continue;
                        case 9:
                            if (x(t, L)) {
                                long j = I & ErrorCode.ERR_UNKNOWN;
                                j4.f(t, j, zzgt.d(j4.G(t, j), p3Var.H(F(L), zzgdVar)));
                                break;
                            } else {
                                j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.H(F(L), zzgdVar));
                                B(t, L);
                                continue;
                            }
                        case 10:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.g());
                            B(t, L);
                            continue;
                        case 11:
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, p3Var.r());
                            B(t, L);
                            continue;
                        case 12:
                            int s2 = p3Var.s();
                            zzgy H = H(L);
                            if (H != null && !H.zzg(s2)) {
                                obj = q3.a(m, s2, obj, g4Var);
                                break;
                            }
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, s2);
                            B(t, L);
                            continue;
                        case 13:
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, p3Var.n());
                            B(t, L);
                            continue;
                        case 14:
                            j4.e(t, I & ErrorCode.ERR_UNKNOWN, p3Var.q());
                            B(t, L);
                            continue;
                        case 15:
                            j4.k(t, I & ErrorCode.ERR_UNKNOWN, p3Var.A());
                            B(t, L);
                            continue;
                        case 16:
                            j4.e(t, I & ErrorCode.ERR_UNKNOWN, p3Var.C());
                            B(t, L);
                            continue;
                        case 17:
                            if (x(t, L)) {
                                long j2 = I & ErrorCode.ERR_UNKNOWN;
                                j4.f(t, j2, zzgt.d(j4.G(t, j2), p3Var.L(F(L), zzgdVar)));
                                break;
                            } else {
                                j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.L(F(L), zzgdVar));
                                B(t, L);
                                continue;
                            }
                        case 18:
                            p3Var.zza(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 19:
                            p3Var.zzb(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 20:
                            p3Var.t(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 21:
                            p3Var.o(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 22:
                            p3Var.zze(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 23:
                            p3Var.h(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 24:
                            p3Var.f(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 25:
                            p3Var.e(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 26:
                            if (K(I)) {
                                p3Var.l(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                                break;
                            } else {
                                p3Var.b(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                                continue;
                            }
                        case 27:
                            p3Var.F(this.n.a(t, I & ErrorCode.ERR_UNKNOWN), F(L), zzgdVar);
                            continue;
                        case 28:
                            p3Var.j(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 29:
                            p3Var.G(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 30:
                            List<Integer> a2 = this.n.a(t, I & ErrorCode.ERR_UNKNOWN);
                            p3Var.E(a2);
                            obj = q3.c(m, a2, H(L), obj, g4Var);
                            continue;
                        case 31:
                            p3Var.K(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 32:
                            p3Var.J(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 33:
                            p3Var.x(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 34:
                            p3Var.v(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 35:
                            p3Var.zza(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 36:
                            p3Var.zzb(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 37:
                            p3Var.t(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 38:
                            p3Var.o(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 39:
                            p3Var.zze(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 40:
                            p3Var.h(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 41:
                            p3Var.f(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 42:
                            p3Var.e(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 43:
                            p3Var.G(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 44:
                            List<Integer> a3 = this.n.a(t, I & ErrorCode.ERR_UNKNOWN);
                            p3Var.E(a3);
                            obj = q3.c(m, a3, H(L), obj, g4Var);
                            continue;
                        case 45:
                            p3Var.K(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 46:
                            p3Var.J(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 47:
                            p3Var.x(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 48:
                            p3Var.v(this.n.a(t, I & ErrorCode.ERR_UNKNOWN));
                            continue;
                        case 49:
                            p3Var.I(this.n.a(t, I & ErrorCode.ERR_UNKNOWN), F(L), zzgdVar);
                            continue;
                        case 50:
                            Object G = G(L);
                            long I2 = I(L) & ErrorCode.ERR_UNKNOWN;
                            Object G2 = j4.G(t, I2);
                            if (G2 == null) {
                                G2 = this.q.b(G);
                                j4.f(t, I2, G2);
                            } else if (this.q.g(G2)) {
                                Object b = this.q.b(G);
                                this.q.c(b, G2);
                                j4.f(t, I2, b);
                                G2 = b;
                            }
                            p3Var.c(this.q.zzl(G2), this.q.e(G), zzgdVar);
                            continue;
                        case 51:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Double.valueOf(p3Var.readDouble()));
                            C(t, m, L);
                            continue;
                        case 52:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Float.valueOf(p3Var.readFloat()));
                            C(t, m, L);
                            continue;
                        case 53:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Long.valueOf(p3Var.B()));
                            C(t, m, L);
                            continue;
                        case 54:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Long.valueOf(p3Var.z()));
                            C(t, m, L);
                            continue;
                        case 55:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(p3Var.u()));
                            C(t, m, L);
                            continue;
                        case 56:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Long.valueOf(p3Var.y()));
                            C(t, m, L);
                            continue;
                        case 57:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(p3Var.i()));
                            C(t, m, L);
                            continue;
                        case 58:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Boolean.valueOf(p3Var.k()));
                            C(t, m, L);
                            continue;
                        case 59:
                            v(t, I, p3Var);
                            C(t, m, L);
                            continue;
                        case 60:
                            if (y(t, m, L)) {
                                long j3 = I & ErrorCode.ERR_UNKNOWN;
                                j4.f(t, j3, zzgt.d(j4.G(t, j3), p3Var.H(F(L), zzgdVar)));
                            } else {
                                j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.H(F(L), zzgdVar));
                                B(t, L);
                            }
                            C(t, m, L);
                            continue;
                        case 61:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.g());
                            C(t, m, L);
                            continue;
                        case 62:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(p3Var.r()));
                            C(t, m, L);
                            continue;
                        case 63:
                            int s3 = p3Var.s();
                            zzgy H2 = H(L);
                            if (H2 != null && !H2.zzg(s3)) {
                                obj = q3.a(m, s3, obj, g4Var);
                                break;
                            }
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(s3));
                            C(t, m, L);
                            continue;
                        case 64:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(p3Var.n()));
                            C(t, m, L);
                            continue;
                        case 65:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Long.valueOf(p3Var.q()));
                            C(t, m, L);
                            continue;
                        case 66:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Integer.valueOf(p3Var.A()));
                            C(t, m, L);
                            continue;
                        case 67:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, Long.valueOf(p3Var.C()));
                            C(t, m, L);
                            continue;
                        case 68:
                            j4.f(t, I & ErrorCode.ERR_UNKNOWN, p3Var.L(F(L), zzgdVar));
                            C(t, m, L);
                            continue;
                        default:
                            if (obj == null) {
                                try {
                                    obj = g4Var.n();
                                } catch (zzhb unused) {
                                    g4Var.e(p3Var);
                                    if (obj == null) {
                                        obj = g4Var.r(t);
                                    }
                                    if (!g4Var.f(obj, p3Var)) {
                                        for (int i = this.k; i < this.l; i++) {
                                            obj = q(t, this.j[i], obj, g4Var);
                                        }
                                        if (obj != null) {
                                            g4Var.k(t, obj);
                                            return;
                                        }
                                        return;
                                    }
                                    break;
                                }
                            }
                            if (!g4Var.f(obj, p3Var)) {
                                for (int i2 = this.k; i2 < this.l; i2++) {
                                    obj = q(t, this.j[i2], obj, g4Var);
                                }
                                if (obj != null) {
                                    g4Var.k(t, obj);
                                    return;
                                }
                                return;
                            }
                            continue;
                    }
                } else if (m == Integer.MAX_VALUE) {
                    for (int i3 = this.k; i3 < this.l; i3++) {
                        obj = q(t, this.j[i3], obj, g4Var);
                    }
                    if (obj != null) {
                        g4Var.k(t, obj);
                        return;
                    }
                    return;
                } else {
                    Object b2 = !this.f ? null : b2Var.b(zzgdVar, this.e, m);
                    if (b2 != null) {
                        if (e2Var == null) {
                            e2Var = b2Var.i(t);
                        }
                        e2<?> e2Var2 = e2Var;
                        obj = b2Var.c(p3Var, b2, zzgdVar, e2Var2, obj, g4Var);
                        e2Var = e2Var2;
                    } else {
                        g4Var.e(p3Var);
                        if (obj == null) {
                            obj = g4Var.r(t);
                        }
                        if (!g4Var.f(obj, p3Var)) {
                            for (int i4 = this.k; i4 < this.l; i4++) {
                                obj = q(t, this.j[i4], obj, g4Var);
                            }
                            if (obj != null) {
                                g4Var.k(t, obj);
                                return;
                            }
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                for (int i5 = this.k; i5 < this.l; i5++) {
                    obj = q(t, this.j[i5], obj, g4Var);
                }
                if (obj != null) {
                    g4Var.k(t, obj);
                }
                throw th;
            }
        }
    }

    public final int k(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, f1 f1Var) throws IOException {
        int k;
        Unsafe unsafe = s;
        long j2 = this.f9973a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(d1.n(bArr, i)));
                    k = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(d1.q(bArr, i)));
                    k = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    k = d1.k(bArr, i, f1Var);
                    unsafe.putObject(t, j, Long.valueOf(f1Var.b));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    k = d1.i(bArr, i, f1Var);
                    unsafe.putObject(t, j, Integer.valueOf(f1Var.f9971a));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(d1.m(bArr, i)));
                    k = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(d1.h(bArr, i)));
                    k = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    k = d1.k(bArr, i, f1Var);
                    unsafe.putObject(t, j, Boolean.valueOf(f1Var.b != 0));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int i9 = d1.i(bArr, i, f1Var);
                    int i10 = f1Var.f9971a;
                    if (i10 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !m4.g(bArr, i9, i9 + i10)) {
                        throw zzhc.zzgt();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, i9, i10, zzgt.f10024a));
                        i9 += i10;
                    }
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int g = d1.g(F(i8), bArr, i, i2, f1Var);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, f1Var.c);
                    } else {
                        unsafe.putObject(t, j, zzgt.d(object, f1Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return g;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    k = d1.t(bArr, i, f1Var);
                    unsafe.putObject(t, j, f1Var.c);
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int i11 = d1.i(bArr, i, f1Var);
                    int i12 = f1Var.f9971a;
                    zzgy H = H(i8);
                    if (H != null && !H.zzg(i12)) {
                        V(t).d(i3, Long.valueOf(i12));
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
                    k = d1.i(bArr, i, f1Var);
                    unsafe.putObject(t, j, Integer.valueOf(zzft.zzav(f1Var.f9971a)));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    k = d1.k(bArr, i, f1Var);
                    unsafe.putObject(t, j, Long.valueOf(zzft.zzr(f1Var.b)));
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    k = d1.f(F(i8), bArr, i, i2, (i3 & (-8)) | 4, f1Var);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, f1Var.c);
                    } else {
                        unsafe.putObject(t, j, zzgt.d(object2, f1Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return k;
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x01fb -> B:104:0x01fc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0133 -> B:55:0x0134). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x01ad -> B:84:0x01ae). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int l(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.vision.f1 r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 918
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.l(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.f1):int");
    }

    public final <K, V> int m(T t, byte[] bArr, int i, int i2, int i3, long j, f1 f1Var) throws IOException {
        Unsafe unsafe = s;
        Object G = G(i3);
        Object object = unsafe.getObject(t, j);
        if (this.q.g(object)) {
            Object b = this.q.b(G);
            this.q.c(b, object);
            unsafe.putObject(t, j, b);
            object = b;
        }
        this.q.e(G);
        this.q.zzl(object);
        int i4 = d1.i(bArr, i, f1Var);
        int i5 = f1Var.f9971a;
        if (i5 >= 0 && i5 <= i2 - i4) {
            throw null;
        }
        throw zzhc.zzgm();
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x0673, code lost:
        if (r1 != 18) goto L81;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v49, types: [com.google.android.gms.internal.vision.i2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v50, types: [com.google.android.gms.internal.vision.v2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v51, types: [com.google.android.gms.internal.vision.m2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v52, types: [com.google.android.gms.internal.vision.v2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v53, types: [com.google.android.gms.internal.vision.m2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v54, types: [com.google.android.gms.internal.vision.zzgz, com.google.android.gms.internal.vision.j1] */
    /* JADX WARN: Type inference failed for: r0v55, types: [com.google.android.gms.internal.vision.m2, com.google.android.gms.internal.vision.zzgz] */
    /* JADX WARN: Type inference failed for: r0v57, types: [com.google.android.gms.internal.vision.v2, com.google.android.gms.internal.vision.zzgz] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int n(T r33, byte[] r34, int r35, int r36, int r37, com.google.android.gms.internal.vision.f1 r38) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1936
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.f3.n(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.f1):int");
    }

    @Override // com.google.android.gms.internal.vision.o3
    public final T newInstance() {
        return (T) this.m.a(this.e);
    }

    public final <K, V, UT, UB> UB p(int i, int i2, Map<K, V> map, zzgy zzgyVar, UB ub, g4<UT, UB> g4Var) {
        y2<?, ?> e = this.q.e(G(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzgyVar.zzg(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = g4Var.n();
                }
                r1 zzaq = zzfh.zzaq(zzhu.a(e, next.getKey(), next.getValue()));
                try {
                    zzhu.b(zzaq.b(), e, next.getKey(), next.getValue());
                    g4Var.b(ub, i2, zzaq.a());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    public final <UT, UB> UB q(Object obj, int i, UB ub, g4<UT, UB> g4Var) {
        zzgy H;
        int i2 = this.f9973a[i];
        Object G = j4.G(obj, I(i) & ErrorCode.ERR_UNKNOWN);
        return (G == null || (H = H(i)) == null) ? ub : (UB) p(i, i2, this.q.zzl(G), H, ub, g4Var);
    }

    public final <K, V> void u(x4 x4Var, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            x4Var.h(i, this.q.e(G(i2)), this.q.d(obj));
        }
    }

    public final void v(Object obj, int i, p3 p3Var) throws IOException {
        if (K(i)) {
            j4.f(obj, i & ErrorCode.ERR_UNKNOWN, p3Var.d());
        } else if (this.g) {
            j4.f(obj, i & ErrorCode.ERR_UNKNOWN, p3Var.a());
        } else {
            j4.f(obj, i & ErrorCode.ERR_UNKNOWN, p3Var.g());
        }
    }

    public final void w(T t, T t2, int i) {
        long I = I(i) & ErrorCode.ERR_UNKNOWN;
        if (x(t2, i)) {
            Object G = j4.G(t, I);
            Object G2 = j4.G(t2, I);
            if (G != null && G2 != null) {
                j4.f(t, I, zzgt.d(G, G2));
                B(t, i);
            } else if (G2 != null) {
                j4.f(t, I, G2);
                B(t, i);
            }
        }
    }

    public final boolean x(T t, int i) {
        int J = J(i);
        long j = J & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (j4.A(t, j) & (1 << (J >>> 20))) != 0;
        }
        int I = I(i);
        long j2 = I & ErrorCode.ERR_UNKNOWN;
        switch ((I & 267386880) >>> 20) {
            case 0:
                return j4.F(t, j2) != 0.0d;
            case 1:
                return j4.E(t, j2) != 0.0f;
            case 2:
                return j4.C(t, j2) != 0;
            case 3:
                return j4.C(t, j2) != 0;
            case 4:
                return j4.A(t, j2) != 0;
            case 5:
                return j4.C(t, j2) != 0;
            case 6:
                return j4.A(t, j2) != 0;
            case 7:
                return j4.D(t, j2);
            case 8:
                Object G = j4.G(t, j2);
                if (G instanceof String) {
                    return !((String) G).isEmpty();
                } else if (G instanceof zzfh) {
                    return !zzfh.zzsd.equals(G);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return j4.G(t, j2) != null;
            case 10:
                return !zzfh.zzsd.equals(j4.G(t, j2));
            case 11:
                return j4.A(t, j2) != 0;
            case 12:
                return j4.A(t, j2) != 0;
            case 13:
                return j4.A(t, j2) != 0;
            case 14:
                return j4.C(t, j2) != 0;
            case 15:
                return j4.A(t, j2) != 0;
            case 16:
                return j4.C(t, j2) != 0;
            case 17:
                return j4.G(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean y(T t, int i, int i2) {
        return j4.A(t, (long) (J(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    public final boolean z(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return x(t, i);
        }
        return (i3 & i4) != 0;
    }
}
