package com.google.android.recaptcha.internal;

import java.util.List;
/* loaded from: classes10.dex */
final class zzib extends zzid {
    private zzib() {
        super(null);
    }

    public /* synthetic */ zzib(zzia zziaVar) {
        super(null);
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final List zza(Object obj, long j) {
        zzhm zzhmVar = (zzhm) zzkg.zzf(obj, j);
        if (zzhmVar.zzc()) {
            return zzhmVar;
        }
        int size = zzhmVar.size();
        zzhm zzd = zzhmVar.zzd(size == 0 ? 10 : size + size);
        zzkg.zzs(obj, j, zzd);
        return zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final void zzb(Object obj, long j) {
        ((zzhm) zzkg.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzid
    public final void zzc(Object obj, Object obj2, long j) {
        zzhm zzhmVar = (zzhm) zzkg.zzf(obj, j);
        zzhm zzhmVar2 = (zzhm) zzkg.zzf(obj2, j);
        int size = zzhmVar.size();
        int size2 = zzhmVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzhmVar.zzc()) {
                zzhmVar = zzhmVar.zzd(size2 + size);
            }
            zzhmVar.addAll(zzhmVar2);
        }
        if (size > 0) {
            zzhmVar2 = zzhmVar;
        }
        zzkg.zzs(obj, j, zzhmVar2);
    }
}
