package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.internal.fitness.zzad;
import com.google.android.gms.internal.fitness.zzde;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public class HistoryClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final HistoryApi j = new zzde();

    @ShowFirstParty
    public HistoryClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzad.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static final /* synthetic */ DataSet c(DailyTotalResult dailyTotalResult) {
        return (DataSet) Preconditions.checkNotNull(dailyTotalResult.getTotal());
    }

    public static final /* synthetic */ DataSet d(DailyTotalResult dailyTotalResult) {
        return (DataSet) Preconditions.checkNotNull(dailyTotalResult.getTotal());
    }

    @NonNull
    public Task<Void> deleteData(@NonNull DataDeleteRequest dataDeleteRequest) {
        return PendingResultUtil.toVoidTask(j.deleteData(asGoogleApiClient(), dataDeleteRequest));
    }

    @NonNull
    public Task<Void> insertData(@NonNull DataSet dataSet) {
        return PendingResultUtil.toVoidTask(j.insertData(asGoogleApiClient(), dataSet));
    }

    @NonNull
    public Task<DataSet> readDailyTotal(@NonNull DataType dataType) {
        return PendingResultUtil.toTask(j.readDailyTotal(asGoogleApiClient(), dataType), h.f8439a);
    }

    @NonNull
    public Task<DataSet> readDailyTotalFromLocalDevice(@NonNull DataType dataType) {
        return PendingResultUtil.toTask(j.readDailyTotalFromLocalDevice(asGoogleApiClient(), dataType), i.f8440a);
    }

    @NonNull
    public Task<DataReadResponse> readData(@NonNull DataReadRequest dataReadRequest) {
        return PendingResultUtil.toResponseTask(j.readData(asGoogleApiClient(), dataReadRequest), new DataReadResponse());
    }

    @NonNull
    public Task<Void> registerDataUpdateListener(@NonNull DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return PendingResultUtil.toVoidTask(j.registerDataUpdateListener(asGoogleApiClient(), dataUpdateListenerRegistrationRequest));
    }

    @NonNull
    public Task<Void> unregisterDataUpdateListener(@NonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(j.unregisterDataUpdateListener(asGoogleApiClient(), pendingIntent));
    }

    @NonNull
    public Task<Void> updateData(@NonNull DataUpdateRequest dataUpdateRequest) {
        return PendingResultUtil.toVoidTask(j.updateData(asGoogleApiClient(), dataUpdateRequest));
    }

    public HistoryClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzad.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
