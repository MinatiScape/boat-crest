package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
/* loaded from: classes10.dex */
public abstract class zzbk extends com.google.android.gms.internal.maps.zzb implements zzbl {
    public zzbk() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaLocation.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
