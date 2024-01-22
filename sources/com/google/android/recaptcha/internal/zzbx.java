package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbx implements zzca {
    @NotNull
    public static final zzbx zza = new zzbx();

    private zzbx() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num = (Integer) zza3;
                if (num != null) {
                    int intValue = num.intValue();
                    try {
                        if (zza2 instanceof String) {
                            zza2 = zzbz.zza(this, (String) zza2, zzbhVar.zza());
                        }
                        zzbhVar.zze().zzf(i, Array.newInstance(zzbg.zza(zza2), intValue));
                        return;
                    } catch (Exception e) {
                        throw new zzs(6, 21, e);
                    }
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
