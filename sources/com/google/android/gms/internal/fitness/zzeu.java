package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;
/* loaded from: classes8.dex */
public abstract class zzeu extends zza implements zzev {
    public zzeu() {
        super("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }

    @Override // com.google.android.gms.internal.fitness.zza
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((zzer) zzd.zza(parcel, zzer.CREATOR), zzbg.zzd(parcel.readStrongBinder()));
        } else if (i == 2) {
            zza((FitnessSensorServiceRequest) zzd.zza(parcel, FitnessSensorServiceRequest.CREATOR), zzcm.zzj(parcel.readStrongBinder()));
        } else if (i != 3) {
            return false;
        } else {
            zza((zzet) zzd.zza(parcel, zzet.CREATOR), zzcm.zzj(parcel.readStrongBinder()));
        }
        return true;
    }
}
