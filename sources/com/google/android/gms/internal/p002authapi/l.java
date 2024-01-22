package com.google.android.gms.internal.p002authapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
/* renamed from: com.google.android.gms.internal.auth-api.l  reason: invalid package */
/* loaded from: classes6.dex */
public final class l extends o {
    public final /* synthetic */ Credential c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(zbl zblVar, GoogleApiClient googleApiClient, Credential credential) {
        super(googleApiClient);
        this.c = credential;
    }

    @Override // com.google.android.gms.internal.p002authapi.o
    public final void b(Context context, zbt zbtVar) throws RemoteException {
        zbtVar.zbc(new n(this), new zbp(this.c));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }
}
