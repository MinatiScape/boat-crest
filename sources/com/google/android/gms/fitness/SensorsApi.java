package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
@Deprecated
/* loaded from: classes6.dex */
public interface SensorsApi {
    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    PendingResult<Status> add(@NonNull GoogleApiClient googleApiClient, @NonNull SensorRequest sensorRequest, @NonNull PendingIntent pendingIntent);

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    PendingResult<Status> add(@NonNull GoogleApiClient googleApiClient, @NonNull SensorRequest sensorRequest, @NonNull OnDataPointListener onDataPointListener);

    @NonNull
    PendingResult<DataSourcesResult> findDataSources(@NonNull GoogleApiClient googleApiClient, @NonNull DataSourcesRequest dataSourcesRequest);

    @NonNull
    PendingResult<Status> remove(@NonNull GoogleApiClient googleApiClient, @NonNull PendingIntent pendingIntent);

    @NonNull
    PendingResult<Status> remove(@NonNull GoogleApiClient googleApiClient, @NonNull OnDataPointListener onDataPointListener);
}
