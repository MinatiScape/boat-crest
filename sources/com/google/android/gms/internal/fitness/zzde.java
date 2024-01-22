package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
/* loaded from: classes8.dex */
public final class zzde implements HistoryApi {
    public final PendingResult<DailyTotalResult> a(GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        return googleApiClient.enqueue(new q0(this, googleApiClient, dataType, z));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        return googleApiClient.enqueue(new j0(this, googleApiClient, dataDeleteRequest));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet) {
        Preconditions.checkNotNull(dataSet, "Must set the data set");
        Preconditions.checkState(!dataSet.getDataPoints().isEmpty(), "Cannot use an empty data set");
        Preconditions.checkNotNull(dataSet.getDataSource().zzj(), "Must set the app package name for the data source");
        return googleApiClient.enqueue(new k0(this, googleApiClient, dataSet, false));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient googleApiClient, DataType dataType) {
        return a(googleApiClient, dataType, false);
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<DailyTotalResult> readDailyTotalFromLocalDevice(GoogleApiClient googleApiClient, DataType dataType) {
        return a(googleApiClient, dataType, true);
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        return googleApiClient.enqueue(new n0(this, googleApiClient, dataReadRequest));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<Status> registerDataUpdateListener(GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return googleApiClient.enqueue(new l0(this, googleApiClient, dataUpdateListenerRegistrationRequest));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<Status> unregisterDataUpdateListener(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return googleApiClient.execute(new o0(this, googleApiClient, pendingIntent));
    }

    @Override // com.google.android.gms.fitness.HistoryApi
    public final PendingResult<Status> updateData(GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        Preconditions.checkNotNull(dataUpdateRequest.getDataSet(), "Must set the data set");
        Preconditions.checkNotZero(dataUpdateRequest.zzv(), "Must set a non-zero value for startTimeMillis/startTime");
        Preconditions.checkNotZero(dataUpdateRequest.zzw(), "Must set a non-zero value for endTimeMillis/endTime");
        return googleApiClient.enqueue(new m0(this, googleApiClient, dataUpdateRequest));
    }
}
