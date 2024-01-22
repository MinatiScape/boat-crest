package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.gcm.zzd;
/* loaded from: classes6.dex */
public final class zzh extends zzd implements zzg {
    public zzh(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }

    @Override // com.google.android.gms.gcm.zzg
    public final void zzf(int i) throws RemoteException {
        Parcel zzd = zzd();
        zzd.writeInt(i);
        zzd(2, zzd);
    }
}
