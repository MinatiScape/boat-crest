package com.google.android.gms.analytics;

import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class zzk<T extends zzk> {

    /* renamed from: a  reason: collision with root package name */
    public final zzr f8185a;
    public final List<zzi> b;
    public final zzh zza;

    @VisibleForTesting
    public zzk(zzr zzrVar, Clock clock) {
        Preconditions.checkNotNull(zzrVar);
        this.f8185a = zzrVar;
        this.b = new ArrayList();
        zzh zzhVar = new zzh(this, clock);
        zzhVar.b();
        this.zza = zzhVar;
    }

    public void zze(zzh zzhVar) {
        throw null;
    }

    public final zzr zzm() {
        return this.f8185a;
    }

    public final void zzn(zzh zzhVar) {
        for (zzi zziVar : this.b) {
            zziVar.zza();
        }
    }
}
