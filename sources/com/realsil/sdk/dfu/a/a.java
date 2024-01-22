package com.realsil.sdk.dfu.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.realsil.sdk.dfu.a.b;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.params.QcConfig;
/* loaded from: classes12.dex */
public interface a extends IInterface {
    int a() throws RemoteException;

    void a(String str, b bVar) throws RemoteException;

    boolean a(String str, DfuConfig dfuConfig, QcConfig qcConfig) throws RemoteException;

    boolean a(boolean z) throws RemoteException;

    Throughput b() throws RemoteException;

    boolean b(String str, b bVar) throws RemoteException;

    boolean c() throws RemoteException;

    /* renamed from: com.realsil.sdk.dfu.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static abstract class AbstractBinderC0719a extends Binder implements a {
        public AbstractBinderC0719a() {
            attachInterface(this, "com.realsil.sdk.dfu.IRealsilDfu");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.realsil.sdk.dfu.IRealsilDfu");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new C0720a(iBinder);
        }

        public static a d() {
            return C0720a.f13594a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        boolean a2 = a(parcel.readString(), parcel.readInt() != 0 ? DfuConfig.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? QcConfig.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeInt(a2 ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        boolean a3 = a(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        parcel2.writeInt(a3 ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        boolean c = c();
                        parcel2.writeNoException();
                        parcel2.writeInt(c ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        int a4 = a();
                        parcel2.writeNoException();
                        parcel2.writeInt(a4);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        Throughput b = b();
                        parcel2.writeNoException();
                        if (b != null) {
                            parcel2.writeInt(1);
                            b.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        boolean b2 = b(parcel.readString(), b.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(b2 ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface("com.realsil.sdk.dfu.IRealsilDfu");
                        a(parcel.readString(), b.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.realsil.sdk.dfu.IRealsilDfu");
            return true;
        }

        /* renamed from: com.realsil.sdk.dfu.a.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0720a implements a {

            /* renamed from: a  reason: collision with root package name */
            public static a f13594a;
            public IBinder b;

            public C0720a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // com.realsil.sdk.dfu.a.a
            public boolean a(String str, DfuConfig dfuConfig, QcConfig qcConfig) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    obtain.writeString(str);
                    if (dfuConfig != null) {
                        obtain.writeInt(1);
                        dfuConfig.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (qcConfig != null) {
                        obtain.writeInt(1);
                        qcConfig.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.b.transact(1, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        return AbstractBinderC0719a.d().a(str, dfuConfig, qcConfig);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.realsil.sdk.dfu.a.a
            public boolean b(String str, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.b.transact(6, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        return AbstractBinderC0719a.d().b(str, bVar);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.realsil.sdk.dfu.a.a
            public boolean c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    if (!this.b.transact(3, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        return AbstractBinderC0719a.d().c();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.realsil.sdk.dfu.a.a
            public boolean a(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.b.transact(2, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        return AbstractBinderC0719a.d().a(z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.realsil.sdk.dfu.a.a
            public int a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    if (!this.b.transact(4, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        return AbstractBinderC0719a.d().a();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.realsil.sdk.dfu.a.a
            public void a(String str, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.realsil.sdk.dfu.IRealsilDfu");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.b.transact(7, obtain, obtain2, 0) && AbstractBinderC0719a.d() != null) {
                        AbstractBinderC0719a.d().a(str, bVar);
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
