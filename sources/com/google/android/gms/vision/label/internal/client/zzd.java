package com.google.android.gms.vision.label.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public abstract class zzd extends com.google.android.gms.internal.vision.zza implements zza {
    public zzd() {
        super("com.google.android.gms.vision.label.internal.client.INativeImageLabelerCreator");
    }

    public static zza asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.label.internal.client.INativeImageLabelerCreator");
        if (queryLocalInterface instanceof zza) {
            return (zza) queryLocalInterface;
        }
        return new zzc(iBinder);
    }

    @Override // com.google.android.gms.internal.vision.zza
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            INativeImageLabeler newImageLabeler = newImageLabeler(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (ImageLabelerOptions) com.google.android.gms.internal.vision.zzd.zza(parcel, ImageLabelerOptions.CREATOR));
            parcel2.writeNoException();
            com.google.android.gms.internal.vision.zzd.zza(parcel2, newImageLabeler);
            return true;
        }
        return false;
    }
}
