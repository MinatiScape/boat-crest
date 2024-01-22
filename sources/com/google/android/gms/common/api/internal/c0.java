package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
/* loaded from: classes6.dex */
public final class c0 implements Runnable {
    public final /* synthetic */ d0 h;

    public c0(d0 d0Var) {
        this.h = d0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Api.Client client;
        Api.Client client2;
        zabq zabqVar = this.h.f8273a;
        client = zabqVar.b;
        client2 = zabqVar.b;
        client.disconnect(client2.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
