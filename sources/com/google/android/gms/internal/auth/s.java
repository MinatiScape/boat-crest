package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes6.dex */
public final class s extends m {
    public s(zzbt zzbtVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.internal.auth.m
    public final void b(Context context, zzbh zzbhVar) throws RemoteException {
        zzbhVar.zzd(new r(this));
    }
}
