package com.mappls.sdk.navigation.ui.utils;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
/* loaded from: classes11.dex */
public class c implements LocationEngine {
    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void getLastLocation(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
        if (locationEngineCallback != null) {
            locationEngineCallback.onSuccess(LocationEngineResult.create((Location) null));
        }
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void removeLocationUpdates(PendingIntent pendingIntent) {
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void removeLocationUpdates(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) {
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, PendingIntent pendingIntent) {
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngine
    public void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback, @Nullable Looper looper) {
    }
}
