package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzgq {
    public static final zzgq zza = new zzgq(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private final Map zzd;

    public zzgq() {
        this.zzd = new HashMap();
    }

    public final zzhd zza(zzip zzipVar, int i) {
        return (zzhd) this.zzd.get(new zzgp(zzipVar, i));
    }

    public zzgq(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
