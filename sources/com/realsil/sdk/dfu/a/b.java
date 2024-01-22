package com.realsil.sdk.dfu.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
/* loaded from: classes12.dex */
public interface b extends IInterface {
    void a(int i) throws RemoteException;

    void a(DfuProgressInfo dfuProgressInfo) throws RemoteException;

    void b(int i) throws RemoteException;

    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.realsil.sdk.dfu.IRealsilDfuCallback");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.realsil.sdk.dfu.IRealsilDfuCallback");
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0721a(iBinder);
        }

        public static b d() {
            return C0721a.f13595a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfuCallback");
                b(parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfuCallback");
                a(parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.realsil.sdk.dfu.IRealsilDfuCallback");
                return true;
            } else {
                parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfuCallback");
                a(parcel.readInt() != 0 ? DfuProgressInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
        }

        /* renamed from: com.realsil.sdk.dfu.a.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0721a implements b {

            /* renamed from: a  reason: collision with root package name */
            public static b f13595a;
            public IBinder b;

            public C0721a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // com.realsil.sdk.dfu.a.b
            public void a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfuCallback");
                    obtain.writeInt(i);
                    if (!this.b.transact(2, obtain, obtain2, 0) && a.d() != null) {
                        a.d().a(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.realsil.sdk.dfu.a.b
            public void b(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfuCallback");
                    obtain.writeInt(i);
                    if (!this.b.transact(1, obtain, obtain2, 0) && a.d() != null) {
                        a.d().b(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.realsil.sdk.dfu.a.b
            public void a(DfuProgressInfo dfuProgressInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfuCallback");
                    if (dfuProgressInfo != null) {
                        obtain.writeInt(1);
                        dfuProgressInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(3, obtain, obtain2, 0) && a.d() != null) {
                        a.d().a(dfuProgressInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
