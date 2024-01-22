package com.google.android.gms.fitness;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;
@Deprecated
/* loaded from: classes6.dex */
public interface BleApi {
    @NonNull
    PendingResult<Status> claimBleDevice(@NonNull GoogleApiClient googleApiClient, @NonNull BleDevice bleDevice);

    @NonNull
    PendingResult<Status> claimBleDevice(@NonNull GoogleApiClient googleApiClient, @NonNull String str);

    @NonNull
    PendingResult<BleDevicesResult> listClaimedBleDevices(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    @Deprecated
    PendingResult<Status> startBleScan(@NonNull GoogleApiClient googleApiClient, @NonNull StartBleScanRequest startBleScanRequest);

    @NonNull
    PendingResult<Status> stopBleScan(@NonNull GoogleApiClient googleApiClient, @NonNull BleScanCallback bleScanCallback);

    @NonNull
    PendingResult<Status> unclaimBleDevice(@NonNull GoogleApiClient googleApiClient, @NonNull BleDevice bleDevice);

    @NonNull
    PendingResult<Status> unclaimBleDevice(@NonNull GoogleApiClient googleApiClient, @NonNull String str);
}
