package com.google.android.gms.internal.vision;

import java.util.Comparator;
/* loaded from: classes10.dex */
public final class l1 implements Comparator<zzfh> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzfh zzfhVar, zzfh zzfhVar2) {
        int zza;
        int zza2;
        zzfh zzfhVar3 = zzfhVar;
        zzfh zzfhVar4 = zzfhVar2;
        zzfq zzfqVar = (zzfq) zzfhVar3.iterator();
        zzfq zzfqVar2 = (zzfq) zzfhVar4.iterator();
        while (zzfqVar.hasNext() && zzfqVar2.hasNext()) {
            zza = zzfh.zza(zzfqVar.nextByte());
            zza2 = zzfh.zza(zzfqVar2.nextByte());
            int compare = Integer.compare(zza, zza2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzfhVar3.size(), zzfhVar4.size());
    }
}
