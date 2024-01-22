package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class d2 implements Runnable {
    public final /* synthetic */ long h;
    public final /* synthetic */ zzij i;

    public d2(zzij zzijVar, long j) {
        this.i = zzijVar;
        this.h = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.zzs.zzd().zzf(this.h);
        this.i.zza = null;
    }
}
