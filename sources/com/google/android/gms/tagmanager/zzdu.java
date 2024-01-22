package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzdu implements zzfi {
    public final /* synthetic */ zzdw zza;

    public zzdu(zzdw zzdwVar) {
        this.zza = zzdwVar;
    }

    @Override // com.google.android.gms.tagmanager.zzfi
    public final void zza(zzca zzcaVar) {
        Clock clock;
        Clock clock2;
        long zza = zzcaVar.zza();
        if (zza == 0) {
            zzdw zzdwVar = this.zza;
            long zzb = zzcaVar.zzb();
            clock2 = this.zza.zzg;
            zzdw.zzi(zzdwVar, zzb, clock2.currentTimeMillis());
            return;
        }
        clock = this.zza.zzg;
        if (zza + 14400000 < clock.currentTimeMillis()) {
            this.zza.zzl(zzcaVar.zzb());
            long zzb2 = zzcaVar.zzb();
            StringBuilder sb = new StringBuilder(47);
            sb.append("Giving up on failed hitId: ");
            sb.append(zzb2);
            zzdh.zzb.zzd(sb.toString());
        }
    }
}
