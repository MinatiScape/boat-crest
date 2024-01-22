package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
/* loaded from: classes10.dex */
public final class n2 implements Runnable {
    public final /* synthetic */ zzic h;
    public final /* synthetic */ zzjj i;

    public n2(zzjj zzjjVar, zzic zzicVar) {
        this.i = zzjjVar;
        this.h = zzicVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdz zzdzVar;
        zzdzVar = this.i.c;
        if (zzdzVar == null) {
            this.i.zzs.zzay().zzd().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzic zzicVar = this.h;
            if (zzicVar == null) {
                zzdzVar.zzq(0L, null, null, this.i.zzs.zzau().getPackageName());
            } else {
                zzdzVar.zzq(zzicVar.zzc, zzicVar.zza, zzicVar.zzb, this.i.zzs.zzau().getPackageName());
            }
            this.i.g();
        } catch (RemoteException e) {
            this.i.zzs.zzay().zzd().zzb("Failed to send current screen to the service", e);
        }
    }
}
