package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
/* loaded from: classes10.dex */
public interface zzek {
    @NonNull
    Task<FetchPlaceResponse> zza(@NonNull AutocompletePrediction autocompletePrediction);

    @NonNull
    Task<FindAutocompletePredictionsResponse> zza(@NonNull String str);

    void zza();
}
