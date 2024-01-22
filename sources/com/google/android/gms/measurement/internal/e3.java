package com.google.android.gms.measurement.internal;
/* loaded from: classes10.dex */
public final class e3 implements Runnable {
    public final /* synthetic */ zzji h;

    public e3(zzji zzjiVar) {
        this.h = zzjiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.h.j.c = null;
        this.h.j.f();
    }
}
