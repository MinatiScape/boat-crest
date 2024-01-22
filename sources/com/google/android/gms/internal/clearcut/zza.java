package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes7.dex */
public class zza implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8609a;
    public final String b;

    public zza(IBinder iBinder, String str) {
        this.f8609a = iBinder;
        this.b = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.f8609a;
    }

    public final Parcel obtainAndWriteInterfaceToken() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final void transactOneway(int i, Parcel parcel) throws RemoteException {
        try {
            this.f8609a.transact(1, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
