package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzbe;
import com.google.android.gms.internal.gtm.zzbi;
import com.google.android.gms.internal.gtm.zzbv;
import java.util.ListIterator;
@VisibleForTesting
/* loaded from: classes6.dex */
public class zza extends zzk<zza> {
    public final zzbv c;
    public boolean d;

    @VisibleForTesting
    public zza(zzbv zzbvVar) {
        super(zzbvVar.zzd(), zzbvVar.zzr());
        this.c = zzbvVar;
    }

    @VisibleForTesting
    public final zzbv a() {
        return this.c;
    }

    public final zzh zza() {
        zzh zzhVar = new zzh(this.zza);
        zzhVar.zzg(this.c.zzh().zza());
        zzhVar.zzg(this.c.zzk().zza());
        zzn(zzhVar);
        return zzhVar;
    }

    public final void zzc(String str) {
        Preconditions.checkNotEmpty(str);
        Uri a2 = zzb.a(str);
        ListIterator<zzt> listIterator = this.zza.zzf().listIterator();
        while (listIterator.hasNext()) {
            if (a2.equals(listIterator.next().zzb())) {
                listIterator.remove();
            }
        }
        this.zza.zzf().add(new zzb(this.c, str));
    }

    public final void zzd(boolean z) {
        this.d = z;
    }

    @Override // com.google.android.gms.analytics.zzk
    public final void zze(zzh zzhVar) {
        zzbe zzbeVar = (zzbe) zzhVar.zzb(zzbe.class);
        if (TextUtils.isEmpty(zzbeVar.zze())) {
            zzbeVar.zzj(this.c.zzi().zzb());
        }
        if (this.d && TextUtils.isEmpty(zzbeVar.zzd())) {
            zzbi zze = this.c.zze();
            zzbeVar.zzi(zze.zza());
            zzbeVar.zzh(zze.zzb());
        }
    }
}
