package com.google.android.libraries.places.api.net;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
public interface PlacesClient {
    @NonNull
    Task<FetchPhotoResponse> fetchPhoto(@NonNull FetchPhotoRequest fetchPhotoRequest);

    @NonNull
    Task<FetchPlaceResponse> fetchPlace(@NonNull FetchPlaceRequest fetchPlaceRequest);

    @NonNull
    Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(@NonNull FindAutocompletePredictionsRequest findAutocompletePredictionsRequest);

    @NonNull
    @RequiresPermission(allOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"})
    Task<FindCurrentPlaceResponse> findCurrentPlace(@NonNull FindCurrentPlaceRequest findCurrentPlaceRequest);
}
