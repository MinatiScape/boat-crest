package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class o2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ Bundle i;
    public final /* synthetic */ zzjj j;

    public o2(zzjj zzjjVar, zzp zzpVar, Bundle bundle) {
        this.j = zzjjVar;
        this.h = zzpVar;
        this.i = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.j.c;
        if (zzdzVar == null) {
            this.j.zzs.zzay().zzd().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.h);
            zzdzVar.zzr(this.i, this.h);
        } catch (RemoteException e) {
            this.j.zzs.zzay().zzd().zzb("Failed to send default event parameters to service", e);
        }
    }
}
