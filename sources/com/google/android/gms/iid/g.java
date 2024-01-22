package com.google.android.gms.iid;

import android.content.Intent;
/* loaded from: classes6.dex */
public final class g implements Runnable {
    public final /* synthetic */ Intent h;
    public final /* synthetic */ Intent i;
    public final /* synthetic */ zze j;

    public g(zze zzeVar, Intent intent, Intent intent2) {
        this.j = zzeVar;
        this.h = intent;
        this.i = intent2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.j.handleIntent(this.h);
        this.j.b(this.i);
    }
}
