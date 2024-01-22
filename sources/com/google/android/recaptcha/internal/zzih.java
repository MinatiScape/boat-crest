package com.google.android.recaptcha.internal;

import com.google.protobuf.i;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzih implements zzjd {
    private static final zzin zza = new zzif();
    private final zzin zzb;

    public zzih() {
        zzin zzinVar;
        zzin[] zzinVarArr = new zzin[2];
        zzinVarArr[0] = zzgy.zza();
        try {
            Set<String> set = i.f11733a;
            zzinVar = (zzin) i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzinVar = zza;
        }
        zzinVarArr[1] = zzinVar;
        zzig zzigVar = new zzig(zzinVarArr);
        byte[] bArr = zzhn.zzd;
        this.zzb = zzigVar;
    }

    private static boolean zzb(zzim zzimVar) {
        return zzimVar.zzc() + (-1) != 1;
    }

    @Override // com.google.android.recaptcha.internal.zzjd
    public final zzjc zza(Class cls) {
        zzje.zzE(cls);
        zzim zzb = this.zzb.zzb(cls);
        if (zzb.zzb()) {
            if (zzhf.class.isAssignableFrom(cls)) {
                return zzit.zzc(zzje.zzz(), zzgt.zzb(), zzb.zza());
            }
            return zzit.zzc(zzje.zzy(), zzgt.zza(), zzb.zza());
        } else if (zzhf.class.isAssignableFrom(cls)) {
            if (zzb(zzb)) {
                return zzis.zzm(cls, zzb, zziw.zzb(), zzid.zze(), zzje.zzz(), zzgt.zzb(), zzil.zzb());
            }
            return zzis.zzm(cls, zzb, zziw.zzb(), zzid.zze(), zzje.zzz(), null, zzil.zzb());
        } else if (zzb(zzb)) {
            return zzis.zzm(cls, zzb, zziw.zza(), zzid.zzd(), zzje.zzy(), zzgt.zza(), zzil.zza());
        } else {
            return zzis.zzm(cls, zzb, zziw.zza(), zzid.zzd(), zzje.zzy(), null, zzil.zza());
        }
    }
}
