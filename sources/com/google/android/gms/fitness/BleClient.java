package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.internal.fitness.zzk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
@Deprecated
/* loaded from: classes6.dex */
public class BleClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final BleApi j;

    static {
        BleApi zzenVar;
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            zzenVar = new zzco();
        } else {
            zzenVar = new zzen();
        }
        j = zzenVar;
    }

    @ShowFirstParty
    public BleClient(@NonNull Context context, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, zzk.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<Void> claimBleDevice(@NonNull BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(j.claimBleDevice(asGoogleApiClient(), bleDevice));
    }

    @NonNull
    public Task<List<BleDevice>> listClaimedBleDevices() {
        return PendingResultUtil.toTask(j.listClaimedBleDevices(asGoogleApiClient()), a.f8425a);
    }

    @NonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public Task<Void> startBleScan(@NonNull List<DataType> list, int i, @NonNull BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzen.zzqh));
        }
        ListenerHolder<L> registerListener = registerListener(bleScanCallback, BleScanCallback.class.getSimpleName());
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(registerListener).register(new b(this, registerListener, list, i)).unregister(new c(this, registerListener)).build());
    }

    @NonNull
    public Task<Boolean> stopBleScan(@NonNull BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzen.zzqh));
        }
        return doUnregisterEventListener(ListenerHolders.createListenerKey(bleScanCallback, BleScanCallback.class.getSimpleName()));
    }

    @NonNull
    public Task<Void> unclaimBleDevice(@NonNull String str) {
        return PendingResultUtil.toVoidTask(j.unclaimBleDevice(asGoogleApiClient(), str));
    }

    public BleClient(@NonNull Activity activity, @NonNull Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, zzk.zzoz, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @NonNull
    public Task<Void> claimBleDevice(@NonNull String str) {
        return PendingResultUtil.toVoidTask(j.claimBleDevice(asGoogleApiClient(), str));
    }

    @NonNull
    public Task<Void> unclaimBleDevice(@NonNull BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(j.unclaimBleDevice(asGoogleApiClient(), bleDevice));
    }
}
