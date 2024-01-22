package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzny;
/* loaded from: classes10.dex */
public final class m3 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ zzjy f10126a;

    public m3(zzjy zzjyVar) {
        this.f10126a = zzjyVar;
    }

    @WorkerThread
    public final void a() {
        this.f10126a.zzg();
        if (this.f10126a.zzs.zzm().i(this.f10126a.zzs.zzav().currentTimeMillis())) {
            this.f10126a.zzs.zzm().k.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.f10126a.zzs.zzay().zzj().zza("Detected application was in foreground");
                c(this.f10126a.zzs.zzav().currentTimeMillis(), false);
            }
        }
    }

    @WorkerThread
    public final void b(long j, boolean z) {
        this.f10126a.zzg();
        this.f10126a.f();
        if (this.f10126a.zzs.zzm().i(j)) {
            this.f10126a.zzs.zzm().k.zza(true);
        }
        this.f10126a.zzs.zzm().n.zzb(j);
        if (this.f10126a.zzs.zzm().k.zzb()) {
            c(j, z);
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final void c(long j, boolean z) {
        this.f10126a.zzg();
        if (this.f10126a.zzs.zzJ()) {
            this.f10126a.zzs.zzm().n.zzb(j);
            this.f10126a.zzs.zzay().zzj().zzb("Session started, time", Long.valueOf(this.f10126a.zzs.zzav().elapsedRealtime()));
            Long valueOf = Long.valueOf(j / 1000);
            this.f10126a.zzs.zzq().h("auto", "_sid", valueOf, j);
            this.f10126a.zzs.zzm().k.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", valueOf.longValue());
            if (this.f10126a.zzs.zzf().zzs(null, zzdw.zzae) && z) {
                bundle.putLong("_aib", 1L);
            }
            this.f10126a.zzs.zzq().c("auto", "_s", j, bundle);
            zzny.zzc();
            if (this.f10126a.zzs.zzf().zzs(null, zzdw.zzai)) {
                String zza = this.f10126a.zzs.zzm().s.zza();
                if (TextUtils.isEmpty(zza)) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", zza);
                this.f10126a.zzs.zzq().c("auto", "_ssr", j, bundle2);
            }
        }
    }
}
