package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcd implements zzca {
    @NotNull
    public static final zzcd zza = new zzcd();

    private zzcd() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length != 0) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Class)) {
                zza2 = null;
            }
            Class cls = (Class) zza2;
            if (cls != null) {
                Class[] zzg = zzbhVar.zze().zzg(ArraysKt___ArraysKt.toList(zznlVarArr).subList(1, length));
                try {
                    zzbhVar.zze().zzf(i, cls.getConstructor((Class[]) Arrays.copyOf(zzg, zzg.length)));
                    return;
                } catch (Exception e) {
                    throw new zzs(6, 9, e);
                }
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
