package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public interface u extends IInterface {

    /* loaded from: classes10.dex */
    public static abstract class a extends Binder implements u {

        /* renamed from: com.google.android.youtube.player.internal.u$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static class C0451a implements u {

            /* renamed from: a  reason: collision with root package name */
            public IBinder f10513a;

            public C0451a(IBinder iBinder) {
                this.f10513a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f10513a;
            }
        }

        public a() {
            attachInterface(this, "com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
        }

        public static u a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof u)) ? new C0451a(iBinder) : (u) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
            return true;
        }
    }
}
