package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zze;
import com.google.android.gms.fitness.result.BleDevicesResult;
/* loaded from: classes8.dex */
public final class zzco implements BleApi {
    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new z(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new a0(this, googleApiClient));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> startBleScan(GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest) {
        return googleApiClient.enqueue(new x(this, googleApiClient, startBleScanRequest, zze.zzu().zza((BleScanCallback) Preconditions.checkNotNull(startBleScanRequest.zzaa()), googleApiClient.getLooper())));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> stopBleScan(GoogleApiClient googleApiClient, BleScanCallback bleScanCallback) {
        com.google.android.gms.fitness.request.zza zzb = zze.zzu().zzb(bleScanCallback, googleApiClient.getLooper());
        if (zzb == null) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.enqueue(new w(this, googleApiClient, zzb));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new b0(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return googleApiClient.execute(new y(this, googleApiClient, bleDevice));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return unclaimBleDevice(googleApiClient, bleDevice.getAddress());
    }
}
