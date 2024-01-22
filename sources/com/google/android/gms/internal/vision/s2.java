package com.google.android.gms.internal.vision;

import java.util.List;
/* loaded from: classes10.dex */
public final class s2 extends r2 {
    public s2() {
        super();
    }

    public static <E> zzgz<E> f(Object obj, long j) {
        return (zzgz) j4.G(obj, j);
    }

    @Override // com.google.android.gms.internal.vision.r2
    public final <L> List<L> a(Object obj, long j) {
        zzgz f = f(obj, j);
        if (f.zzdo()) {
            return f;
        }
        int size = f.size();
        zzgz zzah = f.zzah(size == 0 ? 10 : size << 1);
        j4.f(obj, j, zzah);
        return zzah;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.gms.internal.vision.r2
    public final <E> void b(Object obj, Object obj2, long j) {
        zzgz<E> f = f(obj, j);
        zzgz<E> f2 = f(obj2, j);
        int size = f.size();
        int size2 = f2.size();
        zzgz<E> zzgzVar = f;
        zzgzVar = f;
        if (size > 0 && size2 > 0) {
            boolean zzdo = f.zzdo();
            zzgz<E> zzgzVar2 = f;
            if (!zzdo) {
                zzgzVar2 = f.zzah(size2 + size);
            }
            zzgzVar2.addAll(f2);
            zzgzVar = zzgzVar2;
        }
        if (size > 0) {
            f2 = zzgzVar;
        }
        j4.f(obj, j, f2);
    }

    @Override // com.google.android.gms.internal.vision.r2
    public final void c(Object obj, long j) {
        f(obj, j).zzdp();
    }
}
