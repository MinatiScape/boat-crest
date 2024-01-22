package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.LocalTime;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzk extends LocalTime.zza {
    private Integer zza;
    private Integer zzb;

    @Override // com.google.android.libraries.places.api.model.LocalTime.zza
    public final LocalTime.zza zza(int i) {
        this.zza = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.LocalTime.zza
    public final LocalTime.zza zzb(int i) {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.LocalTime.zza
    public final LocalTime zza() {
        String concat = this.zza == null ? "".concat(" hours") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" minutes");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzaj(this.zza.intValue(), this.zzb.intValue());
    }
}
