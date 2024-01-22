package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e0 implements Runnable {
    public final /* synthetic */ zzgu h;
    public final /* synthetic */ zzfs i;

    public e0(zzfs zzfsVar, zzgu zzguVar) {
        this.i = zzfsVar;
        this.h = zzguVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfs.a(this.i, this.h);
        this.i.zzH(this.h.g);
    }
}
