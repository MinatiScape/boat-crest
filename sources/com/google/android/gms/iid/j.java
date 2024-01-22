package com.google.android.gms.iid;

import android.util.Log;
/* loaded from: classes6.dex */
public final class j implements Runnable {
    public final /* synthetic */ h h;
    public final /* synthetic */ zzi i;

    public j(zzi zziVar, h hVar) {
        this.i = zziVar;
        this.h = hVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zze zzeVar;
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        zzeVar = this.i.f8495a;
        zzeVar.handleIntent(this.h.f8488a);
        this.h.a();
    }
}
