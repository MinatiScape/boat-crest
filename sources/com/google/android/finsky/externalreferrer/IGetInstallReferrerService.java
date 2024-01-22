package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.a;
import com.google.android.a.b;
import com.google.android.a.c;
/* loaded from: classes6.dex */
public interface IGetInstallReferrerService extends IInterface {

    /* loaded from: classes6.dex */
    public static abstract class Stub extends b implements IGetInstallReferrerService {

        /* loaded from: classes6.dex */
        public static class Proxy extends a implements IGetInstallReferrerService {
            public Proxy(IBinder iBinder) {
                super(iBinder);
            }

            @Override // com.google.android.finsky.externalreferrer.IGetInstallReferrerService
            public final Bundle c(Bundle bundle) throws RemoteException {
                Parcel a2 = a();
                c.b(a2, bundle);
                Parcel b = b(a2);
                Bundle bundle2 = (Bundle) c.a(b, Bundle.CREATOR);
                b.recycle();
                return bundle2;
            }
        }

        public static IGetInstallReferrerService b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            if (queryLocalInterface instanceof IGetInstallReferrerService) {
                return (IGetInstallReferrerService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // com.google.android.a.b
        public final boolean a(int i, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i == 1) {
                Bundle c = c((Bundle) c.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                c.c(parcel2, c);
                return true;
            }
            return false;
        }
    }

    Bundle c(Bundle bundle) throws RemoteException;
}
