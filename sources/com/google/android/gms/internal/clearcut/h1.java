package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import sun.misc.Unsafe;
/* loaded from: classes7.dex */
public final class h1<T> implements r1<T> {
    public static final Unsafe r = n2.z();

    /* renamed from: a  reason: collision with root package name */
    public final int[] f8580a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final int e;
    public final zzdo f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int[] k;
    public final int[] l;
    public final j1 m;
    public final s0 n;
    public final i2<?, ?> o;
    public final e0<?> p;
    public final b1 q;

    public h1(int[] iArr, Object[] objArr, int i, int i2, int i3, zzdo zzdoVar, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, j1 j1Var, s0 s0Var, i2<?, ?> i2Var, e0<?> e0Var, b1 b1Var) {
        this.f8580a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.e = i3;
        boolean z3 = zzdoVar instanceof zzcg;
        this.h = z;
        this.g = e0Var != null && e0Var.g(zzdoVar);
        this.i = false;
        this.j = iArr2;
        this.k = iArr3;
        this.l = iArr4;
        this.m = j1Var;
        this.n = s0Var;
        this.o = i2Var;
        this.p = e0Var;
        this.f = zzdoVar;
        this.q = b1Var;
    }

    public static <E> List<E> L(Object obj, long j) {
        return (List) n2.M(obj, j);
    }

    public static <T> double M(T t, long j) {
        return ((Double) n2.M(t, j)).doubleValue();
    }

    public static <T> float N(T t, long j) {
        return ((Float) n2.M(t, j)).floatValue();
    }

    public static <T> int O(T t, long j) {
        return ((Integer) n2.M(t, j)).intValue();
    }

    public static <T> long P(T t, long j) {
        return ((Long) n2.M(t, j)).longValue();
    }

    public static <T> boolean Q(T t, long j) {
        return ((Boolean) n2.M(t, j)).booleanValue();
    }

    public static zzey R(Object obj) {
        zzcg zzcgVar = (zzcg) obj;
        zzey zzeyVar = zzcgVar.zzjp;
        if (zzeyVar == zzey.zzea()) {
            zzey f = zzey.f();
            zzcgVar.zzjp = f;
            return f;
        }
        return zzeyVar;
    }

    public static int e(int i, byte[] bArr, int i2, int i3, Object obj, o oVar) throws IOException {
        return n.c(i, bArr, i2, i3, R(obj), oVar);
    }

