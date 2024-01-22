package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FindCurrentPlaceResponse {
    @NonNull
    public static FindCurrentPlaceResponse newInstance(@NonNull List<PlaceLikelihood> list) {
        return new zzo(zzgi.zza((Collection) list));
    }

    @NonNull
    public abstract List<PlaceLikelihood> getPlaceLikelihoods();
}
