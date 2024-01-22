package com.google.android.libraries.places.internal;

import android.content.Context;
/* loaded from: classes10.dex */
final class zzcx implements zzcz {
    private Context zza;
    private zzdb zzb;
    private zzdj zzc;

    private zzcx() {
    }

    @Override // com.google.android.libraries.places.internal.zzcz
    public final zzcw zza() {
        zzvs.zza(this.zza, Context.class);
        zzvs.zza(this.zzb, zzdb.class);
        zzvs.zza(this.zzc, zzdj.class);
        return new zzcv(this.zza, this.zzb, this.zzc);
    }

    @Override // com.google.android.libraries.places.internal.zzcz
    public final /* synthetic */ zzcz zza(zzdj zzdjVar) {
        this.zzc = (zzdj) zzvs.zza(zzdjVar);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzcz
    public final /* synthetic */ zzcz zza(zzdb zzdbVar) {
        this.zzb = (zzdb) zzvs.zza(zzdbVar);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzcz
    public final /* synthetic */ zzcz zza(Context context) {
        this.zza = (Context) zzvs.zza(context);
        return this;
    }
}
