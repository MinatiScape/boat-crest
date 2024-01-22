package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzmq;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
/* loaded from: classes10.dex */
public final class zzez implements zzfa {
    private final zzdn zza;
    private final zzdj zzb;

    public zzez(zzdn zzdnVar, zzdj zzdjVar) {
        this.zza = zzdnVar;
        this.zzb = zzdjVar;
    }

    @Override // com.google.android.libraries.places.internal.zzfa
    public final void zza(zzey zzeyVar) {
        zzmq.zzp.zzc zzh = zzmq.zzp.zza().zza(zzeyVar.zzd()).zzb(zzeyVar.zze()).zzc(zzeyVar.zzf()).zza(zzeyVar.zzg()).zzb(zzeyVar.zzh()).zzc(zzeyVar.zzi()).zzd(zzeyVar.zzj()).zze(zzeyVar.zzk().length()).zzf(zzeyVar.zzl()).zzg(zzeyVar.zzm()).zzd(zzeyVar.zzn()).zzh(zzeyVar.zzo());
        if (zzeyVar.zza() == zzec.FRAGMENT) {
            zzh.zza(zzmq.zzp.zzd.ANDROID_AUTOCOMPLETE_FRAGMENT);
        } else if (zzeyVar.zza() == zzec.INTENT) {
            zzh.zza(zzmq.zzp.zzd.ANDROID_AUTOCOMPLETE_MANUAL_LAUNCHER);
        } else {
            zzh.zza(zzmq.zzp.zzd.UNKNOWN_ORIGIN);
        }
        if (zzeyVar.zzb() == AutocompleteActivityMode.FULLSCREEN) {
            zzh.zza(zzmq.zzp.zza.FULLSCREEN);
        } else if (zzeyVar.zzb() == AutocompleteActivityMode.OVERLAY) {
            zzh.zza(zzmq.zzp.zza.OVERLAY);
        }
        this.zza.zza(zzdq.zza((zzmq.zzs) ((zzsf) zzdq.zza(this.zzb).zza(zzmq.zzs.zzc.AUTOCOMPLETE_WIDGET_SESSION).zza((zzmq.zzp) ((zzsf) zzh.zzg())).zzg())));
    }
}
