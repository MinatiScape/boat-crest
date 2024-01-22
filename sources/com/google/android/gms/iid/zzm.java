package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public final class zzm extends com.google.android.gms.internal.gcm.zzd implements zzl {
    public zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    @Override // com.google.android.gms.iid.zzl
    public final void send(Message message) throws RemoteException {
        Parcel zzd = zzd();
        com.google.android.gms.internal.gcm.zze.zzd(zzd, message);
        zze(1, zzd);
    }
}
