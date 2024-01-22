package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
/* loaded from: classes10.dex */
final class zzdx extends zzef {
    private final zzeh zza;
    private final String zzb;
    private final zzgi<AutocompletePrediction> zzc;
    private final Place zzd;
    private final AutocompletePrediction zze;
    private final Status zzf;

    private zzdx(zzeh zzehVar, @Nullable String str, @Nullable zzgi<AutocompletePrediction> zzgiVar, @Nullable Place place, @Nullable AutocompletePrediction autocompletePrediction, @Nullable Status status) {
        this.zza = zzehVar;
        this.zzb = str;
        this.zzc = zzgiVar;
        this.zzd = place;
        this.zze = autocompletePrediction;
        this.zzf = status;
    }

    public final boolean equals(Object obj) {
        String str;
        zzgi<AutocompletePrediction> zzgiVar;
        Place place;
        AutocompletePrediction autocompletePrediction;
        Status status;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzef) {
            zzef zzefVar = (zzef) obj;
            if (this.zza.equals(zzefVar.zza()) && ((str = this.zzb) != null ? str.equals(zzefVar.zzb()) : zzefVar.zzb() == null) && ((zzgiVar = this.zzc) != null ? zzgiVar.equals(zzefVar.zzc()) : zzefVar.zzc() == null) && ((place = this.zzd) != null ? place.equals(zzefVar.zzd()) : zzefVar.zzd() == null) && ((autocompletePrediction = this.zze) != null ? autocompletePrediction.equals(zzefVar.zze()) : zzefVar.zze() == null) && ((status = this.zzf) != null ? status.equals(zzefVar.zzf()) : zzefVar.zzf() == null)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        String str = this.zzb;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        zzgi<AutocompletePrediction> zzgiVar = this.zzc;
        int hashCode3 = (hashCode2 ^ (zzgiVar == null ? 0 : zzgiVar.hashCode())) * 1000003;
        Place place = this.zzd;
        int hashCode4 = (hashCode3 ^ (place == null ? 0 : place.hashCode())) * 1000003;
        AutocompletePrediction autocompletePrediction = this.zze;
        int hashCode5 = (hashCode4 ^ (autocompletePrediction == null ? 0 : autocompletePrediction.hashCode())) * 1000003;
        Status status = this.zzf;
        return hashCode5 ^ (status != null ? status.hashCode() : 0);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String str = this.zzb;
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        String valueOf4 = String.valueOf(this.zze);
        String valueOf5 = String.valueOf(this.zzf);
        StringBuilder sb = new StringBuilder(valueOf.length() + 76 + String.valueOf(str).length() + valueOf2.length() + valueOf3.length() + valueOf4.length() + valueOf5.length());
        sb.append("AutocompleteState{type=");
        sb.append(valueOf);
        sb.append(", query=");
        sb.append(str);
        sb.append(", predictions=");
        sb.append(valueOf2);
        sb.append(", place=");
        sb.append(valueOf3);
        sb.append(", prediction=");
        sb.append(valueOf4);
        sb.append(", status=");
        sb.append(valueOf5);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @NonNull
    public final zzeh zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @Nullable
    public final zzgi<AutocompletePrediction> zzc() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @Nullable
    public final Place zzd() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @Nullable
    public final AutocompletePrediction zze() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.internal.zzef
    @Nullable
    public final Status zzf() {
        return this.zzf;
    }
}
