package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class q1 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ zzhv i;

    public q1(zzhv zzhvVar, AtomicReference atomicReference) {
        this.i = zzhvVar;
        this.h = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.h) {
            this.h.set(Double.valueOf(this.i.zzs.zzf().zza(this.i.zzs.zzh().zzl(), zzdw.zzN)));
            this.h.notify();
        }
    }
}
