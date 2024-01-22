package com.google.android.gms.internal.gtm;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzrm {
    @VisibleForTesting
    public final Map<String, Object> zza;
    public final Context zzb;
    public final zzsb zzc;
    public final Clock zzd;
    public final Map<String, Object> zze;

    public zzrm(Context context) {
        HashMap hashMap = new HashMap();
        zzsb zzsbVar = new zzsb(context);
        Clock defaultClock = DefaultClock.getInstance();
        this.zza = new HashMap();
        this.zzb = context;
        this.zzd = defaultClock;
        this.zzc = zzsbVar;
        this.zze = hashMap;
    }
}
