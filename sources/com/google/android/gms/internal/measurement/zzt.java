package com.google.android.gms.internal.measurement;

import androidx.core.app.NotificationCompat;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzt extends zzai {
    public final zzr h;

    public zzt(zzr zzrVar) {
        super("internal.logger");
        this.h = zzrVar;
        this.zze.put("log", new b5(this, false, true));
        this.zze.put(NotificationCompat.GROUP_KEY_SILENT, new z4(this, NotificationCompat.GROUP_KEY_SILENT));
        ((zzai) this.zze.get(NotificationCompat.GROUP_KEY_SILENT)).zzr("log", new b5(this, true, true));
        this.zze.put("unmonitored", new a5(this, "unmonitored"));
        ((zzai) this.zze.get("unmonitored")).zzr("log", new b5(this, false, false));
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        return zzap.zzf;
    }
}
