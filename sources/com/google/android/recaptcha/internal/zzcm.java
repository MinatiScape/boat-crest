package com.google.android.recaptcha.internal;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcm implements zzca {
    @NotNull
    public static final zzcm zza = new zzcm();

    private zzcm() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length != 0) {
            Constructor<?> zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Constructor<?> constructor = zza2 instanceof Constructor ? zza2 : zza2.getClass().getConstructor(new Class[0]);
                Object[] zzh = zzbhVar.zze().zzh(ArraysKt___ArraysKt.toList(zznlVarArr).subList(1, length));
                try {
                    zzbhVar.zze().zzf(i, constructor.newInstance(Arrays.copyOf(zzh, zzh.length)));
                    return;
                } catch (Exception e) {
                    throw new zzs(6, 14, e);
                }
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
