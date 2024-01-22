package com.google.android.gms.internal.p002authapi;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
/* renamed from: com.google.android.gms.internal.auth-api.j  reason: invalid package */
/* loaded from: classes6.dex */
public final class j extends o {
    public final /* synthetic */ CredentialRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(zbl zblVar, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.c = credentialRequest;
    }

    @Override // com.google.android.gms.internal.p002authapi.o
    public final void b(Context context, zbt zbtVar) throws RemoteException {
        zbtVar.zbd(new i(this), this.c);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zbe(status, null);
    }
}
