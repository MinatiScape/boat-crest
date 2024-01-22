package com.google.android.gms.vision.label.internal.client;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes10.dex */
public interface INativeImageLabeler extends IInterface {

    /* loaded from: classes10.dex */
    public static abstract class zza extends com.google.android.gms.internal.vision.zza implements INativeImageLabeler {
        public zza() {
            super("com.google.android.gms.vision.label.internal.client.INativeImageLabeler");
        }

        @Override // com.google.android.gms.internal.vision.zza
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                zzf[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (LabelOptions) com.google.android.gms.internal.vision.zzd.zza(parcel, LabelOptions.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedArray(zza, 1);
            } else if (i != 2) {
                return false;
            } else {
                zzq();
                parcel2.writeNoException();
            }
            return true;
        }
    }

    zzf[] zza(IObjectWrapper iObjectWrapper, LabelOptions labelOptions) throws RemoteException;

    void zzq() throws RemoteException;
}
