package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f10467a;
    public final String b = "com.google.android.play.core.integrity.protocol.IIntegrityService";

    public a(IBinder iBinder, String str) {
        this.f10467a = iBinder;
    }

    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f10467a;
    }

    public final void b(int i, Parcel parcel) throws RemoteException {
        try {
            this.f10467a.transact(2, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
