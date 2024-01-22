package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface ITelephony extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements ITelephony {
        @Override // com.android.internal.telephony.ITelephony
        public void answerRingingCall() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephony
        public boolean endCall() throws RemoteException {
            return false;
        }

        @Override // com.android.internal.telephony.ITelephony
        public void silenceRinger() throws RemoteException {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ITelephony {

        /* loaded from: classes.dex */
        public static class a implements ITelephony {
            public static ITelephony b;

            /* renamed from: a  reason: collision with root package name */
            public IBinder f2142a;

            public a(IBinder iBinder) {
                this.f2142a = iBinder;
            }

            @Override // com.android.internal.telephony.ITelephony
            public void answerRingingCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    if (!this.f2142a.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().answerRingingCall();
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
                return this.f2142a;
            }

            @Override // com.android.internal.telephony.ITelephony
            public void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeFloat(f);
                    obtain.writeDouble(d);
                    obtain.writeString(str);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.f2142a.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().basicTypes(i, j, z, f, d, str);
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    obtain2.readException();
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public boolean endCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    if (!this.f2142a.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endCall();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephony
            public void silenceRinger() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.android.internal.telephony.ITelephony");
                    if (!this.f2142a.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().silenceRinger();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.android.internal.telephony.ITelephony");
        }

        public static ITelephony asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.android.internal.telephony.ITelephony");
            if (queryLocalInterface != null && (queryLocalInterface instanceof ITelephony)) {
                return (ITelephony) queryLocalInterface;
            }
            return new a(iBinder);
        }

        public static ITelephony getDefaultImpl() {
            return a.b;
        }

        public static boolean setDefaultImpl(ITelephony iTelephony) {
            if (a.b == null) {
                if (iTelephony != null) {
                    a.b = iTelephony;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                basicTypes(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean endCall = endCall();
                parcel2.writeNoException();
                parcel2.writeInt(endCall ? 1 : 0);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                answerRingingCall();
                parcel2.writeNoException();
                return true;
            } else if (i != 4) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.android.internal.telephony.ITelephony");
                return true;
            } else {
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                silenceRinger();
                parcel2.writeNoException();
                return true;
            }
        }
    }

    void answerRingingCall() throws RemoteException;

    void basicTypes(int i, long j, boolean z, float f, double d, String str) throws RemoteException;

    boolean endCall() throws RemoteException;

    void silenceRinger() throws RemoteException;
}
