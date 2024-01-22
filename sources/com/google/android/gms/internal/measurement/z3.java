package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class z3 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8938a;
    public static final l4<?, ?> b;
    public static final l4<?, ?> c;
    public static final l4<?, ?> d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        f8938a = cls;
        b = C(false);
        c = C(true);
        d = new m4();
    }

    public static int A(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjg.zzA(i << 3) + 1);
    }

    public static void B(int i, List<Long> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.k(i, list, z);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:5:0x0004
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static com.google.android.gms.internal.measurement.l4<?, ?> C(boolean r6) {
        /*
            r0 = 0
            java.lang.Class<com.google.protobuf.z0> r1 = com.google.protobuf.z0.class
            goto L5
        L4:
            r1 = r0
        L5:
            if (r1 != 0) goto L8
            return r0
        L8:
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch: java.lang.Throwable -> L23
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L23
            java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.Throwable -> L23
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L23
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Throwable -> L23
            r2[r5] = r6     // Catch: java.lang.Throwable -> L23
            java.lang.Object r6 = r1.newInstance(r2)     // Catch: java.lang.Throwable -> L23
            com.google.android.gms.internal.measurement.l4 r6 = (com.google.android.gms.internal.measurement.l4) r6     // Catch: java.lang.Throwable -> L23
            return r6
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.z3.C(boolean):com.google.android.gms.internal.measurement.l4");
    }

    public static int D(List<?> list) {
        return list.size();
    }

    public static int E(int i, List<zziy> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzjg.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzz += zzjg.zzt(list.get(i2));
        }
        return zzz;
    }

    public static int F(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return G(list) + (size * zzjg.zzz(i));
    }

    public static int G(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof c3) {
            c3 c3Var = (c3) list;
            i = 0;
            while (i2 < size) {
                i += zzjg.zzv(c3Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjg.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int H(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjg.zzA(i << 3) + 4);
    }

    public static int I(List<?> list) {
        return list.size() * 4;
    }

    public static int J(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjg.zzA(i << 3) + 8);
    }

    public static int K(List<?> list) {
        return list.size() * 8;
    }

    public static int L(int i, List<zzlg> list, x3 x3Var) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzjg.c(i, list.get(i3), x3Var);
            }
            return i2;
        }
        return 0;
    }

    public static int M(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return N(list) + (size * zzjg.zzz(i));
    }

    public static int N(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof c3) {
            c3 c3Var = (c3) list;
            i = 0;
            while (i2 < size) {
                i += zzjg.zzv(c3Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjg.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int O(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return P(list) + (list.size() * zzjg.zzz(i));
    }

    public static int P(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g3) {
            g3 g3Var = (g3) list;
            i = 0;
            while (i2 < size) {
                i += zzjg.zzB(g3Var.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjg.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int Q(int i, Object obj, x3 x3Var) {
        if (obj instanceof zzkm) {
            int zzA = zzjg.zzA(i << 3);
            int zza = ((zzkm) obj).zza();
            return zzA + zzjg.zzA(zza) + zza;
        }
        return zzjg.zzA(i << 3) + zzjg.d((zzlg) obj, x3Var);
    }

    public static int R(int i, List<?> list, x3 x3Var) {
        int d2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzjg.zzz(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzkm) {
                d2 = zzjg.zzw((zzkm) obj);
            } else {
                d2 = zzjg.d((zzlg) obj, x3Var);
            }
            zzz += d2;
        }
        return zzz;
    }

    public static int S(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return T(list) + (size * zzjg.zzz(i));
    }

    public static int T(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof c3) {
            c3 c3Var = (c3) list;
            i = 0;
            while (i2 < size) {
                int a2 = c3Var.a(i2);
                i += zzjg.zzA((a2 >> 31) ^ (a2 + a2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = list.get(i2).intValue();
                i += zzjg.zzA((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    public static int U(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return V(list) + (size * zzjg.zzz(i));
    }

    public static int V(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g3) {
            g3 g3Var = (g3) list;
            i = 0;
            while (i2 < size) {
                long zza = g3Var.zza(i2);
                i += zzjg.zzB((zza >> 63) ^ (zza + zza));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = list.get(i2).longValue();
                i += zzjg.zzB((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    public static int W(int i, List<?> list) {
        int zzy;
        int zzy2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzjg.zzz(i) * size;
        if (list instanceof zzko) {
            zzko zzkoVar = (zzko) list;
            while (i2 < size) {
                Object zzf = zzkoVar.zzf(i2);
                if (zzf instanceof zziy) {
                    zzy2 = zzjg.zzt((zziy) zzf);
                } else {
                    zzy2 = zzjg.zzy((String) zzf);
                }
                zzz += zzy2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zziy) {
                    zzy = zzjg.zzt((zziy) obj);
                } else {
                    zzy = zzjg.zzy((String) obj);
                }
                zzz += zzy;
                i2++;
            }
        }
        return zzz;
    }

    public static int X(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return Y(list) + (size * zzjg.zzz(i));
    }

    public static int Y(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof c3) {
            c3 c3Var = (c3) list;
            i = 0;
            while (i2 < size) {
                i += zzjg.zzA(c3Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjg.zzA(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int Z(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return a0(list) + (size * zzjg.zzz(i));
    }

    public static l4<?, ?> a() {
        return c;
    }

    public static int a0(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof g3) {
            g3 g3Var = (g3) list;
            i = 0;
            while (i2 < size) {
                i += zzjg.zzB(g3Var.zza(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzjg.zzB(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static l4<?, ?> b() {
        return d;
    }

    public static l4<?, ?> b0() {
        return b;
    }

    public static <UT, UB> UB c(int i, List<Integer> list, zzkd zzkdVar, UB ub, l4<UT, UB> l4Var) {
        if (zzkdVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzkdVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) d(i, intValue, ub, l4Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub;
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzkdVar.zza(intValue2)) {
                    ub = (UB) d(i, intValue2, ub, l4Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static <UT, UB> UB d(int i, int i2, UB ub, l4<UT, UB> l4Var) {
        if (ub == null) {
            ub = l4Var.e();
        }
        l4Var.f(ub, i, i2);
        return ub;
    }

    public static <T, FT extends zzjp<FT>> void e(v2<FT> v2Var, T t, T t2) {
        v2Var.a(t2);
        throw null;
    }

    public static <T, UT, UB> void f(l4<UT, UB> l4Var, T t, T t2) {
        l4Var.h(t, l4Var.d(l4Var.c(t), l4Var.c(t2)));
    }

    public static void g(Class<?> cls) {
        Class<?> cls2;
        if (!zzjz.class.isAssignableFrom(cls) && (cls2 = f8938a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean h(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> void i(k3 k3Var, T t, T t2, long j) {
        t4.x(t, j, k3.b(t4.k(t, j), t4.k(t2, j)));
    }

    public static void j(int i, List<Boolean> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.n(i, list, z);
    }

    public static void k(int i, List<zziy> list, s2 s2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.p(i, list);
    }

    public static void l(int i, List<Double> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.r(i, list, z);
    }

    public static void m(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.u(i, list, z);
    }

    public static void n(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.w(i, list, z);
    }

    public static void o(int i, List<Long> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.y(i, list, z);
    }

    public static void p(int i, List<Float> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.A(i, list, z);
    }

    public static void q(int i, List<?> list, s2 s2Var, x3 x3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            s2Var.B(i, list.get(i2), x3Var);
        }
    }

    public static void r(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.D(i, list, z);
    }

    public static void s(int i, List<Long> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.F(i, list, z);
    }

    public static void t(int i, List<?> list, s2 s2Var, x3 x3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            s2Var.G(i, list.get(i2), x3Var);
        }
    }

    public static void u(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.I(i, list, z);
    }

    public static void v(int i, List<Long> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.K(i, list, z);
    }

    public static void w(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.b(i, list, z);
    }

    public static void x(int i, List<Long> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.d(i, list, z);
    }

    public static void y(int i, List<String> list, s2 s2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.g(i, list);
    }

    public static void z(int i, List<Integer> list, s2 s2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        s2Var.i(i, list, z);
    }
}
