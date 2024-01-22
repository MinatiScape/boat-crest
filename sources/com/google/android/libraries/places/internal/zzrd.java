package com.google.android.libraries.places.internal;

import java.util.Comparator;
/* loaded from: classes10.dex */
final class zzrd implements Comparator<zzrb> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzrb zzrbVar, zzrb zzrbVar2) {
        int zzb;
        int zzb2;
        zzrb zzrbVar3 = zzrbVar;
        zzrb zzrbVar4 = zzrbVar2;
        zzrk zzrkVar = (zzrk) zzrbVar3.iterator();
        zzrk zzrkVar2 = (zzrk) zzrbVar4.iterator();
        while (zzrkVar.hasNext() && zzrkVar2.hasNext()) {
            zzb = zzrb.zzb(zzrkVar.zza());
            zzb2 = zzrb.zzb(zzrkVar2.zza());
            int compare = Integer.compare(zzb, zzb2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzrbVar3.zza(), zzrbVar4.zza());
    }
}
