package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzed;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.Objects;
import org.bouncycastle.crypto.tls.CipherSuite;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzdu extends zzed {
    private final AutocompleteActivityMode zza;
    private final zzgi<Place.Field> zzb;
    private final zzec zzc;
    private final String zzd;
    private final String zze;
    private final LocationBias zzf;
    private final LocationRestriction zzg;
    private final zzgi<String> zzh;
    private final TypeFilter zzi;
    private final int zzj;
    private final int zzk;

    public zzdu(AutocompleteActivityMode autocompleteActivityMode, zzgi<Place.Field> zzgiVar, zzec zzecVar, @Nullable String str, @Nullable String str2, @Nullable LocationBias locationBias, @Nullable LocationRestriction locationRestriction, zzgi<String> zzgiVar2, @Nullable TypeFilter typeFilter, int i, int i2) {
        Objects.requireNonNull(autocompleteActivityMode, "Null mode");
        this.zza = autocompleteActivityMode;
        Objects.requireNonNull(zzgiVar, "Null placeFields");
        this.zzb = zzgiVar;
        Objects.requireNonNull(zzecVar, "Null origin");
        this.zzc = zzecVar;
        this.zzd = str;
        this.zze = str2;
        this.zzf = locationBias;
        this.zzg = locationRestriction;
        Objects.requireNonNull(zzgiVar2, "Null countries");
        this.zzh = zzgiVar2;
        this.zzi = typeFilter;
        this.zzj = i;
        this.zzk = i2;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        LocationBias locationBias;
        LocationRestriction locationRestriction;
        TypeFilter typeFilter;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzed) {
            zzed zzedVar = (zzed) obj;
            if (this.zza.equals(zzedVar.zza()) && this.zzb.equals(zzedVar.zzb()) && this.zzc.equals(zzedVar.zzc()) && ((str = this.zzd) != null ? str.equals(zzedVar.zzd()) : zzedVar.zzd() == null) && ((str2 = this.zze) != null ? str2.equals(zzedVar.zze()) : zzedVar.zze() == null) && ((locationBias = this.zzf) != null ? locationBias.equals(zzedVar.zzf()) : zzedVar.zzf() == null) && ((locationRestriction = this.zzg) != null ? locationRestriction.equals(zzedVar.zzg()) : zzedVar.zzg() == null) && this.zzh.equals(zzedVar.zzh()) && ((typeFilter = this.zzi) != null ? typeFilter.equals(zzedVar.zzi()) : zzedVar.zzi() == null) && this.zzj == zzedVar.zzj() && this.zzk == zzedVar.zzk()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003;
        String str = this.zzd;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.zze;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        LocationBias locationBias = this.zzf;
        int hashCode4 = (hashCode3 ^ (locationBias == null ? 0 : locationBias.hashCode())) * 1000003;
        LocationRestriction locationRestriction = this.zzg;
        int hashCode5 = (((hashCode4 ^ (locationRestriction == null ? 0 : locationRestriction.hashCode())) * 1000003) ^ this.zzh.hashCode()) * 1000003;
        TypeFilter typeFilter = this.zzi;
        return ((((hashCode5 ^ (typeFilter != null ? typeFilter.hashCode() : 0)) * 1000003) ^ this.zzj) * 1000003) ^ this.zzk;
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        String valueOf3 = String.valueOf(this.zzc);
        String str = this.zzd;
        String str2 = this.zze;
        String valueOf4 = String.valueOf(this.zzf);
        String valueOf5 = String.valueOf(this.zzg);
        String valueOf6 = String.valueOf(this.zzh);
        String valueOf7 = String.valueOf(this.zzi);
        int i = this.zzj;
        int i2 = this.zzk;
        StringBuilder sb = new StringBuilder(valueOf.length() + CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 + valueOf2.length() + valueOf3.length() + String.valueOf(str).length() + String.valueOf(str2).length() + valueOf4.length() + valueOf5.length() + valueOf6.length() + valueOf7.length());
        sb.append("AutocompleteOptions{mode=");
        sb.append(valueOf);
        sb.append(", placeFields=");
        sb.append(valueOf2);
        sb.append(", origin=");
        sb.append(valueOf3);
        sb.append(", initialQuery=");
        sb.append(str);
        sb.append(", hint=");
        sb.append(str2);
        sb.append(", locationBias=");
        sb.append(valueOf4);
        sb.append(", locationRestriction=");
        sb.append(valueOf5);
        sb.append(", countries=");
        sb.append(valueOf6);
        sb.append(", typeFilter=");
        sb.append(valueOf7);
        sb.append(", primaryColor=");
        sb.append(i);
        sb.append(", primaryColorDark=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @NonNull
    public final AutocompleteActivityMode zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @NonNull
    public final zzgi<Place.Field> zzb() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @NonNull
    public final zzec zzc() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @Nullable
    public final String zze() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @Nullable
    public final LocationBias zzf() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @Nullable
    public final LocationRestriction zzg() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @NonNull
    public final zzgi<String> zzh() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    @Nullable
    public final TypeFilter zzi() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    public final int zzj() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    public final int zzk() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.internal.zzed
    public final zzed.zza zzl() {
        return new zzdw(this);
    }
}
