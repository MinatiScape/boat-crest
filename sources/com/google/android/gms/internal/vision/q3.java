package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class q3 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f9996a = C();
    public static final g4<?, ?> b = J(false);
    public static final g4<?, ?> c = J(true);
    public static final g4<?, ?> d = new h4();

    public static g4<?, ?> A() {
        return c;
    }

    public static g4<?, ?> B() {
        return d;
    }

    public static Class<?> C() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<?> D() {
        return com.google.protobuf.z0.class;
    }

    public static void E(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzj(i, list, z);
    }

    public static void F(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzm(i, list, z);
    }

    public static void G(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzb(i, list, z);
    }

    public static void H(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzk(i, list, z);
    }

    public static void I(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzh(i, list, z);
    }

    public static g4<?, ?> J(boolean z) {
        try {
            Class<?> D = D();
            if (D == null) {
                return null;
            }
            return (g4) D.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void K(int i, List<Boolean> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzi(i, list, z);
    }

    public static int L(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return O(list) + (list.size() * zzga.zzbb(i));
    }

    public static int M(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return Q(list) + (size * zzga.zzbb(i));
    }

    public static int N(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return S(list) + (size * zzga.zzbb(i));
    }

    public static int O(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzv(v2Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzv(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int P(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return U(list) + (size * zzga.zzbb(i));
    }

    public static int Q(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzw(v2Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzw(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int R(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return W(list) + (size * zzga.zzbb(i));
    }

    public static int S(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzx(v2Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzx(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int T(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return Y(list) + (size * zzga.zzbb(i));
    }

    public static int U(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbh(m2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzbh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int V(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return a0(list) + (size * zzga.zzbb(i));
    }

    public static int W(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbc(m2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzbc(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int X(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzn(i, 0);
    }

    public static int Y(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbd(m2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzbd(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int Z(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzg(i, 0L);
    }

    public static <UT, UB> UB a(int i, int i2, UB ub, g4<UT, UB> g4Var) {
        if (ub == null) {
            ub = g4Var.n();
        }
        g4Var.a(ub, i, i2);
        return ub;
    }

    public static int a0(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            i = 0;
            while (i2 < size) {
                i += zzga.zzbe(m2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzga.zzbe(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static <UT, UB> UB b(int i, List<Integer> list, zzgv<?> zzgvVar, UB ub, g4<UT, UB> g4Var) {
        if (zzgvVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzgvVar.zzh(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) a(i, intValue, ub, g4Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzgvVar.zzh(intValue2) == null) {
                    ub = (UB) a(i, intValue2, ub, g4Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int b0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzb(i, true);
    }

    public static <UT, UB> UB c(int i, List<Integer> list, zzgy zzgyVar, UB ub, g4<UT, UB> g4Var) {
        if (zzgyVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzgyVar.zzg(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) a(i, intValue, ub, g4Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzgyVar.zzg(intValue2)) {
                    ub = (UB) a(i, intValue2, ub, g4Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int c0(List<?> list) {
        return list.size() << 2;
    }

    public static void d(int i, List<String> list, x4 x4Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zza(i, list);
    }

    public static int d0(List<?> list) {
        return list.size() << 3;
    }

    public static void e(int i, List<?> list, x4 x4Var, o3 o3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.l(i, list, o3Var);
    }

    public static int e0(List<?> list) {
        return list.size();
    }

    public static void f(int i, List<Double> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzg(i, list, z);
    }

    public static <T, FT extends zzgk<FT>> void g(b2<FT> b2Var, T t, T t2) {
        e2<FT> h = b2Var.h(t2);
        if (h.f9969a.isEmpty()) {
            return;
        }
        b2Var.i(t).g(h);
    }

    public static <T> void h(z2 z2Var, T t, T t2, long j) {
        j4.f(t, j, z2Var.c(j4.G(t, j), j4.G(t2, j)));
    }

    public static <T, UT, UB> void i(g4<UT, UB> g4Var, T t, T t2) {
        g4Var.j(t, g4Var.l(g4Var.q(t), g4Var.q(t2)));
    }

    public static void j(int i, List<zzfh> list, x4 x4Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzb(i, list);
    }

    public static void k(int i, List<?> list, x4 x4Var, o3 o3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.i(i, list, o3Var);
    }

    public static void l(int i, List<Float> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzf(i, list, z);
    }

    public static int m(int i, Object obj, o3 o3Var) {
        if (obj instanceof zzhh) {
            return zzga.zza(i, (zzhh) obj);
        }
        return zzga.e(i, (zzic) obj, o3Var);
    }

    public static int n(int i, List<?> list) {
        int zzy;
        int zzy2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzbb = zzga.zzbb(i) * size;
        if (list instanceof zzhj) {
            zzhj zzhjVar = (zzhj) list;
            while (i2 < size) {
                Object raw = zzhjVar.getRaw(i2);
                if (raw instanceof zzfh) {
                    zzy2 = zzga.zzb((zzfh) raw);
                } else {
                    zzy2 = zzga.zzy((String) raw);
                }
                zzbb += zzy2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzfh) {
                    zzy = zzga.zzb((zzfh) obj);
                } else {
                    zzy = zzga.zzy((String) obj);
                }
                zzbb += zzy;
                i2++;
            }
        }
        return zzbb;
    }

    public static int o(int i, List<?> list, o3 o3Var) {
        int a2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = zzga.zzbb(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhh) {
                a2 = zzga.zza((zzhh) obj);
            } else {
                a2 = zzga.a((zzic) obj, o3Var);
            }
            zzbb += a2;
        }
        return zzbb;
    }

    public static void p(int i, List<Long> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzc(i, list, z);
    }

    public static int q(int i, List<zzfh> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbb = size * zzga.zzbb(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzbb += zzga.zzb(list.get(i2));
        }
        return zzbb;
    }

    public static int r(int i, List<zzic> list, o3 o3Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzga.g(i, list.get(i3), o3Var);
        }
        return i2;
    }

    public static void s(int i, List<Long> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzd(i, list, z);
    }

    public static void t(int i, List<Long> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzn(i, list, z);
    }

    public static boolean u(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static void v(int i, List<Long> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zze(i, list, z);
    }

    public static void w(int i, List<Long> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zzl(i, list, z);
    }

    public static void x(Class<?> cls) {
        Class<?> cls2;
        if (!zzgs.class.isAssignableFrom(cls) && (cls2 = f9996a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void y(int i, List<Integer> list, x4 x4Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        x4Var.zza(i, list, z);
    }

    public static g4<?, ?> z() {
        return b;
    }
}
