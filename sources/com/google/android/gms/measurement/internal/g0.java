package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class g0 implements Runnable {
    public final /* synthetic */ zzab h;
    public final /* synthetic */ zzgk i;

    public g0(zzgk zzgkVar, zzab zzabVar) {
        this.i = zzgkVar;
        this.h = zzabVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzkn zzknVar3;
        zzknVar = this.i.f10155a;
        zzknVar.a();
        if (this.h.zzc.zza() == null) {
            zzknVar3 = this.i.f10155a;
            zzknVar3.m(this.h);
            return;
        }
        zzknVar2 = this.i.f10155a;
        zzknVar2.q(this.h);
    }
}
