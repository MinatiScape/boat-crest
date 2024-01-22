package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzd extends m {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Long> f10143a;
    public final Map<String, Integer> b;
    public long c;

    public zzd(zzfs zzfsVar) {
        super(zzfsVar);
        this.b = new ArrayMap();
        this.f10143a = new ArrayMap();
    }

    public static /* bridge */ /* synthetic */ void a(zzd zzdVar, String str, long j) {
        zzdVar.zzg();
        Preconditions.checkNotEmpty(str);
        if (zzdVar.b.isEmpty()) {
            zzdVar.c = j;
        }
        Integer num = zzdVar.b.get(str);
        if (num != null) {
            zzdVar.b.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (zzdVar.b.size() >= 100) {
            zzdVar.zzs.zzay().zzk().zza("Too many ads visible");
        } else {
            zzdVar.b.put(str, 1);
            zzdVar.f10143a.put(str, Long.valueOf(j));
        }
    }

    public static /* bridge */ /* synthetic */ void b(zzd zzdVar, String str, long j) {
        zzdVar.zzg();
        Preconditions.checkNotEmpty(str);
        Integer num = zzdVar.b.get(str);
        if (num != null) {
            zzic zzj = zzdVar.zzs.zzs().zzj(false);
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                zzdVar.b.remove(str);
                Long l = zzdVar.f10143a.get(str);
                if (l == null) {
                    zzdVar.zzs.zzay().zzd().zza("First ad unit exposure time was never set");
                } else {
                    long longValue = l.longValue();
                    zzdVar.f10143a.remove(str);
                    zzdVar.e(str, j - longValue, zzj);
                }
                if (zzdVar.b.isEmpty()) {
                    long j2 = zzdVar.c;
                    if (j2 == 0) {
                        zzdVar.zzs.zzay().zzd().zza("First ad exposure time was never set");
                        return;
                    }
                    zzdVar.d(j - j2, zzj);
                    zzdVar.c = 0L;
                    return;
                }
                return;
            }
            zzdVar.b.put(str, Integer.valueOf(intValue));
            return;
        }
        zzdVar.zzs.zzay().zzd().zzb("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    @WorkerThread
    public final void d(long j, zzic zzicVar) {
        if (zzicVar == null) {
            this.zzs.zzay().zzj().zza("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            this.zzs.zzay().zzj().zzb("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzku.zzJ(zzicVar, bundle, true);
            this.zzs.zzq().b("am", "_xa", bundle);
        }
    }

    @WorkerThread
    public final void e(String str, long j, zzic zzicVar) {
        if (zzicVar == null) {
            this.zzs.zzay().zzj().zza("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            this.zzs.zzay().zzj().zzb("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzku.zzJ(zzicVar, bundle, true);
            this.zzs.zzq().b("am", "_xu", bundle);
        }
    }

    @WorkerThread
    public final void f(long j) {
        for (String str : this.f10143a.keySet()) {
            this.f10143a.put(str, Long.valueOf(j));
        }
        if (this.f10143a.isEmpty()) {
            return;
        }
        this.c = j;
    }

    public final void zzd(String str, long j) {
        if (str != null && str.length() != 0) {
            this.zzs.zzaz().zzp(new a(this, str, j));
        } else {
            this.zzs.zzay().zzd().zza("Ad unit id must be a non-empty string");
        }
    }

    public final void zze(String str, long j) {
        if (str != null && str.length() != 0) {
            this.zzs.zzaz().zzp(new i(this, str, j));
        } else {
            this.zzs.zzay().zzd().zza("Ad unit id must be a non-empty string");
        }
    }

    @WorkerThread
    public final void zzf(long j) {
        zzic zzj = this.zzs.zzs().zzj(false);
        for (String str : this.f10143a.keySet()) {
            e(str, j - this.f10143a.get(str).longValue(), zzj);
        }
        if (!this.f10143a.isEmpty()) {
            d(j - this.c, zzj);
        }
        f(j);
    }
}
