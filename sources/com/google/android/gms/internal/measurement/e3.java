package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class e3 extends f3 {
    public /* synthetic */ e3(zzkr zzkrVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.f3
    public final void a(Object obj, long j) {
        ((zzkg) t4.k(obj, j)).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    @Override // com.google.android.gms.internal.measurement.f3
    public final <E> void b(Object obj, Object obj2, long j) {
        zzkg<E> zzkgVar = (zzkg) t4.k(obj, j);
        zzkg<E> zzkgVar2 = (zzkg) t4.k(obj2, j);
        int size = zzkgVar.size();
        int size2 = zzkgVar2.size();
        zzkg<E> zzkgVar3 = zzkgVar;
        zzkgVar3 = zzkgVar;
        if (size > 0 && size2 > 0) {
            boolean zzc = zzkgVar.zzc();
            zzkg<E> zzkgVar4 = zzkgVar;
            if (!zzc) {
                zzkgVar4 = zzkgVar.zzd(size2 + size);
            }
            zzkgVar4.addAll(zzkgVar2);
            zzkgVar3 = zzkgVar4;
        }
        if (size > 0) {
            zzkgVar2 = zzkgVar3;
        }
        t4.x(obj, j, zzkgVar2);
    }
}
