package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zac;
/* loaded from: classes6.dex */
public final class zal extends com.google.android.gms.internal.base.zaa {
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public final void zae(zak zakVar) throws RemoteException {
        Parcel zaa = zaa();
        zac.zae(zaa, zakVar);
        zad(1, zaa);
    }
}
