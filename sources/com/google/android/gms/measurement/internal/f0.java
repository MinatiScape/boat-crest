package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class f0 implements Runnable {
    public final /* synthetic */ zzab h;
    public final /* synthetic */ zzp i;
    public final /* synthetic */ zzgk j;

    public f0(zzgk zzgkVar, zzab zzabVar, zzp zzpVar) {
        this.j = zzgkVar;
        this.h = zzabVar;
        this.i = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzkn zzknVar3;
        zzknVar = this.j.f10155a;
        zzknVar.a();
        if (this.h.zzc.zza() == null) {
            zzknVar3 = this.j.f10155a;
            zzknVar3.n(this.h, this.i);
            return;
        }
        zzknVar2 = this.j.f10155a;
        zzknVar2.r(this.h, this.i);
    }
}
