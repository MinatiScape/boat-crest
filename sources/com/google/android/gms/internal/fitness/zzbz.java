package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.zzak;
/* loaded from: classes8.dex */
public final class zzbz extends zzb implements zzbw {
    public zzbz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbw
    public final void zza(com.google.android.gms.fitness.request.zzbi zzbiVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbiVar);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbw
    public final void zza(com.google.android.gms.fitness.request.zzbm zzbmVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbmVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzbw
    public final void zza(zzak zzakVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzakVar);
        zza(3, zza);
    }
}
