package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcs implements zzca {
    @NotNull
    public static final zzcs zza = new zzcs();

    private zzcs() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        if (zznlVarArr.length == 1) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Byte)) {
                zza2 = null;
            }
            Byte b = (Byte) zza2;
            if (b != null) {
                zzbhVar.zzj(b.byteValue());
                return;
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
