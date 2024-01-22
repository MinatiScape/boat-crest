package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class s1 implements Runnable {
    public final /* synthetic */ zzag h;
    public final /* synthetic */ long i;
    public final /* synthetic */ int j;
    public final /* synthetic */ long k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ zzhv m;

    public s1(zzhv zzhvVar, zzag zzagVar, long j, int i, long j2, boolean z) {
        this.m = zzhvVar;
        this.h = zzagVar;
        this.i = j;
        this.j = i;
        this.k = j2;
        this.l = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.m.g(this.h);
        this.m.d(this.i, false);
        zzhv.k(this.m, this.h, this.j, this.k, true, this.l);
    }
}
