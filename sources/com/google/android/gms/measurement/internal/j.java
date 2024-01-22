package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class j implements Runnable {
    public final /* synthetic */ long h;
    public final /* synthetic */ zzd i;

    public j(zzd zzdVar, long j) {
        this.i = zzdVar;
        this.h = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.i.f(this.h);
    }
}
