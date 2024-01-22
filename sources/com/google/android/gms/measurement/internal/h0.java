package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes10.dex */
public final class h0 implements Callable<List<z3>> {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ zzgk k;

    public h0(zzgk zzgkVar, String str, String str2, String str3) {
        this.k = zzgkVar;
        this.h = str;
        this.i = str2;
        this.j = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ List<z3> call() throws Exception {
        zzkn zzknVar;
        zzkn zzknVar2;
        zzknVar = this.k.f10155a;
        zzknVar.a();
        zzknVar2 = this.k.f10155a;
        return zzknVar2.zzi().S(this.h, this.i, this.j);
    }
}
