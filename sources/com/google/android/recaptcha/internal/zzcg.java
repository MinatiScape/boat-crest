package com.google.android.recaptcha.internal;

import java.lang.reflect.Field;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcg implements zzca {
    @NotNull
    public static final zzcg zza = new zzcg();

    private zzcg() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Field)) {
                zza2 = null;
            }
            Field field = (Field) zza2;
            if (field != null) {
                try {
                    zzbhVar.zze().zzf(i, field.get(zzbhVar.zze().zza(zznlVarArr[1])));
                    return;
                } catch (Exception e) {
                    throw new zzs(6, 16, e);
                }
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
