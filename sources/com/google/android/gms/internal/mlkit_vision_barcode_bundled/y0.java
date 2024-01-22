package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes8.dex */
public final class y0<T> implements l1<T> {
    public static final int[] n = new int[0];
    public static final Unsafe o = g2.l();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f9623a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final zzfo e;
    public final boolean f;
    public final boolean g;
    public final int[] h;
    public final int i;
    public final int j;
    public final o0 k;
    public final y1 l;
    public final c0 m;

    public y0(int[] iArr, Object[] objArr, int i, int i2, zzfo zzfoVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, a1 a1Var, o0 o0Var, y1 y1Var, c0 c0Var, t0 t0Var) {
        this.f9623a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = z;
        boolean z3 = false;
        if (c0Var != null && c0Var.g(zzfoVar)) {
            z3 = true;
        }
        this.f = z3;
        this.h = iArr2;
        this.i = i3;
        this.j = i4;
        this.k = o0Var;
        this.l = y1Var;
        this.m = c0Var;
        this.e = zzfoVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0288  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0 A(java.lang.Class r31, com.google.android.gms.internal.mlkit_vision_barcode_bundled.v0 r32, com.google.android.gms.internal.mlkit_vision_barcode_bundled.a1 r33, com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0 r34, com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1 r35, com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0 r36, com.google.android.gms.internal.mlkit_vision_barcode_bundled.t0 r37) {
        /*
            Method dump skipped, instructions count: 990
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0.A(java.lang.Class, com.google.android.gms.internal.mlkit_vision_barcode_bundled.v0, com.google.android.gms.internal.mlkit_vision_barcode_bundled.a1, com.google.android.gms.internal.mlkit_vision_barcode_bundled.o0, com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1, com.google.android.gms.internal.mlkit_vision_barcode_bundled.c0, com.google.android.gms.internal.mlkit_vision_barcode_bundled.t0):com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0");
    }

    public static double B(Object obj, long j) {
        return ((Double) g2.k(obj, j)).doubleValue();
    }

    public static float C(Object obj, long j) {
        return ((Float) g2.k(obj, j)).floatValue();
    }

    public static int E(Object obj, long j) {
        return ((Integer) g2.k(obj, j)).intValue();
    }

    public static int M(int i) {
        return (i >>> 20) & 255;
    }

    public static long O(Object obj, long j) {
        return ((Long) g2.k(obj, j)).longValue();
    }

    public static Field h(Class cls, String str) {
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

    public static void i(Object obj) {
        if (!u(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    public static boolean t(Object obj, int i, l1 l1Var) {
        return l1Var.zzk(g2.k(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public static boolean u(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzed) {
            return ((zzed) obj).h();
        }
        return true;
    }

    public static boolean w(Object obj, long j) {
        return ((Boolean) g2.k(obj, j)).booleanValue();
    }

    public static final void x(int i, Object obj, m2 m2Var) throws IOException {
        if (obj instanceof String) {
            m2Var.zzG(i, (String) obj);
        } else {
            m2Var.c(i, (zzdb) obj);
        }
    }

    public static zzgz z(Object obj) {
        zzed zzedVar = (zzed) obj;
        zzgz zzgzVar = zzedVar.zzc;
        if (zzgzVar == zzgz.zzc()) {
            zzgz c = zzgz.c();
            zzedVar.zzc = c;
            return c;
        }
        return zzgzVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final int D(Object obj) {
        int i;
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int S;
        boolean z;
        int B;
        int G;
        int zzy7;
        int zzy8;
        int i2;
        int zzy9;
        int zzy10;
        int zzy11;
        int zzy12;
        Unsafe unsafe = o;
        int i3 = ErrorCode.ERR_UNKNOWN;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.f9623a.length) {
            int N = N(i5);
            int[] iArr = this.f9623a;
            int i8 = iArr[i5];
            int M = M(N);
            if (M <= 17) {
                int i9 = iArr[i5 + 2];
                int i10 = i9 & i3;
                int i11 = i9 >>> 20;
                if (i10 != i4) {
                    i7 = unsafe.getInt(obj, i10);
                    i4 = i10;
                }
                i = 1 << i11;
            } else {
                i = 0;
            }
            long j = N & i3;
            switch (M) {
                case 0:
                    if ((i7 & i) != 0) {
                        zzy = zzdj.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if ((i7 & i) != 0) {
                        zzy2 = zzdj.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if ((i7 & i) != 0) {
                        zzz = zzdj.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzdj.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ((i7 & i) != 0) {
                        zzz = zzdj.zzz(unsafe.getLong(obj, j));
                        zzy3 = zzdj.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if ((i7 & i) != 0) {
                        zzz = zzdj.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzdj.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i7 & i) != 0) {
                        zzy = zzdj.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if ((i7 & i) != 0) {
                        zzy2 = zzdj.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if ((i7 & i) != 0) {
                        zzy4 = zzdj.zzy(i8 << 3) + 1;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if ((i7 & i) != 0) {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzdb) {
                            int i12 = zzdj.zzb;
                            int zzd = ((zzdb) object).zzd();
                            zzy5 = zzdj.zzy(zzd) + zzd;
                            zzy6 = zzdj.zzy(i8 << 3);
                            zzy4 = zzy6 + zzy5;
                            i6 += zzy4;
                            break;
                        } else {
                            zzz = zzdj.zzx((String) object);
                            zzy3 = zzdj.zzy(i8 << 3);
                            i6 += zzy3 + zzz;
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if ((i7 & i) != 0) {
                        zzy4 = n1.M(i8, unsafe.getObject(obj, j), d(i5));
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if ((i7 & i) != 0) {
                        int i13 = zzdj.zzb;
                        int zzd2 = ((zzdb) unsafe.getObject(obj, j)).zzd();
                        zzy5 = zzdj.zzy(zzd2) + zzd2;
                        zzy6 = zzdj.zzy(i8 << 3);
                        zzy4 = zzy6 + zzy5;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if ((i7 & i) != 0) {
                        zzz = zzdj.zzy(unsafe.getInt(obj, j));
                        zzy3 = zzdj.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if ((i7 & i) != 0) {
                        zzz = zzdj.zzu(unsafe.getInt(obj, j));
                        zzy3 = zzdj.zzy(i8 << 3);
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if ((i7 & i) != 0) {
                        zzy2 = zzdj.zzy(i8 << 3);
                        zzy4 = zzy2 + 4;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if ((i7 & i) != 0) {
                        zzy = zzdj.zzy(i8 << 3);
                        zzy4 = zzy + 8;
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if ((i7 & i) != 0) {
                        int i14 = unsafe.getInt(obj, j);
                        zzy3 = zzdj.zzy(i8 << 3);
                        zzz = zzdj.zzy((i14 >> 31) ^ (i14 + i14));
                        i6 += zzy3 + zzz;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if ((i & i7) != 0) {
                        long j2 = unsafe.getLong(obj, j);
                        i6 += zzdj.zzy(i8 << 3) + zzdj.zzz((j2 >> 63) ^ (j2 + j2));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if ((i7 & i) != 0) {
                        zzy4 = zzdj.c(i8, (zzfo) unsafe.getObject(obj, j), d(i5));
                        i6 += zzy4;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzy4 = n1.F(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 19:
                    zzy4 = n1.D(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 20:
                    zzy4 = n1.K(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 21:
                    zzy4 = n1.V(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 22:
                    zzy4 = n1.I(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 23:
                    zzy4 = n1.F(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 24:
                    zzy4 = n1.D(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 25:
                    zzy4 = n1.z(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += zzy4;
                    break;
                case 26:
                    S = n1.S(i8, (List) unsafe.getObject(obj, j));
                    i6 += S;
                    break;
                case 27:
                    S = n1.N(i8, (List) unsafe.getObject(obj, j), d(i5));
                    i6 += S;
                    break;
                case 28:
                    S = n1.A(i8, (List) unsafe.getObject(obj, j));
                    i6 += S;
                    break;
                case 29:
                    S = n1.T(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += S;
                    break;
                case 30:
                    z = false;
                    B = n1.B(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += B;
                    break;
                case 31:
                    z = false;
                    B = n1.D(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += B;
                    break;
                case 32:
                    z = false;
                    B = n1.F(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += B;
                    break;
                case 33:
                    z = false;
                    B = n1.O(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += B;
                    break;
                case 34:
                    z = false;
                    B = n1.Q(i8, (List) unsafe.getObject(obj, j), false);
                    i6 += B;
                    break;
                case 35:
                    G = n1.G((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 36:
                    G = n1.E((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 37:
                    G = n1.L((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 38:
                    G = n1.W((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 39:
                    G = n1.J((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 40:
                    G = n1.G((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 41:
                    G = n1.E((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 42:
                    int i15 = n1.e;
                    G = ((List) unsafe.getObject(obj, j)).size();
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 43:
                    G = n1.U((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 44:
                    G = n1.C((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 45:
                    G = n1.E((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 46:
                    G = n1.G((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 47:
                    G = n1.P((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 48:
                    G = n1.R((List) unsafe.getObject(obj, j));
                    if (G > 0) {
                        zzy7 = zzdj.zzy(G);
                        zzy8 = zzdj.zzy(i8 << 3);
                        i2 = zzy8 + zzy7;
                        i6 += i2 + G;
                    }
                    break;
                case 49:
                    S = n1.H(i8, (List) unsafe.getObject(obj, j), d(i5));
                    i6 += S;
                    break;
                case 50:
                    t0.a(i8, unsafe.getObject(obj, j), e(i5));
                    break;
                case 51:
                    if (v(obj, i8, i5)) {
                        zzy9 = zzdj.zzy(i8 << 3);
                        S = zzy9 + 8;
                        i6 += S;
                    }
                    break;
                case 52:
                    if (v(obj, i8, i5)) {
                        zzy10 = zzdj.zzy(i8 << 3);
                        S = zzy10 + 4;
                        i6 += S;
                    }
                    break;
                case 53:
                    if (v(obj, i8, i5)) {
                        G = zzdj.zzz(O(obj, j));
                        i2 = zzdj.zzy(i8 << 3);
                        i6 += i2 + G;
                    }
                    break;
                case 54:
                    if (v(obj, i8, i5)) {
                        G = zzdj.zzz(O(obj, j));
                        i2 = zzdj.zzy(i8 << 3);
                        i6 += i2 + G;
                    }
                    break;
                case 55:
                    if (v(obj, i8, i5)) {
                        G = zzdj.zzu(E(obj, j));
                        i2 = zzdj.zzy(i8 << 3);
                        i6 += i2 + G;
                    }
                    break;
                case 56:
                    if (v(obj, i8, i5)) {
                        zzy9 = zzdj.zzy(i8 << 3);
                        S = zzy9 + 8;
                        i6 += S;
                    }
                    break;
                case 57:
                    if (v(obj, i8, i5)) {
                        zzy10 = zzdj.zzy(i8 << 3);
                        S = zzy10 + 4;
                        i6 += S;
                    }
                    break;
                case 58:
                    if (v(obj, i8, i5)) {
                        S = zzdj.zzy(i8 << 3) + 1;
                        i6 += S;
                    }
                    break;
                case 59:
                    if (v(obj, i8, i5)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzdb) {
                            int i16 = zzdj.zzb;
                            int zzd3 = ((zzdb) object2).zzd();
                            zzy11 = zzdj.zzy(zzd3) + zzd3;
                            zzy12 = zzdj.zzy(i8 << 3);
                            S = zzy12 + zzy11;
                            i6 += S;
                        } else {
                            G = zzdj.zzx((String) object2);
                            i2 = zzdj.zzy(i8 << 3);
                            i6 += i2 + G;
                        }
                    }
                    break;
                case 60:
                    if (v(obj, i8, i5)) {
                        S = n1.M(i8, unsafe.getObject(obj, j), d(i5));
                        i6 += S;
                    }
                    break;
                case 61:
                    if (v(obj, i8, i5)) {
                        int i17 = zzdj.zzb;
                        int zzd4 = ((zzdb) unsafe.getObject(obj, j)).zzd();
                        zzy11 = zzdj.zzy(zzd4) + zzd4;
                        zzy12 = zzdj.zzy(i8 << 3);
                        S = zzy12 + zzy11;
                        i6 += S;
                    }
                    break;
                case 62:
                    if (v(obj, i8, i5)) {
                        G = zzdj.zzy(E(obj, j));
                        i2 = zzdj.zzy(i8 << 3);
                        i6 += i2 + G;
                    }
                    break;
                case 63:
                    if (v(obj, i8, i5)) {
                        G = zzdj.zzu(E(obj, j));
                        i2 = zzdj.zzy(i8 << 3);
                        i6 += i2 + G;
                    }
                    break;
                case 64:
                    if (v(obj, i8, i5)) {
                        zzy10 = zzdj.zzy(i8 << 3);
                        S = zzy10 + 4;
                        i6 += S;
                    }
                    break;
                case 65:
                    if (v(obj, i8, i5)) {
                        zzy9 = zzdj.zzy(i8 << 3);
                        S = zzy9 + 8;
                        i6 += S;
                    }
                    break;
                case 66:
                    if (v(obj, i8, i5)) {
                        int E = E(obj, j);
                        i2 = zzdj.zzy(i8 << 3);
                        G = zzdj.zzy((E >> 31) ^ (E + E));
                        i6 += i2 + G;
                    }
                    break;
                case 67:
                    if (v(obj, i8, i5)) {
                        long O = O(obj, j);
                        i6 += zzdj.zzy(i8 << 3) + zzdj.zzz((O >> 63) ^ (O + O));
                    }
                    break;
                case 68:
                    if (v(obj, i8, i5)) {
                        S = zzdj.c(i8, (zzfo) unsafe.getObject(obj, j), d(i5));
                        i6 += S;
                    }
                    break;
            }
            i5 += 3;
            i3 = ErrorCode.ERR_UNKNOWN;
        }
        int i18 = 0;
        y1 y1Var = this.l;
        int a2 = i6 + y1Var.a(y1Var.d(obj));
        if (this.f) {
            f0 b = this.m.b(obj);
            for (int i19 = 0; i19 < b.f9593a.b(); i19++) {
                Map.Entry h = b.f9593a.h(i19);
                i18 += f0.a((zzds) h.getKey(), h.getValue());
            }
            for (Map.Entry entry : b.f9593a.d()) {
                i18 += f0.a((zzds) entry.getKey(), entry.getValue());
            }
            return a2 + i18;
        }
        return a2;
    }

    public final int F(Object obj, byte[] bArr, int i, int i2, int i3, long j, m mVar) throws IOException {
        Unsafe unsafe = o;
        Object e = e(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzfi) object).zze()) {
            zzfi zzb = zzfi.zza().zzb();
            t0.b(zzb, object);
            unsafe.putObject(obj, j, zzb);
        }
        zzfh zzfhVar = (zzfh) e;
        throw null;
    }

    public final int G(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, m mVar) throws IOException {
        Unsafe unsafe = o;
        long j2 = this.f9623a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(n.q(bArr, i))));
                    int i9 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(n.b(bArr, i))));
                    int i10 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int m = n.m(bArr, i, mVar);
                    unsafe.putObject(obj, j, Long.valueOf(mVar.b));
                    unsafe.putInt(obj, j2, i4);
                    return m;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int j3 = n.j(bArr, i, mVar);
                    unsafe.putObject(obj, j, Integer.valueOf(mVar.f9604a));
                    unsafe.putInt(obj, j2, i4);
                    return j3;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(n.q(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(obj, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(n.b(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(obj, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int m2 = n.m(bArr, i, mVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(mVar.b != 0));
                    unsafe.putInt(obj, j2, i4);
                    return m2;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int j4 = n.j(bArr, i, mVar);
                    int i13 = mVar.f9604a;
                    if (i13 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) != 0 && !l2.h(bArr, j4, j4 + i13)) {
                        throw zzeo.zzc();
                    } else {
                        unsafe.putObject(obj, j, new String(bArr, j4, i13, zzem.f9632a));
                        j4 += i13;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return j4;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object g = g(obj, i4, i8);
                    int o2 = n.o(g, d(i8), bArr, i, i2, mVar);
                    o(obj, i4, i8, g);
                    return o2;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int a2 = n.a(bArr, i, mVar);
                    unsafe.putObject(obj, j, mVar.c);
                    unsafe.putInt(obj, j2, i4);
                    return a2;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int j5 = n.j(bArr, i, mVar);
                    int i14 = mVar.f9604a;
                    zzeh c = c(i8);
                    if (c != null && !c.zza(i14)) {
                        z(obj).f(i3, Long.valueOf(i14));
                    } else {
                        unsafe.putObject(obj, j, Integer.valueOf(i14));
                        unsafe.putInt(obj, j2, i4);
                    }
                    return j5;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int j6 = n.j(bArr, i, mVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzdf.zzb(mVar.f9604a)));
                    unsafe.putInt(obj, j2, i4);
                    return j6;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int m3 = n.m(bArr, i, mVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzdf.zzc(mVar.b)));
                    unsafe.putInt(obj, j2, i4);
                    return m3;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object g2 = g(obj, i4, i8);
                    int n2 = n.n(g2, d(i8), bArr, i, i2, (i3 & (-8)) | 4, mVar);
                    o(obj, i4, i8, g2);
                    return n2;
                }
                break;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:113:0x0216 -> B:114:0x0217). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:133:0x0264 -> B:134:0x0265). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x019b -> B:82:0x019c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int H(java.lang.Object r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m r30) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0.H(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m):int");
    }

    public final int I(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return L(i, 0);
    }

    public final int J(int i, int i2) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        return L(i, i2);
    }

    public final int K(int i) {
        return this.f9623a[i + 2];
    }

    public final int L(int i, int i2) {
        int length = (this.f9623a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.f9623a[i4];
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

    public final int N(int i) {
        return this.f9623a[i + 1];
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x02e8, code lost:
        if (r0 != r24) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02ea, code lost:
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r2 = r15;
        r10 = r18;
        r1 = r23;
        r6 = r25;
        r7 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x02fd, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0329, code lost:
        if (r0 != r14) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x034c, code lost:
        if (r0 != r14) goto L44;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 970
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0.a(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x09e5  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(java.lang.Object r18, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2 r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2848
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0.b(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m2):void");
    }

    public final zzeh c(int i) {
        int i2 = i / 3;
        return (zzeh) this.b[i2 + i2 + 1];
    }

    public final l1 d(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        l1 l1Var = (l1) this.b[i3];
        if (l1Var != null) {
            return l1Var;
        }
        l1 b = d1.a().b((Class) this.b[i3 + 1]);
        this.b[i3] = b;
        return b;
    }

    public final Object e(int i) {
        int i2 = i / 3;
        return this.b[i2 + i2];
    }

    public final Object f(Object obj, int i) {
        l1 d = d(i);
        int N = N(i) & ErrorCode.ERR_UNKNOWN;
        if (!r(obj, i)) {
            return d.zze();
        }
        Object object = o.getObject(obj, N);
        if (u(object)) {
            return object;
        }
        Object zze = d.zze();
        if (object != null) {
            d.zzg(zze, object);
        }
        return zze;
    }

    public final Object g(Object obj, int i, int i2) {
        l1 d = d(i2);
        if (!v(obj, i, i2)) {
            return d.zze();
        }
        Object object = o.getObject(obj, N(i2) & ErrorCode.ERR_UNKNOWN);
        if (u(object)) {
            return object;
        }
        Object zze = d.zze();
        if (object != null) {
            d.zzg(zze, object);
        }
        return zze;
    }

    public final void j(Object obj, Object obj2, int i) {
        if (r(obj2, i)) {
            int N = N(i) & ErrorCode.ERR_UNKNOWN;
            Unsafe unsafe = o;
            long j = N;
            Object object = unsafe.getObject(obj2, j);
            if (object != null) {
                l1 d = d(i);
                if (!r(obj, i)) {
                    if (!u(object)) {
                        unsafe.putObject(obj, j, object);
                    } else {
                        Object zze = d.zze();
                        d.zzg(zze, object);
                        unsafe.putObject(obj, j, zze);
                    }
                    l(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, j);
                if (!u(object2)) {
                    Object zze2 = d.zze();
                    d.zzg(zze2, object2);
                    unsafe.putObject(obj, j, zze2);
                    object2 = zze2;
                }
                d.zzg(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + this.f9623a[i] + " is present but null: " + obj2.toString());
        }
    }

    public final void k(Object obj, Object obj2, int i) {
        int i2 = this.f9623a[i];
        if (v(obj2, i2, i)) {
            int N = N(i) & ErrorCode.ERR_UNKNOWN;
            Unsafe unsafe = o;
            long j = N;
            Object object = unsafe.getObject(obj2, j);
            if (object != null) {
                l1 d = d(i);
                if (!v(obj, i2, i)) {
                    if (!u(object)) {
                        unsafe.putObject(obj, j, object);
                    } else {
                        Object zze = d.zze();
                        d.zzg(zze, object);
                        unsafe.putObject(obj, j, zze);
                    }
                    m(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, j);
                if (!u(object2)) {
                    Object zze2 = d.zze();
                    d.zzg(zze2, object2);
                    unsafe.putObject(obj, j, zze2);
                    object2 = zze2;
                }
                d.zzg(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + this.f9623a[i] + " is present but null: " + obj2.toString());
        }
    }

    public final void l(Object obj, int i) {
        int K = K(i);
        long j = 1048575 & K;
        if (j == 1048575) {
            return;
        }
        g2.v(obj, j, (1 << (K >>> 20)) | g2.h(obj, j));
    }

    public final void m(Object obj, int i, int i2) {
        g2.v(obj, K(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    public final void n(Object obj, int i, Object obj2) {
        o.putObject(obj, N(i) & ErrorCode.ERR_UNKNOWN, obj2);
        l(obj, i);
    }

    public final void o(Object obj, int i, int i2, Object obj2) {
        o.putObject(obj, N(i2) & ErrorCode.ERR_UNKNOWN, obj2);
        m(obj, i, i2);
    }

    public final void p(m2 m2Var, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        zzfh zzfhVar = (zzfh) e(i2);
        throw null;
    }

    public final boolean q(Object obj, Object obj2, int i) {
        return r(obj, i) == r(obj2, i);
    }

    public final boolean r(Object obj, int i) {
        int K = K(i);
        long j = K & ErrorCode.ERR_UNKNOWN;
        if (j != 1048575) {
            return (g2.h(obj, j) & (1 << (K >>> 20))) != 0;
        }
        int N = N(i);
        long j2 = N & ErrorCode.ERR_UNKNOWN;
        switch (M(N)) {
            case 0:
                return Double.doubleToRawLongBits(g2.f(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(g2.g(obj, j2)) != 0;
            case 2:
                return g2.i(obj, j2) != 0;
            case 3:
                return g2.i(obj, j2) != 0;
            case 4:
                return g2.h(obj, j2) != 0;
            case 5:
                return g2.i(obj, j2) != 0;
            case 6:
                return g2.h(obj, j2) != 0;
            case 7:
                return g2.B(obj, j2);
            case 8:
                Object k = g2.k(obj, j2);
                if (k instanceof String) {
                    return !((String) k).isEmpty();
                } else if (k instanceof zzdb) {
                    return !zzdb.zzb.equals(k);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return g2.k(obj, j2) != null;
            case 10:
                return !zzdb.zzb.equals(g2.k(obj, j2));
            case 11:
                return g2.h(obj, j2) != 0;
            case 12:
                return g2.h(obj, j2) != 0;
            case 13:
                return g2.h(obj, j2) != 0;
            case 14:
                return g2.i(obj, j2) != 0;
            case 15:
                return g2.h(obj, j2) != 0;
            case 16:
                return g2.i(obj, j2) != 0;
            case 17:
                return g2.k(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean s(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return r(obj, i);
        }
        return (i3 & i4) != 0;
    }

    public final boolean v(Object obj, int i, int i2) {
        return g2.h(obj, (long) (K(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:176:0x054d, code lost:
        if (r6 == r1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x054f, code lost:
        r32.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0555, code lost:
        r2 = r9.i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0559, code lost:
        if (r2 >= r9.j) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x055b, code lost:
        r4 = r9.h[r2];
        r5 = r9.f9623a[r4];
        r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.g2.k(r12, r9.N(r4) & r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x056d, code lost:
        if (r5 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0574, code lost:
        if (r9.c(r4) != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0576, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0579, code lost:
        r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfi) r5;
        r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfh) r9.e(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0581, code lost:
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0582, code lost:
        if (r7 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x0586, code lost:
        if (r0 != r38) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x058d, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze();
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0590, code lost:
        if (r0 > r38) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0592, code lost:
        if (r3 != r7) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0594, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0599, code lost:
        throw com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int y(java.lang.Object r35, byte[] r36, int r37, int r38, int r39, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m r40) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1512
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.y0.y(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.m):int");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final int zza(Object obj) {
        int zzy;
        int zzy2;
        int zzz;
        int zzy3;
        int zzy4;
        int zzy5;
        int zzy6;
        int M;
        int zzy7;
        int zzz2;
        int zzy8;
        int zzy9;
        if (this.g) {
            Unsafe unsafe = o;
            int i = 0;
            for (int i2 = 0; i2 < this.f9623a.length; i2 += 3) {
                int N = N(i2);
                int M2 = M(N);
                int i3 = this.f9623a[i2];
                int i4 = N & ErrorCode.ERR_UNKNOWN;
                if (M2 >= zzdu.zzJ.zza() && M2 <= zzdu.zzW.zza()) {
                    int i5 = this.f9623a[i2 + 2];
                }
                long j = i4;
                switch (M2) {
                    case 0:
                        if (r(obj, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (r(obj, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (r(obj, i2)) {
                            zzz = zzdj.zzz(g2.i(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (r(obj, i2)) {
                            zzz = zzdj.zzz(g2.i(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (r(obj, i2)) {
                            zzz = zzdj.zzu(g2.h(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (r(obj, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (r(obj, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (r(obj, i2)) {
                            zzy4 = zzdj.zzy(i3 << 3);
                            M = zzy4 + 1;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (r(obj, i2)) {
                            Object k = g2.k(obj, j);
                            if (k instanceof zzdb) {
                                int i6 = i3 << 3;
                                int i7 = zzdj.zzb;
                                int zzd = ((zzdb) k).zzd();
                                zzy5 = zzdj.zzy(zzd) + zzd;
                                zzy6 = zzdj.zzy(i6);
                                M = zzy6 + zzy5;
                                i += M;
                                break;
                            } else {
                                zzz = zzdj.zzx((String) k);
                                zzy3 = zzdj.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (r(obj, i2)) {
                            M = n1.M(i3, g2.k(obj, j), d(i2));
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (r(obj, i2)) {
                            int i8 = i3 << 3;
                            int i9 = zzdj.zzb;
                            int zzd2 = ((zzdb) g2.k(obj, j)).zzd();
                            zzy5 = zzdj.zzy(zzd2) + zzd2;
                            zzy6 = zzdj.zzy(i8);
                            M = zzy6 + zzy5;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (r(obj, i2)) {
                            zzz = zzdj.zzy(g2.h(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (r(obj, i2)) {
                            zzz = zzdj.zzu(g2.h(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (r(obj, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (r(obj, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (r(obj, i2)) {
                            int h = g2.h(obj, j);
                            zzy3 = zzdj.zzy(i3 << 3);
                            zzz = zzdj.zzy((h >> 31) ^ (h + h));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (r(obj, i2)) {
                            long i10 = g2.i(obj, j);
                            zzy7 = zzdj.zzy(i3 << 3);
                            zzz2 = zzdj.zzz((i10 + i10) ^ (i10 >> 63));
                            M = zzy7 + zzz2;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (r(obj, i2)) {
                            M = zzdj.c(i3, (zzfo) g2.k(obj, j), d(i2));
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        M = n1.F(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 19:
                        M = n1.D(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 20:
                        M = n1.K(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 21:
                        M = n1.V(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 22:
                        M = n1.I(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 23:
                        M = n1.F(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 24:
                        M = n1.D(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 25:
                        M = n1.z(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 26:
                        M = n1.S(i3, (List) g2.k(obj, j));
                        i += M;
                        break;
                    case 27:
                        M = n1.N(i3, (List) g2.k(obj, j), d(i2));
                        i += M;
                        break;
                    case 28:
                        M = n1.A(i3, (List) g2.k(obj, j));
                        i += M;
                        break;
                    case 29:
                        M = n1.T(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 30:
                        M = n1.B(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 31:
                        M = n1.D(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 32:
                        M = n1.F(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 33:
                        M = n1.O(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 34:
                        M = n1.Q(i3, (List) g2.k(obj, j), false);
                        i += M;
                        break;
                    case 35:
                        zzz = n1.G((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i11 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i11);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzz = n1.E((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i12 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i12);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzz = n1.L((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i13 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i13);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzz = n1.W((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i14 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i14);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzz = n1.J((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i15 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i15);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzz = n1.G((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i16 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i16);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzz = n1.E((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i17 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i17);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        int i18 = n1.e;
                        zzz = ((List) unsafe.getObject(obj, j)).size();
                        if (zzz > 0) {
                            int i19 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i19);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzz = n1.U((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i20 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i20);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzz = n1.C((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i21 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i21);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzz = n1.E((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i22 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i22);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzz = n1.G((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i23 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i23);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzz = n1.P((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i24 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i24);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzz = n1.R((List) unsafe.getObject(obj, j));
                        if (zzz > 0) {
                            int i25 = i3 << 3;
                            zzy8 = zzdj.zzy(zzz);
                            zzy9 = zzdj.zzy(i25);
                            zzy3 = zzy9 + zzy8;
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        M = n1.H(i3, (List) g2.k(obj, j), d(i2));
                        i += M;
                        break;
                    case 50:
                        t0.a(i3, g2.k(obj, j), e(i2));
                        break;
                    case 51:
                        if (v(obj, i3, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (v(obj, i3, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (v(obj, i3, i2)) {
                            zzz = zzdj.zzz(O(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (v(obj, i3, i2)) {
                            zzz = zzdj.zzz(O(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (v(obj, i3, i2)) {
                            zzz = zzdj.zzu(E(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (v(obj, i3, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (v(obj, i3, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (v(obj, i3, i2)) {
                            zzy4 = zzdj.zzy(i3 << 3);
                            M = zzy4 + 1;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (v(obj, i3, i2)) {
                            Object k2 = g2.k(obj, j);
                            if (k2 instanceof zzdb) {
                                int i26 = i3 << 3;
                                int i27 = zzdj.zzb;
                                int zzd3 = ((zzdb) k2).zzd();
                                zzy5 = zzdj.zzy(zzd3) + zzd3;
                                zzy6 = zzdj.zzy(i26);
                                M = zzy6 + zzy5;
                                i += M;
                                break;
                            } else {
                                zzz = zzdj.zzx((String) k2);
                                zzy3 = zzdj.zzy(i3 << 3);
                                i += zzy3 + zzz;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (v(obj, i3, i2)) {
                            M = n1.M(i3, g2.k(obj, j), d(i2));
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (v(obj, i3, i2)) {
                            int i28 = i3 << 3;
                            int i29 = zzdj.zzb;
                            int zzd4 = ((zzdb) g2.k(obj, j)).zzd();
                            zzy5 = zzdj.zzy(zzd4) + zzd4;
                            zzy6 = zzdj.zzy(i28);
                            M = zzy6 + zzy5;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (v(obj, i3, i2)) {
                            zzz = zzdj.zzy(E(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (v(obj, i3, i2)) {
                            zzz = zzdj.zzu(E(obj, j));
                            zzy3 = zzdj.zzy(i3 << 3);
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (v(obj, i3, i2)) {
                            zzy2 = zzdj.zzy(i3 << 3);
                            M = zzy2 + 4;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (v(obj, i3, i2)) {
                            zzy = zzdj.zzy(i3 << 3);
                            M = zzy + 8;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (v(obj, i3, i2)) {
                            int E = E(obj, j);
                            zzy3 = zzdj.zzy(i3 << 3);
                            zzz = zzdj.zzy((E >> 31) ^ (E + E));
                            i += zzy3 + zzz;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (v(obj, i3, i2)) {
                            long O = O(obj, j);
                            zzy7 = zzdj.zzy(i3 << 3);
                            zzz2 = zzdj.zzz((O + O) ^ (O >> 63));
                            M = zzy7 + zzz2;
                            i += M;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (v(obj, i3, i2)) {
                            M = zzdj.c(i3, (zzfo) g2.k(obj, j), d(i2));
                            i += M;
                            break;
                        } else {
                            break;
                        }
                }
            }
            y1 y1Var = this.l;
            return i + y1Var.a(y1Var.d(obj));
        }
        return D(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int floatToIntBits;
        int length = this.f9623a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int N = N(i3);
            int i4 = this.f9623a[i3];
            long j = 1048575 & N;
            int i5 = 37;
            switch (M(N)) {
                case 0:
                    i = i2 * 53;
                    doubleToLongBits = Double.doubleToLongBits(g2.f(obj, j));
                    byte[] bArr = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 1:
                    i = i2 * 53;
                    floatToIntBits = Float.floatToIntBits(g2.g(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 2:
                    i = i2 * 53;
                    doubleToLongBits = g2.i(obj, j);
                    byte[] bArr2 = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 3:
                    i = i2 * 53;
                    doubleToLongBits = g2.i(obj, j);
                    byte[] bArr3 = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 4:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 5:
                    i = i2 * 53;
                    doubleToLongBits = g2.i(obj, j);
                    byte[] bArr4 = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 6:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 7:
                    i = i2 * 53;
                    floatToIntBits = zzem.zza(g2.B(obj, j));
                    i2 = i + floatToIntBits;
                    break;
                case 8:
                    i = i2 * 53;
                    floatToIntBits = ((String) g2.k(obj, j)).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 9:
                    Object k = g2.k(obj, j);
                    if (k != null) {
                        i5 = k.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    floatToIntBits = g2.k(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 11:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 12:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 13:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 14:
                    i = i2 * 53;
                    doubleToLongBits = g2.i(obj, j);
                    byte[] bArr5 = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 15:
                    i = i2 * 53;
                    floatToIntBits = g2.h(obj, j);
                    i2 = i + floatToIntBits;
                    break;
                case 16:
                    i = i2 * 53;
                    doubleToLongBits = g2.i(obj, j);
                    byte[] bArr6 = zzem.zzd;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i2 = i + floatToIntBits;
                    break;
                case 17:
                    Object k2 = g2.k(obj, j);
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
                    floatToIntBits = g2.k(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 50:
                    i = i2 * 53;
                    floatToIntBits = g2.k(obj, j).hashCode();
                    i2 = i + floatToIntBits;
                    break;
                case 51:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = Double.doubleToLongBits(B(obj, j));
                        byte[] bArr7 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = Float.floatToIntBits(C(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = O(obj, j);
                        byte[] bArr8 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = O(obj, j);
                        byte[] bArr9 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = O(obj, j);
                        byte[] bArr10 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = zzem.zza(w(obj, j));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = ((String) g2.k(obj, j)).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = g2.k(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = g2.k(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = O(obj, j);
                        byte[] bArr11 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = E(obj, j);
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        doubleToLongBits = O(obj, j);
                        byte[] bArr12 = zzem.zzd;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (v(obj, i4, i3)) {
                        i = i2 * 53;
                        floatToIntBits = g2.k(obj, j).hashCode();
                        i2 = i + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.l.d(obj).hashCode();
        return this.f ? (hashCode * 53) + this.m.b(obj).f9593a.hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final Object zze() {
        return ((zzed) this.e).d();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final void zzf(Object obj) {
        if (u(obj)) {
            if (obj instanceof zzed) {
                zzed zzedVar = (zzed) obj;
                zzedVar.g(Integer.MAX_VALUE);
                zzedVar.zzb = 0;
                zzedVar.f();
            }
            int length = this.f9623a.length;
            for (int i = 0; i < length; i += 3) {
                int N = N(i);
                int i2 = 1048575 & N;
                int M = M(N);
                long j = i2;
                if (M != 9) {
                    if (M != 60 && M != 68) {
                        switch (M) {
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
                                this.k.a(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = o;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzfi) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    } else if (v(obj, this.f9623a[i], i)) {
                        d(i).zzf(o.getObject(obj, j));
                    }
                }
                if (r(obj, i)) {
                    d(i).zzf(o.getObject(obj, j));
                }
            }
            this.l.g(obj);
            if (this.f) {
                this.m.e(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final void zzg(Object obj, Object obj2) {
        i(obj);
        Objects.requireNonNull(obj2);
        for (int i = 0; i < this.f9623a.length; i += 3) {
            int N = N(i);
            int i2 = this.f9623a[i];
            long j = 1048575 & N;
            switch (M(N)) {
                case 0:
                    if (r(obj2, i)) {
                        g2.t(obj, j, g2.f(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (r(obj2, i)) {
                        g2.u(obj, j, g2.g(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (r(obj2, i)) {
                        g2.w(obj, j, g2.i(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (r(obj2, i)) {
                        g2.w(obj, j, g2.i(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (r(obj2, i)) {
                        g2.w(obj, j, g2.i(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (r(obj2, i)) {
                        g2.r(obj, j, g2.B(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (r(obj2, i)) {
                        g2.x(obj, j, g2.k(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    j(obj, obj2, i);
                    break;
                case 10:
                    if (r(obj2, i)) {
                        g2.x(obj, j, g2.k(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (r(obj2, i)) {
                        g2.w(obj, j, g2.i(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (r(obj2, i)) {
                        g2.v(obj, j, g2.h(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (r(obj2, i)) {
                        g2.w(obj, j, g2.i(obj2, j));
                        l(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    j(obj, obj2, i);
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
                    this.k.b(obj, obj2, j);
                    break;
                case 50:
                    int i3 = n1.e;
                    g2.x(obj, j, t0.b(g2.k(obj, j), g2.k(obj2, j)));
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
                    if (v(obj2, i2, i)) {
                        g2.x(obj, j, g2.k(obj2, j));
                        m(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    k(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (v(obj2, i2, i)) {
                        g2.x(obj, j, g2.k(obj2, j));
                        m(obj, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    k(obj, obj2, i);
                    break;
            }
        }
        n1.d(this.l, obj, obj2);
        if (this.f) {
            n1.c(this.m, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final boolean zzj(Object obj, Object obj2) {
        boolean x;
        int length = this.f9623a.length;
        for (int i = 0; i < length; i += 3) {
            int N = N(i);
            long j = N & ErrorCode.ERR_UNKNOWN;
            switch (M(N)) {
                case 0:
                    if (q(obj, obj2, i) && Double.doubleToLongBits(g2.f(obj, j)) == Double.doubleToLongBits(g2.f(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (q(obj, obj2, i) && Float.floatToIntBits(g2.g(obj, j)) == Float.floatToIntBits(g2.g(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (q(obj, obj2, i) && g2.i(obj, j) == g2.i(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (q(obj, obj2, i) && g2.i(obj, j) == g2.i(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (q(obj, obj2, i) && g2.i(obj, j) == g2.i(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (q(obj, obj2, i) && g2.B(obj, j) == g2.B(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (q(obj, obj2, i) && n1.x(g2.k(obj, j), g2.k(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (q(obj, obj2, i) && n1.x(g2.k(obj, j), g2.k(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (q(obj, obj2, i) && n1.x(g2.k(obj, j), g2.k(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (q(obj, obj2, i) && g2.i(obj, j) == g2.i(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (q(obj, obj2, i) && g2.h(obj, j) == g2.h(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (q(obj, obj2, i) && g2.i(obj, j) == g2.i(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (q(obj, obj2, i) && n1.x(g2.k(obj, j), g2.k(obj2, j))) {
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
                    x = n1.x(g2.k(obj, j), g2.k(obj2, j));
                    break;
                case 50:
                    x = n1.x(g2.k(obj, j), g2.k(obj2, j));
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
                    long K = K(i) & ErrorCode.ERR_UNKNOWN;
                    if (g2.h(obj, K) == g2.h(obj2, K) && n1.x(g2.k(obj, j), g2.k(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!x) {
                return false;
            }
        }
        if (this.l.d(obj).equals(this.l.d(obj2))) {
            if (this.f) {
                return this.m.b(obj).equals(this.m.b(obj2));
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.l1
    public final boolean zzk(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.i) {
            int i6 = this.h[i5];
            int i7 = this.f9623a[i6];
            int N = N(i6);
            int i8 = this.f9623a[i6 + 2];
            int i9 = i8 & ErrorCode.ERR_UNKNOWN;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = o.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & N) != 0 && !s(obj, i6, i, i2, i10)) {
                return false;
            }
            int M = M(N);
            if (M != 9 && M != 17) {
                if (M != 27) {
                    if (M == 60 || M == 68) {
                        if (v(obj, i7, i6) && !t(obj, N, d(i6))) {
                            return false;
                        }
                    } else if (M != 49) {
                        if (M == 50 && !((zzfi) g2.k(obj, N & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                            zzfh zzfhVar = (zzfh) e(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) g2.k(obj, N & ErrorCode.ERR_UNKNOWN);
                if (list.isEmpty()) {
                    continue;
                } else {
                    l1 d = d(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!d.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (s(obj, i6, i, i2, i10) && !t(obj, N, d(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.f || this.m.b(obj).k();
    }
}
