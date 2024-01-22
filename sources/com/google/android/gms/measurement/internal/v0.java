package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class v0 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ String j;
    public final /* synthetic */ long k;
    public final /* synthetic */ zzgk l;

    public v0(zzgk zzgkVar, String str, String str2, String str3, long j) {
        this.l = zzgkVar;
        this.h = str;
        this.i = str2;
        this.j = str3;
        this.k = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn zzknVar;
        zzkn zzknVar2;
        String str = this.h;
        if (str == null) {
            zzknVar2 = this.l.f10155a;
            zzknVar2.O().zzs().zzy(this.i, null);
            return;
        }
        zzic zzicVar = new zzic(this.j, str, this.k);
        zzknVar = this.l.f10155a;
        zzknVar.O().zzs().zzy(this.i, zzicVar);
    }
}
