package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public interface zzr extends IInterface {
    void zzd(LocationAvailability locationAvailability) throws RemoteException;

    void zze(LocationResult locationResult) throws RemoteException;

    void zzf() throws RemoteException;
}
