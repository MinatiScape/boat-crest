package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzdj;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzdh extends zzdj.zza {
    private String zza;
    private Integer zzb;
    private zzdj.zzb zzc;

    @Override // com.google.android.libraries.places.internal.zzdj.zza
    public final zzdj.zza zza(String str) {
        Objects.requireNonNull(str, "Null packageName");
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzdj.zza
    public final zzdj.zza zza(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzdj.zza
    public final zzdj.zza zza(zzdj.zzb zzbVar) {
        Objects.requireNonNull(zzbVar, "Null requestSource");
        this.zzc = zzbVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzdj.zza
    public final zzdj zza() {
        String concat = this.zza == null ? "".concat(" packageName") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" versionCode");
        }
        if (this.zzc == null) {
            concat = String.valueOf(concat).concat(" requestSource");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzdf(this.zza, this.zzb.intValue(), this.zzc);
    }
}
