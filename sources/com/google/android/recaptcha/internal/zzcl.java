package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcl implements zzca {
    @NotNull
    public static final zzcl zza = new zzcl();

    private zzcl() {
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
                    if (zza2 instanceof Integer) {
                        obj = Integer.valueOf(((Number) zza2).intValue() * intValue);
                    } else if (zza2 instanceof int[]) {
                        int[] iArr = (int[]) zza2;
                        ArrayList arrayList = new ArrayList(iArr.length);
                        for (int i2 : iArr) {
                            arrayList.add(Integer.valueOf(i2 * intValue));
                        }
                        obj = (Serializable) arrayList.toArray(new Integer[0]);
                    } else {
                        throw new zzs(4, 5, null);
                    }
                    zzbhVar.zze().zzf(i, obj);
                    return;
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
