package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class q0 implements Runnable {
    public final /* synthetic */ zzat h;
    public final /* synthetic */ String i;
    public final /* synthetic */ zzgk j;

    public q0(zzgk zzgkVar, zzat zzatVar, String str) {
        this.j = zzgkVar;
        this.h = zzatVar;
        this.i = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzknVar = this.j.f10155a;
        zzknVar.a();
        zzknVar2 = this.j.f10155a;
        zzknVar2.e(this.h, this.i);
    }
}
