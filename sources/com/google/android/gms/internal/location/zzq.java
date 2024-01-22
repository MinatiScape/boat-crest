package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
/* loaded from: classes8.dex */
public interface zzq extends IInterface {
    void zzb(Status status, Location location) throws RemoteException;
}
