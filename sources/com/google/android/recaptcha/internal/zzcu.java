package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcu implements zzca {
    @NotNull
    public static final zzcu zza = new zzcu();

    private zzcu() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof int[])) {
                zza2 = null;
            }
            int[] iArr = (int[]) zza2;
            if (iArr != null) {
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    zzbi zze = zzbhVar.zze();
                    StringBuilder sb = new StringBuilder();
                    try {
                        for (int i2 : iArr) {
                            sb.append(str.charAt(i2));
                        }
                        zze.zzf(i, sb.toString());
                        return;
                    } catch (Exception e) {
                        throw new zzs(4, 22, e);
                    }
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
