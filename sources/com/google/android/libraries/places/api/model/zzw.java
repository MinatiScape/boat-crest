package com.google.android.libraries.places.api.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
import java.util.Objects;
/* loaded from: classes10.dex */
abstract class zzw extends RectangularBounds {
    private final LatLng zza;
    private final LatLng zzb;

    public zzw(LatLng latLng, LatLng latLng2) {
        Objects.requireNonNull(latLng, "Null southwest");
        this.zza = latLng;
        Objects.requireNonNull(latLng2, "Null northeast");
        this.zzb = latLng2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RectangularBounds) {
            RectangularBounds rectangularBounds = (RectangularBounds) obj;
            if (this.zza.equals(rectangularBounds.getSouthwest()) && this.zzb.equals(rectangularBounds.getNortheast())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    @NonNull
    public LatLng getNortheast() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds
    @NonNull
    public LatLng getSouthwest() {
        return this.zza;
    }

    public int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(valueOf.length() + 41 + valueOf2.length());
        sb.append("RectangularBounds{southwest=");
        sb.append(valueOf);
        sb.append(", northeast=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
