package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class k2 implements Runnable {
    public final /* synthetic */ AtomicReference h;
    public final /* synthetic */ zzp i;
    public final /* synthetic */ zzjj j;

    public k2(zzjj zzjjVar, AtomicReference atomicReference, zzp zzpVar) {
        this.j = zzjjVar;
        this.h = atomicReference;
        this.i = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference atomicReference;
        zzdz zzdzVar;
        synchronized (this.h) {
            try {
            } catch (RemoteException e) {
                this.j.zzs.zzay().zzd().zzb("Failed to get app instance id", e);
                atomicReference = this.h;
            }
            if (!this.j.zzs.zzm().d().zzk()) {
                this.j.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                this.j.zzs.zzq().f(null);
                this.j.zzs.zzm().f.zzb(null);
                this.h.set(null);
                this.h.notify();
                return;
            }
            zzdzVar = this.j.c;
            if (zzdzVar == null) {
                this.j.zzs.zzay().zzd().zza("Failed to get app instance id");
                this.h.notify();
                return;
            }
            Preconditions.checkNotNull(this.i);
            this.h.set(zzdzVar.zzd(this.i));
            String str = (String) this.h.get();
            if (str != null) {
                this.j.zzs.zzq().f(str);
                this.j.zzs.zzm().f.zzb(str);
            }
            this.j.g();
            atomicReference = this.h;
            atomicReference.notify();
        }
    }
}
