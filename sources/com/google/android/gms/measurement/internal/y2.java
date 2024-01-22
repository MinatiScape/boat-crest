package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public final class y2 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ zzp j;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf k;
    public final /* synthetic */ zzjj l;

    public y2(zzjj zzjjVar, String str, String str2, zzp zzpVar, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.l = zzjjVar;
        this.h = str;
        this.i = str2;
        this.j = zzpVar;
        this.k = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfs zzfsVar;
        zzdz zzdzVar;
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            try {
                zzdzVar = this.l.c;
                if (zzdzVar == null) {
                    this.l.zzs.zzay().zzd().zzc("Failed to get conditional properties; not connected to service", this.h, this.i);
                    zzfsVar = this.l.zzs;
                } else {
                    Preconditions.checkNotNull(this.j);
                    arrayList = zzku.zzG(zzdzVar.zzf(this.h, this.i, this.j));
                    this.l.g();
                    zzfsVar = this.l.zzs;
                }
            } catch (RemoteException e) {
                this.l.zzs.zzay().zzd().zzd("Failed to get conditional properties; remote exception", this.h, this.i, e);
                zzfsVar = this.l.zzs;
            }
            zzfsVar.zzv().zzP(this.k, arrayList);
        } catch (Throwable th) {
            this.l.zzs.zzv().zzP(this.k, arrayList);
            throw th;
        }
    }
}
