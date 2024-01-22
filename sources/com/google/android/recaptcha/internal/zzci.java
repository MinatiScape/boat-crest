package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzci implements zzca {
    @NotNull
    public static final zzci zza = new zzci();

    private zzci() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length >= 2) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Method)) {
                zza2 = null;
            }
            Method method = (Method) zza2;
            if (method != null) {
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                Object[] zzh = zzbhVar.zze().zzh(ArraysKt___ArraysKt.toList(zznlVarArr).subList(2, length));
                try {
                    zzbhVar.zze().zzf(i, method.invoke(zza3, Arrays.copyOf(zzh, zzh.length)));
                    return;
                } catch (Exception e) {
                    throw new zzs(6, 15, e);
                }
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
