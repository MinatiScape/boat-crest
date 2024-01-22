package com.mappls.sdk.maps.location.engine;

import android.app.PendingIntent;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface LocationEngineImpl<T> {
    @NonNull
    T createListener(LocationEngineCallback<LocationEngineResult> locationEngineCallback);

    void getLastLocation(@NonNull LocationEngineCallback<LocationEngineResult> locationEngineCallback) throws SecurityException;

    void removeLocationUpdates(PendingIntent pendingIntent);

    void removeLocationUpdates(T t);

    void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull PendingIntent pendingIntent) throws SecurityException;

    void requestLocationUpdates(@NonNull LocationEngineRequest locationEngineRequest, @NonNull T t, @Nullable Looper looper) throws SecurityException;
}
