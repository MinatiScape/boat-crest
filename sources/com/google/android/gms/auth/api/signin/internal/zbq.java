package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
/* loaded from: classes6.dex */
public abstract class zbq extends com.google.android.gms.internal.p002authapi.zbb implements zbr {
    public zbq() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.p002authapi.zbb
    public final boolean zba(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 101:
                com.google.android.gms.internal.p002authapi.zbc.zbb(parcel);
                zbd((GoogleSignInAccount) com.google.android.gms.internal.p002authapi.zbc.zba(parcel, GoogleSignInAccount.CREATOR), (Status) com.google.android.gms.internal.p002authapi.zbc.zba(parcel, Status.CREATOR));
                break;
            case 102:
                com.google.android.gms.internal.p002authapi.zbc.zbb(parcel);
                zbc((Status) com.google.android.gms.internal.p002authapi.zbc.zba(parcel, Status.CREATOR));
                break;
            case 103:
                com.google.android.gms.internal.p002authapi.zbc.zbb(parcel);
                zbb((Status) com.google.android.gms.internal.p002authapi.zbc.zba(parcel, Status.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
