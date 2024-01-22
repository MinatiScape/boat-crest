package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
/* loaded from: classes6.dex */
public final class h implements Runnable {
    public final /* synthetic */ zaaw h;

    public h(zaaw zaawVar) {
        this.h = zaawVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        zaaw zaawVar = this.h;
        googleApiAvailabilityLight = zaawVar.d;
        context = zaawVar.c;
        googleApiAvailabilityLight.cancelAvailabilityErrorNotifications(context);
    }
}
