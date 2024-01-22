package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class i2 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ zzp i;
    public final /* synthetic */ boolean j;
    public final /* synthetic */ zzjj k;

    public i2(zzjj zzjjVar, AtomicReference atomicReference, zzp zzpVar, boolean z) {
        this.k = zzjjVar;
        this.h = atomicReference;
        this.i = zzpVar;
        this.j = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzdz zzdzVar;
        synchronized (this.h) {
            try {
                zzdzVar = this.k.c;
            } catch (RemoteException e) {
                this.k.zzs.zzay().zzd().zzb("Failed to get all user properties; remote exception", e);
                atomicReference = this.h;
            }
            if (zzdzVar == null) {
                this.k.zzs.zzay().zzd().zza("Failed to get all user properties; not connected to service");
                this.h.notify();
                return;
            }
            Preconditions.checkNotNull(this.i);
            this.h.set(zzdzVar.zze(this.i, this.j));
            this.k.g();
            atomicReference = this.h;
            atomicReference.notify();
        }
    }
}
