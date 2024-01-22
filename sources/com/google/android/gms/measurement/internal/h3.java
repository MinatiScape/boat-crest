package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class h3 implements Runnable {
    public final /* synthetic */ long h;
    public final /* synthetic */ zzjy i;

    public h3(zzjy zzjyVar, long j) {
        this.i = zzjyVar;
        this.h = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjy.d(this.i, this.h);
    }
}
