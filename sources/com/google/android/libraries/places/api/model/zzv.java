package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.RectangularBounds;
import java.util.Objects;
/* loaded from: classes10.dex */
final class zzv extends RectangularBounds.zza {
    private LatLng zza;
    private LatLng zzb;

    @Override // com.google.android.libraries.places.api.model.RectangularBounds.zza
    public final RectangularBounds.zza zza(LatLng latLng) {
        Objects.requireNonNull(latLng, "Null southwest");
        this.zza = latLng;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds.zza
    public final RectangularBounds.zza zzb(LatLng latLng) {
        Objects.requireNonNull(latLng, "Null northeast");
        this.zzb = latLng;
        return this;
    }

    @Override // com.google.android.libraries.places.api.model.RectangularBounds.zza
    public final RectangularBounds zza() {
        String concat = this.zza == null ? "".concat(" southwest") : "";
        if (this.zzb == null) {
            concat = String.valueOf(concat).concat(" northeast");
        }
        if (!concat.isEmpty()) {
            throw new IllegalStateException(concat.length() != 0 ? "Missing required properties:".concat(concat) : new String("Missing required properties:"));
        }
        return new zzax(this.zza, this.zzb);
    }
}
