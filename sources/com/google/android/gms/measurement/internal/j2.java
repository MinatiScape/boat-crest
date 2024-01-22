package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class j2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzjj i;

    public j2(zzjj zzjjVar, zzp zzpVar) {
        this.i = zzjjVar;
        this.h = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.i.c;
        if (zzdzVar == null) {
            this.i.zzs.zzay().zzd().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.h);
            zzdzVar.zzm(this.h);
        } catch (RemoteException e) {
            this.i.zzs.zzay().zzd().zzb("Failed to reset data on the service: remote exception", e);
        }
        this.i.g();
    }
}