    public static int i(r1<?> r1Var, int i, byte[] bArr, int i2, int i3, zzcn<?> zzcnVar, o oVar) throws IOException {
        int k = k(r1Var, bArr, i2, i3, oVar);
        while (true) {
            zzcnVar.add(oVar.c);
            if (k >= i3) {
                break;
            }
            int e = n.e(bArr, k, oVar);
            if (i != oVar.f8592a) {
                break;
            }
            k = k(r1Var, bArr, e, i3, oVar);
        }
        return k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int j(r1 r1Var, byte[] bArr, int i, int i2, int i3, o oVar) throws IOException {
        h1 h1Var = (h1) r1Var;
        Object newInstance = h1Var.newInstance();
        int p = h1Var.p(newInstance, bArr, i, i2, i3, oVar);
        h1Var.zzc(newInstance);
        oVar.c = newInstance;
        return p;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int k(r1 r1Var, byte[] bArr, int i, int i2, o oVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = n.d(i4, bArr, i3, oVar);
            i4 = oVar.f8592a;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzco.zzbl();
        }
        Object newInstance = r1Var.newInstance();
        int i6 = i4 + i5;
        r1Var.h(newInstance, bArr, i5, i6, oVar);
        r1Var.zzc(newInstance);
        oVar.c = newInstance;
        return i6;
    }

    public static <UT, UB> int l(i2<UT, UB> i2Var, T t) {
        return i2Var.j(i2Var.k(t));
    }

    public static <T> h1<T> q(Class<T> cls, e1 e1Var, j1 j1Var, s0 s0Var, i2<?, ?> i2Var, e0<?> e0Var, b1 b1Var) {
        int j;
        int i;
        int i2;
        int b;
        int i3;
        int i4;
        if (!(e1Var instanceof o1)) {
            ((e2) e1Var).a();
            throw new NoSuchMethodError();
        }
        o1 o1Var = (o1) e1Var;
        boolean z = o1Var.a() == zzcg.zzg.zzkm;
        if (o1Var.c() == 0) {
            j = 0;
            i = 0;
            i2 = 0;
        } else {
            int e = o1Var.e();
            int f = o1Var.f();
            j = o1Var.j();
            i = e;
            i2 = f;
        }
        int[] iArr = new int[j << 2];
        Object[] objArr = new Object[j << 1];
        int[] iArr2 = o1Var.g() > 0 ? new int[o1Var.g()] : null;
        int[] iArr3 = o1Var.h() > 0 ? new int[o1Var.h()] : null;
        p1 d = o1Var.d();
        if (d.a()) {
            int g = d.g();
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                if (g >= o1Var.k() || i5 >= ((g - i) << 2)) {
                    if (d.k()) {
                        b = (int) n2.b(d.l());
                        i3 = (int) n2.b(d.m());
                        i4 = 0;
                    } else {
                        b = (int) n2.b(d.n());
                        if (d.o()) {
                            i3 = (int) n2.b(d.p());
                            i4 = d.q();
                        } else {
                            i3 = 0;
                            i4 = 0;
                        }
                    }
                    iArr[i5] = d.g();
                    int i8 = i5 + 1;
                    iArr[i8] = (d.s() ? PKIFailureInfo.duplicateCertReq : 0) | (d.r() ? 268435456 : 0) | (d.h() << 20) | b;
                    iArr[i5 + 2] = i3 | (i4 << 20);
                    if (d.v() != null) {
                        int i9 = (i5 / 4) << 1;
                        objArr[i9] = d.v();
                        if (d.t() != null) {
                            objArr[i9 + 1] = d.t();
                        } else if (d.u() != null) {
                            objArr[i9 + 1] = d.u();
                        }
                    } else if (d.t() != null) {
                        objArr[((i5 / 4) << 1) + 1] = d.t();
                    } else if (d.u() != null) {
                        objArr[((i5 / 4) << 1) + 1] = d.u();
                    }
                    int h = d.h();
                    if (h == zzcb.zziw.ordinal()) {
                        iArr2[i6] = i5;
                        i6++;
                    } else if (h >= 18 && h <= 49) {
                        iArr3[i7] = iArr[i8] & ErrorCode.ERR_UNKNOWN;
                        i7++;
                    }
                    if (!d.a()) {
                        break;
                    }
                    g = d.g();
                } else {
                    for (int i10 = 0; i10 < 4; i10++) {
                        iArr[i5 + i10] = -1;
                    }
                }
                i5 += 4;
            }
        }
        return new h1<>(iArr, objArr, i, i2, o1Var.k(), o1Var.zzch(), z, false, o1Var.i(), iArr2, iArr3, j1Var, s0Var, i2Var, e0Var, b1Var);
    }

    public static void s(int i, Object obj, z2 z2Var) throws IOException {
        if (obj instanceof String) {
            z2Var.zza(i, (String) obj);
        } else {
            z2Var.i(i, (zzbb) obj);
        }
    }

