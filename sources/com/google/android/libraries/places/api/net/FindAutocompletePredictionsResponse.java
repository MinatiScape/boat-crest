package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzgi;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class FindAutocompletePredictionsResponse {
    @NonNull
    public static FindAutocompletePredictionsResponse newInstance(@NonNull List<AutocompletePrediction> list) {
        return new zzk(zzgi.zza((Collection) list));
    }

    @NonNull
    public abstract List<AutocompletePrediction> getAutocompletePredictions();
}
