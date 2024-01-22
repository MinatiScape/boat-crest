package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.zzaw;
import com.google.android.gms.fitness.request.zzay;
/* loaded from: classes8.dex */
public final class zzcd extends zzb implements zzca {
    public zzcd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(zzay zzayVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzayVar);
        zza(1, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(com.google.android.gms.fitness.request.zzba zzbaVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbaVar);
        zza(2, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, sessionInsertRequest);
        zza(3, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(SessionReadRequest sessionReadRequest) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, sessionReadRequest);
        zza(4, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(zzaw zzawVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzawVar);
        zza(5, zza);
    }

    @Override // com.google.android.gms.internal.fitness.zzca
    public final void zza(com.google.android.gms.fitness.request.zzbc zzbcVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbcVar);
        zza(6, zza);
    }
}
