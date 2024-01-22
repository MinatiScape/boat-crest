package com.google.android.gms.internal.firebase_ml;

import java.util.Comparator;
/* loaded from: classes7.dex */
public final class a6 implements Comparator<zzvv> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzvv zzvvVar, zzvv zzvvVar2) {
        int zzb;
        int zzb2;
        zzvv zzvvVar3 = zzvvVar;
        zzvv zzvvVar4 = zzvvVar2;
        zzwa zzwaVar = (zzwa) zzvvVar3.iterator();
        zzwa zzwaVar2 = (zzwa) zzvvVar4.iterator();
        while (zzwaVar.hasNext() && zzwaVar2.hasNext()) {
            zzb = zzvv.zzb(zzwaVar.nextByte());
            zzb2 = zzvv.zzb(zzwaVar2.nextByte());
            int compare = Integer.compare(zzb, zzb2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzvvVar3.size(), zzvvVar4.size());
    }
}
