package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class n0 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzgk i;

    public n0(zzgk zzgkVar, zzp zzpVar) {
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
        zzp zzpVar = this.h;
        zzknVar2.zzaz().zzg();
        zzknVar2.b();
        Preconditions.checkNotEmpty(zzpVar.zza);
        zzknVar2.L(zzpVar);
    }
}
