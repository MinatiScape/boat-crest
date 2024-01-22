package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzdw extends zzed.zza {
    private AutocompleteActivityMode zza;
    private zzgi<Place.Field> zzb;
    private zzec zzc;
    private String zzd;
    private String zze;
    private LocationBias zzf;
    private LocationRestriction zzg;
    private zzgi<String> zzh;
    private TypeFilter zzi;
    private Integer zzj;
    private Integer zzk;

    public zzdw() {
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(AutocompleteActivityMode autocompleteActivityMode) {
        Objects.requireNonNull(autocompleteActivityMode, "Null mode");
        this.zza = autocompleteActivityMode;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zzb(@Nullable String str) {
        this.zze = str;
        return this;
    }

    private zzdw(zzed zzedVar) {
        this.zza = zzedVar.zza();
        this.zzb = zzedVar.zzb();
        this.zzc = zzedVar.zzc();
        this.zzd = zzedVar.zzd();
        this.zze = zzedVar.zze();
        this.zzf = zzedVar.zzf();
        this.zzg = zzedVar.zzg();
        this.zzh = zzedVar.zzh();
        this.zzi = zzedVar.zzi();
        this.zzj = Integer.valueOf(zzedVar.zzj());
        this.zzk = Integer.valueOf(zzedVar.zzk());
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zzb(List<String> list) {
        this.zzh = zzgi.zza((Collection) list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(List<Place.Field> list) {
        this.zzb = zzgi.zza((Collection) list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zzb(int i) {
        this.zzk = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(zzec zzecVar) {
        Objects.requireNonNull(zzecVar, "Null origin");
        this.zzc = zzecVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(@Nullable String str) {
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(@Nullable LocationBias locationBias) {
        this.zzf = locationBias;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(@Nullable LocationRestriction locationRestriction) {
        this.zzg = locationRestriction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(@Nullable TypeFilter typeFilter) {
        this.zzi = typeFilter;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed.zza zza(int i) {
        this.zzj = Integer.valueOf(i);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzed.zza
    public final zzed zza() {
        String concat = this.zza == null ? "".concat(" mode") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" placeFields");
        }
        if (this.zzc == null) {
            concat = String.valueOf(concat).concat(" origin");
        }
        if (this.zzh == null) {
            concat = String.valueOf(concat).concat(" countries");
        }
        if (this.zzj == null) {
            concat = String.valueOf(concat).concat(" primaryColor");
        }
        if (this.zzk == null) {
            concat = String.valueOf(concat).concat(" primaryColorDark");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzdv(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj.intValue(), this.zzk.intValue());
    }
}
