package com.google.android.libraries.places.api.model;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzr extends PlaceLikelihood {
    private final Place zza;
    private final double zzb;

    public zzr(Place place, double d) {
        Objects.requireNonNull(place, "Null place");
        this.zza = place;
        this.zzb = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PlaceLikelihood) {
            PlaceLikelihood placeLikelihood = (PlaceLikelihood) obj;
            if (this.zza.equals(placeLikelihood.getPlace()) && Double.doubleToLongBits(this.zzb) == Double.doubleToLongBits(placeLikelihood.getLikelihood())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    @FloatRange(from = 0.0d, to = 1.0d)
    public double getLikelihood() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.PlaceLikelihood
    @NonNull
    public Place getPlace() {
        return this.zza;
    }

    public int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.zzb) >>> 32) ^ Double.doubleToLongBits(this.zzb)));
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        double d = this.zzb;
        StringBuilder sb = new StringBuilder(valueOf.length() + 60);
        sb.append("PlaceLikelihood{place=");
        sb.append(valueOf);
        sb.append(", likelihood=");
        sb.append(d);
        sb.append("}");
        return sb.toString();
    }
}
