package com.google.android.gms.location;

import android.app.PendingIntent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import java.util.List;
/* loaded from: classes10.dex */
public interface GeofencingClient extends HasApiKey<Api.ApiOptions.NoOptions> {
    @NonNull
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    Task<Void> addGeofences(@NonNull GeofencingRequest geofencingRequest, @NonNull PendingIntent pendingIntent);

    @NonNull
    Task<Void> removeGeofences(@NonNull PendingIntent pendingIntent);

    @NonNull
    Task<Void> removeGeofences(@NonNull List<String> list);
}
