package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class k implements IGmsServiceBroker {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8336a;

    public k(IBinder iBinder) {
        this.f8336a = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f8336a;
    }

    @Override // com.google.android.gms.common.internal.IGmsServiceBroker
    public final void getService(IGmsCallbacks iGmsCallbacks, @Nullable GetServiceRequest getServiceRequest) throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder(iGmsCallbacks != null ? iGmsCallbacks.asBinder() : null);
            if (getServiceRequest != null) {
                obtain.writeInt(1);
                zzn.a(getServiceRequest, obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f8336a.transact(46, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
