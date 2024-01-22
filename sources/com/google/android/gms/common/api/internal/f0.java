package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Map;
/* loaded from: classes6.dex */
public final class f0 implements Runnable {
    public final /* synthetic */ ConnectionResult h;
    public final /* synthetic */ g0 i;

    public f0(g0 g0Var, ConnectionResult connectionResult) {
        this.i = g0Var;
        this.h = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map map;
        ApiKey apiKey;
        Api.Client client;
        Api.Client client2;
        Api.Client client3;
        Api.Client client4;
        g0 g0Var = this.i;
        map = g0Var.f.s;
        apiKey = g0Var.b;
        zabq zabqVar = (zabq) map.get(apiKey);
        if (zabqVar == null) {
            return;
        }
        if (this.h.isSuccess()) {
            this.i.e = true;
            client = this.i.f8276a;
            if (client.requiresSignIn()) {
                this.i.e();
                return;
            }
            try {
                g0 g0Var2 = this.i;
                client3 = g0Var2.f8276a;
                client4 = g0Var2.f8276a;
                client3.getRemoteService(null, client4.getScopesForConnectionlessNonSignIn());
                return;
            } catch (SecurityException e) {
                Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                client2 = this.i.f8276a;
                client2.disconnect("Failed to get service from broker.");
                zabqVar.zar(new ConnectionResult(10), null);
                return;
            }
        }
        zabqVar.zar(this.h, null);
    }
}
