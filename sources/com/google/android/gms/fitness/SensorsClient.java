package com.google.android.gms.fitness;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.internal.fitness.zzap;
import com.google.android.gms.internal.fitness.zzdx;
import com.google.android.gms.tasks.Task;
import java.util.List;
/* loaded from: classes6.dex */
public class SensorsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final SensorsApi j = new zzdx();

    @ShowFirstParty
    public SensorsClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzap.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    public Task<Void> add(@NonNull SensorRequest sensorRequest, @NonNull OnDataPointListener onDataPointListener) {
        ListenerHolder<L> registerListener = registerListener(onDataPointListener, OnDataPointListener.class.getSimpleName());
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(registerListener).register(new m(this, registerListener, sensorRequest)).unregister(new n(this, registerListener)).build());
    }

    @NonNull
    public Task<List<DataSource>> findDataSources(@NonNull DataSourcesRequest dataSourcesRequest) {
        return PendingResultUtil.toTask(j.findDataSources(asGoogleApiClient(), dataSourcesRequest), l.f8443a);
    }

    @NonNull
    public Task<Boolean> remove(@NonNull OnDataPointListener onDataPointListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onDataPointListener, OnDataPointListener.class.getSimpleName()));
    }

    public SensorsClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzap.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<Void> remove(@NonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(j.remove(asGoogleApiClient(), pendingIntent));
    }

    @NonNull
    @RequiresPermission(anyOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional = true)
    @SuppressLint({"InlinedApi"})
    public Task<Void> add(@NonNull SensorRequest sensorRequest, @NonNull PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(j.add(asGoogleApiClient(), sensorRequest, pendingIntent));
    }
}
