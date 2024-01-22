package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public final class zzs extends com.google.android.gms.internal.location.zza implements zzu {
    public zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    @Override // com.google.android.gms.location.zzu
    public final void zzd(Location location) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.location.zzu
    public final void zze() throws RemoteException {
        throw null;
    }
}
