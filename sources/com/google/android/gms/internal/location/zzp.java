package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes8.dex */
public abstract class zzp extends zzb implements zzq {
    public zzp() {
        super("com.google.android.gms.location.internal.ILocationStatusCallback");
    }

    @Override // com.google.android.gms.internal.location.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc.zzb(parcel);
            zzb((Status) zzc.zza(parcel, Status.CREATOR), (Location) zzc.zza(parcel, Location.CREATOR));
            return true;
        }
        return false;
    }
}
