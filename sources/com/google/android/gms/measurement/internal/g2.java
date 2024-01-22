package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
/* loaded from: classes10.dex */
public final class g2 implements Runnable {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ zzp j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf l;
    public final /* synthetic */ zzjj m;

    public g2(zzjj zzjjVar, String str, String str2, zzp zzpVar, boolean z, com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        this.m = zzjjVar;
        this.h = str;
        this.i = str2;
        this.j = zzpVar;
        this.k = z;
        this.l = zzcfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        RemoteException e;
        zzdz zzdzVar;
        Bundle bundle2 = new Bundle();
        try {
            zzdzVar = this.m.c;
            if (zzdzVar == null) {
                this.m.zzs.zzay().zzd().zzc("Failed to get user properties; not connected to service", this.h, this.i);
                this.m.zzs.zzv().zzQ(this.l, bundle2);
                return;
            }
            Preconditions.checkNotNull(this.j);
            List<zzkq> zzh = zzdzVar.zzh(this.h, this.i, this.k, this.j);
            bundle = new Bundle();
            if (zzh != null) {
                for (zzkq zzkqVar : zzh) {
                    String str = zzkqVar.zze;
                    if (str != null) {
                        bundle.putString(zzkqVar.zzb, str);
                    } else {
                        Long l = zzkqVar.zzd;
                        if (l != null) {
                            bundle.putLong(zzkqVar.zzb, l.longValue());
                        } else {
                            Double d = zzkqVar.zzg;
                            if (d != null) {
                                bundle.putDouble(zzkqVar.zzb, d.doubleValue());
                            }
                        }
                    }
                }
            }
            try {
                try {
                    this.m.g();
                    this.m.zzs.zzv().zzQ(this.l, bundle);
                } catch (RemoteException e2) {
                    e = e2;
                    this.m.zzs.zzay().zzd().zzc("Failed to get user properties; remote exception", this.h, e);
                    this.m.zzs.zzv().zzQ(this.l, bundle);
                }
            } catch (Throwable th) {
                th = th;
                bundle2 = bundle;
                this.m.zzs.zzv().zzQ(this.l, bundle2);
                throw th;
            }
        } catch (RemoteException e3) {
            bundle = bundle2;
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            this.m.zzs.zzv().zzQ(this.l, bundle2);
            throw th;
        }
    }
}
