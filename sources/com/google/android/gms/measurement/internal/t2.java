package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class t2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzjj i;

    public t2(zzjj zzjjVar, zzp zzpVar) {
        this.i = zzjjVar;
        this.h = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.i.c;
        if (zzdzVar == null) {
            this.i.zzs.zzay().zzd().zza("Failed to send consent settings to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.h);
            zzdzVar.zzp(this.h);
            this.i.g();
        } catch (RemoteException e) {
            this.i.zzs.zzay().zzd().zzb("Failed to send consent settings to the service", e);
        }
    }
}
