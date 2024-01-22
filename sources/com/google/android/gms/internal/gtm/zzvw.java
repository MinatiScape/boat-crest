package com.google.android.gms.internal.gtm;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzvw extends zzvy {
    public /* synthetic */ zzvw(zzvv zzvvVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.gtm.zzvy
    public final <L> List<L> zza(Object obj, long j) {
        zzvh zzvhVar = (zzvh) zzxy.zzf(obj, j);
        if (zzvhVar.zzc()) {
            return zzvhVar;
        }
        int size = zzvhVar.size();
        zzvh zzd = zzvhVar.zzd(size == 0 ? 10 : size + size);
        zzxy.zzs(obj, j, zzd);
        return zzd;
    }

    @Override // com.google.android.gms.internal.gtm.zzvy
    public final void zzb(Object obj, long j) {
        ((zzvh) zzxy.zzf(obj, j)).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    @Override // com.google.android.gms.internal.gtm.zzvy
    public final <E> void zzc(Object obj, Object obj2, long j) {
        zzvh<E> zzvhVar = (zzvh) zzxy.zzf(obj, j);
        zzvh<E> zzvhVar2 = (zzvh) zzxy.zzf(obj2, j);
        int size = zzvhVar.size();
        int size2 = zzvhVar2.size();
        zzvh<E> zzvhVar3 = zzvhVar;
        zzvhVar3 = zzvhVar;
        if (size > 0 && size2 > 0) {
            boolean zzc = zzvhVar.zzc();
            zzvh<E> zzvhVar4 = zzvhVar;
            if (!zzc) {
                zzvhVar4 = zzvhVar.zzd(size2 + size);
            }
            zzvhVar4.addAll(zzvhVar2);
            zzvhVar3 = zzvhVar4;
        }
        if (size > 0) {
            zzvhVar2 = zzvhVar3;
        }
        zzxy.zzs(obj, j, zzvhVar2);
    }
}
