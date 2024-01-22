package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzbv implements zzca {
    @NotNull
    public static final zzbv zza = new zzbv();

    private zzbv() {
    }

    @Override // com.google.android.recaptcha.internal.zzca
    public final void zza(int i, @NotNull zzbh zzbhVar, @NotNull zznl... zznlVarArr) throws zzs {
        int i2;
        int length = zznlVarArr.length;
        if (length != 4 && length != 5) {
            throw new zzs(4, 3, null);
        }
        Object zza2 = zzbhVar.zze().zza(zznlVarArr[0]);
        if (true != (zza2 instanceof String)) {
            zza2 = null;
        }
        String str = (String) zza2;
        if (str != null) {
            Object zza3 = zzbhVar.zze().zza(zznlVarArr[1]);
            if (true != (zza3 instanceof Object)) {
                zza3 = null;
            }
            if (zza3 != null) {
                Object zza4 = zzbhVar.zze().zza(zznlVarArr[2]);
                if (true != (zza4 instanceof String)) {
                    zza4 = null;
                }
                String str2 = (String) zza4;
                if (str2 != null) {
                    String zza5 = zzbz.zza(this, str2, zzbhVar.zza());
                    Object zza6 = zzbhVar.zze().zza(zznlVarArr[3]);
                    if (length == 5) {
                        Object zza7 = zzbhVar.zze().zza(zznlVarArr[4]);
                        if (true != (zza7 instanceof Integer)) {
                            zza7 = null;
                        }
                        Integer num = (Integer) zza7;
                        if (num != null) {
                            i2 = num.intValue();
                        } else {
                            throw new zzs(4, 5, null);
                        }
                    } else {
                        i2 = -1;
                    }
                    try {
                        byte zza8 = zzbhVar.zza();
                        if (zza3 instanceof String) {
                            zza3 = zzbz.zza(this, (String) zza3, zza8);
                        }
                        Class zza9 = zzbg.zza(zza3);
                        zzbhVar.zze().zzf(i, Proxy.newProxyInstance(zza9.getClassLoader(), new Class[]{zza9}, new zzbd(new zzbu(zzbhVar, str, i2), zza5, zza6)));
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
}
