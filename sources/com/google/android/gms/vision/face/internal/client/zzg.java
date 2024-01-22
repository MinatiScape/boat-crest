package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzp;
/* loaded from: classes10.dex */
public abstract class zzg extends com.google.android.gms.internal.vision.zza implements zzh {
    public zzg() {
        super("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    @Override // com.google.android.gms.internal.vision.zza
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            FaceParcel[] zzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzp) com.google.android.gms.internal.vision.zzd.zza(parcel, zzp.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedArray(zzc, 1);
        } else if (i == 2) {
            boolean zzd = zzd(parcel.readInt());
            parcel2.writeNoException();
            com.google.android.gms.internal.vision.zzd.writeBoolean(parcel2, zzd);
        } else if (i == 3) {
            zzm();
            parcel2.writeNoException();
        } else if (i != 4) {
            return false;
        } else {
            FaceParcel[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), (zzp) com.google.android.gms.internal.vision.zzd.zza(parcel, zzp.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedArray(zza, 1);
        }
        return true;
    }
}
