package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class f1 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ zzhv j;

    public f1(zzhv zzhvVar, AtomicReference atomicReference, boolean z) {
        this.j = zzhvVar;
        this.h = atomicReference;
        this.i = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.j.zzs.zzt().zzx(this.h, this.i);
    }
}
