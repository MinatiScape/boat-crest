package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes10.dex */
public final class l2 implements Runnable {
    public final /* synthetic */ zzp h;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf i;
    public final /* synthetic */ zzjj j;

    public l2(zzjj zzjjVar, zzp zzpVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.j = zzjjVar;
        this.h = zzpVar;
        this.i = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfs zzfsVar;
        zzdz zzdzVar;
        String str = null;
        try {
            try {
                if (this.j.zzs.zzm().d().zzk()) {
                    zzdzVar = this.j.c;
                    if (zzdzVar == null) {
                        this.j.zzs.zzay().zzd().zza("Failed to get app instance id");
                        zzfsVar = this.j.zzs;
                    } else {
                        Preconditions.checkNotNull(this.h);
                        str = zzdzVar.zzd(this.h);
                        if (str != null) {
                            this.j.zzs.zzq().f(str);
                            this.j.zzs.zzm().f.zzb(str);
                        }
                        this.j.g();
                        zzfsVar = this.j.zzs;
                    }
                } else {
                    this.j.zzs.zzay().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    this.j.zzs.zzq().f(null);
                    this.j.zzs.zzm().f.zzb(null);
                    zzfsVar = this.j.zzs;
                }
            } catch (RemoteException e) {
                this.j.zzs.zzay().zzd().zzb("Failed to get app instance id", e);
                zzfsVar = this.j.zzs;
            }
            zzfsVar.zzv().zzU(this.i, str);
        } catch (Throwable th) {
            this.j.zzs.zzv().zzU(this.i, null);
            throw th;
        }
    }
}
