package com.google.android.libraries.places.internal;

import com.google.protobuf.i;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zztf implements zzuh {
    private static final zztp zzb = new zzte();
    private final zztp zza;

    public zztf() {
        this(new zzth(zzsd.zza(), zza()));
    }

    @Override // com.google.android.libraries.places.internal.zzuh
    public final <T> zzue<T> zza(Class<T> cls) {
        zzug.zza((Class<?>) cls);
        zztm zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            if (zzsf.class.isAssignableFrom(cls)) {
                return zztv.zza(zzug.zzc(), zzry.zza(), zzb2.zzc());
            }
            return zztv.zza(zzug.zza(), zzry.zzb(), zzb2.zzc());
        } else if (zzsf.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzts.zza(cls, zzb2, zztz.zzb(), zzsy.zzb(), zzug.zzc(), zzry.zza(), zztn.zzb());
            }
            return zzts.zza(cls, zzb2, zztz.zzb(), zzsy.zzb(), zzug.zzc(), null, zztn.zzb());
        } else if (zza(zzb2)) {
            return zzts.zza(cls, zzb2, zztz.zza(), zzsy.zza(), zzug.zza(), zzry.zzb(), zztn.zza());
        } else {
            return zzts.zza(cls, zzb2, zztz.zza(), zzsy.zza(), zzug.zzb(), null, zztn.zza());
        }
    }

    private zztf(zztp zztpVar) {
        this.zza = (zztp) zzsg.zza(zztpVar, "messageInfoFactory");
    }

    private static boolean zza(zztm zztmVar) {
        return zztmVar.zza() == zzua.zza;
    }

    private static zztp zza() {
        try {
            Set<String> set = i.f11733a;
            return (zztp) i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzb;
        }
    }
}
