package com.google.android.gms.common.api.internal;
/* loaded from: classes6.dex */
public final class m0 implements Runnable {
    public final /* synthetic */ com.google.android.gms.signin.internal.zak h;
    public final /* synthetic */ zact i;

    public m0(zact zactVar, com.google.android.gms.signin.internal.zak zakVar) {
        this.i = zactVar;
        this.h = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zact.b(this.i, this.h);
    }
}
