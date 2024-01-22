package com.google.android.gms.internal.measurement;

import java.util.Comparator;
/* loaded from: classes8.dex */
public final class k2 implements Comparator<zziy> {
    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zziy zziyVar, zziy zziyVar2) {
        zziy zziyVar3 = zziyVar;
        zziy zziyVar4 = zziyVar2;
        j2 j2Var = new j2(zziyVar3);
        j2 j2Var2 = new j2(zziyVar4);
        while (j2Var.hasNext() && j2Var2.hasNext()) {
            int zza = zzip.zza(j2Var.zza() & 255, j2Var2.zza() & 255);
            if (zza != 0) {
                return zza;
            }
        }
        return zzip.zza(zziyVar3.zzd(), zziyVar4.zzd());
    }
}
