package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class b1 implements Runnable {
    public final /* synthetic */ long h;
    public final /* synthetic */ zzhv i;

    public b1(zzhv zzhvVar, long j) {
        this.i = zzhvVar;
        this.h = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.zzs.zzm().j.zzb(this.h);
        this.i.zzs.zzay().zzc().zzb("Session timeout duration set", Long.valueOf(this.h));
    }
}
