package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class t0 implements Callable<List<z3>> {
    public final /* synthetic */ String h;
    public final /* synthetic */ zzgk i;

    public t0(zzgk zzgkVar, String str) {
        this.i = zzgkVar;
        this.h = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ List<z3> call() throws Exception {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzknVar = this.i.f10155a;
        zzknVar.a();
        zzknVar2 = this.i.f10155a;
        return zzknVar2.zzi().R(this.h);
    }
}
