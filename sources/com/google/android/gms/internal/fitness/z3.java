package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.List;
/* loaded from: classes8.dex */
public final class z3 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8858a = x();
    public static final n4<?, ?> b = s(false);
    public static final n4<?, ?> c = s(true);
    public static final n4<?, ?> d = new o4();

    public static void A(int i, List<Long> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzn(i, list, z);
    }

    public static boolean B(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int C(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof u2) {
            u2 u2Var = (u2) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzs(u2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzs(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void D(int i, List<Long> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zze(i, list, z);
    }

    public static void E(Class<?> cls) {
        Class<?> cls2;
        if (!zzgy.class.isAssignableFrom(cls) && (cls2 = f8858a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static int F(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof u2) {
            u2 u2Var = (u2) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzt(u2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void G(int i, List<Long> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzl(i, list, z);
    }

    public static int H(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof u2) {
            u2 u2Var = (u2) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzu(u2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void I(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zza(i, list, z);
    }

    public static int J(List<?> list) {
        return list.size() << 2;
    }

    public static void K(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzj(i, list, z);
    }

    public static int L(List<?> list) {
        return list.size() << 3;
    }

    public static void M(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzm(i, list, z);
    }

    public static int N(List<?> list) {
        return list.size();
    }

    public static void O(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzb(i, list, z);
    }

    public static void P(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzk(i, list, z);
    }

    public static void Q(int i, List<Integer> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzh(i, list, z);
    }

    public static void R(int i, List<Boolean> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzi(i, list, z);
    }

    public static int S(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return g(list) + (list.size() * zzgk.zzr(i));
    }

    public static int T(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return n(list) + (size * zzgk.zzr(i));
    }

    public static int U(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return r(list) + (size * zzgk.zzr(i));
    }

    public static int V(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z(list) + (size * zzgk.zzr(i));
    }

    public static int W(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C(list) + (size * zzgk.zzr(i));
    }

    public static int X(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return F(list) + (size * zzgk.zzr(i));
    }

    public static int Y(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return H(list) + (size * zzgk.zzr(i));
    }

    public static int Z(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzl(i, 0);
    }

    public static void a(int i, List<String> list, d5 d5Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zza(i, list);
    }

    public static int a0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzg(i, 0L);
    }

    public static void b(int i, List<?> list, d5 d5Var, x3 x3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.g(i, list, x3Var);
    }

    public static int b0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzgk.zzb(i, true);
    }

    public static void c(int i, List<Double> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzg(i, list, z);
    }

    public static <T, FT extends zzgv<FT>> void d(m2<FT> m2Var, T t, T t2) {
        q2<FT> c2 = m2Var.c(t2);
        if (c2.f8845a.isEmpty()) {
            return;
        }
        m2Var.d(t).g(c2);
    }

    public static <T> void e(h3 h3Var, T t, T t2, long j) {
        s4.g(t, j, h3Var.c(s4.G(t, j), s4.G(t2, j)));
    }

    public static <T, UT, UB> void f(n4<UT, UB> n4Var, T t, T t2) {
        n4Var.d(t, n4Var.e(n4Var.g(t), n4Var.g(t2)));
    }

    public static int g(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d3) {
            d3 d3Var = (d3) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzd(d3Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void h(int i, List<zzfx> list, d5 d5Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzb(i, list);
    }

    public static void i(int i, List<?> list, d5 d5Var, x3 x3Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.n(i, list, x3Var);
    }

    public static void j(int i, List<Float> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzf(i, list, z);
    }

    public static int k(int i, Object obj, x3 x3Var) {
        if (obj instanceof zzhp) {
            return zzgk.zza(i, (zzhp) obj);
        }
        return zzgk.d(i, (zzik) obj, x3Var);
    }

    public static int l(int i, List<?> list) {
        int zzm;
        int zzm2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzgk.zzr(i) * size;
        if (list instanceof zzhr) {
            zzhr zzhrVar = (zzhr) list;
            while (i2 < size) {
                Object zzaf = zzhrVar.zzaf(i2);
                if (zzaf instanceof zzfx) {
                    zzm2 = zzgk.zzb((zzfx) zzaf);
                } else {
                    zzm2 = zzgk.zzm((String) zzaf);
                }
                zzr += zzm2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzfx) {
                    zzm = zzgk.zzb((zzfx) obj);
                } else {
                    zzm = zzgk.zzm((String) obj);
                }
                zzr += zzm;
                i2++;
            }
        }
        return zzr;
    }

    public static int m(int i, List<?> list, x3 x3Var) {
        int a2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzgk.zzr(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzhp) {
                a2 = zzgk.zza((zzhp) obj);
            } else {
                a2 = zzgk.a((zzik) obj, x3Var);
            }
            zzr += a2;
        }
        return zzr;
    }

    public static int n(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d3) {
            d3 d3Var = (d3) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zze(d3Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void o(int i, List<Long> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzc(i, list, z);
    }

    public static int p(int i, List<zzfx> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzgk.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzgk.zzb(list.get(i2));
        }
        return zzr;
    }

    public static int q(int i, List<zzik> list, x3 x3Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzgk.f(i, list.get(i3), x3Var);
        }
        return i2;
    }

    public static int r(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d3) {
            d3 d3Var = (d3) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzf(d3Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static n4<?, ?> s(boolean z) {
        try {
            Class<?> y = y();
            if (y == null) {
                return null;
            }
            return (n4) y.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void t(int i, List<Long> list, d5 d5Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        d5Var.zzd(i, list, z);
    }

    public static n4<?, ?> u() {
        return b;
    }

    public static n4<?, ?> v() {
        return c;
    }

    public static n4<?, ?> w() {
        return d;
    }

    public static Class<?> x() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<?> y() {
        return com.google.protobuf.z0.class;
    }

    public static int z(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof u2) {
            u2 u2Var = (u2) list;
            i = 0;
            while (i2 < size) {
                i += zzgk.zzx(u2Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzgk.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }
}
