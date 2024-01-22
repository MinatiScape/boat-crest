package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class t1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8599a = C();
    public static final i2<?, ?> b = w(false);
    public static final i2<?, ?> c = w(true);
    public static final i2<?, ?> d = new j2();

    public static i2<?, ?> A() {
        return c;
    }

    public static i2<?, ?> B() {
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

    public static int E(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof o0) {
            o0 o0Var = (o0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzs(o0Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzs(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void F(int i, List<Long> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzn(i, list, z);
    }

    public static int G(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof o0) {
            o0 o0Var = (o0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzt(o0Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void H(int i, List<Long> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zze(i, list, z);
    }

    public static void I(Class<?> cls) {
        Class<?> cls2;
        if (!zzcg.class.isAssignableFrom(cls) && (cls2 = f8599a) != null && !cls2.isAssignableFrom(cls)) {
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
        if (list instanceof o0) {
            o0 o0Var = (o0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzu(o0Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void K(int i, List<Long> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzl(i, list, z);
    }

    public static int L(List<?> list) {
        return list.size() << 2;
    }

    public static void M(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zza(i, list, z);
    }

    public static int N(List<?> list) {
        return list.size() << 3;
    }

    public static void O(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzj(i, list, z);
    }

    public static int P(List<?> list) {
        return list.size();
    }

    public static void Q(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzm(i, list, z);
    }

    public static void R(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzb(i, list, z);
    }

    public static void S(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzk(i, list, z);
    }

    public static void T(int i, List<Integer> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzh(i, list, z);
    }

    public static void U(int i, List<Boolean> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzi(i, list, z);
    }

    public static int V(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzbn.zzr(i));
    }

    public static int W(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return j(list) + (size * zzbn.zzr(i));
    }

    public static int X(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return q(list) + (size * zzbn.zzr(i));
    }

    public static int Y(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return v(list) + (size * zzbn.zzr(i));
    }

    public static int Z(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return E(list) + (size * zzbn.zzr(i));
    }

    public static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w0) {
            w0 w0Var = (w0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zze(w0Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int a0(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return G(list) + (size * zzbn.zzr(i));
    }

    public static <UT, UB> UB b(int i, int i2, UB ub, i2<UT, UB> i2Var) {
        if (ub == null) {
            ub = i2Var.f();
        }
        i2Var.a(ub, i, i2);
        return ub;
    }

    public static int b0(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return J(list) + (size * zzbn.zzr(i));
    }

    public static <UT, UB> UB c(int i, List<Integer> list, zzck<?> zzckVar, UB ub, i2<UT, UB> i2Var) {
        if (zzckVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzckVar.zzb(intValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) b(i, intValue, ub, i2Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzckVar.zzb(intValue2) == null) {
                    ub = (UB) b(i, intValue2, ub, i2Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static int c0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzj(i, 0);
    }

    public static void d(int i, List<String> list, z2 z2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zza(i, list);
    }

    public static int d0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzg(i, 0L);
    }

    public static void e(int i, List<?> list, z2 z2Var, r1 r1Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.h(i, list, r1Var);
    }

    public static int e0(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzc(i, true);
    }

    public static void f(int i, List<Double> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzg(i, list, z);
    }

    public static <T, FT extends zzca<FT>> void g(e0<FT> e0Var, T t, T t2) {
        i0<FT> b2 = e0Var.b(t2);
        if (b2.b()) {
            return;
        }
        e0Var.e(t).h(b2);
    }

    public static <T> void h(b1 b1Var, T t, T t2, long j) {
        n2.i(t, j, b1Var.zzb(n2.M(t, j), n2.M(t2, j)));
    }

    public static <T, UT, UB> void i(i2<UT, UB> i2Var, T t, T t2) {
        i2Var.g(t, i2Var.i(i2Var.k(t), i2Var.k(t2)));
    }

    public static int j(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w0) {
            w0 w0Var = (w0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzf(w0Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void k(int i, List<zzbb> list, z2 z2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzb(i, list);
    }

    public static void l(int i, List<?> list, z2 z2Var, r1 r1Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.g(i, list, r1Var);
    }

    public static void m(int i, List<Float> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzf(i, list, z);
    }

    public static int n(int i, Object obj, r1 r1Var) {
        return obj instanceof zzcv ? zzbn.zza(i, (zzcv) obj) : zzbn.e(i, (zzdo) obj, r1Var);
    }

    public static int o(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        if (list instanceof zzcx) {
            zzcx zzcxVar = (zzcx) list;
            while (i2 < size) {
                Object raw = zzcxVar.getRaw(i2);
                zzr += raw instanceof zzbb ? zzbn.zzb((zzbb) raw) : zzbn.zzh((String) raw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzr += obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj);
                i2++;
            }
        }
        return zzr;
    }

    public static int p(int i, List<?> list, r1 r1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzr += obj instanceof zzcv ? zzbn.zza((zzcv) obj) : zzbn.f((zzdo) obj, r1Var);
        }
        return zzr;
    }

    public static int q(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof w0) {
            w0 w0Var = (w0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzg(w0Var.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static void r(int i, List<Long> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzc(i, list, z);
    }

    public static boolean s(int i, int i2, int i3) {
        if (i2 < 40) {
            return true;
        }
        long j = i3;
        return ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * j) + 3) + ((j + 3) * 3);
    }

    public static int t(int i, List<zzbb> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzbn.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzbn.zzb(list.get(i2));
        }
        return zzr;
    }

    public static int u(int i, List<zzdo> list, r1 r1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbn.g(i, list.get(i3), r1Var);
        }
        return i2;
    }

    public static int v(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof o0) {
            o0 o0Var = (o0) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzx(o0Var.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzbn.zzx(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static i2<?, ?> w(boolean z) {
        try {
            Class<?> D = D();
            if (D == null) {
                return null;
            }
            return (i2) D.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void x(int i, List<Long> list, z2 z2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        z2Var.zzd(i, list, z);
    }

    public static boolean y(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static i2<?, ?> z() {
        return b;
    }
}
