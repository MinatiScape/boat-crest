package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
/* loaded from: classes10.dex */
public final class q2 implements Runnable {
    public final /* synthetic */ zzat h;
    public final /* synthetic */ String i;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf j;
    public final /* synthetic */ zzjj k;

    public q2(zzjj zzjjVar, zzat zzatVar, String str, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.k = zzjjVar;
        this.h = zzatVar;
        this.i = str;
        this.j = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfs zzfsVar;
        zzdz zzdzVar;
        byte[] bArr = null;
        try {
            try {
                zzdzVar = this.k.c;
                if (zzdzVar == null) {
                    this.k.zzs.zzay().zzd().zza("Discarding data. Failed to send event to service to bundle");
                    zzfsVar = this.k.zzs;
                } else {
                    bArr = zzdzVar.zzu(this.h, this.i);
                    this.k.g();
                    zzfsVar = this.k.zzs;
                }
            } catch (RemoteException e) {
                this.k.zzs.zzay().zzd().zzb("Failed to send event to the service to bundle", e);
                zzfsVar = this.k.zzs;
            }
            zzfsVar.zzv().zzR(this.j, bArr);
        } catch (Throwable th) {
            this.k.zzs.zzv().zzR(this.j, bArr);
            throw th;
        }
    }
}
