package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
/* loaded from: classes6.dex */
public final class zbs extends com.google.android.gms.internal.p002authapi.zba {
    public zbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zbc(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        com.google.android.gms.internal.p002authapi.zbc.zbd(zba, zbrVar);
        com.google.android.gms.internal.p002authapi.zbc.zbc(zba, googleSignInOptions);
        zbb(103, zba);
    }

    public final void zbd(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        com.google.android.gms.internal.p002authapi.zbc.zbd(zba, zbrVar);
        com.google.android.gms.internal.p002authapi.zbc.zbc(zba, googleSignInOptions);
        zbb(102, zba);
    }

    public final void zbe(zbr zbrVar, GoogleSignInOptions googleSignInOptions) throws RemoteException {
        Parcel zba = zba();
        com.google.android.gms.internal.p002authapi.zbc.zbd(zba, zbrVar);
        com.google.android.gms.internal.p002authapi.zbc.zbc(zba, googleSignInOptions);
        zbb(101, zba);
    }
}
