package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
/* loaded from: classes8.dex */
public abstract class r0 implements Runnable {
    public final long h;
    public final long i;
    public final boolean j;
    public final /* synthetic */ zzee k;

    public r0(zzee zzeeVar, boolean z) {
        this.k = zzeeVar;
        this.h = zzeeVar.zza.currentTimeMillis();
        this.i = zzeeVar.zza.elapsedRealtime();
        this.j = z;
    }

    public abstract void a() throws RemoteException;

    public void b() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        z = this.k.e;
        if (z) {
            b();
            return;
        }
        try {
            a();
        } catch (Exception e) {
            this.k.c(e, false, this.j);
            b();
        }
    }
}
