package com.google.android.gms.iid;

import android.content.Intent;
import android.util.Log;
/* loaded from: classes6.dex */
public final class i implements Runnable {
    public final /* synthetic */ Intent h;
    public final /* synthetic */ h i;

    public i(h hVar, Intent intent) {
        this.i = hVar;
        this.h = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String action = this.h.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        this.i.a();
    }
}
