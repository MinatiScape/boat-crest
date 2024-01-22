package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class p3 extends f {
    public final /* synthetic */ zzkb e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p3(zzkb zzkbVar, y0 y0Var) {
        super(y0Var);
        this.e = zzkbVar;
    }

    @Override // com.google.android.gms.measurement.internal.f
    public final void c() {
        this.e.zza();
        this.e.zzs.zzay().zzj().zza("Starting upload from DelayedRunnable");
        this.e.zzf.u();
    }
}
