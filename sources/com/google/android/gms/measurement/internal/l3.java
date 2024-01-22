package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzoh;
/* loaded from: classes10.dex */
public final class l3 {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public long f10124a;
    @VisibleForTesting
    public long b;
    public final f c;
    public final /* synthetic */ zzjy d;

    public l3(zzjy zzjyVar) {
        this.d = zzjyVar;
        this.c = new k3(this, zzjyVar.zzs);
        long elapsedRealtime = zzjyVar.zzs.zzav().elapsedRealtime();
        this.f10124a = elapsedRealtime;
        this.b = elapsedRealtime;
    }

    public final void a() {
        this.c.b();
        this.f10124a = 0L;
        this.b = 0L;
    }

    @WorkerThread
    public final void b(long j) {
        this.c.b();
    }

    @WorkerThread
    public final void c(long j) {
        this.d.zzg();
        this.c.b();
        this.f10124a = j;
        this.b = j;
    }

    @WorkerThread
    public final boolean d(boolean z, boolean z2, long j) {
        this.d.zzg();
        this.d.zza();
        zzoh.zzc();
        if (this.d.zzs.zzf().zzs(null, zzdw.zzaj)) {
            if (this.d.zzs.zzJ()) {
                this.d.zzs.zzm().n.zzb(this.d.zzs.zzav().currentTimeMillis());
            }
        } else {
            this.d.zzs.zzm().n.zzb(this.d.zzs.zzav().currentTimeMillis());
        }
        long j2 = j - this.f10124a;
        if (!z && j2 < 1000) {
            this.d.zzs.zzay().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
            return false;
        }
        if (!z2) {
            j2 = j - this.b;
            this.b = j;
        }
        this.d.zzs.zzay().zzj().zzb("Recording user engagement, ms", Long.valueOf(j2));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j2);
        zzku.zzJ(this.d.zzs.zzs().zzj(!this.d.zzs.zzf().zzu()), bundle, true);
        zzaf zzf = this.d.zzs.zzf();
        zzdv<Boolean> zzdvVar = zzdw.zzT;
        if (!zzf.zzs(null, zzdvVar) && z2) {
            bundle.putLong("_fr", 1L);
        }
        if (!this.d.zzs.zzf().zzs(null, zzdvVar) || !z2) {
            this.d.zzs.zzq().b("auto", "_e", bundle);
        }
        this.f10124a = j;
        this.c.b();
        this.c.d(3600000L);
        return true;
    }
}
