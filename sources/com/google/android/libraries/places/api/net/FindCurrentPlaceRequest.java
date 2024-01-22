package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzdc;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FindCurrentPlaceRequest implements zzdc {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public FindCurrentPlaceRequest build() {
            zza(zzgi.zza((Collection) zza().getPlaceFields()));
            return zza();
        }

        @Nullable
        public abstract CancellationToken getCancellationToken();

        @NonNull
        public abstract Builder setCancellationToken(@Nullable CancellationToken cancellationToken);

        @NonNull
        public abstract Builder zza(@NonNull List<Place.Field> list);

        @NonNull
        public abstract FindCurrentPlaceRequest zza();
    }

    @NonNull
    public static Builder builder(@NonNull List<Place.Field> list) {
        return new zzp().zza(list);
    }

    @NonNull
    public static FindCurrentPlaceRequest newInstance(@NonNull List<Place.Field> list) {
        return builder(list).build();
    }

    @Override // com.google.android.libraries.places.internal.zzdc
    @Nullable
    public abstract CancellationToken getCancellationToken();

    @NonNull
    public abstract List<Place.Field> getPlaceFields();
}
