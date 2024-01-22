package com.google.android.libraries.places.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class zzrt {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzrt zzc;
    private static final zzrt zzd = new zzrt(true);
    private final Map<Object, Object> zze;

    public zzrt() {
        this.zze = new HashMap();
    }

    public static zzrt zza() {
        zzrt zzrtVar = zzc;
        if (zzrtVar == null) {
            synchronized (zzrt.class) {
                zzrtVar = zzc;
                if (zzrtVar == null) {
                    zzrtVar = zzd;
                    zzc = zzrtVar;
                }
            }
        }
        return zzrtVar;
    }

    private zzrt(boolean z) {
        this.zze = Collections.emptyMap();
    }
}
