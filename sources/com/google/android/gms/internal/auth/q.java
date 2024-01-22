package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;
/* loaded from: classes6.dex */
public final class q extends l {
    public final /* synthetic */ ProxyRequest c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(zzbt zzbtVar, GoogleApiClient googleApiClient, ProxyRequest proxyRequest) {
        super(googleApiClient);
        this.c = proxyRequest;
    }

    @Override // com.google.android.gms.internal.auth.l
    public final void b(Context context, zzbh zzbhVar) throws RemoteException {
        zzbhVar.zze(new p(this), this.c);
    }
}
