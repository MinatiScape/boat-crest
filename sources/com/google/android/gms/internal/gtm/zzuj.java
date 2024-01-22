package com.google.android.gms.internal.gtm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class zzuj {
    public static final zzuj zza = new zzuj(true);
    public static volatile zzuj zzc;
    public static volatile zzuj zzd;
    public final Map<zzui, zzux<?, ?>> zze;

    public zzuj() {
        this.zze = new HashMap();
    }

    public static zzuj zza() {
        zzuj zzujVar = zzc;
        if (zzujVar == null) {
            synchronized (zzuj.class) {
                zzujVar = zzc;
                if (zzujVar == null) {
                    zzujVar = zza;
                    zzc = zzujVar;
                }
            }
        }
        return zzujVar;
    }

    public static zzuj zzb() {
        zzuj zzujVar = zzd;
        if (zzujVar != null) {
            return zzujVar;
        }
        synchronized (zzuj.class) {
            zzuj zzujVar2 = zzd;
            if (zzujVar2 != null) {
                return zzujVar2;
            }
            zzuj zzb = zzur.zzb(zzuj.class);
            zzd = zzb;
            return zzb;
        }
    }

    public <ContainingType extends zzwk> zzux<ContainingType, ?> zzc(ContainingType containingtype, int i) {
        return (zzux<ContainingType, ?>) this.zze.get(new zzui(containingtype, i));
    }

    public zzuj(boolean z) {
        this.zze = Collections.emptyMap();
    }
}
