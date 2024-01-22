package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class s0 implements Runnable {
    public final /* synthetic */ zzkq h;
    public final /* synthetic */ zzp i;
    public final /* synthetic */ zzgk j;

    public s0(zzgk zzgkVar, zzkq zzkqVar, zzp zzpVar) {
        this.j = zzgkVar;
        this.h = zzkqVar;
        this.i = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzkn zzknVar3;
        zzknVar = this.j.f10155a;
        zzknVar.a();
        if (this.h.zza() == null) {
            zzknVar3 = this.j.f10155a;
            zzknVar3.o(this.h, this.i);
            return;
        }
        zzknVar2 = this.j.f10155a;
        zzknVar2.t(this.h, this.i);
    }
}
