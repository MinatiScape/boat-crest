package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class a3 extends z2 {
    public a3() {
        super();
    }

    public static <E> zzhh<E> e(Object obj, long j) {
        return (zzhh) s4.G(obj, j);
    }

    @Override // com.google.android.gms.internal.fitness.z2
    public final void a(Object obj, long j) {
        e(obj, j).zzar();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    @Override // com.google.android.gms.internal.fitness.z2
    public final <E> void b(Object obj, Object obj2, long j) {
        zzhh<E> e = e(obj, j);
        zzhh<E> e2 = e(obj2, j);
        int size = e.size();
        int size2 = e2.size();
        zzhh<E> zzhhVar = e;
        zzhhVar = e;
        if (size > 0 && size2 > 0) {
            boolean zzaq = e.zzaq();
            zzhh<E> zzhhVar2 = e;
            if (!zzaq) {
                zzhhVar2 = e.zzae(size2 + size);
            }
            zzhhVar2.addAll(e2);
            zzhhVar = zzhhVar2;
        }
        if (size > 0) {
            e2 = zzhhVar;
        }
        s4.g(obj, j, e2);
    }
}
