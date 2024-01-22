package com.google.android.libraries.places.api.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
abstract class zze extends AutocompletePrediction {
    private final String zza;
    private final Integer zzb;
    private final List<Place.Type> zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final List<AutocompletePrediction.zza> zzg;
    private final List<AutocompletePrediction.zza> zzh;
    private final List<AutocompletePrediction.zza> zzi;

    public zze(String str, @Nullable Integer num, List<Place.Type> list, String str2, String str3, String str4, @Nullable List<AutocompletePrediction.zza> list2, @Nullable List<AutocompletePrediction.zza> list3, @Nullable List<AutocompletePrediction.zza> list4) {
        Objects.requireNonNull(str, "Null placeId");
        this.zza = str;
        this.zzb = num;
        Objects.requireNonNull(list, "Null placeTypes");
        this.zzc = list;
        Objects.requireNonNull(str2, "Null fullText");
        this.zzd = str2;
        Objects.requireNonNull(str3, "Null primaryText");
        this.zze = str3;
        Objects.requireNonNull(str4, "Null secondaryText");
        this.zzf = str4;
        this.zzg = list2;
        this.zzh = list3;
        this.zzi = list4;
    }

    public boolean equals(Object obj) {
        Integer num;
        List<AutocompletePrediction.zza> list;
        List<AutocompletePrediction.zza> list2;
        List<AutocompletePrediction.zza> list3;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AutocompletePrediction) {
            AutocompletePrediction autocompletePrediction = (AutocompletePrediction) obj;
            if (this.zza.equals(autocompletePrediction.getPlaceId()) && ((num = this.zzb) != null ? num.equals(autocompletePrediction.getDistanceMeters()) : autocompletePrediction.getDistanceMeters() == null) && this.zzc.equals(autocompletePrediction.getPlaceTypes()) && this.zzd.equals(autocompletePrediction.zza()) && this.zze.equals(autocompletePrediction.zzb()) && this.zzf.equals(autocompletePrediction.zzc()) && ((list = this.zzg) != null ? list.equals(autocompletePrediction.zzd()) : autocompletePrediction.zzd() == null) && ((list2 = this.zzh) != null ? list2.equals(autocompletePrediction.zze()) : autocompletePrediction.zze() == null) && ((list3 = this.zzi) != null ? list3.equals(autocompletePrediction.zzf()) : autocompletePrediction.zzf() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @Nullable
    public Integer getDistanceMeters() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @NonNull
    public String getPlaceId() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @NonNull
    public List<Place.Type> getPlaceTypes() {
        return this.zzc;
    }

    public int hashCode() {
        int hashCode = (this.zza.hashCode() ^ 1000003) * 1000003;
        Integer num = this.zzb;
        int hashCode2 = (((((((((hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003;
        List<AutocompletePrediction.zza> list = this.zzg;
        int hashCode3 = (hashCode2 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        List<AutocompletePrediction.zza> list2 = this.zzh;
        int hashCode4 = (hashCode3 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        List<AutocompletePrediction.zza> list3 = this.zzi;
        return hashCode4 ^ (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String str2 = this.zzd;
        String str3 = this.zze;
        String str4 = this.zzf;
        String valueOf3 = String.valueOf(this.zzg);
        String valueOf4 = String.valueOf(this.zzh);
        String valueOf5 = String.valueOf(this.zzi);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 195 + valueOf.length() + valueOf2.length() + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + valueOf3.length() + valueOf4.length() + valueOf5.length());
        sb.append("AutocompletePrediction{placeId=");
        sb.append(str);
        sb.append(", distanceMeters=");
        sb.append(valueOf);
        sb.append(", placeTypes=");
        sb.append(valueOf2);
        sb.append(", fullText=");
        sb.append(str2);
        sb.append(", primaryText=");
        sb.append(str3);
        sb.append(", secondaryText=");
        sb.append(str4);
        sb.append(", fullTextMatchedSubstrings=");
        sb.append(valueOf3);
        sb.append(", primaryTextMatchedSubstrings=");
        sb.append(valueOf4);
        sb.append(", secondaryTextMatchedSubstrings=");
        sb.append(valueOf5);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @NonNull
    public final String zza() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @NonNull
    public final String zzb() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @NonNull
    public final String zzc() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @Nullable
    public final List<AutocompletePrediction.zza> zzd() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @Nullable
    public final List<AutocompletePrediction.zza> zze() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction
    @Nullable
    public final List<AutocompletePrediction.zza> zzf() {
        return this.zzi;
    }
}
