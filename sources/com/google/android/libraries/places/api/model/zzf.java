package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzf extends zzba {
    private Integer zza;
    private Integer zzb;

    @Override // com.google.android.libraries.places.api.model.zzba
    public final zzba zza(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.zzba
    public final zzba zzb(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.zzba
    public final AutocompletePrediction.zza zza() {
        String concat = this.zza == null ? "".concat(" offset") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" length");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzaf(this.zza.intValue(), this.zzb.intValue());
    }
}
