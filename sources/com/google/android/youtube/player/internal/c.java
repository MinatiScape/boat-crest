package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public interface c extends IInterface {

    /* loaded from: classes10.dex */
    public static abstract class a extends Binder implements c {

        /* renamed from: com.google.android.youtube.player.internal.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static class C0440a implements c {

            /* renamed from: a  reason: collision with root package name */
            public IBinder f10491a;

            public C0440a(IBinder iBinder) {
                this.f10491a = iBinder;
            }

            @Override // com.google.android.youtube.player.internal.c
            public final void a(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IConnectionCallbacks");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    this.f10491a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f10491a;
            }
        }

        public a() {
            attachInterface(this, "com.google.android.youtube.player.internal.IConnectionCallbacks");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.google.android.youtube.player.internal.IConnectionCallbacks");
                return true;
            }
            parcel.enforceInterface("com.google.android.youtube.player.internal.IConnectionCallbacks");
            a(parcel.readString(), parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }
    }

    void a(String str, IBinder iBinder) throws RemoteException;
}
