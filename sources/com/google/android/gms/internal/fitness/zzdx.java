package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.zzv;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzam;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.result.DataSourcesResult;
/* loaded from: classes8.dex */
public final class zzdx implements SensorsApi {
    public final PendingResult<Status> a(GoogleApiClient googleApiClient, @Nullable zzv zzvVar, @Nullable PendingIntent pendingIntent) {
        return googleApiClient.execute(new z0(this, googleApiClient, zzvVar, pendingIntent));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        return b(googleApiClient, sensorRequest, zzan.zzx().zza(onDataPointListener, googleApiClient.getLooper()), null);
    }

    public final PendingResult<Status> b(GoogleApiClient googleApiClient, SensorRequest sensorRequest, @Nullable zzv zzvVar, @Nullable PendingIntent pendingIntent) {
        return googleApiClient.enqueue(new a1(this, googleApiClient, sensorRequest, zzvVar, pendingIntent));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<DataSourcesResult> findDataSources(GoogleApiClient googleApiClient, DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.enqueue(new y0(this, googleApiClient, dataSourcesRequest));
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, OnDataPointListener onDataPointListener) {
        zzam zzb = zzan.zzx().zzb(onDataPointListener, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return a(googleApiClient, zzb, null);
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> add(GoogleApiClient googleApiClient, SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return b(googleApiClient, sensorRequest, null, pendingIntent);
    }

    @Override // com.google.android.gms.fitness.SensorsApi
    public final PendingResult<Status> remove(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return a(googleApiClient, null, pendingIntent);
    }
}
