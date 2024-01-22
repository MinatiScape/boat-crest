package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzdz extends zzee {
    private zzeh zza;
    private String zzb;
    private zzgi<AutocompletePrediction> zzc;
    private Place zzd;
    private AutocompletePrediction zze;
    private Status zzf;

    public final zzee zza(zzeh zzehVar) {
        Objects.requireNonNull(zzehVar, "Null type");
        this.zza = zzehVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzee zza(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzee zza(List<AutocompletePrediction> list) {
        this.zzc = list == null ? null : zzgi.zza((Collection) list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzee zza(Place place) {
        this.zzd = place;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzee zza(AutocompletePrediction autocompletePrediction) {
        this.zze = autocompletePrediction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzee zza(Status status) {
        this.zzf = status;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzee
    public final zzef zza() {
        String concat = this.zza == null ? "".concat(" type") : "";
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzdx(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
