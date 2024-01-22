package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class m2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ zzjj i;

    public m2(zzjj zzjjVar, zzp zzpVar) {
        this.i = zzjjVar;
        this.h = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.i.c;
        if (zzdzVar == null) {
            this.i.zzs.zzay().zzd().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(this.h);
            zzdzVar.zzj(this.h);
            this.i.zzs.zzi().zzm();
            this.i.b(zzdzVar, null, this.h);
            this.i.g();
        } catch (RemoteException e) {
            this.i.zzs.zzay().zzd().zzb("Failed to send app launch to the service", e);
        }
    }
}
