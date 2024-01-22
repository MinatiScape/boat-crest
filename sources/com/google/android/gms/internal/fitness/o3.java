package com.google.android.gms.internal.fitness;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class o3<T> implements x3<T> {
    public static final int[] n = new int[0];
    public static final Unsafe o = s4.s();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8841a;
    public final Object[] b;
    public final zzik c;
    public final boolean d;
    public final boolean e;
    public final int[] f;
    public final int g;
    public final int h;
    public final p3 i;
    public final z2 j;
    public final n4<?, ?> k;
    public final m2<?> l;
    public final h3 m;

    public o3(int[] iArr, Object[] objArr, int i, int i2, zzik zzikVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, p3 p3Var, z2 z2Var, n4<?, ?> n4Var, m2<?> m2Var, h3 h3Var) {
        this.f8841a = iArr;
        this.b = objArr;
        boolean z3 = zzikVar instanceof zzgy;
        this.e = z;
        this.d = m2Var != null && m2Var.f(zzikVar);
        this.f = iArr2;
        this.g = i3;
        this.h = i4;
        this.i = p3Var;
        this.j = z2Var;
        this.k = n4Var;
        this.l = m2Var;
        this.c = zzikVar;
        this.m = h3Var;
    }

    public static <T> double A(T t, long j) {
        return ((Double) s4.G(t, j)).doubleValue();
    }

    public static <T> float B(T t, long j) {
        return ((Float) s4.G(t, j)).floatValue();
    }

    public static <T> int C(T t, long j) {
        return ((Integer) s4.G(t, j)).intValue();
    }

    public static <T> long D(T t, long j) {
        return ((Long) s4.G(t, j)).longValue();
    }

    public static <T> boolean E(T t, long j) {
        return ((Boolean) s4.G(t, j)).booleanValue();
    }

    public static <UT, UB> int f(n4<UT, UB> n4Var, T t) {
        return n4Var.f(n4Var.g(t));
    }

    /* JADX WARN: Removed duplicated region for block: B:163:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> com.google.android.gms.internal.fitness.o3<T> g(java.lang.Class<T> r33, com.google.android.gms.internal.fitness.l3 r34, com.google.android.gms.internal.fitness.p3 r35, com.google.android.gms.internal.fitness.z2 r36, com.google.android.gms.internal.fitness.n4<?, ?> r37, com.google.android.gms.internal.fitness.m2<?> r38, com.google.android.gms.internal.fitness.h3 r39) {
        /*
            Method dump skipped, instructions count: 1045
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.o3.g(java.lang.Class, com.google.android.gms.internal.fitness.l3, com.google.android.gms.internal.fitness.p3, com.google.android.gms.internal.fitness.z2, com.google.android.gms.internal.fitness.n4, com.google.android.gms.internal.fitness.m2, com.google.android.gms.internal.fitness.h3):com.google.android.gms.internal.fitness.o3");
    }

    public static Field h(Class<?> cls, String str) {
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

    public static void i(int i, Object obj, d5 d5Var) throws IOException {
        if (obj instanceof String) {
            d5Var.zza(i, (String) obj);
        } else {
            d5Var.j(i, (zzfx) obj);
        }
    }

    public static <UT, UB> void j(n4<UT, UB> n4Var, T t, d5 d5Var) throws IOException {
        n4Var.a(n4Var.g(t), d5Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean o(Object obj, int i, x3 x3Var) {
        return x3Var.zzl(s4.G(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static List<?> z(Object obj, long j) {
        return (List) s4.G(obj, j);
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final int a(T t) {
        int i;
        int zzj;
        int length = this.f8841a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int r = r(i3);
            int i4 = this.f8841a[i3];
            long j = 1048575 & r;
            int i5 = 37;
            switch ((r & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zzj = zzhc.zzj(Double.doubleToLongBits(s4.F(t, j)));
                    i2 = i + zzj;
                    break;
                case 1:
                    i = i2 * 53;
                    zzj = Float.floatToIntBits(s4.E(t, j));
                    i2 = i + zzj;
                    break;
                case 2:
                    i = i2 * 53;
                    zzj = zzhc.zzj(s4.C(t, j));
                    i2 = i + zzj;
                    break;
                case 3:
                    i = i2 * 53;
                    zzj = zzhc.zzj(s4.C(t, j));
                    i2 = i + zzj;
                    break;
                case 4:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 5:
                    i = i2 * 53;
                    zzj = zzhc.zzj(s4.C(t, j));
                    i2 = i + zzj;
                    break;
                case 6:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 7:
                    i = i2 * 53;
                    zzj = zzhc.zzc(s4.D(t, j));
                    i2 = i + zzj;
                    break;
                case 8:
                    i = i2 * 53;
                    zzj = ((String) s4.G(t, j)).hashCode();
                    i2 = i + zzj;
                    break;
                case 9:
                    Object G = s4.G(t, j);
                    if (G != null) {
                        i5 = G.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzj = s4.G(t, j).hashCode();
                    i2 = i + zzj;
                    break;
                case 11:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 12:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 13:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 14:
                    i = i2 * 53;
                    zzj = zzhc.zzj(s4.C(t, j));
                    i2 = i + zzj;
                    break;
                case 15:
                    i = i2 * 53;
                    zzj = s4.A(t, j);
                    i2 = i + zzj;
                    break;
                case 16:
                    i = i2 * 53;
                    zzj = zzhc.zzj(s4.C(t, j));
                    i2 = i + zzj;
                    break;
                case 17:
                    Object G2 = s4.G(t, j);
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
                    zzj = s4.G(t, j).hashCode();
                    i2 = i + zzj;
                    break;
                case 50:
                    i = i2 * 53;
                    zzj = s4.G(t, j).hashCode();
                    i2 = i + zzj;
                    break;
                case 51:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(Double.doubleToLongBits(A(t, j)));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = Float.floatToIntBits(B(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(D(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(D(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(D(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzc(E(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = ((String) s4.G(t, j)).hashCode();
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = s4.G(t, j).hashCode();
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = s4.G(t, j).hashCode();
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(D(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = C(t, j);
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = zzhc.zzj(D(t, j));
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (m(t, i4, i3)) {
                        i = i2 * 53;
                        zzj = s4.G(t, j).hashCode();
                        i2 = i + zzj;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.k.g(t).hashCode();
        return this.d ? (hashCode * 53) + this.l.c(t).hashCode() : hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.fitness.s4.F(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.fitness.s4.F(r11, r6))) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.fitness.z3.B(com.google.android.gms.internal.fitness.s4.G(r10, r6), com.google.android.gms.internal.fitness.s4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (com.google.android.gms.internal.fitness.z3.B(com.google.android.gms.internal.fitness.s4.G(r10, r6), com.google.android.gms.internal.fitness.s4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x007e, code lost:
        if (com.google.android.gms.internal.fitness.s4.C(r10, r6) == com.google.android.gms.internal.fitness.s4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a4, code lost:
        if (com.google.android.gms.internal.fitness.s4.C(r10, r6) == com.google.android.gms.internal.fitness.s4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c8, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (com.google.android.gms.internal.fitness.z3.B(com.google.android.gms.internal.fitness.s4.G(r10, r6), com.google.android.gms.internal.fitness.s4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0106, code lost:
        if (com.google.android.gms.internal.fitness.z3.B(com.google.android.gms.internal.fitness.s4.G(r10, r6), com.google.android.gms.internal.fitness.s4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011c, code lost:
        if (com.google.android.gms.internal.fitness.z3.B(com.google.android.gms.internal.fitness.s4.G(r10, r6), com.google.android.gms.internal.fitness.s4.G(r11, r6)) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x012e, code lost:
        if (com.google.android.gms.internal.fitness.s4.D(r10, r6) == com.google.android.gms.internal.fitness.s4.D(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0140, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0154, code lost:
        if (com.google.android.gms.internal.fitness.s4.C(r10, r6) == com.google.android.gms.internal.fitness.s4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0165, code lost:
        if (com.google.android.gms.internal.fitness.s4.A(r10, r6) == com.google.android.gms.internal.fitness.s4.A(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0178, code lost:
        if (com.google.android.gms.internal.fitness.s4.C(r10, r6) == com.google.android.gms.internal.fitness.s4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x018b, code lost:
        if (com.google.android.gms.internal.fitness.s4.C(r10, r6) == com.google.android.gms.internal.fitness.s4.C(r11, r6)) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.fitness.s4.E(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.fitness.s4.E(r11, r6))) goto L85;
     */
    @Override // com.google.android.gms.internal.fitness.x3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.o3.b(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final void c(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f8841a.length; i += 3) {
            int r = r(i);
            long j = 1048575 & r;
            int i2 = this.f8841a[i];
            switch ((r & 267386880) >>> 20) {
                case 0:
                    if (w(t2, i)) {
                        s4.c(t, j, s4.F(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (w(t2, i)) {
                        s4.d(t, j, s4.E(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (w(t2, i)) {
                        s4.f(t, j, s4.C(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (w(t2, i)) {
                        s4.f(t, j, s4.C(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (w(t2, i)) {
                        s4.f(t, j, s4.C(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (w(t2, i)) {
                        s4.h(t, j, s4.D(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (w(t2, i)) {
                        s4.g(t, j, s4.G(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    l(t, t2, i);
                    break;
                case 10:
                    if (w(t2, i)) {
                        s4.g(t, j, s4.G(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (w(t2, i)) {
                        s4.f(t, j, s4.C(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (w(t2, i)) {
                        s4.e(t, j, s4.A(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (w(t2, i)) {
                        s4.f(t, j, s4.C(t2, j));
                        x(t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    l(t, t2, i);
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
                    this.j.b(t, t2, j);
                    break;
                case 50:
                    z3.e(this.m, t, t2, j);
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
                    if (m(t2, i2, i)) {
                        s4.g(t, j, s4.G(t2, j));
                        t(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    v(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (m(t2, i2, i)) {
                        s4.g(t, j, s4.G(t2, j));
                        t(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    v(t, t2, i);
                    break;
            }
        }
        z3.f(this.k, t, t2);
        if (this.d) {
            z3.d(this.l, t, t2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.fitness.x3
    public final int d(T t) {
        int i;
        int i2;
        int i3;
        long j;
        int zzd;
        int zzb;
        int zzm;
        int Z;
        int L;
        int zzr;
        int zzt;
        int zzb2;
        int L2;
        int zzr2;
        int zzt2;
        boolean z = this.e;
        int i4 = 267386880;
        int i5 = ErrorCode.ERR_UNKNOWN;
        int i6 = 1;
        int i7 = 0;
        if (z) {
            Unsafe unsafe = o;
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.f8841a.length) {
                int r = r(i8);
                int i10 = (r & i4) >>> 20;
                int i11 = this.f8841a[i8];
                long j2 = r & ErrorCode.ERR_UNKNOWN;
                if (i10 >= zzgu.zzwo.id() && i10 <= zzgu.zzxb.id()) {
                    int i12 = this.f8841a[i8 + 2];
                }
                switch (i10) {
                    case 0:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzb(i11, 0.0d);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 1:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzb(i11, 0.0f);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 2:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzd(i11, s4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 3:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zze(i11, s4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 4:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzi(i11, s4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 5:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzg(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 6:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzl(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 7:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzb(i11, true);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 8:
                        if (w(t, i8)) {
                            Object G = s4.G(t, j2);
                            if (G instanceof zzfx) {
                                zzb2 = zzgk.zzc(i11, (zzfx) G);
                                break;
                            } else {
                                zzb2 = zzgk.zzb(i11, (String) G);
                                break;
                            }
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 9:
                        if (w(t, i8)) {
                            zzb2 = z3.k(i11, s4.G(t, j2), p(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 10:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzc(i11, (zzfx) s4.G(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 11:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzj(i11, s4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 12:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzn(i11, s4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 13:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzm(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 14:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzh(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 15:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzk(i11, s4.A(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 16:
                        if (w(t, i8)) {
                            zzb2 = zzgk.zzf(i11, s4.C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 17:
                        if (w(t, i8)) {
                            zzb2 = zzgk.f(i11, (zzik) s4.G(t, j2), p(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 18:
                        zzb2 = z3.a0(i11, z(t, j2), false);
                        break;
                    case 19:
                        zzb2 = z3.Z(i11, z(t, j2), false);
                        break;
                    case 20:
                        zzb2 = z3.S(i11, z(t, j2), false);
                        break;
                    case 21:
                        zzb2 = z3.T(i11, z(t, j2), false);
                        break;
                    case 22:
                        zzb2 = z3.W(i11, z(t, j2), false);
                        break;
                    case 23:
                        zzb2 = z3.a0(i11, z(t, j2), false);
                        break;
                    case 24:
                        zzb2 = z3.Z(i11, z(t, j2), false);
                        break;
                    case 25:
                        zzb2 = z3.b0(i11, z(t, j2), false);
                        break;
                    case 26:
                        zzb2 = z3.l(i11, z(t, j2));
                        break;
                    case 27:
                        zzb2 = z3.m(i11, z(t, j2), p(i8));
                        break;
                    case 28:
                        zzb2 = z3.p(i11, z(t, j2));
                        break;
                    case 29:
                        zzb2 = z3.X(i11, z(t, j2), false);
                        break;
                    case 30:
                        zzb2 = z3.V(i11, z(t, j2), false);
                        break;
                    case 31:
                        zzb2 = z3.Z(i11, z(t, j2), false);
                        break;
                    case 32:
                        zzb2 = z3.a0(i11, z(t, j2), false);
                        break;
                    case 33:
                        zzb2 = z3.Y(i11, z(t, j2), false);
                        break;
                    case 34:
                        zzb2 = z3.U(i11, z(t, j2), false);
                        break;
                    case 35:
                        L2 = z3.L((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 36:
                        L2 = z3.J((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 37:
                        L2 = z3.g((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 38:
                        L2 = z3.n((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 39:
                        L2 = z3.C((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 40:
                        L2 = z3.L((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 41:
                        L2 = z3.J((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 42:
                        L2 = z3.N((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 43:
                        L2 = z3.F((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 44:
                        L2 = z3.z((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 45:
                        L2 = z3.J((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 46:
                        L2 = z3.L((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 47:
                        L2 = z3.H((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 48:
                        L2 = z3.r((List) unsafe.getObject(t, j2));
                        if (L2 > 0) {
                            zzr2 = zzgk.zzr(i11);
                            zzt2 = zzgk.zzt(L2);
                            zzb2 = zzr2 + zzt2 + L2;
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 49:
                        zzb2 = z3.q(i11, z(t, j2), p(i8));
                        break;
                    case 50:
                        zzb2 = this.m.a(i11, s4.G(t, j2), q(i8));
                        break;
                    case 51:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzb(i11, 0.0d);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 52:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzb(i11, 0.0f);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 53:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzd(i11, D(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 54:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zze(i11, D(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 55:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzi(i11, C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 56:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzg(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 57:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzl(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 58:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzb(i11, true);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 59:
                        if (m(t, i11, i8)) {
                            Object G2 = s4.G(t, j2);
                            if (G2 instanceof zzfx) {
                                zzb2 = zzgk.zzc(i11, (zzfx) G2);
                                break;
                            } else {
                                zzb2 = zzgk.zzb(i11, (String) G2);
                                break;
                            }
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 60:
                        if (m(t, i11, i8)) {
                            zzb2 = z3.k(i11, s4.G(t, j2), p(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 61:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzc(i11, (zzfx) s4.G(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 62:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzj(i11, C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 63:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzn(i11, C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 64:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzm(i11, 0);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 65:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzh(i11, 0L);
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 66:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzk(i11, C(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 67:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.zzf(i11, D(t, j2));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    case 68:
                        if (m(t, i11, i8)) {
                            zzb2 = zzgk.f(i11, (zzik) s4.G(t, j2), p(i8));
                            break;
                        } else {
                            continue;
                            i8 += 3;
                            i4 = 267386880;
                        }
                    default:
                        i8 += 3;
                        i4 = 267386880;
                }
                i9 += zzb2;
                i8 += 3;
                i4 = 267386880;
            }
            return i9 + f(this.k, t);
        }
        Unsafe unsafe2 = o;
        int i13 = 1048575;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < this.f8841a.length) {
            int r2 = r(i14);
            int[] iArr = this.f8841a;
            int i17 = iArr[i14];
            int i18 = (r2 & 267386880) >>> 20;
            if (i18 <= 17) {
                int i19 = iArr[i14 + 2];
                int i20 = i19 & i5;
                i = i6 << (i19 >>> 20);
                if (i20 != i13) {
                    i16 = unsafe2.getInt(t, i20);
                    i13 = i20;
                }
            } else {
                i = 0;
            }
            long j3 = r2 & i5;
            switch (i18) {
                case 0:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzgk.zzb(i17, 0.0d);
                        continue;
                        i14 += 3;
                        i6 = i2;
                        i7 = i3;
                        i5 = ErrorCode.ERR_UNKNOWN;
                    }
                    break;
                case 1:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzgk.zzb(i17, 0.0f);
                        break;
                    }
                    break;
                case 2:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzgk.zzd(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 3:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzgk.zze(i17, unsafe2.getLong(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 4:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i & i16) != 0) {
                        zzd = zzgk.zzi(i17, unsafe2.getInt(t, j3));
                        i15 += zzd;
                        break;
                    }
                    break;
                case 5:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    if ((i16 & i) != 0) {
                        zzd = zzgk.zzg(i17, 0L);
                        i15 += zzd;
                        break;
                    }
                    break;
                case 6:
                    i2 = 1;
                    i3 = 0;
                    if ((i16 & i) != 0) {
                        i15 += zzgk.zzl(i17, 0);
                    }
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 7:
                    if ((i16 & i) != 0) {
                        i2 = 1;
                        i15 += zzgk.zzb(i17, true);
                        i3 = 0;
                        j = 0;
                        i14 += 3;
                        i6 = i2;
                        i7 = i3;
                        i5 = ErrorCode.ERR_UNKNOWN;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 8:
                    if ((i16 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzfx) {
                            zzb = zzgk.zzc(i17, (zzfx) object);
                        } else {
                            zzb = zzgk.zzb(i17, (String) object);
                        }
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 9:
                    if ((i16 & i) != 0) {
                        zzb = z3.k(i17, unsafe2.getObject(t, j3), p(i14));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 10:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzc(i17, (zzfx) unsafe2.getObject(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 11:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzj(i17, unsafe2.getInt(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 12:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzn(i17, unsafe2.getInt(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 13:
                    if ((i16 & i) != 0) {
                        zzm = zzgk.zzm(i17, 0);
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 14:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzh(i17, 0L);
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 15:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzk(i17, unsafe2.getInt(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 16:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.zzf(i17, unsafe2.getLong(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 17:
                    if ((i16 & i) != 0) {
                        zzb = zzgk.f(i17, (zzik) unsafe2.getObject(t, j3), p(i14));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 18:
                    zzb = z3.a0(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 19:
                    i3 = 0;
                    Z = z3.Z(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 20:
                    i3 = 0;
                    Z = z3.S(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 21:
                    i3 = 0;
                    Z = z3.T(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 22:
                    i3 = 0;
                    Z = z3.W(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 23:
                    i3 = 0;
                    Z = z3.a0(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 24:
                    i3 = 0;
                    Z = z3.Z(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 25:
                    i3 = 0;
                    Z = z3.b0(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 26:
                    zzb = z3.l(i17, (List) unsafe2.getObject(t, j3));
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 27:
                    zzb = z3.m(i17, (List) unsafe2.getObject(t, j3), p(i14));
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 28:
                    zzb = z3.p(i17, (List) unsafe2.getObject(t, j3));
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 29:
                    zzb = z3.X(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 30:
                    i3 = 0;
                    Z = z3.V(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 31:
                    i3 = 0;
                    Z = z3.Z(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 32:
                    i3 = 0;
                    Z = z3.a0(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 33:
                    i3 = 0;
                    Z = z3.Y(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 34:
                    i3 = 0;
                    Z = z3.U(i17, (List) unsafe2.getObject(t, j3), false);
                    i15 += Z;
                    i2 = 1;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 35:
                    L = z3.L((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 36:
                    L = z3.J((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 37:
                    L = z3.g((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 38:
                    L = z3.n((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 39:
                    L = z3.C((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 40:
                    L = z3.L((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 41:
                    L = z3.J((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 42:
                    L = z3.N((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 43:
                    L = z3.F((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 44:
                    L = z3.z((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 45:
                    L = z3.J((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 46:
                    L = z3.L((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 47:
                    L = z3.H((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 48:
                    L = z3.r((List) unsafe2.getObject(t, j3));
                    if (L > 0) {
                        zzr = zzgk.zzr(i17);
                        zzt = zzgk.zzt(L);
                        zzm = zzr + zzt + L;
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 49:
                    zzb = z3.q(i17, (List) unsafe2.getObject(t, j3), p(i14));
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 50:
                    zzb = this.m.a(i17, unsafe2.getObject(t, j3), q(i14));
                    i15 += zzb;
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 51:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzb(i17, 0.0d);
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 52:
                    if (m(t, i17, i14)) {
                        zzm = zzgk.zzb(i17, 0.0f);
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 53:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzd(i17, D(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 54:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zze(i17, D(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 55:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzi(i17, C(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 56:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzg(i17, 0L);
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 57:
                    if (m(t, i17, i14)) {
                        zzm = zzgk.zzl(i17, 0);
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 58:
                    if (m(t, i17, i14)) {
                        zzm = zzgk.zzb(i17, true);
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 59:
                    if (m(t, i17, i14)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzfx) {
                            zzb = zzgk.zzc(i17, (zzfx) object2);
                        } else {
                            zzb = zzgk.zzb(i17, (String) object2);
                        }
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 60:
                    if (m(t, i17, i14)) {
                        zzb = z3.k(i17, unsafe2.getObject(t, j3), p(i14));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 61:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzc(i17, (zzfx) unsafe2.getObject(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 62:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzj(i17, C(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 63:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzn(i17, C(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 64:
                    if (m(t, i17, i14)) {
                        zzm = zzgk.zzm(i17, 0);
                        i15 += zzm;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 65:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzh(i17, 0L);
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 66:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzk(i17, C(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 67:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.zzf(i17, D(t, j3));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                case 68:
                    if (m(t, i17, i14)) {
                        zzb = zzgk.f(i17, (zzik) unsafe2.getObject(t, j3), p(i14));
                        i15 += zzb;
                    }
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
                default:
                    i2 = 1;
                    i3 = 0;
                    j = 0;
                    i14 += 3;
                    i6 = i2;
                    i7 = i3;
                    i5 = ErrorCode.ERR_UNKNOWN;
            }
            i14 += 3;
            i6 = i2;
            i7 = i3;
            i5 = ErrorCode.ERR_UNKNOWN;
        }
        int i21 = i7;
        int f = i15 + f(this.k, t);
        if (this.d) {
            q2<?> c = this.l.c(t);
            for (int i22 = i21; i22 < c.f8845a.n(); i22++) {
                Map.Entry<?, Object> h = c.f8845a.h(i22);
                i21 += q2.k((zzgv) h.getKey(), h.getValue());
            }
            for (Map.Entry<?, Object> entry : c.f8845a.o()) {
                i21 += q2.k((zzgv) entry.getKey(), entry.getValue());
            }
            return f + i21;
        }
        return f;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0a2a  */
    @Override // com.google.android.gms.internal.fitness.x3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(T r14, com.google.android.gms.internal.fitness.d5 r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.o3.e(java.lang.Object, com.google.android.gms.internal.fitness.d5):void");
    }

    public final <K, V> void k(d5 d5Var, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            d5Var.l(i, this.m.b(q(i2)), this.m.e(obj));
        }
    }

    public final void l(T t, T t2, int i) {
        long r = r(i) & ErrorCode.ERR_UNKNOWN;
        if (w(t2, i)) {
            Object G = s4.G(t, r);
            Object G2 = s4.G(t2, r);
            if (G != null && G2 != null) {
                s4.g(t, r, zzhc.d(G, G2));
                x(t, i);
            } else if (G2 != null) {
                s4.g(t, r, G2);
                x(t, i);
            }
        }
    }

    public final boolean m(T t, int i, int i2) {
        return s4.A(t, (long) (s(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    public final boolean n(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return w(t, i);
        }
        return (i3 & i4) != 0;
    }

    public final x3 p(int i) {
        int i2 = (i / 3) << 1;
        x3 x3Var = (x3) this.b[i2];
        if (x3Var != null) {
            return x3Var;
        }
        x3<T> b = t3.a().b((Class) this.b[i2 + 1]);
        this.b[i2] = b;
        return b;
    }

    public final Object q(int i) {
        return this.b[(i / 3) << 1];
    }

    public final int r(int i) {
        return this.f8841a[i + 1];
    }

    public final int s(int i) {
        return this.f8841a[i + 2];
    }

    public final void t(T t, int i, int i2) {
        s4.e(t, s(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x048b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(T r18, com.google.android.gms.internal.fitness.d5 r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.o3.u(java.lang.Object, com.google.android.gms.internal.fitness.d5):void");
    }

    public final void v(T t, T t2, int i) {
        int r = r(i);
        int i2 = this.f8841a[i];
        long j = r & ErrorCode.ERR_UNKNOWN;
        if (m(t2, i2, i)) {
            Object G = m(t, i2, i) ? s4.G(t, j) : null;
            Object G2 = s4.G(t2, j);
            if (G != null && G2 != null) {
                s4.g(t, j, zzhc.d(G, G2));
                t(t, i2, i);
            } else if (G2 != null) {
                s4.g(t, j, G2);
                t(t, i2, i);
            }
        }
    }

    public final boolean w(T t, int i) {
        int s = s(i);
        long j = s & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (s4.A(t, j) & (1 << (s >>> 20))) != 0;
        }
        int r = r(i);
        long j2 = r & ErrorCode.ERR_UNKNOWN;
        switch ((r & 267386880) >>> 20) {
            case 0:
                return s4.F(t, j2) != 0.0d;
            case 1:
                return s4.E(t, j2) != 0.0f;
            case 2:
                return s4.C(t, j2) != 0;
            case 3:
                return s4.C(t, j2) != 0;
            case 4:
                return s4.A(t, j2) != 0;
            case 5:
                return s4.C(t, j2) != 0;
            case 6:
                return s4.A(t, j2) != 0;
            case 7:
                return s4.D(t, j2);
            case 8:
                Object G = s4.G(t, j2);
                if (G instanceof String) {
                    return !((String) G).isEmpty();
                } else if (G instanceof zzfx) {
                    return !zzfx.zzub.equals(G);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return s4.G(t, j2) != null;
            case 10:
                return !zzfx.zzub.equals(s4.G(t, j2));
            case 11:
                return s4.A(t, j2) != 0;
            case 12:
                return s4.A(t, j2) != 0;
            case 13:
                return s4.A(t, j2) != 0;
            case 14:
                return s4.C(t, j2) != 0;
            case 15:
                return s4.A(t, j2) != 0;
            case 16:
                return s4.C(t, j2) != 0;
            case 17:
                return s4.G(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final void x(T t, int i) {
        int s = s(i);
        long j = 1048575 & s;
        if (j == 1048575) {
            return;
        }
        s4.e(t, j, (1 << (s >>> 20)) | s4.A(t, j));
    }

    public final boolean y(T t, T t2, int i) {
        return w(t, i) == w(t2, i);
    }

    @Override // com.google.android.gms.internal.fitness.x3
    public final void zze(T t) {
        int i;
        int i2 = this.g;
        while (true) {
            i = this.h;
            if (i2 >= i) {
                break;
            }
            long r = r(this.f[i2]) & ErrorCode.ERR_UNKNOWN;
            Object G = s4.G(t, r);
            if (G != null) {
                s4.g(t, r, this.m.d(G));
            }
            i2++;
        }
        int length = this.f.length;
        while (i < length) {
            this.j.a(t, this.f[i]);
            i++;
        }
        this.k.c(t);
        if (this.d) {
            this.l.e(t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.fitness.x3
    public final boolean zzl(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.g) {
                return !this.d || this.l.c(t).c();
            }
            int i6 = this.f[i5];
            int i7 = this.f8841a[i6];
            int r = r(i6);
            int i8 = this.f8841a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = o.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (((268435456 & r) != 0) && !n(t, i6, i, i2, i10)) {
                return false;
            }
            int i11 = (267386880 & r) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (m(t, i7, i6) && !o(t, r, p(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 == 50 && !this.m.e(s4.G(t, r & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            this.m.b(q(i6));
                            throw null;
                        }
                    }
                }
                List list = (List) s4.G(t, r & ErrorCode.ERR_UNKNOWN);
                if (!list.isEmpty()) {
                    x3 p = p(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!p.zzl(list.get(i12))) {
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
            } else if (n(t, i6, i, i2, i10) && !o(t, r, p(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
    }
}
