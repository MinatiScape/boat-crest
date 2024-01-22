package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.Place;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FetchPlaceResponse {
    @NonNull
    public static FetchPlaceResponse newInstance(@NonNull Place place) {
        return new zzg(place);
    }

    @NonNull
    public abstract Place getPlace();
}
