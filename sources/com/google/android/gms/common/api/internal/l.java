package com.google.android.gms.common.api.internal;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class l extends q {
    public final Map i;
    public final /* synthetic */ zaaw j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(zaaw zaawVar, Map map) {
        super(zaawVar, null);
        this.j = zaawVar;
        this.i = map;
    }

    @Override // com.google.android.gms.common.api.internal.q
    @GuardedBy("mLock")
    @WorkerThread
    public final void a() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        boolean z;
        Context context2;
        zabi zabiVar;
        com.google.android.gms.signin.zae zaeVar;
        com.google.android.gms.signin.zae zaeVar2;
        zabi zabiVar2;
        Context context3;
        boolean z2;
        googleApiAvailabilityLight = this.j.d;
        com.google.android.gms.common.internal.zal zalVar = new com.google.android.gms.common.internal.zal(googleApiAvailabilityLight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client client : this.i.keySet()) {
            if (client.requiresGooglePlayServices()) {
                z2 = ((i) this.i.get(client)).c;
                if (!z2) {
                    arrayList.add(client);
                }
            }
            arrayList2.add(client);
        }
        int i = -1;
        int i2 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i2 < size) {
                context3 = this.j.c;
                i = zalVar.zab(context3, (Api.Client) arrayList2.get(i2));
                i2++;
                if (i == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i2 < size2) {
                context = this.j.c;
                i = zalVar.zab(context, (Api.Client) arrayList.get(i2));
                i2++;
                if (i != 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            ConnectionResult connectionResult = new ConnectionResult(i, null);
            zaaw zaawVar = this.j;
            zabiVar2 = zaawVar.f8298a;
            zabiVar2.f(new j(this, zaawVar, connectionResult));
            return;
        }
        zaaw zaawVar2 = this.j;
        z = zaawVar2.m;
        if (z) {
            zaeVar = zaawVar2.k;
            if (zaeVar != null) {
                zaeVar2 = zaawVar2.k;
                zaeVar2.zab();
            }
        }
        for (Api.Client client2 : this.i.keySet()) {
            BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = (BaseGmsClient.ConnectionProgressReportCallbacks) this.i.get(client2);
            if (client2.requiresGooglePlayServices()) {
                context2 = this.j.c;
                if (zalVar.zab(context2, client2) != 0) {
                    zaaw zaawVar3 = this.j;
                    zabiVar = zaawVar3.f8298a;
                    zabiVar.f(new k(this, zaawVar3, connectionProgressReportCallbacks));
                }
            }
            client2.connect(connectionProgressReportCallbacks);
        }
    }
}
