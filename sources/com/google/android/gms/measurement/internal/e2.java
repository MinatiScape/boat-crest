package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e2 implements Runnable {
    public final /* synthetic */ zzic h;
    public final /* synthetic */ long i;
    public final /* synthetic */ zzij j;

    public e2(zzij zzijVar, zzic zzicVar, long j) {
        this.j = zzijVar;
        this.h = zzicVar;
        this.i = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.j.d(this.h, false, this.i);
        zzij zzijVar = this.j;
        zzijVar.zza = null;
        zzijVar.zzs.zzt().zzG(null);
    }
}
