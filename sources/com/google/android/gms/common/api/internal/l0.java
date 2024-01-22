package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes6.dex */
public final class l0 implements Runnable {
    public final /* synthetic */ zact h;

    public l0(zact zactVar) {
        this.h = zactVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zacs zacsVar;
        zacsVar = this.h.g;
        zacsVar.zae(new ConnectionResult(4));
    }
}
