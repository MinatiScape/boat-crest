package com.google.android.libraries.places.api.model;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class RectangularBounds implements LocationBias, LocationRestriction {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class zza {
        @NonNull
        public abstract zza zza(LatLng latLng);

        @NonNull
        public abstract RectangularBounds zza();

        @NonNull
        public abstract zza zzb(LatLng latLng);
    }

    @NonNull
    public static RectangularBounds newInstance(@NonNull LatLngBounds latLngBounds) {
        return new zzv().zza(latLngBounds.southwest).zzb(latLngBounds.northeast).zza();
    }

    @NonNull
    public abstract LatLng getNortheast();

    @NonNull
    public abstract LatLng getSouthwest();

    @NonNull
    public static RectangularBounds newInstance(@NonNull LatLng latLng, @NonNull LatLng latLng2) {
        return newInstance(new LatLngBounds(latLng, latLng2));
    }
}
