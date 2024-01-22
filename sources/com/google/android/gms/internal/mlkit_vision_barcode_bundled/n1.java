package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.List;
/* loaded from: classes8.dex */
public final class n1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f9605a;
    public static final y1 b;
    public static final y1 c;
    public static final y1 d;
    public static final /* synthetic */ int e = 0;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        f9605a = cls;
        b = y(false);
        c = y(true);
        d = new z1();
    }

    public static int A(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = size * zzdj.zzy(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd = ((zzdb) list.get(i2)).zzd();
            zzy += zzdj.zzy(zzd) + zzd;
        }
        return zzy;
    }

    public static int B(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C(list) + (size * zzdj.zzy(i << 3));
    }

    public static int C(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof j0) {
            j0 j0Var = (j0) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(j0Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int D(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdj.zzy(i << 3) + 4);
    }

    public static int E(List list) {
        return list.size() * 4;
    }

    public static int F(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdj.zzy(i << 3) + 8);
    }

    public static int G(List list) {
        return list.size() * 8;
    }

    public static int H(int i, List list, l1 l1Var) {
        int size = list.size();
        if (size != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                i2 += zzdj.c(i, (zzfo) list.get(i3), l1Var);
            }
            return i2;
        }
        return 0;
    }

    public static int I(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return J(list) + (size * zzdj.zzy(i << 3));
    }

    public static int J(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof j0) {
            j0 j0Var = (j0) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(j0Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int K(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return L(list) + (list.size() * zzdj.zzy(i << 3));
    }

    public static int L(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof p0) {
            p0 p0Var = (p0) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(p0Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int M(int i, Object obj, l1 l1Var) {
        if (obj instanceof zzeu) {
            int i2 = zzdj.zzb;
            int zza = ((zzeu) obj).zza();
            return zzdj.zzy(i << 3) + zzdj.zzy(zza) + zza;
        }
        return zzdj.zzy(i << 3) + zzdj.d((zzfo) obj, l1Var);
    }

    public static int N(int i, List list, l1 l1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = zzdj.zzy(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzeu) {
                int zza = ((zzeu) obj).zza();
                zzy += zzdj.zzy(zza) + zza;
            } else {
                zzy += zzdj.d((zzfo) obj, l1Var);
            }
        }
        return zzy;
    }

    public static int O(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return P(list) + (size * zzdj.zzy(i << 3));
    }

    public static int P(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof j0) {
            j0 j0Var = (j0) list;
            i = 0;
            while (i2 < size) {
                int a2 = j0Var.a(i2);
                i += zzdj.zzy((a2 >> 31) ^ (a2 + a2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i += zzdj.zzy((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    public static int Q(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return R(list) + (size * zzdj.zzy(i << 3));
    }

    public static int R(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof p0) {
            p0 p0Var = (p0) list;
            i = 0;
            while (i2 < size) {
                long a2 = p0Var.a(i2);
                i += zzdj.zzz((a2 >> 63) ^ (a2 + a2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i += zzdj.zzz((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    public static int S(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int i3 = zzdj.zzb;
        boolean z = list instanceof zzew;
        int zzy = zzdj.zzy(i << 3) * size;
        if (z) {
            zzew zzewVar = (zzew) list;
            while (i2 < size) {
                Object zzf = zzewVar.zzf(i2);
                if (zzf instanceof zzdb) {
                    int zzd = ((zzdb) zzf).zzd();
                    zzy += zzdj.zzy(zzd) + zzd;
                } else {
                    zzy += zzdj.zzx((String) zzf);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzdb) {
                    int zzd2 = ((zzdb) obj).zzd();
                    zzy += zzdj.zzy(zzd2) + zzd2;
                } else {
                    zzy += zzdj.zzx((String) obj);
                }
                i2++;
            }
        }
        return zzy;
    }

    public static int T(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return U(list) + (size * zzdj.zzy(i << 3));
    }

    public static int U(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof j0) {
            j0 j0Var = (j0) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzy(j0Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzdj.zzy(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int V(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return W(list) + (size * zzdj.zzy(i << 3));
    }

    public static int W(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof p0) {
            p0 p0Var = (p0) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(p0Var.a(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static y1 X() {
        return b;
    }

    public static y1 Y() {
        return c;
    }

    public static y1 a() {
        return d;
    }

    public static Object b(Object obj, int i, int i2, Object obj2, y1 y1Var) {
        if (obj2 == null) {
            obj2 = y1Var.c(obj);
        }
        y1Var.f(obj2, i, i2);
        return obj2;
    }

    public static void c(c0 c0Var, Object obj, Object obj2) {
        f0 b2 = c0Var.b(obj2);
        if (b2.f9593a.isEmpty()) {
            return;
        }
        c0Var.c(obj).h(b2);
    }

    public static void d(y1 y1Var, Object obj, Object obj2) {
        y1Var.h(obj, y1Var.e(y1Var.d(obj), y1Var.d(obj2)));
    }

    public static void e(Class cls) {
        Class cls2;
        if (!zzed.class.isAssignableFrom(cls) && (cls2 = f9605a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void f(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzc(i, list, z);
    }

    public static void g(int i, List list, m2 m2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zze(i, list);
    }

    public static void h(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzg(i, list, z);
    }

    public static void i(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzj(i, list, z);
    }

    public static void j(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzl(i, list, z);
    }

    public static void k(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzn(i, list, z);
    }

    public static void l(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzp(i, list, z);
    }

    public static void m(int i, List list, m2 m2Var, l1 l1Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((z) m2Var).a(i, list.get(i2), l1Var);
        }
    }

    public static void n(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzs(i, list, z);
    }

    public static void o(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzu(i, list, z);
    }

    public static void p(int i, List list, m2 m2Var, l1 l1Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((z) m2Var).b(i, list.get(i2), l1Var);
        }
    }

    public static void q(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzy(i, list, z);
    }

    public static void r(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzA(i, list, z);
    }

    public static void s(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzC(i, list, z);
    }

    public static void t(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzE(i, list, z);
    }

    public static void u(int i, List list, m2 m2Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzH(i, list);
    }

    public static void v(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzJ(i, list, z);
    }

    public static void w(int i, List list, m2 m2Var, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        m2Var.zzL(i, list, z);
    }

    public static boolean x(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:5:0x0004
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1 y(boolean r6) {
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
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1 r6 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1) r6     // Catch: java.lang.Throwable -> L23
            return r6
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.n1.y(boolean):com.google.android.gms.internal.mlkit_vision_barcode_bundled.y1");
    }

    public static int z(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdj.zzy(i << 3) + 1);
    }
}
