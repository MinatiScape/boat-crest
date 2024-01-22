package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public abstract class zzau extends com.google.android.gms.internal.maps.zzb implements zzav {
    public zzau() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzd(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        } else if (i == 2) {
            zzb(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        } else if (i != 3) {
            return false;
        } else {
            zzc(com.google.android.gms.internal.maps.zzw.zzb(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }
}
