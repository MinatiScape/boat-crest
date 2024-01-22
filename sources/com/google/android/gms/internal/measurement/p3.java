package com.google.android.gms.internal.measurement;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class p3<T> implements x3<T> {
    public static final int[] o = new int[0];
    public static final Unsafe p = t4.l();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8921a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final zzlg e;
    public final boolean f;
    public final boolean g;
    public final int[] h;
    public final int i;
    public final int j;
    public final f3 k;
    public final l4<?, ?> l;
    public final v2<?> m;
    public final k3 n;

    /* JADX WARN: Multi-variable type inference failed */
    public p3(int[] iArr, int[] iArr2, Object[] objArr, int i, int i2, zzlg zzlgVar, boolean z, boolean z2, int[] iArr3, int i3, int i4, r3 r3Var, f3 f3Var, l4<?, ?> l4Var, v2<?> v2Var, k3 k3Var) {
        this.f8921a = iArr;
        this.b = iArr2;
        this.c = objArr;
        this.d = i;
        this.g = zzlgVar;
        boolean z3 = false;
        if (l4Var != 0 && l4Var.c((zzlg) i2)) {
            z3 = true;
        }
        this.f = z3;
        this.h = z2;
        this.i = iArr3;
        this.j = i3;
        this.k = r3Var;
        this.l = f3Var;
        this.m = l4Var;
        this.e = i2;
        this.n = v2Var;
    }

    public static <T> p3<T> A(Class<T> cls, m3 m3Var, r3 r3Var, f3 f3Var, l4<?, ?> l4Var, v2<?> v2Var, k3 k3Var) {
        if (m3Var instanceof w3) {
            return B((w3) m3Var, r3Var, f3Var, l4Var, v2Var, k3Var);
        }
        j4 j4Var = (j4) m3Var;
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
    public static <T> com.google.android.gms.internal.measurement.p3<T> B(com.google.android.gms.internal.measurement.w3 r34, com.google.android.gms.internal.measurement.r3 r35, com.google.android.gms.internal.measurement.f3 r36, com.google.android.gms.internal.measurement.l4<?, ?> r37, com.google.android.gms.internal.measurement.v2<?> r38, com.google.android.gms.internal.measurement.k3 r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.p3.B(com.google.android.gms.internal.measurement.w3, com.google.android.gms.internal.measurement.r3, com.google.android.gms.internal.measurement.f3, com.google.android.gms.internal.measurement.l4, com.google.android.gms.internal.measurement.v2, com.google.android.gms.internal.measurement.k3):com.google.android.gms.internal.measurement.p3");
    }

    public static <T> double C(T t, long j) {
        return ((Double) t4.k(t, j)).doubleValue();
    }

    public static <T> float D(T t, long j) {
        return ((Float) t4.k(t, j)).floatValue();
    }

    public static <T> int G(T t, long j) {
        return ((Integer) t4.k(t, j)).intValue();
    }

    public static int e(int i) {
        return (i >>> 20) & 255;
    }

    public static <T> long g(T t, long j) {
        return ((Long) t4.k(t, j)).longValue();
    }

    public static Field k(Class<?> cls, String str) {
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

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean s(Object obj, int i, x3 x3Var) {
        return x3Var.b(t4.k(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static <T> boolean u(T t, long j) {
        return ((Boolean) t4.k(t, j)).booleanValue();
    }

    public static final void x(int i, Object obj, s2 s2Var) throws IOException {
        if (obj instanceof String) {
            s2Var.f(i, (String) obj);
        } else {
            s2Var.o(i, (zziy) obj);
        }
    }

    public static zzmj z(Object obj) {
        zzjz zzjzVar = (zzjz) obj;
        zzmj zzmjVar = zzjzVar.zzc;
        if (zzmjVar == zzmj.zzc()) {
            zzmj b = zzmj.b();
            zzjzVar.zzc = b;
            return b;
        }
        return zzmjVar;
    }

    public final int E(T t) {
        int i;
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int Q;
        int zzz;
        int zzA8;
        int i2;
        Unsafe unsafe = p;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.f8921a.length; i6 += 3) {
            int f = f(i6);
            int i7 = this.f8921a[i6];
            int e = e(f);
            if (e <= 17) {
                int i8 = this.f8921a[i6 + 2];
                int i9 = i8 & ErrorCode.ERR_UNKNOWN;
                i = 1 << (i8 >>> 20);
                if (i9 != i3) {
                    i5 = unsafe.getInt(t, i9);
                    i3 = i9;
                }
            } else {
                i = 0;
            }
            long j = f & ErrorCode.ERR_UNKNOWN;
            switch (e) {
                case 0:
                    if ((i5 & i) != 0) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 1:
                    if ((i5 & i) != 0) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 2:
                    if ((i5 & i) != 0) {
                        long j2 = unsafe.getLong(t, j);
                        zzA3 = zzjg.zzA(i7 << 3);
                        zzB = zzjg.zzB(j2);
                        Q = zzA3 + zzB;
                        break;
                    } else {
                        continue;
                    }
                case 3:
                    if ((i5 & i) != 0) {
                        long j3 = unsafe.getLong(t, j);
                        zzA3 = zzjg.zzA(i7 << 3);
                        zzB = zzjg.zzB(j3);
                        Q = zzA3 + zzB;
                        break;
                    } else {
                        continue;
                    }
                case 4:
                    if ((i5 & i) != 0) {
                        int i10 = unsafe.getInt(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzv(i10);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 5:
                    if ((i5 & i) != 0) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 6:
                    if ((i5 & i) != 0) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 7:
                    if ((i5 & i) != 0) {
                        zzA5 = zzjg.zzA(i7 << 3);
                        Q = zzA5 + 1;
                        break;
                    } else {
                        continue;
                    }
                case 8:
                    if ((i5 & i) != 0) {
                        Object object = unsafe.getObject(t, j);
                        if (object instanceof zziy) {
                            zzA6 = zzjg.zzA(i7 << 3);
                            zzd = ((zziy) object).zzd();
                            zzA7 = zzjg.zzA(zzd);
                            i2 = zzA6 + zzA7 + zzd;
                            i4 += i2;
                        } else {
                            zzA4 = zzjg.zzA(i7 << 3);
                            zzv = zzjg.zzy((String) object);
                            i2 = zzA4 + zzv;
                            i4 += i2;
                        }
                    } else {
                        continue;
                    }
                case 9:
                    if ((i5 & i) != 0) {
                        Q = z3.Q(i7, unsafe.getObject(t, j), i(i6));
                        break;
                    } else {
                        continue;
                    }
                case 10:
                    if ((i5 & i) != 0) {
                        zzA6 = zzjg.zzA(i7 << 3);
                        zzd = ((zziy) unsafe.getObject(t, j)).zzd();
                        zzA7 = zzjg.zzA(zzd);
                        i2 = zzA6 + zzA7 + zzd;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 11:
                    if ((i5 & i) != 0) {
                        int i11 = unsafe.getInt(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzA(i11);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 12:
                    if ((i5 & i) != 0) {
                        int i12 = unsafe.getInt(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzv(i12);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 13:
                    if ((i5 & i) != 0) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 14:
                    if ((i5 & i) != 0) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 15:
                    if ((i5 & i) != 0) {
                        int i13 = unsafe.getInt(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzA((i13 >> 31) ^ (i13 + i13));
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 16:
                    if ((i5 & i) != 0) {
                        long j4 = unsafe.getLong(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzB((j4 >> 63) ^ (j4 + j4));
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 17:
                    if ((i5 & i) != 0) {
                        Q = zzjg.c(i7, (zzlg) unsafe.getObject(t, j), i(i6));
                        break;
                    } else {
                        continue;
                    }
                case 18:
                    Q = z3.J(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 19:
                    Q = z3.H(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 20:
                    Q = z3.O(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 21:
                    Q = z3.Z(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 22:
                    Q = z3.M(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 23:
                    Q = z3.J(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 24:
                    Q = z3.H(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 25:
                    Q = z3.A(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 26:
                    Q = z3.W(i7, (List) unsafe.getObject(t, j));
                    break;
                case 27:
                    Q = z3.R(i7, (List) unsafe.getObject(t, j), i(i6));
                    break;
                case 28:
                    Q = z3.E(i7, (List) unsafe.getObject(t, j));
                    break;
                case 29:
                    Q = z3.X(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 30:
                    Q = z3.F(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 31:
                    Q = z3.H(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 32:
                    Q = z3.J(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 33:
                    Q = z3.S(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 34:
                    Q = z3.U(i7, (List) unsafe.getObject(t, j), false);
                    break;
                case 35:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 36:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 37:
                    zzv = z3.P((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 38:
                    zzv = z3.a0((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 39:
                    zzv = z3.N((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 40:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 41:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 42:
                    zzv = z3.D((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 43:
                    zzv = z3.Y((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 44:
                    zzv = z3.G((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 45:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 46:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 47:
                    zzv = z3.T((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 48:
                    zzv = z3.V((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i7);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 49:
                    Q = z3.L(i7, (List) unsafe.getObject(t, j), i(i6));
                    break;
                case 50:
                    k3.a(i7, unsafe.getObject(t, j), j(i6));
                    continue;
                case 51:
                    if (t(t, i7, i6)) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 52:
                    if (t(t, i7, i6)) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 53:
                    if (t(t, i7, i6)) {
                        long g = g(t, j);
                        zzA3 = zzjg.zzA(i7 << 3);
                        zzB = zzjg.zzB(g);
                        Q = zzA3 + zzB;
                        break;
                    } else {
                        continue;
                    }
                case 54:
                    if (t(t, i7, i6)) {
                        long g2 = g(t, j);
                        zzA3 = zzjg.zzA(i7 << 3);
                        zzB = zzjg.zzB(g2);
                        Q = zzA3 + zzB;
                        break;
                    } else {
                        continue;
                    }
                case 55:
                    if (t(t, i7, i6)) {
                        int G = G(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzv(G);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 56:
                    if (t(t, i7, i6)) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 57:
                    if (t(t, i7, i6)) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 58:
                    if (t(t, i7, i6)) {
                        zzA5 = zzjg.zzA(i7 << 3);
                        Q = zzA5 + 1;
                        break;
                    } else {
                        continue;
                    }
                case 59:
                    if (t(t, i7, i6)) {
                        Object object2 = unsafe.getObject(t, j);
                        if (object2 instanceof zziy) {
                            zzA6 = zzjg.zzA(i7 << 3);
                            zzd = ((zziy) object2).zzd();
                            zzA7 = zzjg.zzA(zzd);
                            i2 = zzA6 + zzA7 + zzd;
                            i4 += i2;
                        } else {
                            zzA4 = zzjg.zzA(i7 << 3);
                            zzv = zzjg.zzy((String) object2);
                            i2 = zzA4 + zzv;
                            i4 += i2;
                        }
                    } else {
                        continue;
                    }
                case 60:
                    if (t(t, i7, i6)) {
                        Q = z3.Q(i7, unsafe.getObject(t, j), i(i6));
                        break;
                    } else {
                        continue;
                    }
                case 61:
                    if (t(t, i7, i6)) {
                        zzA6 = zzjg.zzA(i7 << 3);
                        zzd = ((zziy) unsafe.getObject(t, j)).zzd();
                        zzA7 = zzjg.zzA(zzd);
                        i2 = zzA6 + zzA7 + zzd;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 62:
                    if (t(t, i7, i6)) {
                        int G2 = G(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzA(G2);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 63:
                    if (t(t, i7, i6)) {
                        int G3 = G(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzv(G3);
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 64:
                    if (t(t, i7, i6)) {
                        zzA2 = zzjg.zzA(i7 << 3);
                        Q = zzA2 + 4;
                        break;
                    } else {
                        continue;
                    }
                case 65:
                    if (t(t, i7, i6)) {
                        zzA = zzjg.zzA(i7 << 3);
                        Q = zzA + 8;
                        break;
                    } else {
                        continue;
                    }
                case 66:
                    if (t(t, i7, i6)) {
                        int G4 = G(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzA((G4 >> 31) ^ (G4 + G4));
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 67:
                    if (t(t, i7, i6)) {
                        long g3 = g(t, j);
                        zzA4 = zzjg.zzA(i7 << 3);
                        zzv = zzjg.zzB((g3 >> 63) ^ (g3 + g3));
                        i2 = zzA4 + zzv;
                        i4 += i2;
                    } else {
                        continue;
                    }
                case 68:
                    if (t(t, i7, i6)) {
                        Q = zzjg.c(i7, (zzlg) unsafe.getObject(t, j), i(i6));
                        break;
                    } else {
                        continue;
                    }
                default:
            }
            i4 += Q;
        }
        l4<?, ?> l4Var = this.l;
        int a2 = i4 + l4Var.a(l4Var.c(t));
        if (this.f) {
            this.m.a(t);
            throw null;
        }
        return a2;
    }

    public final int F(T t) {
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int Q;
        int zzz;
        int zzA8;
        int i;
        Unsafe unsafe = p;
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8921a.length; i3 += 3) {
            int f = f(i3);
            int e = e(f);
            int i4 = this.f8921a[i3];
            long j = f & ErrorCode.ERR_UNKNOWN;
            if (e >= zzjr.zzJ.zza() && e <= zzjr.zzW.zza()) {
                int i5 = this.f8921a[i3 + 2];
            }
            switch (e) {
                case 0:
                    if (q(t, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (q(t, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (q(t, i3)) {
                        long i6 = t4.i(t, j);
                        zzA3 = zzjg.zzA(i4 << 3);
                        zzB = zzjg.zzB(i6);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (q(t, i3)) {
                        long i7 = t4.i(t, j);
                        zzA3 = zzjg.zzA(i4 << 3);
                        zzB = zzjg.zzB(i7);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (q(t, i3)) {
                        int h = t4.h(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzv(h);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (q(t, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (q(t, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (q(t, i3)) {
                        zzA5 = zzjg.zzA(i4 << 3);
                        Q = zzA5 + 1;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!q(t, i3)) {
                        break;
                    } else {
                        Object k = t4.k(t, j);
                        if (k instanceof zziy) {
                            zzA6 = zzjg.zzA(i4 << 3);
                            zzd = ((zziy) k).zzd();
                            zzA7 = zzjg.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjg.zzA(i4 << 3);
                            zzv = zzjg.zzy((String) k);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 9:
                    if (q(t, i3)) {
                        Q = z3.Q(i4, t4.k(t, j), i(i3));
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (q(t, i3)) {
                        zzA6 = zzjg.zzA(i4 << 3);
                        zzd = ((zziy) t4.k(t, j)).zzd();
                        zzA7 = zzjg.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (q(t, i3)) {
                        int h2 = t4.h(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzA(h2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (q(t, i3)) {
                        int h3 = t4.h(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzv(h3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (q(t, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (q(t, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (q(t, i3)) {
                        int h4 = t4.h(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzA((h4 >> 31) ^ (h4 + h4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (q(t, i3)) {
                        long i8 = t4.i(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzB((i8 >> 63) ^ (i8 + i8));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (q(t, i3)) {
                        Q = zzjg.c(i4, (zzlg) t4.k(t, j), i(i3));
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    Q = z3.J(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 19:
                    Q = z3.H(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 20:
                    Q = z3.O(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 21:
                    Q = z3.Z(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 22:
                    Q = z3.M(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 23:
                    Q = z3.J(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 24:
                    Q = z3.H(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 25:
                    Q = z3.A(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 26:
                    Q = z3.W(i4, (List) t4.k(t, j));
                    i2 += Q;
                    break;
                case 27:
                    Q = z3.R(i4, (List) t4.k(t, j), i(i3));
                    i2 += Q;
                    break;
                case 28:
                    Q = z3.E(i4, (List) t4.k(t, j));
                    i2 += Q;
                    break;
                case 29:
                    Q = z3.X(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 30:
                    Q = z3.F(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 31:
                    Q = z3.H(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 32:
                    Q = z3.J(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 33:
                    Q = z3.S(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 34:
                    Q = z3.U(i4, (List) t4.k(t, j), false);
                    i2 += Q;
                    break;
                case 35:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzv = z3.P((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzv = z3.a0((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzv = z3.N((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzv = z3.D((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzv = z3.Y((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzv = z3.G((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzv = z3.I((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzv = z3.K((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzv = z3.T((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzv = z3.V((List) unsafe.getObject(t, j));
                    if (zzv > 0) {
                        zzz = zzjg.zzz(i4);
                        zzA8 = zzjg.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    Q = z3.L(i4, (List) t4.k(t, j), i(i3));
                    i2 += Q;
                    break;
                case 50:
                    k3.a(i4, t4.k(t, j), j(i3));
                    break;
                case 51:
                    if (t(t, i4, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (t(t, i4, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (t(t, i4, i3)) {
                        long g = g(t, j);
                        zzA3 = zzjg.zzA(i4 << 3);
                        zzB = zzjg.zzB(g);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (t(t, i4, i3)) {
                        long g2 = g(t, j);
                        zzA3 = zzjg.zzA(i4 << 3);
                        zzB = zzjg.zzB(g2);
                        i2 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (t(t, i4, i3)) {
                        int G = G(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzv(G);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (t(t, i4, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (t(t, i4, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (t(t, i4, i3)) {
                        zzA5 = zzjg.zzA(i4 << 3);
                        Q = zzA5 + 1;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!t(t, i4, i3)) {
                        break;
                    } else {
                        Object k2 = t4.k(t, j);
                        if (k2 instanceof zziy) {
                            zzA6 = zzjg.zzA(i4 << 3);
                            zzd = ((zziy) k2).zzd();
                            zzA7 = zzjg.zzA(zzd);
                            i = zzA6 + zzA7 + zzd;
                            i2 += i;
                            break;
                        } else {
                            zzA4 = zzjg.zzA(i4 << 3);
                            zzv = zzjg.zzy((String) k2);
                            i = zzA4 + zzv;
                            i2 += i;
                        }
                    }
                case 60:
                    if (t(t, i4, i3)) {
                        Q = z3.Q(i4, t4.k(t, j), i(i3));
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (t(t, i4, i3)) {
                        zzA6 = zzjg.zzA(i4 << 3);
                        zzd = ((zziy) t4.k(t, j)).zzd();
                        zzA7 = zzjg.zzA(zzd);
                        i = zzA6 + zzA7 + zzd;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (t(t, i4, i3)) {
                        int G2 = G(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzA(G2);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (t(t, i4, i3)) {
                        int G3 = G(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzv(G3);
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (t(t, i4, i3)) {
                        zzA2 = zzjg.zzA(i4 << 3);
                        Q = zzA2 + 4;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (t(t, i4, i3)) {
                        zzA = zzjg.zzA(i4 << 3);
                        Q = zzA + 8;
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (t(t, i4, i3)) {
                        int G4 = G(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzA((G4 >> 31) ^ (G4 + G4));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (t(t, i4, i3)) {
                        long g3 = g(t, j);
                        zzA4 = zzjg.zzA(i4 << 3);
                        zzv = zzjg.zzB((g3 >> 63) ^ (g3 + g3));
                        i = zzA4 + zzv;
                        i2 += i;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (t(t, i4, i3)) {
                        Q = zzjg.c(i4, (zzlg) t4.k(t, j), i(i3));
                        i2 += Q;
                        break;
                    } else {
                        break;
                    }
            }
        }
        l4<?, ?> l4Var = this.l;
        return i2 + l4Var.a(l4Var.c(t));
    }

    public final <K, V> int H(T t, byte[] bArr, int i, int i2, int i3, long j, g2 g2Var) throws IOException {
        Unsafe unsafe = p;
        Object j2 = j(i3);
        Object object = unsafe.getObject(t, j);
        if (!((zzla) object).zze()) {
            zzla<K, V> zzb = zzla.zza().zzb();
            k3.b(zzb, object);
            unsafe.putObject(t, j, zzb);
        }
        zzkz zzkzVar = (zzkz) j2;
        throw null;
    }

    public final int I(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, g2 g2Var) throws IOException {
        Unsafe unsafe = p;
        long j2 = this.f8921a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(Double.longBitsToDouble(h2.n(bArr, i))));
                    unsafe.putInt(t, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(Float.intBitsToFloat(h2.b(bArr, i))));
                    unsafe.putInt(t, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int m = h2.m(bArr, i, g2Var);
                    unsafe.putObject(t, j, Long.valueOf(g2Var.b));
                    unsafe.putInt(t, j2, i4);
                    return m;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int j3 = h2.j(bArr, i, g2Var);
                    unsafe.putObject(t, j, Integer.valueOf(g2Var.f8911a));
                    unsafe.putInt(t, j2, i4);
                    return j3;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(h2.n(bArr, i)));
                    unsafe.putInt(t, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(h2.b(bArr, i)));
                    unsafe.putInt(t, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int m2 = h2.m(bArr, i, g2Var);
                    unsafe.putObject(t, j, Boolean.valueOf(g2Var.b != 0));
                    unsafe.putInt(t, j2, i4);
                    return m2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int j4 = h2.j(bArr, i, g2Var);
                    int i9 = g2Var.f8911a;
                    if (i9 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !y4.f(bArr, j4, j4 + i9)) {
                        throw zzkj.zzc();
                    } else {
                        unsafe.putObject(t, j, new String(bArr, j4, i9, zzkh.f8963a));
                        j4 += i9;
                    }
                    unsafe.putInt(t, j2, i4);
                    return j4;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int d = h2.d(i(i8), bArr, i, i2, g2Var);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, g2Var.c);
                    } else {
                        unsafe.putObject(t, j, zzkh.d(object, g2Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return d;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int a2 = h2.a(bArr, i, g2Var);
                    unsafe.putObject(t, j, g2Var.c);
                    unsafe.putInt(t, j2, i4);
                    return a2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int j5 = h2.j(bArr, i, g2Var);
                    int i10 = g2Var.f8911a;
                    zzkd h = h(i8);
                    if (h != null && !h.zza(i10)) {
                        z(t).d(i3, Long.valueOf(i10));
                    } else {
                        unsafe.putObject(t, j, Integer.valueOf(i10));
                        unsafe.putInt(t, j2, i4);
                    }
                    return j5;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int j6 = h2.j(bArr, i, g2Var);
                    unsafe.putObject(t, j, Integer.valueOf(zzjc.zzb(g2Var.f8911a)));
                    unsafe.putInt(t, j2, i4);
                    return j6;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int m3 = h2.m(bArr, i, g2Var);
                    unsafe.putObject(t, j, Long.valueOf(zzjc.zzc(g2Var.b)));
                    unsafe.putInt(t, j2, i4);
                    return m3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int c = h2.c(i(i8), bArr, i, i2, (i3 & (-8)) | 4, g2Var);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, g2Var.c);
                    } else {
                        unsafe.putObject(t, j, zzkh.d(object2, g2Var.c));
                    }
                    unsafe.putInt(t, j2, i4);
                    return c;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x02a9, code lost:
        if (r0 != r15) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x02ab, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r10 = r18;
        r2 = r19;
        r1 = r20;
        r6 = r24;
        r7 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x02c1, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x02f4, code lost:
        if (r0 != r15) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0317, code lost:
        if (r0 != r15) goto L154;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int J(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.measurement.g2 r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 898
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.p3.J(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.g2):int");
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
    public final int K(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.measurement.g2 r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.p3.K(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.measurement.g2):int");
    }

    public final int L(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return O(i, 0);
    }

    public final int M(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return O(i, i2);
    }

    public final int N(int i) {
        return this.f8921a[i + 2];
    }

    public final int O(int i, int i2) {
        int length = (this.f8921a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.f8921a[i4];
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

    @Override // com.google.android.gms.internal.measurement.x3
    public final void a(T t, byte[] bArr, int i, int i2, g2 g2Var) throws IOException {
        if (this.g) {
            J(t, bArr, i, i2, g2Var);
        } else {
            y(t, bArr, i, i2, 0, g2Var);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.x3
    public final boolean b(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.i) {
            int i6 = this.h[i5];
            int i7 = this.f8921a[i6];
            int f = f(i6);
            int i8 = this.f8921a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = p.getInt(t, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & f) != 0 && !r(t, i6, i, i2, i10)) {
                return false;
            }
            int e = e(f);
            if (e != 9 && e != 17) {
                if (e != 27) {
                    if (e == 60 || e == 68) {
                        if (t(t, i7, i6) && !s(t, f, i(i6))) {
                            return false;
                        }
                    } else if (e != 49) {
                        if (e == 50 && !((zzla) t4.k(t, f & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            zzkz zzkzVar = (zzkz) j(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN);
                if (list.isEmpty()) {
                    continue;
                } else {
                    x3 i11 = i(i6);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!i11.b(list.get(i12))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (r(t, i6, i, i2, i10) && !s(t, f, i(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (this.f) {
            this.m.a(t);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void c(T t, s2 s2Var) throws IOException {
        if (!this.g) {
            v(t, s2Var);
        } else if (!this.f) {
            int length = this.f8921a.length;
            for (int i = 0; i < length; i += 3) {
                int f = f(i);
                int i2 = this.f8921a[i];
                switch (e(f)) {
                    case 0:
                        if (q(t, i)) {
                            s2Var.q(i2, t4.f(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (q(t, i)) {
                            s2Var.z(i2, t4.g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (q(t, i)) {
                            s2Var.E(i2, t4.i(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (q(t, i)) {
                            s2Var.j(i2, t4.i(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (q(t, i)) {
                            s2Var.C(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (q(t, i)) {
                            s2Var.x(i2, t4.i(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (q(t, i)) {
                            s2Var.v(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (q(t, i)) {
                            s2Var.m(i2, t4.B(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (q(t, i)) {
                            x(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (q(t, i)) {
                            s2Var.G(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), i(i));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (q(t, i)) {
                            s2Var.o(i2, (zziy) t4.k(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (q(t, i)) {
                            s2Var.h(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (q(t, i)) {
                            s2Var.t(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (q(t, i)) {
                            s2Var.H(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (q(t, i)) {
                            s2Var.J(i2, t4.i(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (q(t, i)) {
                            s2Var.a(i2, t4.h(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (q(t, i)) {
                            s2Var.c(i2, t4.i(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (q(t, i)) {
                            s2Var.B(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), i(i));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        z3.l(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 19:
                        z3.p(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 20:
                        z3.s(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 21:
                        z3.B(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 22:
                        z3.r(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 23:
                        z3.o(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 24:
                        z3.n(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 25:
                        z3.j(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 26:
                        z3.y(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var);
                        break;
                    case 27:
                        z3.t(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, i(i));
                        break;
                    case 28:
                        z3.k(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var);
                        break;
                    case 29:
                        z3.z(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 30:
                        z3.m(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 31:
                        z3.u(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 32:
                        z3.v(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 33:
                        z3.w(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 34:
                        z3.x(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, false);
                        break;
                    case 35:
                        z3.l(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 36:
                        z3.p(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 37:
                        z3.s(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 38:
                        z3.B(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 39:
                        z3.r(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 40:
                        z3.o(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 41:
                        z3.n(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 42:
                        z3.j(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 43:
                        z3.z(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 44:
                        z3.m(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 45:
                        z3.u(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 46:
                        z3.v(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 47:
                        z3.w(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 48:
                        z3.x(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, true);
                        break;
                    case 49:
                        z3.q(this.f8921a[i], (List) t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var, i(i));
                        break;
                    case 50:
                        w(s2Var, i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), i);
                        break;
                    case 51:
                        if (t(t, i2, i)) {
                            s2Var.q(i2, C(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (t(t, i2, i)) {
                            s2Var.z(i2, D(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (t(t, i2, i)) {
                            s2Var.E(i2, g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (t(t, i2, i)) {
                            s2Var.j(i2, g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (t(t, i2, i)) {
                            s2Var.C(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (t(t, i2, i)) {
                            s2Var.x(i2, g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (t(t, i2, i)) {
                            s2Var.v(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (t(t, i2, i)) {
                            s2Var.m(i2, u(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (t(t, i2, i)) {
                            x(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), s2Var);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (t(t, i2, i)) {
                            s2Var.G(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), i(i));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (t(t, i2, i)) {
                            s2Var.o(i2, (zziy) t4.k(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (t(t, i2, i)) {
                            s2Var.h(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (t(t, i2, i)) {
                            s2Var.t(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (t(t, i2, i)) {
                            s2Var.H(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (t(t, i2, i)) {
                            s2Var.J(i2, g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (t(t, i2, i)) {
                            s2Var.a(i2, G(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (t(t, i2, i)) {
                            s2Var.c(i2, g(t, f & ErrorCode.ERR_UNKNOWN));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (t(t, i2, i)) {
                            s2Var.B(i2, t4.k(t, f & ErrorCode.ERR_UNKNOWN), i(i));
                            break;
                        } else {
                            break;
                        }
                }
            }
            l4<?, ?> l4Var = this.l;
            l4Var.i(l4Var.c(t), s2Var);
        } else {
            this.m.a(t);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final boolean d(T t, T t2) {
        boolean h;
        int length = this.f8921a.length;
        for (int i = 0; i < length; i += 3) {
            int f = f(i);
            long j = f & ErrorCode.ERR_UNKNOWN;
            switch (e(f)) {
                case 0:
                    if (p(t, t2, i) && Double.doubleToLongBits(t4.f(t, j)) == Double.doubleToLongBits(t4.f(t2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (p(t, t2, i) && Float.floatToIntBits(t4.g(t, j)) == Float.floatToIntBits(t4.g(t2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (p(t, t2, i) && t4.i(t, j) == t4.i(t2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (p(t, t2, i) && t4.i(t, j) == t4.i(t2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (p(t, t2, i) && t4.i(t, j) == t4.i(t2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (p(t, t2, i) && t4.B(t, j) == t4.B(t2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (p(t, t2, i) && z3.h(t4.k(t, j), t4.k(t2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (p(t, t2, i) && z3.h(t4.k(t, j), t4.k(t2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (p(t, t2, i) && z3.h(t4.k(t, j), t4.k(t2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (p(t, t2, i) && t4.i(t, j) == t4.i(t2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (p(t, t2, i) && t4.h(t, j) == t4.h(t2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (p(t, t2, i) && t4.i(t, j) == t4.i(t2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (p(t, t2, i) && z3.h(t4.k(t, j), t4.k(t2, j))) {
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
                    h = z3.h(t4.k(t, j), t4.k(t2, j));
                    break;
                case 50:
                    h = z3.h(t4.k(t, j), t4.k(t2, j));
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
                    long N = N(i) & ErrorCode.ERR_UNKNOWN;
                    if (t4.h(t, N) == t4.h(t2, N) && z3.h(t4.k(t, j), t4.k(t2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!h) {
                return false;
            }
        }
        if (this.l.c(t).equals(this.l.c(t2))) {
            if (this.f) {
                this.m.a(t);
                this.m.a(t2);
                throw null;
            }
            return true;
        }
        return false;
    }

    public final int f(int i) {
        return this.f8921a[i + 1];
    }

    public final zzkd h(int i) {
        int i2 = i / 3;
        return (zzkd) this.b[i2 + i2 + 1];
    }

    public final x3 i(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        x3 x3Var = (x3) this.b[i3];
        if (x3Var != null) {
            return x3Var;
        }
        x3<T> b = u3.a().b((Class) this.b[i3 + 1]);
        this.b[i3] = b;
        return b;
    }

    public final Object j(int i) {
        int i2 = i / 3;
        return this.b[i2 + i2];
    }

    public final void l(T t, T t2, int i) {
        long f = f(i) & ErrorCode.ERR_UNKNOWN;
        if (q(t2, i)) {
            Object k = t4.k(t, f);
            Object k2 = t4.k(t2, f);
            if (k != null && k2 != null) {
                t4.x(t, f, zzkh.d(k, k2));
                n(t, i);
            } else if (k2 != null) {
                t4.x(t, f, k2);
                n(t, i);
            }
        }
    }

    public final void m(T t, T t2, int i) {
        int f = f(i);
        int i2 = this.f8921a[i];
        long j = f & ErrorCode.ERR_UNKNOWN;
        if (t(t2, i2, i)) {
            Object k = t(t, i2, i) ? t4.k(t, j) : null;
            Object k2 = t4.k(t2, j);
            if (k != null && k2 != null) {
                t4.x(t, j, zzkh.d(k, k2));
                o(t, i2, i);
            } else if (k2 != null) {
                t4.x(t, j, k2);
                o(t, i2, i);
            }
        }
    }

    public final void n(T t, int i) {
        int N = N(i);
        long j = 1048575 & N;
        if (j == 1048575) {
            return;
        }
        t4.v(t, j, (1 << (N >>> 20)) | t4.h(t, j));
    }

    public final void o(T t, int i, int i2) {
        t4.v(t, N(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    public final boolean p(T t, T t2, int i) {
        return q(t, i) == q(t2, i);
    }

    public final boolean q(T t, int i) {
        int N = N(i);
        long j = N & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (t4.h(t, j) & (1 << (N >>> 20))) != 0;
        }
        int f = f(i);
        long j2 = f & ErrorCode.ERR_UNKNOWN;
        switch (e(f)) {
            case 0:
                return t4.f(t, j2) != 0.0d;
            case 1:
                return t4.g(t, j2) != 0.0f;
            case 2:
                return t4.i(t, j2) != 0;
            case 3:
                return t4.i(t, j2) != 0;
            case 4:
                return t4.h(t, j2) != 0;
            case 5:
                return t4.i(t, j2) != 0;
            case 6:
                return t4.h(t, j2) != 0;
            case 7:
                return t4.B(t, j2);
            case 8:
                Object k = t4.k(t, j2);
                if (k instanceof String) {
                    return !((String) k).isEmpty();
                } else if (k instanceof zziy) {
                    return !zziy.zzb.equals(k);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return t4.k(t, j2) != null;
            case 10:
                return !zziy.zzb.equals(t4.k(t, j2));
            case 11:
                return t4.h(t, j2) != 0;
            case 12:
                return t4.h(t, j2) != 0;
            case 13:
                return t4.h(t, j2) != 0;
            case 14:
                return t4.i(t, j2) != 0;
            case 15:
                return t4.h(t, j2) != 0;
            case 16:
                return t4.i(t, j2) != 0;
            case 17:
                return t4.k(t, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean r(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return q(t, i);
        }
        return (i3 & i4) != 0;
    }

    public final boolean t(T t, int i, int i2) {
        return t4.h(t, (long) (N(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void v(T t, s2 s2Var) throws IOException {
        int i;
        boolean z;
        if (!this.f) {
            int length = this.f8921a.length;
            Unsafe unsafe = p;
            int i2 = ErrorCode.ERR_UNKNOWN;
            int i3 = 1048575;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                int f = f(i4);
                int i6 = this.f8921a[i4];
                int e = e(f);
                if (e <= 17) {
                    int i7 = this.f8921a[i4 + 2];
                    int i8 = i7 & i2;
                    if (i8 != i3) {
                        i5 = unsafe.getInt(t, i8);
                        i3 = i8;
                    }
                    i = 1 << (i7 >>> 20);
                } else {
                    i = 0;
                }
                long j = f & i2;
                switch (e) {
                    case 0:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.q(i6, t4.f(t, j));
                            break;
                        }
                    case 1:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.z(i6, t4.g(t, j));
                            break;
                        }
                    case 2:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.E(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 3:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.j(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 4:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.C(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 5:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.x(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 6:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.v(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 7:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.m(i6, t4.B(t, j));
                            break;
                        }
                    case 8:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            x(i6, unsafe.getObject(t, j), s2Var);
                            break;
                        }
                    case 9:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.G(i6, unsafe.getObject(t, j), i(i4));
                            break;
                        }
                    case 10:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.o(i6, (zziy) unsafe.getObject(t, j));
                            break;
                        }
                    case 11:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.h(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 12:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.t(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 13:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.H(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 14:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.J(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 15:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.a(i6, unsafe.getInt(t, j));
                            break;
                        }
                    case 16:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.c(i6, unsafe.getLong(t, j));
                            break;
                        }
                    case 17:
                        if ((i5 & i) == 0) {
                            break;
                        } else {
                            s2Var.B(i6, unsafe.getObject(t, j), i(i4));
                            break;
                        }
                    case 18:
                        z3.l(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 19:
                        z3.p(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 20:
                        z3.s(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 21:
                        z3.B(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 22:
                        z3.r(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 23:
                        z3.o(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 24:
                        z3.n(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 25:
                        z3.j(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 26:
                        z3.y(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var);
                        break;
                    case 27:
                        z3.t(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, i(i4));
                        break;
                    case 28:
                        z3.k(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var);
                        break;
                    case 29:
                        z = false;
                        z3.z(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 30:
                        z = false;
                        z3.m(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 31:
                        z = false;
                        z3.u(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 32:
                        z = false;
                        z3.v(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 33:
                        z = false;
                        z3.w(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 34:
                        z = false;
                        z3.x(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, false);
                        break;
                    case 35:
                        z3.l(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 36:
                        z3.p(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 37:
                        z3.s(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 38:
                        z3.B(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 39:
                        z3.r(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 40:
                        z3.o(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 41:
                        z3.n(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 42:
                        z3.j(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 43:
                        z3.z(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 44:
                        z3.m(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 45:
                        z3.u(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 46:
                        z3.v(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 47:
                        z3.w(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 48:
                        z3.x(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, true);
                        break;
                    case 49:
                        z3.q(this.f8921a[i4], (List) unsafe.getObject(t, j), s2Var, i(i4));
                        break;
                    case 50:
                        w(s2Var, i6, unsafe.getObject(t, j), i4);
                        break;
                    case 51:
                        if (t(t, i6, i4)) {
                            s2Var.q(i6, C(t, j));
                        }
                        break;
                    case 52:
                        if (t(t, i6, i4)) {
                            s2Var.z(i6, D(t, j));
                        }
                        break;
                    case 53:
                        if (t(t, i6, i4)) {
                            s2Var.E(i6, g(t, j));
                        }
                        break;
                    case 54:
                        if (t(t, i6, i4)) {
                            s2Var.j(i6, g(t, j));
                        }
                        break;
                    case 55:
                        if (t(t, i6, i4)) {
                            s2Var.C(i6, G(t, j));
                        }
                        break;
                    case 56:
                        if (t(t, i6, i4)) {
                            s2Var.x(i6, g(t, j));
                        }
                        break;
                    case 57:
                        if (t(t, i6, i4)) {
                            s2Var.v(i6, G(t, j));
                        }
                        break;
                    case 58:
                        if (t(t, i6, i4)) {
                            s2Var.m(i6, u(t, j));
                        }
                        break;
                    case 59:
                        if (t(t, i6, i4)) {
                            x(i6, unsafe.getObject(t, j), s2Var);
                        }
                        break;
                    case 60:
                        if (t(t, i6, i4)) {
                            s2Var.G(i6, unsafe.getObject(t, j), i(i4));
                        }
                        break;
                    case 61:
                        if (t(t, i6, i4)) {
                            s2Var.o(i6, (zziy) unsafe.getObject(t, j));
                        }
                        break;
                    case 62:
                        if (t(t, i6, i4)) {
                            s2Var.h(i6, G(t, j));
                        }
                        break;
                    case 63:
                        if (t(t, i6, i4)) {
                            s2Var.t(i6, G(t, j));
                        }
                        break;
                    case 64:
                        if (t(t, i6, i4)) {
                            s2Var.H(i6, G(t, j));
                        }
                        break;
                    case 65:
                        if (t(t, i6, i4)) {
                            s2Var.J(i6, g(t, j));
                        }
                        break;
                    case 66:
                        if (t(t, i6, i4)) {
                            s2Var.a(i6, G(t, j));
                        }
                        break;
                    case 67:
                        if (t(t, i6, i4)) {
                            s2Var.c(i6, g(t, j));
                        }
                        break;
                    case 68:
                        if (t(t, i6, i4)) {
                            s2Var.B(i6, unsafe.getObject(t, j), i(i4));
                        }
                        break;
                }
                i4 += 3;
                i2 = ErrorCode.ERR_UNKNOWN;
            }
            l4<?, ?> l4Var = this.l;
            l4Var.i(l4Var.c(t), s2Var);
            return;
        }
        this.m.a(t);
        throw null;
    }

    public final <K, V> void w(s2 s2Var, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzkz zzkzVar = (zzkz) j(i2);
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:144:0x0419, code lost:
        if (r6 == 1048575) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x041b, code lost:
        r26.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0421, code lost:
        r3 = r9.i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0425, code lost:
        if (r3 >= r9.j) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0427, code lost:
        r4 = r9.h[r3];
        r5 = r9.f8921a[r4];
        r5 = com.google.android.gms.internal.measurement.t4.k(r12, r9.f(r4) & com.jieli.jl_bt_ota.constant.ErrorCode.ERR_UNKNOWN);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0439, code lost:
        if (r5 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0440, code lost:
        if (r9.h(r4) != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0442, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0445, code lost:
        r5 = (com.google.android.gms.internal.measurement.zzla) r5;
        r0 = (com.google.android.gms.internal.measurement.zzkz) r9.j(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x044d, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x044e, code lost:
        if (r7 != 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0452, code lost:
        if (r0 != r32) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0459, code lost:
        throw com.google.android.gms.internal.measurement.zzkj.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x045c, code lost:
        if (r0 > r32) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x045e, code lost:
        if (r1 != r7) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0460, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0465, code lost:
        throw com.google.android.gms.internal.measurement.zzkj.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int y(T r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.measurement.g2 r34) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1164
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.p3.y(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.g2):int");
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final int zza(T t) {
        return this.g ? F(t) : E(t);
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final int zzb(T t) {
        int i;
        int zzc;
        int length = this.f8921a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int f = f(i3);
            int i4 = this.f8921a[i3];
            long j = 1048575 & f;
            int i5 = 37;
            switch (e(f)) {
                case 0:
                    i = i2 * 53;
                    zzc = zzkh.zzc(Double.doubleToLongBits(t4.f(t, j)));
                    i2 = i + zzc;
                    break;
                case 1:
                    i = i2 * 53;
                    zzc = Float.floatToIntBits(t4.g(t, j));
                    i2 = i + zzc;
                    break;
                case 2:
                    i = i2 * 53;
                    zzc = zzkh.zzc(t4.i(t, j));
                    i2 = i + zzc;
                    break;
                case 3:
                    i = i2 * 53;
                    zzc = zzkh.zzc(t4.i(t, j));
                    i2 = i + zzc;
                    break;
                case 4:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 5:
                    i = i2 * 53;
                    zzc = zzkh.zzc(t4.i(t, j));
                    i2 = i + zzc;
                    break;
                case 6:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 7:
                    i = i2 * 53;
                    zzc = zzkh.zza(t4.B(t, j));
                    i2 = i + zzc;
                    break;
                case 8:
                    i = i2 * 53;
                    zzc = ((String) t4.k(t, j)).hashCode();
                    i2 = i + zzc;
                    break;
                case 9:
                    Object k = t4.k(t, j);
                    if (k != null) {
                        i5 = k.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzc = t4.k(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 11:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 12:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 13:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 14:
                    i = i2 * 53;
                    zzc = zzkh.zzc(t4.i(t, j));
                    i2 = i + zzc;
                    break;
                case 15:
                    i = i2 * 53;
                    zzc = t4.h(t, j);
                    i2 = i + zzc;
                    break;
                case 16:
                    i = i2 * 53;
                    zzc = zzkh.zzc(t4.i(t, j));
                    i2 = i + zzc;
                    break;
                case 17:
                    Object k2 = t4.k(t, j);
                    if (k2 != null) {
                        i5 = k2.hashCode();
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
                    zzc = t4.k(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 50:
                    i = i2 * 53;
                    zzc = t4.k(t, j).hashCode();
                    i2 = i + zzc;
                    break;
                case 51:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(Double.doubleToLongBits(C(t, j)));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = Float.floatToIntBits(D(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(g(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(g(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(g(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zza(u(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = ((String) t4.k(t, j)).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = t4.k(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = t4.k(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(g(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = G(t, j);
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = zzkh.zzc(g(t, j));
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (t(t, i4, i3)) {
                        i = i2 * 53;
                        zzc = t4.k(t, j).hashCode();
                        i2 = i + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.l.c(t).hashCode();
        if (this.f) {
            this.m.a(t);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final T zze() {
        return (T) ((zzjz) this.e).zzl(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void zzf(T t) {
        int i;
        int i2 = this.i;
        while (true) {
            i = this.j;
            if (i2 >= i) {
                break;
            }
            long f = f(this.h[i2]) & ErrorCode.ERR_UNKNOWN;
            Object k = t4.k(t, f);
            if (k != null) {
                ((zzla) k).zzc();
                t4.x(t, f, k);
            }
            i2++;
        }
        int length = this.h.length;
        while (i < length) {
            this.k.a(t, this.h[i]);
            i++;
        }
        this.l.g(t);
        if (this.f) {
            this.m.b(t);
        }
    }

    @Override // com.google.android.gms.internal.measurement.x3
    public final void zzg(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f8921a.length; i += 3) {
            int f = f(i);
            long j = 1048575 & f;
            int i2 = this.f8921a[i];
            switch (e(f)) {
                case 0:
                    if (q(t2, i)) {
                        t4.t(t, j, t4.f(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (q(t2, i)) {
                        t4.u(t, j, t4.g(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (q(t2, i)) {
                        t4.w(t, j, t4.i(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (q(t2, i)) {
                        t4.w(t, j, t4.i(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (q(t2, i)) {
                        t4.w(t, j, t4.i(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (q(t2, i)) {
                        t4.r(t, j, t4.B(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (q(t2, i)) {
                        t4.x(t, j, t4.k(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    l(t, t2, i);
                    break;
                case 10:
                    if (q(t2, i)) {
                        t4.x(t, j, t4.k(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (q(t2, i)) {
                        t4.w(t, j, t4.i(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (q(t2, i)) {
                        t4.v(t, j, t4.h(t2, j));
                        n(t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (q(t2, i)) {
                        t4.w(t, j, t4.i(t2, j));
                        n(t, i);
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
                    this.k.b(t, t2, j);
                    break;
                case 50:
                    z3.i(this.n, t, t2, j);
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
                    if (t(t2, i2, i)) {
                        t4.x(t, j, t4.k(t2, j));
                        o(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    m(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (t(t2, i2, i)) {
                        t4.x(t, j, t4.k(t2, j));
                        o(t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    m(t, t2, i);
                    break;
            }
        }
        z3.f(this.l, t, t2);
        if (this.f) {
            z3.e(this.m, t, t2);
        }
    }
}
