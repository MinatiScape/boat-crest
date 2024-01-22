package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.internal.zzdc;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FindAutocompletePredictionsRequest implements zzdc {

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public FindAutocompletePredictionsRequest build() {
            setCountries(zzgi.zza((Collection) getCountries()));
            return zza();
        }

        @Nullable
        public abstract CancellationToken getCancellationToken();

        @NonNull
        public abstract List<String> getCountries();

        @Nullable
        public abstract LocationBias getLocationBias();

        @Nullable
        public abstract LocationRestriction getLocationRestriction();

        @Nullable
        public abstract LatLng getOrigin();

        @Nullable
        public abstract String getQuery();

        @Nullable
        public abstract AutocompleteSessionToken getSessionToken();

        @Nullable
        public abstract TypeFilter getTypeFilter();

        @NonNull
        public abstract Builder setCancellationToken(@Nullable CancellationToken cancellationToken);

        @NonNull
        public abstract Builder setCountries(@NonNull List<String> list);

        @NonNull
        public Builder setCountries(@NonNull String... strArr) {
            return setCountries(zzgi.zza((Object[]) strArr));
        }

        @NonNull
        public Builder setCountry(@Nullable String str) {
            setCountries(str == null ? zzgi.zza() : zzgi.zza(str));
            return this;
        }

        @NonNull
        public abstract Builder setLocationBias(@Nullable LocationBias locationBias);

        @NonNull
        public abstract Builder setLocationRestriction(@Nullable LocationRestriction locationRestriction);

        @NonNull
        public abstract Builder setOrigin(@Nullable LatLng latLng);

        @NonNull
        public abstract Builder setQuery(@Nullable String str);

        @NonNull
        public abstract Builder setSessionToken(@Nullable AutocompleteSessionToken autocompleteSessionToken);

        @NonNull
        public abstract Builder setTypeFilter(@Nullable TypeFilter typeFilter);

        @NonNull
        public abstract FindAutocompletePredictionsRequest zza();
    }

    @NonNull
    public static Builder builder() {
        return new zzl().setCountries(new ArrayList());
    }

    @NonNull
    public static FindAutocompletePredictionsRequest newInstance(@Nullable String str) {
        return builder().setQuery(str).build();
    }

    @Override // com.google.android.libraries.places.internal.zzdc
    @Nullable
    public abstract CancellationToken getCancellationToken();

    @NonNull
    public abstract List<String> getCountries();

    @Nullable
    public String getCountry() {
        if (getCountries().size() <= 1) {
            Iterator<T> it = getCountries().iterator();
            return (String) (it.hasNext() ? it.next() : null);
        }
        throw new UnsupportedOperationException("Multiple countries found in this request - use getCountries() instead of getCountry().");
    }

    @Nullable
    public abstract LocationBias getLocationBias();

    @Nullable
    public abstract LocationRestriction getLocationRestriction();

    @Nullable
    public abstract LatLng getOrigin();

    @Nullable
    public abstract String getQuery();

    @Nullable
    public abstract AutocompleteSessionToken getSessionToken();

    @Nullable
    public abstract TypeFilter getTypeFilter();
}
