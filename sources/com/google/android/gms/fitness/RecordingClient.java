package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzdo;
import com.google.android.gms.tasks.Task;
import java.util.List;
/* loaded from: classes6.dex */
public class RecordingClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final RecordingApi j = new zzdo();

    @ShowFirstParty
    public RecordingClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzaj.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<List<Subscription>> listSubscriptions() {
        return PendingResultUtil.toTask(j.listSubscriptions(asGoogleApiClient()), j.f8441a);
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    public Task<Void> subscribe(@NonNull DataType dataType) {
        return PendingResultUtil.toVoidTask(j.subscribe(asGoogleApiClient(), dataType));
    }

    @NonNull
    public Task<Void> unsubscribe(@NonNull DataType dataType) {
        return PendingResultUtil.toVoidTask(j.unsubscribe(asGoogleApiClient(), dataType));
    }

    public RecordingClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzaj.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    public Task<Void> subscribe(@NonNull DataSource dataSource) {
        return PendingResultUtil.toVoidTask(j.subscribe(asGoogleApiClient(), dataSource));
    }

    @NonNull
    public Task<Void> unsubscribe(@NonNull DataSource dataSource) {
        return PendingResultUtil.toVoidTask(j.unsubscribe(asGoogleApiClient(), dataSource));
    }

    @NonNull
    public Task<List<Subscription>> listSubscriptions(@NonNull DataType dataType) {
        return PendingResultUtil.toTask(j.listSubscriptions(asGoogleApiClient(), dataType), k.f8442a);
    }

    @NonNull
    public Task<Void> unsubscribe(@NonNull Subscription subscription) {
        return PendingResultUtil.toVoidTask(j.unsubscribe(asGoogleApiClient(), subscription));
    }
}
