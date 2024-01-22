package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes10.dex */
public final class f extends a implements h {
    public f(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.integrity.protocol.IIntegrityService");
    }

    @Override // com.google.android.play.integrity.internal.h
    public final void c(Bundle bundle, j jVar) throws RemoteException {
        Parcel a2 = a();
        c.c(a2, bundle);
        c.d(a2, jVar);
        b(2, a2);
    }
}
