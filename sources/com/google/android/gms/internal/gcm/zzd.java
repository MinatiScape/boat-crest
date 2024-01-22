package com.google.android.gms.internal.gcm;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes8.dex */
public class zzd implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8873a;
    public final String b;

    public zzd(IBinder iBinder, String str) {
        this.f8873a = iBinder;
        this.b = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f8873a;
    }

    public final Parcel zzd() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final void zze(int i, Parcel parcel) throws RemoteException {
        try {
            this.f8873a.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }

    public final void zzd(int i, Parcel parcel) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        try {
            this.f8873a.transact(2, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }
}
