package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpl;
/* loaded from: classes10.dex */
public final class p0 implements Runnable {
    public final /* synthetic */ zzat h;
    public final /* synthetic */ zzp i;
    public final /* synthetic */ zzgk j;

    public p0(zzgk zzgkVar, zzat zzatVar, zzp zzpVar) {
        this.j = zzgkVar;
        this.h = zzatVar;
        this.i = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzat c = this.j.c(this.h, this.i);
        zzpl.zzc();
        zzknVar = this.j.f10155a;
        if (zzknVar.zzg().zzs(null, zzdw.zzav)) {
            this.j.f(c, this.i);
        } else {
            this.j.b(c, this.i);
        }
    }
}
