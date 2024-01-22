package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class n1 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ zzhv i;

    public n1(zzhv zzhvVar, AtomicReference atomicReference) {
        this.i = zzhvVar;
        this.h = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.h) {
            this.h.set(this.i.zzs.zzf().zzo(this.i.zzs.zzh().zzl(), zzdw.zzK));
            this.h.notify();
        }
    }
}
