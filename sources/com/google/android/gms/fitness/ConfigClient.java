package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.internal.fitness.zzcw;
import com.google.android.gms.internal.fitness.zzq;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public class ConfigClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final ConfigApi j = new zzcw();

    @ShowFirstParty
    public ConfigClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzq.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static final /* synthetic */ DataType c(DataTypeResult dataTypeResult) {
        return (DataType) Preconditions.checkNotNull(dataTypeResult.getDataType());
    }

    public static final /* synthetic */ DataType d(DataTypeResult dataTypeResult) {
        return (DataType) Preconditions.checkNotNull(dataTypeResult.getDataType());
    }

    @NonNull
    @Deprecated
    public Task<DataType> createCustomDataType(@NonNull DataTypeCreateRequest dataTypeCreateRequest) {
        return PendingResultUtil.toTask(j.createCustomDataType(asGoogleApiClient(), dataTypeCreateRequest), e.f8437a);
    }

    @NonNull
    public Task<Void> disableFit() {
        return PendingResultUtil.toVoidTask(j.disableFit(asGoogleApiClient()));
    }

    @NonNull
    @Deprecated
    public Task<DataType> readDataType(@NonNull String str) {
        return PendingResultUtil.toTask(j.readDataType(asGoogleApiClient(), str), d.f8428a);
    }

    public ConfigClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzq.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
