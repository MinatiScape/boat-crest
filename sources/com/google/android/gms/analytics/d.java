package com.google.android.gms.analytics;

import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes6.dex */
public final class d implements Runnable {
    public final /* synthetic */ zzh h;
    public final /* synthetic */ zzr i;

    public d(zzr zzrVar, zzh zzhVar) {
        this.i = zzrVar;
        this.h = zzhVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<zzs> list;
        this.h.a().zze(this.h);
        list = this.i.b;
        for (zzs zzsVar : list) {
            zzsVar.zza();
        }
        zzh zzhVar = this.h;
        Preconditions.checkNotMainThread("deliver should be called from worker thread");
        Preconditions.checkArgument(zzhVar.zzm(), "Measurement must be submitted");
        List<zzt> zzf = zzhVar.zzf();
        if (zzf.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (zzt zztVar : zzf) {
            Uri zzb = zztVar.zzb();
            if (!hashSet.contains(zzb)) {
                hashSet.add(zzb);
                zztVar.zze(zzhVar);
            }
        }
    }
}
