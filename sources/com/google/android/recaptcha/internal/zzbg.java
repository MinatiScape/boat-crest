package com.google.android.recaptcha.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzbg {
    @NotNull
    public static final zzbg zza = new zzbg();

    private zzbg() {
    }

    @NotNull
    public static final Class zza(@Nullable Object obj) {
        Class cls;
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (!(obj instanceof Integer)) {
            if (obj instanceof String) {
                String str = (String) obj;
                if (zzaz.zzc(str)) {
                    try {
                        return Class.forName(str);
                    } catch (Exception e) {
                        throw new zzs(6, 8, e);
                    }
                }
                throw new zzs(6, 47, null);
            }
            throw new zzs(6, 8, null);
        }
        int intValue = ((Number) obj).intValue();
        if (intValue == 1) {
            cls = Integer.TYPE;
        } else if (intValue == 2) {
            cls = Short.TYPE;
        } else {
            cls = intValue == 3 ? Byte.TYPE : intValue == 4 ? Long.TYPE : intValue == 5 ? Character.TYPE : intValue == 6 ? Float.TYPE : intValue == 7 ? Double.TYPE : intValue == 8 ? Boolean.TYPE : null;
        }
        if (cls != null) {
            return cls;
        }
        throw new zzs(4, 6, null);
    }
}
