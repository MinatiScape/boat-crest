package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;
/* loaded from: classes8.dex */
public final class zzck extends zzb implements zzci {
    public zzck(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzci
    public final void zza(SessionStopResult sessionStopResult) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, sessionStopResult);
        zzb(1, zza);
    }
}
