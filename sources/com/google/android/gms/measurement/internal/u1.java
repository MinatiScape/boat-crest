package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class u1 implements Runnable {
    public final /* synthetic */ zzag h;
    public final /* synthetic */ int i;
    public final /* synthetic */ long j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ zzhv l;

    public u1(zzhv zzhvVar, zzag zzagVar, int i, long j, boolean z) {
        this.l = zzhvVar;
        this.h = zzagVar;
        this.i = i;
        this.j = j;
        this.k = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.g(this.h);
        zzhv.k(this.l, this.h, this.i, this.j, false, this.k);
    }
}
