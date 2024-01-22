package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zziy {
    public static final /* synthetic */ int zza = 0;
    private static final zziy zzb = new zziy();
    private final ConcurrentMap zzd = new ConcurrentHashMap();
    private final zzjd zzc = new zzih();

    private zziy() {
    }

    public static zziy zza() {
        return zzb;
    }

    public final zzjc zzb(Class cls) {
        zzhn.zzc(cls, "messageType");
        zzjc zzjcVar = (zzjc) this.zzd.get(cls);
        if (zzjcVar == null) {
            zzjcVar = this.zzc.zza(cls);
            zzhn.zzc(cls, "messageType");
            zzhn.zzc(zzjcVar, "schema");
            zzjc zzjcVar2 = (zzjc) this.zzd.putIfAbsent(cls, zzjcVar);
            if (zzjcVar2 != null) {
                return zzjcVar2;
            }
        }
        return zzjcVar;
    }
}
