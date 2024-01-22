package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;
/* loaded from: classes8.dex */
public abstract class zzr extends zzb implements zzs {
    public zzr() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc.zzb(parcel);
            zzb((LocationSettingsResult) zzc.zza(parcel, LocationSettingsResult.CREATOR));
            return true;
        }
        return false;
    }
}
