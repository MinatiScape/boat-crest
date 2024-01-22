package com.google.android.libraries.places.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes10.dex */
final class zzo extends LocationCallback {
    private final /* synthetic */ TaskCompletionSource zza;

    public zzo(zzk zzkVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationAvailability(LocationAvailability locationAvailability) {
        try {
            super.onLocationAvailability(locationAvailability);
            if (locationAvailability.isLocationAvailable()) {
                return;
            }
            this.zza.trySetException(new ApiException(new Status(8, "Location unavailable.")));
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationResult(LocationResult locationResult) {
        try {
            super.onLocationResult(locationResult);
            this.zza.trySetResult(locationResult.getLastLocation());
        } catch (Error | RuntimeException e) {
            zzdk.zza(e);
            throw e;
        }
    }
}
