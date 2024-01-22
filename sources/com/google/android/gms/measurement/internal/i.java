package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class i implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ long i;
    public final /* synthetic */ zzd j;

    public i(zzd zzdVar, String str, long j) {
        this.j = zzdVar;
        this.h = str;
        this.i = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzd.b(this.j, this.h, this.i);
    }
}
