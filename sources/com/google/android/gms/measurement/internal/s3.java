package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class s3 implements Runnable {
    public final /* synthetic */ zzko h;
    public final /* synthetic */ zzkn i;

    public s3(zzkn zzknVar, zzko zzkoVar) {
        this.i = zzknVar;
        this.h = zzkoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkn.R(this.i, this.h);
        this.i.zzQ();
    }
}
