package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbw implements zzca {
    @NotNull
    public static final zzbw zza = new zzbw();

    private zzbw() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int length = zznlVarArr.length;
        if (length != 4 && length != 5) {
            throw new zzs(4, 3, null);
        }
        Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
        if (true != (zza2 instanceof Integer)) {
            zza2 = null;
        }
        Integer num = (Integer) zza2;
        if (num != null) {
            int intValue = num.intValue();
            Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
            if (true != (zza3 instanceof Integer)) {
                zza3 = null;
            }
            Integer num2 = (Integer) zza3;
            if (num2 != null) {
                int intValue2 = num2.intValue();
                Object zza4 = zzbhVar.zze().zza(zznlVarArr[2]);
                if (true != (zza4 instanceof String)) {
                    zza4 = null;
                }
                String str = (String) zza4;
                if (str != null) {
                    String zza5 = zzbz.zza(this, str, zzbhVar.zza());
                    Object zza6 = zzbhVar.zze().zza(zznlVarArr[3]);
                    if (true != (zza6 instanceof String)) {
                        zza6 = null;
                    }
                    String str2 = (String) zza6;
                    if (str2 != null) {
                        String zza7 = zzbz.zza(this, str2, zzbhVar.zza());
                        Object zza8 = length == 5 ? zzbhVar.zze().zza(zznlVarArr[4]) : null;
                        zzbe zzbeVar = new zzbe(intValue2);
                        try {
                            Class zza9 = zzbg.zza(zza5);
                            zzbhVar.zze().zzf(intValue, Proxy.newProxyInstance(zza9.getClassLoader(), new Class[]{zza9}, new zzbf(zzbeVar, zza7, zza8)));
                            zzbhVar.zze().zzf(i, zzbeVar);
                            return;
                        } catch (Exception e) {
                            throw new zzs(6, 20, e);
                        }
                    }
                    throw new zzs(4, 5, null);
                }
                throw new zzs(4, 5, null);
            }
            throw new zzs(4, 5, null);
        }
        throw new zzs(4, 5, null);
    }
}
