package com.google.android.gms.measurement.internal;

import android.os.Bundle;
/* loaded from: classes10.dex */
public final class a2 implements Runnable {
    public final /* synthetic */ Bundle h;
    public final /* synthetic */ zzic i;
    public final /* synthetic */ zzic j;
    public final /* synthetic */ long k;
    public final /* synthetic */ zzij l;

    public a2(zzij zzijVar, Bundle bundle, zzic zzicVar, zzic zzicVar2, long j) {
        this.l = zzijVar;
        this.h = bundle;
        this.i = zzicVar;
        this.j = zzicVar2;
        this.k = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzij.i(this.l, this.h, this.i, this.j, this.k);
    }
}
