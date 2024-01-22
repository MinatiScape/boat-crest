package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzcf implements zzca {
    @NotNull
    public static final zzcf zza = new zzcf();

    private zzcf() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length >= 2) {
            Class<?> zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Class<?> cls = zza2 instanceof Class ? zza2 : zza2.getClass();
                Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
                if (true != (zza3 instanceof String)) {
                    zza3 = null;
                }
                String str = (String) zza3;
                if (str != null) {
                    String zza4 = zzbz.zza(this, str, zzbhVar.zza());
                    if (!Intrinsics.areEqual(zza4, "forName")) {
                        Class[] zzg = zzbhVar.zze().zzg(ArraysKt___ArraysKt.toList(zznlVarArr).subList(2, length));
                        try {
                            zzbhVar.zze().zzf(i, cls.getMethod(zza4, (Class[]) Arrays.copyOf(zzg, zzg.length)));
                            return;
                        } catch (Exception e) {
                            throw new zzs(6, 13, e);
                        }
                    }
                    throw new zzs(6, 48, null);
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 3, null);
    }
}
