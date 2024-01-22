package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class u0 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzgk i;

    public u0(zzgk zzgkVar, zzp zzpVar) {
        this.i = zzgkVar;
        this.h = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzknVar = this.i.f10155a;
        zzknVar.a();
        zzknVar2 = this.i.f10155a;
        zzknVar2.k(this.h);
    }
}
