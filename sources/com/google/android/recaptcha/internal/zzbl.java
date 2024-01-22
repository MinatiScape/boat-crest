package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbl implements zzca {
    @NotNull
    public static final zzbl zza = new zzbl();

    private zzbl() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        Object obj;
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
                            obj = String.valueOf(((String) zza2).charAt(intValue));
                        } else {
                            obj = zza2 instanceof List ? ((List) zza2).get(intValue) : Array.get(zza2, intValue);
                        }
                        zzbhVar.zze().zzf(i, obj);
                        return;
                    } catch (Exception e) {
                        if (e instanceof ArrayIndexOutOfBoundsException) {
                            throw new zzs(4, 22, e);
                        }
                        throw new zzs(4, 23, e);
                    }
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
