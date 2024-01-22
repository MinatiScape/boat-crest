package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzhh;
import com.google.android.libraries.places.internal.zzhj;
import com.google.android.libraries.places.internal.zzmq;
/* loaded from: classes10.dex */
public final class zzdq {
    public static zzmq.zzs.zza zza(zzdj zzdjVar) {
        zzmq.zzs.zze zzeVar;
        int i = zzdp.zza[zzdjVar.zzc().ordinal()];
        if (i == 1) {
            zzeVar = zzmq.zzs.zze.AUTOCOMPLETE_WIDGET;
        } else if (i != 2) {
            zzeVar = zzmq.zzs.zze.UNKNOWN_SOURCE;
        } else {
            zzeVar = zzmq.zzs.zze.PROGRAMMATIC_API;
        }
        return zzmq.zzs.zza().zza((zzhj.zza) ((zzsf) zzhj.zza.zza().zza(zzdjVar.zza()).zza(zzdjVar.zzb()).zzg())).zza(true).zza(zzeVar).zzb("2.4.0");
    }

    public static zzhh.zza zza(zzmq.zzs zzsVar) {
        return (zzhh.zza) ((zzsf) zzhh.zza.zza().zza(zzhh.zza.zzb.PLACES).zza(zzsVar).zzg());
    }
}
