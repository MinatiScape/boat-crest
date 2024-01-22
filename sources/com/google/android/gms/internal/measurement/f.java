package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;
/* loaded from: classes8.dex */
public final class f implements Comparator<zzap> {
    public final /* synthetic */ zzai h;
    public final /* synthetic */ zzg i;

    public f(zzai zzaiVar, zzg zzgVar) {
        this.h = zzaiVar;
        this.i = zzgVar;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zzap zzapVar, zzap zzapVar2) {
        zzap zzapVar3 = zzapVar;
        zzap zzapVar4 = zzapVar2;
        zzai zzaiVar = this.h;
        zzg zzgVar = this.i;
        if (zzapVar3 instanceof zzau) {
            return !(zzapVar4 instanceof zzau) ? 1 : 0;
        } else if (zzapVar4 instanceof zzau) {
            return -1;
        } else {
            if (zzaiVar == null) {
                return zzapVar3.zzi().compareTo(zzapVar4.zzi());
            }
            return (int) zzh.zza(zzaiVar.zza(zzgVar, Arrays.asList(zzapVar3, zzapVar4)).zzh().doubleValue());
        }
    }
}
