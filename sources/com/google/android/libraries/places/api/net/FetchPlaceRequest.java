package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.internal.zzdc;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FetchPlaceRequest implements zzdc {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public FetchPlaceRequest build() {
            zza(zzgi.zza((Collection) zza().getPlaceFields()));
            return zza();
        }

        @Nullable
        public abstract CancellationToken getCancellationToken();

        @Nullable
        public abstract AutocompleteSessionToken getSessionToken();

        @NonNull
        public abstract Builder setCancellationToken(@Nullable CancellationToken cancellationToken);

        @NonNull
        public abstract Builder setSessionToken(@Nullable AutocompleteSessionToken autocompleteSessionToken);

        @NonNull
        public abstract Builder zza(@NonNull String str);

        @NonNull
        public abstract Builder zza(@NonNull List<Place.Field> list);

        @NonNull
        public abstract FetchPlaceRequest zza();
    }

    @NonNull
    public static Builder builder(@NonNull String str, @NonNull List<Place.Field> list) {
        return new zzh().zza(str).zza(list);
    }

    @NonNull
    public static FetchPlaceRequest newInstance(@NonNull String str, @NonNull List<Place.Field> list) {
        return builder(str, list).build();
    }

    @Override // com.google.android.libraries.places.internal.zzdc
    @Nullable
    public abstract CancellationToken getCancellationToken();

    @NonNull
    public abstract List<Place.Field> getPlaceFields();

    @NonNull
    public abstract String getPlaceId();

    @Nullable
    public abstract AutocompleteSessionToken getSessionToken();
}
