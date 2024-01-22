package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class e8 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8677a = b0();
    public static final u8<?, ?> b = l(false);
    public static final u8<?, ?> c = l(true);
    public static final u8<?, ?> d = new v8();

    public static int A(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d7) {
            d7 d7Var = (d7) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzaa(d7Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzaa(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void B(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzm(i, list, z);
    }

    public static int C(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d7) {
            d7 d7Var = (d7) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzab(d7Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzab(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void D(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzb(i, list, z);
    }

    public static int E(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w6) {
            w6 w6Var = (w6) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdl(w6Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdl(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void F(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzk(i, list, z);
    }

    public static int G(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w6) {
            w6 w6Var = (w6) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdg(w6Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdg(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void H(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzh(i, list, z);
    }

    public static void I(Class<?> cls) {
        Class<?> cls2;
        if (!zzwz.class.isAssignableFrom(cls) && (cls2 = f8677a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static int J(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w6) {
            w6 w6Var = (w6) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdh(w6Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void K(int i, List<Boolean> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzi(i, list, z);
    }

    public static int L(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return y(list) + (list.size() * zzwi.zzdf(i));
    }

    public static int M(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w6) {
            w6 w6Var = (w6) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdi(w6Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzdi(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int N(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return A(list) + (size * zzwi.zzdf(i));
    }

    public static int O(List<?> list) {
        return list.size() << 2;
    }

    public static int P(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C(list) + (size * zzwi.zzdf(i));
    }

    public static int Q(List<?> list) {
        return list.size() << 3;
    }

    public static int R(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return E(list) + (size * zzwi.zzdf(i));
    }

    public static int S(List<?> list) {
        return list.size();
    }

    public static int T(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return G(list) + (size * zzwi.zzdf(i));
    }

    public static int U(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return J(list) + (size * zzwi.zzdf(i));
    }

    public static int V(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return M(list) + (size * zzwi.zzdf(i));
    }

    public static int W(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzwi.zzo(i, 0);
    }

    public static int X(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzwi.zzg(i, 0L);
    }

    public static u8<?, ?> Y() {
        return b;
    }

    public static u8<?, ?> Z() {
        return c;
    }

    public static <UT, UB> UB a(int i, int i2, UB ub, u8<UT, UB> u8Var) {
        if (ub == null) {
            ub = u8Var.l();
        }
        u8Var.a(ub, i, i2);
        return ub;
    }

    public static u8<?, ?> a0() {
        return d;
    }

    public static <UT, UB> UB b(int i, List<Integer> list, zzxe zzxeVar, UB ub, u8<UT, UB> u8Var) {
        if (zzxeVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzxeVar.zzb(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) a(i, intValue, ub, u8Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzxeVar.zzb(intValue2)) {
                    ub = (UB) a(i, intValue2, ub, u8Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static Class<?> b0() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void c(int i, List<String> list, p pVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zza(i, list);
    }

    public static Class<?> c0() {
        return com.google.protobuf.z0.class;
    }

    public static void d(int i, List<?> list, p pVar, c8 c8Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.j(i, list, c8Var);
    }

    public static int d0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzwi.zzb(i, true);
    }

    public static void e(int i, List<Double> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzg(i, list, z);
    }

    public static <T, FT extends zzwt<FT>> void f(o6<FT> o6Var, T t, T t2) {
        p6<FT> e = o6Var.e(t2);
        if (e.f8717a.isEmpty()) {
            return;
        }
        o6Var.f(t).i(e);
    }

    public static <T> void g(k7 k7Var, T t, T t2, long j) {
        b.g(t, j, k7Var.c(b.z(t, j), b.z(t2, j)));
    }

    public static <T, UT, UB> void h(u8<UT, UB> u8Var, T t, T t2) {
        u8Var.h(t, u8Var.j(u8Var.e(t), u8Var.e(t2)));
    }

    public static void i(int i, List<zzvv> list, p pVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzb(i, list);
    }

    public static void j(int i, List<?> list, p pVar, c8 c8Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.g(i, list, c8Var);
    }

    public static void k(int i, List<Float> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzf(i, list, z);
    }

    public static u8<?, ?> l(boolean z) {
        try {
            Class<?> c0 = c0();
            if (c0 == null) {
                return null;
            }
            return (u8) c0.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int m(int i, Object obj, c8 c8Var) {
        if (obj instanceof zzxt) {
            return zzwi.zza(i, (zzxt) obj);
        }
        return zzwi.e(i, (zzyk) obj, c8Var);
    }

    public static int n(int i, List<?> list) {
        int zzcl;
        int zzcl2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzdf = zzwi.zzdf(i) * size;
        if (list instanceof zzxv) {
            zzxv zzxvVar = (zzxv) list;
            while (i2 < size) {
                Object raw = zzxvVar.getRaw(i2);
                if (raw instanceof zzvv) {
                    zzcl2 = zzwi.zzd((zzvv) raw);
                } else {
                    zzcl2 = zzwi.zzcl((String) raw);
                }
                zzdf += zzcl2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzvv) {
                    zzcl = zzwi.zzd((zzvv) obj);
                } else {
                    zzcl = zzwi.zzcl((String) obj);
                }
                zzdf += zzcl;
                i2++;
            }
        }
        return zzdf;
    }

    public static int o(int i, List<?> list, c8 c8Var) {
        int a2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzdf = zzwi.zzdf(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzxt) {
                a2 = zzwi.zza((zzxt) obj);
            } else {
                a2 = zzwi.a((zzyk) obj, c8Var);
            }
            zzdf += a2;
        }
        return zzdf;
    }

    public static void p(int i, List<Long> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzc(i, list, z);
    }

    public static int q(int i, List<zzvv> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzdf = size * zzwi.zzdf(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzdf += zzwi.zzd(list.get(i2));
        }
        return zzdf;
    }

    public static int r(int i, List<zzyk> list, c8 c8Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzwi.f(i, list.get(i3), c8Var);
        }
        return i2;
    }

    public static void s(int i, List<Long> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzd(i, list, z);
    }

    public static void t(int i, List<Long> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzn(i, list, z);
    }

    public static void u(int i, List<Long> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zze(i, list, z);
    }

    public static boolean v(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static void w(int i, List<Long> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzl(i, list, z);
    }

    public static void x(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zza(i, list, z);
    }

    public static int y(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof d7) {
            d7 d7Var = (d7) list;
            i = 0;
            while (i2 < size) {
                i += zzwi.zzz(d7Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzwi.zzz(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void z(int i, List<Integer> list, p pVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        pVar.zzj(i, list, z);
    }
}
