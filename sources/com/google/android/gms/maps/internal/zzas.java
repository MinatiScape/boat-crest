package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public abstract class zzas extends com.google.android.gms.internal.maps.zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            boolean zzb = zzb(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
            parcel2.writeNoException();
            com.google.android.gms.internal.maps.zzc.zzc(parcel2, zzb);
            return true;
        }
        return false;
    }
}
