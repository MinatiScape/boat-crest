package com.google.android.libraries.places.api.model;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
/* loaded from: classes10.dex */
abstract class zzg extends AutocompletePrediction.zza {
    private final int zza;
    private final int zzb;

    public zzg(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AutocompletePrediction.zza) {
            AutocompletePrediction.zza zzaVar = (AutocompletePrediction.zza) obj;
            if (this.zza == zzaVar.zza() && this.zzb == zzaVar.zzb()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.zza ^ 1000003) * 1000003) ^ this.zzb;
    }

    public String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        StringBuilder sb = new StringBuilder(54);
        sb.append("SubstringMatch{offset=");
        sb.append(i);
        sb.append(", length=");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.zza
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.AutocompletePrediction.zza
    public final int zzb() {
        return this.zzb;
    }
}