    public static <UT, UB> void t(i2<UT, UB> i2Var, T t, z2 z2Var) throws IOException {
        i2Var.c(i2Var.k(t), z2Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean z(Object obj, int i, r1 r1Var) {
        return r1Var.f(n2.M(obj, i & ErrorCode.ERR_UNKNOWN));
    }

    public final r1 A(int i) {
        int i2 = (i / 4) << 1;
        r1 r1Var = (r1) this.b[i2];
        if (r1Var != null) {
            return r1Var;
        }
        r1<T> b = m1.a().b((Class) this.b[i2 + 1]);
        this.b[i2] = b;
        return b;
    }

    public final Object B(int i) {
        return this.b[(i / 4) << 1];
    }

    public final zzck<?> C(int i) {
        return (zzck) this.b[((i / 4) << 1) + 1];
    }

    public final int D(int i) {
        return this.f8580a[i + 1];
    }

    public final int E(int i) {
        return this.f8580a[i + 2];
    }

    public final int F(int i) {
        int i2 = this.c;
        if (i >= i2) {
            int i3 = this.e;
            if (i < i3) {
                int i4 = (i - i2) << 2;
                if (this.f8580a[i4] == i) {
                    return i4;
                }
                return -1;
            } else if (i <= this.d) {
                int i5 = i3 - i2;
                int length = (this.f8580a.length / 4) - 1;
                while (i5 <= length) {
                    int i6 = (length + i5) >>> 1;
                    int i7 = i6 << 2;
                    int i8 = this.f8580a[i7];
                    if (i == i8) {
                        return i7;
                    }
                    if (i < i8) {
                        length = i6 - 1;
                    } else {
                        i5 = i6 + 1;
                    }
                }
            }
        }
        return -1;
    }

    public final void G(T t, int i) {
        if (this.h) {
            return;
        }
        int E = E(i);
        long j = E & ErrorCode.ERR_UNKNOWN;
        n2.g(t, j, n2.H(t, j) | (1 << (E >>> 20)));
    }

    public final void H(T t, int i, int i2) {
        n2.g(t, E(i2) & ErrorCode.ERR_UNKNOWN, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0494  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void I(T r19, com.google.android.gms.internal.clearcut.z2 r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.I(java.lang.Object, com.google.android.gms.internal.clearcut.z2):void");
    }

    public final void J(T t, T t2, int i) {
        int D = D(i);
        int i2 = this.f8580a[i];
        long j = D & ErrorCode.ERR_UNKNOWN;
        if (x(t2, i2, i)) {
            Object M = n2.M(t, j);
            Object M2 = n2.M(t2, j);
            if (M != null && M2 != null) {
                n2.i(t, j, zzci.c(M, M2));
                H(t, i2, i);
            } else if (M2 != null) {
                n2.i(t, j, M2);
                H(t, i2, i);
            }
        }
    }

    public final boolean K(T t, T t2, int i) {
        return w(t, i) == w(t2, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ce, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e0, code lost:
        if (r3 != null) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(T r9) {
        /*
            Method dump skipped, instructions count: 476
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.a(java.lang.Object):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a0, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0038, code lost:
        if (com.google.android.gms.internal.clearcut.t1.y(com.google.android.gms.internal.clearcut.n2.M(r10, r6), com.google.android.gms.internal.clearcut.n2.M(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005c, code lost:
        if (com.google.android.gms.internal.clearcut.t1.y(com.google.android.gms.internal.clearcut.n2.M(r10, r6), com.google.android.gms.internal.clearcut.n2.M(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0096, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a8, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00cc, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        if (com.google.android.gms.internal.clearcut.t1.y(com.google.android.gms.internal.clearcut.n2.M(r10, r6), com.google.android.gms.internal.clearcut.n2.M(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f8, code lost:
        if (com.google.android.gms.internal.clearcut.t1.y(com.google.android.gms.internal.clearcut.n2.M(r10, r6), com.google.android.gms.internal.clearcut.n2.M(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x010e, code lost:
        if (com.google.android.gms.internal.clearcut.t1.y(com.google.android.gms.internal.clearcut.n2.M(r10, r6), com.google.android.gms.internal.clearcut.n2.M(r11, r6)) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0120, code lost:
        if (com.google.android.gms.internal.clearcut.n2.J(r10, r6) == com.google.android.gms.internal.clearcut.n2.J(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0145, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0156, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0169, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x017c, code lost:
        if (com.google.android.gms.internal.clearcut.n2.I(r10, r6) == com.google.android.gms.internal.clearcut.n2.I(r11, r6)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018d, code lost:
        if (com.google.android.gms.internal.clearcut.n2.H(r10, r6) == com.google.android.gms.internal.clearcut.n2.H(r11, r6)) goto L84;
     */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.b(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final void c(T t, T t2) {
        Objects.requireNonNull(t2);
        for (int i = 0; i < this.f8580a.length; i += 4) {
            int D = D(i);
            long j = 1048575 & D;
            int i2 = this.f8580a[i];
            switch ((D & 267386880) >>> 20) {
                case 0:
                    if (w(t2, i)) {
                        n2.e(t, j, n2.L(t2, j));
                        G(t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (w(t2, i)) {
                        n2.f(t, j, n2.K(t2, j));
                        G(t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.h(t, j, n2.I(t2, j));
                    G(t, i);
                    break;
                case 3:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.h(t, j, n2.I(t2, j));
                    G(t, i);
                    break;
                case 4:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 5:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.h(t, j, n2.I(t2, j));
                    G(t, i);
                    break;
                case 6:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 7:
                    if (w(t2, i)) {
                        n2.j(t, j, n2.J(t2, j));
                        G(t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.i(t, j, n2.M(t2, j));
                    G(t, i);
                    break;
                case 9:
                case 17:
                    v(t, t2, i);
                    break;
                case 10:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.i(t, j, n2.M(t2, j));
                    G(t, i);
                    break;
                case 11:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 12:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 13:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 14:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.h(t, j, n2.I(t2, j));
                    G(t, i);
                    break;
                case 15:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.g(t, j, n2.H(t2, j));
                    G(t, i);
                    break;
                case 16:
                    if (!w(t2, i)) {
                        break;
                    }
                    n2.h(t, j, n2.I(t2, j));
                    G(t, i);
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
                    t1.h(this.q, t, t2, j);
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
                    if (!x(t2, i2, i)) {
                        break;
                    }
                    n2.i(t, j, n2.M(t2, j));
                    H(t, i2, i);
                    break;
                case 60:
                case 68:
                    J(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!x(t2, i2, i)) {
                        break;
                    }
                    n2.i(t, j, n2.M(t2, j));
                    H(t, i2, i);
                    break;
            }
        }
        if (this.h) {
            return;
        }
        t1.i(this.o, t, t2);
        if (this.g) {
            t1.g(this.p, t, t2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0181, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0193, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a5, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01b6, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01c7, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01d8, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01e9, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020b, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x020d, code lost:
        r2.putInt(r20, r14, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0211, code lost:
        r3 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r3) + com.google.android.gms.internal.clearcut.zzbn.zzt(r5)) + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0334, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x0414, code lost:
        if (x(r20, r15, r5) != false) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x0434, code lost:
        if (x(r20, r15, r5) != false) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x043c, code lost:
        if (x(r20, r15, r5) != false) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x045c, code lost:
        if (x(r20, r15, r5) != false) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0464, code lost:
        if (x(r20, r15, r5) != false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0474, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x047c, code lost:
        if (x(r20, r15, r5) != false) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0514, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0526, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0538, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x054a, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x055c, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x056e, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x0580, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x0592, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x05a3, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x05b4, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x05c5, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x05d6, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x05e7, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x05f8, code lost:
        if (r19.i != false) goto L334;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x05fa, code lost:
        r2.putInt(r20, r9, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x05fe, code lost:
        r9 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r15) + com.google.android.gms.internal.clearcut.zzbn.zzt(r4)) + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x06c4, code lost:
        if ((r12 & r18) != 0) goto L266;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x06c6, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.g(r15, (com.google.android.gms.internal.clearcut.zzdo) r2.getObject(r20, r10), A(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x06f1, code lost:
        if ((r12 & r18) != 0) goto L278;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x06f3, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzh(r15, 0L);
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x06fc, code lost:
        if ((r12 & r18) != 0) goto L281;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x06fe, code lost:
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r15, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x0721, code lost:
        if ((r12 & r18) != 0) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x0723, code lost:
        r4 = r2.getObject(r20, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0727, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, (com.google.android.gms.internal.clearcut.zzbb) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x0730, code lost:
        if ((r12 & r18) != 0) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x0732, code lost:
        r4 = com.google.android.gms.internal.clearcut.t1.n(r15, r2.getObject(r20, r10), A(r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x074a, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L294;
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x074d, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzb(r15, (java.lang.String) r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0757, code lost:
        if ((r12 & r18) != 0) goto L305;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x0759, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzbn.zzc(r15, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0127, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0139, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x014b, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x015d, code lost:
        if (r19.i != false) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x016f, code lost:
        if (r19.i != false) goto L104;
     */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d(T r20) {
        /*
            Method dump skipped, instructions count: 2306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.d(java.lang.Object):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.clearcut.r1
    public final boolean f(T t) {
        int i;
        int i2;
        boolean z;
        int[] iArr = this.j;
        if (iArr != null && iArr.length != 0) {
            int i3 = -1;
            int length = iArr.length;
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5 = i + 1) {
                int i6 = iArr[i5];
                int F = F(i6);
                int D = D(F);
                if (this.h) {
                    i = i5;
                    i2 = 0;
                } else {
                    int i7 = this.f8580a[F + 2];
                    int i8 = i7 & ErrorCode.ERR_UNKNOWN;
                    i2 = 1 << (i7 >>> 20);
                    if (i8 != i3) {
                        i = i5;
                        i4 = r.getInt(t, i8);
                        i3 = i8;
                    } else {
                        i = i5;
                    }
                }
                if (((268435456 & D) != 0) && !y(t, F, i4, i2)) {
                    return false;
                }
                int i9 = (267386880 & D) >>> 20;
                if (i9 != 9 && i9 != 17) {
                    if (i9 != 27) {
                        if (i9 == 60 || i9 == 68) {
                            if (x(t, i6, F) && !z(t, D, A(F))) {
                                return false;
                            }
                        } else if (i9 != 49) {
                            if (i9 == 50 && !this.q.e(n2.M(t, D & ErrorCode.ERR_UNKNOWN)).isEmpty()) {
                                this.q.zzl(B(F));
                                throw null;
                            }
                        }
                    }
                    List list = (List) n2.M(t, D & ErrorCode.ERR_UNKNOWN);
                    if (!list.isEmpty()) {
                        r1 A = A(F);
                        for (int i10 = 0; i10 < list.size(); i10++) {
                            if (!A.f(list.get(i10))) {
                                z = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    if (!z) {
                        return false;
                    }
                } else if (y(t, F, i4, i2) && !z(t, D, A(F))) {
                    return false;
                }
            }
            if (this.g && !this.p.b(t).d()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0976  */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(T r14, com.google.android.gms.internal.clearcut.z2 r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2736
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.g(java.lang.Object, com.google.android.gms.internal.clearcut.z2):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0164, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0188, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a1, code lost:
        if (r0 == r15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01a3, code lost:
        r2 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v25, types: [int] */
    @Override // com.google.android.gms.internal.clearcut.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(T r23, byte[] r24, int r25, int r26, com.google.android.gms.internal.clearcut.o r27) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.h(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.o):void");
    }

    public final int m(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, o oVar) throws IOException {
        Object valueOf;
        Object valueOf2;
        int g;
        long j2;
        int i9;
        Object valueOf3;
        int i10;
        Unsafe unsafe = r;
        long j3 = this.f8580a[i8 + 2] & ErrorCode.ERR_UNKNOWN;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    valueOf = Double.valueOf(n.l(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    g = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    valueOf2 = Float.valueOf(n.n(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    g = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    g = n.g(bArr, i, oVar);
                    j2 = oVar.b;
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    g = n.e(bArr, i, oVar);
                    i9 = oVar.f8592a;
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    valueOf = Long.valueOf(n.k(bArr, i));
                    unsafe.putObject(t, j, valueOf);
                    g = i + 8;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    valueOf2 = Integer.valueOf(n.h(bArr, i));
                    unsafe.putObject(t, j, valueOf2);
                    g = i + 4;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    g = n.g(bArr, i, oVar);
                    valueOf3 = Boolean.valueOf(oVar.b != 0);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    g = n.e(bArr, i, oVar);
                    i10 = oVar.f8592a;
                    if (i10 == 0) {
                        valueOf3 = "";
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return g;
                    } else if ((i6 & PKIFailureInfo.duplicateCertReq) == 0 || p2.i(bArr, g, g + i10)) {
                        unsafe.putObject(t, j, new String(bArr, g, i10, zzci.f8618a));
                        g += i10;
                        unsafe.putInt(t, j3, i4);
                        return g;
                    } else {
                        throw zzco.zzbp();
                    }
                }
                return i;
            case 60:
                if (i5 == 2) {
                    g = k(A(i8), bArr, i, i2, oVar);
                    Object object = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = oVar.c;
                    if (object != null) {
                        valueOf3 = zzci.c(object, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    g = n.e(bArr, i, oVar);
                    i10 = oVar.f8592a;
                    if (i10 == 0) {
                        valueOf3 = zzbb.zzfi;
                        unsafe.putObject(t, j, valueOf3);
                        unsafe.putInt(t, j3, i4);
                        return g;
                    }
                    unsafe.putObject(t, j, zzbb.zzb(bArr, g, i10));
                    g += i10;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int e = n.e(bArr, i, oVar);
                    int i11 = oVar.f8592a;
                    zzck<?> C = C(i8);
                    if (C != null && C.zzb(i11) == null) {
                        R(t).d(i3, Long.valueOf(i11));
                        return e;
                    }
                    unsafe.putObject(t, j, Integer.valueOf(i11));
                    g = e;
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    g = n.e(bArr, i, oVar);
                    i9 = zzbk.zzm(oVar.f8592a);
                    valueOf3 = Integer.valueOf(i9);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    g = n.g(bArr, i, oVar);
                    j2 = zzbk.zza(oVar.b);
                    valueOf3 = Long.valueOf(j2);
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    g = j(A(i8), bArr, i, i2, (i3 & (-8)) | 4, oVar);
                    Object object2 = unsafe.getInt(t, j3) == i4 ? unsafe.getObject(t, j) : null;
                    valueOf3 = oVar.c;
                    if (object2 != null) {
                        valueOf3 = zzci.c(object2, valueOf3);
                    }
                    unsafe.putObject(t, j, valueOf3);
                    unsafe.putInt(t, j3, i4);
                    return g;
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x0233, code lost:
        if (r29.b != 0) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0235, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0237, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0238, code lost:
        r12.addBoolean(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x023b, code lost:
        if (r4 >= r19) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x023d, code lost:
        r6 = com.google.android.gms.internal.clearcut.n.e(r17, r4, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x0243, code lost:
        if (r20 != r29.f8592a) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0245, code lost:
        r4 = com.google.android.gms.internal.clearcut.n.g(r17, r6, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x024d, code lost:
        if (r29.b == 0) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:?, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0137, code lost:
        if (r4 == 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0139, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzfi);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x013f, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzb(r17, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0147, code lost:
        if (r1 >= r19) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0149, code lost:
        r4 = com.google.android.gms.internal.clearcut.n.e(r17, r1, r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x014f, code lost:
        if (r20 != r29.f8592a) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0151, code lost:
        r1 = com.google.android.gms.internal.clearcut.n.e(r17, r4, r29);
        r4 = r29.f8592a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0157, code lost:
        if (r4 != 0) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:245:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:247:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:100:0x01e2 -> B:91:0x01bb). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:126:0x0237 -> B:127:0x0238). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:132:0x024d -> B:125:0x0235). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x013f -> B:66:0x0147). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0157 -> B:64:0x0139). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:81:0x0194 -> B:82:0x0198). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x01a8 -> B:79:0x0189). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x01ce -> B:96:0x01d2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int n(T r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.clearcut.o r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 994
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.n(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.clearcut.o):int");
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final T newInstance() {
        return (T) this.m.a(this.f);
    }

    public final <K, V> int o(T t, byte[] bArr, int i, int i2, int i3, int i4, long j, o oVar) throws IOException {
        Unsafe unsafe = r;
        Object B = B(i3);
        Object object = unsafe.getObject(t, j);
        if (this.q.d(object)) {
            Object zzk = this.q.zzk(B);
            this.q.zzb(zzk, object);
            unsafe.putObject(t, j, zzk);
            object = zzk;
        }
        this.q.zzl(B);
        this.q.f(object);
        int e = n.e(bArr, i, oVar);
        int i5 = oVar.f8592a;
        if (i5 < 0 || i5 > i2 - e) {
            throw zzco.zzbl();
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0372 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int p(T r27, byte[] r28, int r29, int r30, int r31, com.google.android.gms.internal.clearcut.o r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1072
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.h1.p(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.clearcut.o):int");
    }

    public final <K, V, UT, UB> UB r(int i, int i2, Map<K, V> map, zzck<?> zzckVar, UB ub, i2<UT, UB> i2Var) {
        a1<?, ?> zzl = this.q.zzl(B(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (zzckVar.zzb(((Integer) next.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = i2Var.f();
                }
                u zzk = zzbb.zzk(zzdg.a(zzl, next.getKey(), next.getValue()));
                try {
                    zzdg.b(zzk.b(), zzl, next.getKey(), next.getValue());
                    i2Var.b(ub, i2, zzk.a());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final <K, V> void u(z2 z2Var, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            z2Var.n(i, this.q.zzl(B(i2)), this.q.e(obj));
        }
    }

    public final void v(T t, T t2, int i) {
        long D = D(i) & ErrorCode.ERR_UNKNOWN;
        if (w(t2, i)) {
            Object M = n2.M(t, D);
            Object M2 = n2.M(t2, D);
            if (M != null && M2 != null) {
                n2.i(t, D, zzci.c(M, M2));
                G(t, i);
            } else if (M2 != null) {
                n2.i(t, D, M2);
                G(t, i);
            }
        }
    }

    public final boolean w(T t, int i) {
        if (!this.h) {
            int E = E(i);
            return (n2.H(t, (long) (E & ErrorCode.ERR_UNKNOWN)) & (1 << (E >>> 20))) != 0;
        }
        int D = D(i);
        long j = D & ErrorCode.ERR_UNKNOWN;
        switch ((D & 267386880) >>> 20) {
            case 0:
                return n2.L(t, j) != 0.0d;
            case 1:
                return n2.K(t, j) != 0.0f;
            case 2:
                return n2.I(t, j) != 0;
            case 3:
                return n2.I(t, j) != 0;
            case 4:
                return n2.H(t, j) != 0;
            case 5:
                return n2.I(t, j) != 0;
            case 6:
                return n2.H(t, j) != 0;
            case 7:
                return n2.J(t, j);
            case 8:
                Object M = n2.M(t, j);
                if (M instanceof String) {
                    return !((String) M).isEmpty();
                } else if (M instanceof zzbb) {
                    return !zzbb.zzfi.equals(M);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return n2.M(t, j) != null;
            case 10:
                return !zzbb.zzfi.equals(n2.M(t, j));
            case 11:
                return n2.H(t, j) != 0;
            case 12:
                return n2.H(t, j) != 0;
            case 13:
                return n2.H(t, j) != 0;
            case 14:
                return n2.I(t, j) != 0;
            case 15:
                return n2.H(t, j) != 0;
            case 16:
                return n2.I(t, j) != 0;
            case 17:
                return n2.M(t, j) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean x(T t, int i, int i2) {
        return n2.H(t, (long) (E(i2) & ErrorCode.ERR_UNKNOWN)) == i;
    }

    public final boolean y(T t, int i, int i2, int i3) {
        return this.h ? w(t, i) : (i2 & i3) != 0;
    }

    @Override // com.google.android.gms.internal.clearcut.r1
    public final void zzc(T t) {
        int[] iArr = this.k;
        if (iArr != null) {
            for (int i : iArr) {
                long D = D(i) & ErrorCode.ERR_UNKNOWN;
                Object M = n2.M(t, D);
                if (M != null) {
                    n2.i(t, D, this.q.b(M));
                }
            }
        }
        int[] iArr2 = this.l;
        if (iArr2 != null) {
            for (int i2 : iArr2) {
                this.n.a(t, i2);
            }
        }
        this.o.d(t);
        if (this.g) {
            this.p.f(t);
        }
    }
}
