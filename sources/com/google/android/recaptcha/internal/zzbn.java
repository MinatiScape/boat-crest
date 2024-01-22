package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbn implements zzca {
    @NotNull
    public static final zzbn zza = new zzbn();

    private zzbn() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 3) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Integer)) {
                zza2 = null;
            }
            Integer num = (Integer) zza2;
            if (num != null) {
                int intValue = num.intValue();
                if (intValue != 0) {
                    Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                    if (true != (zza3 instanceof Object)) {
                        zza3 = null;
                    }
                    if (zza3 != null) {
                        Object zza4 = zzbhVar.zze().zza(zznlVarArr[2]);
                        if (true != (zza4 instanceof Object)) {
                            zza4 = null;
                        }
                        if (zza4 != null) {
                            if (Intrinsics.areEqual(zza3, zza4)) {
                                zzbhVar.zzi(zzbhVar.zzb() + intValue);
                                return;
                            }
                            return;
                        }
                        throw new zzs(4, 5, null);
                    }
                    throw new zzs(4, 5, null);
                }
                throw new zzs(4, 6, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
