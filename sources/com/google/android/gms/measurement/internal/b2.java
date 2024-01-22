package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class b2 implements Runnable {
    public final /* synthetic */ zzic h;
    public final /* synthetic */ zzic i;
    public final /* synthetic */ long j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ zzij l;

    public b2(zzij zzijVar, zzic zzicVar, zzic zzicVar2, long j, boolean z) {
        this.l = zzijVar;
        this.h = zzicVar;
        this.i = zzicVar2;
        this.j = j;
        this.k = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.c(this.h, this.i, this.j, this.k, null);
    }
}
