package com.google.android.gms.internal.fitness;

import java.util.Comparator;
/* loaded from: classes8.dex */
public final class z1 implements Comparator<zzfx> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzfx zzfxVar, zzfx zzfxVar2) {
        int zza;
        int zza2;
        zzfx zzfxVar3 = zzfxVar;
        zzfx zzfxVar4 = zzfxVar2;
        zzgc zzgcVar = (zzgc) zzfxVar3.iterator();
        zzgc zzgcVar2 = (zzgc) zzfxVar4.iterator();
        while (zzgcVar.hasNext() && zzgcVar2.hasNext()) {
            zza = zzfx.zza(zzgcVar.nextByte());
            zza2 = zzfx.zza(zzgcVar2.nextByte());
            int compare = Integer.compare(zza, zza2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzfxVar3.size(), zzfxVar4.size());
    }
}
