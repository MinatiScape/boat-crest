package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcj implements zzca {
    @NotNull
    public static final zzcj zza = new zzcj();

    private zzcj() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length != 0) {
            Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Method)) {
                zza2 = null;
            }
            Method method = (Method) zza2;
            if (method != null) {
                Object[] zzh = zzbhVar.zze().zzh(ArraysKt___ArraysKt.toList(zznlVarArr).subList(1, length));
                try {
                    zzbhVar.zze().zzf(i, method.invoke(null, Arrays.copyOf(zzh, zzh.length)));
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
