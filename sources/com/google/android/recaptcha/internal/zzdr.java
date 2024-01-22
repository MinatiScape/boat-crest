package com.google.android.recaptcha.internal;

import javax.annotation.CheckForNull;
/* loaded from: classes10.dex */
public final class zzdr {
    public static void zza(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzb(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void zzc(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(zzdu.zza(str, Character.valueOf(c)));
        }
    }

    public static void zzd(int i, int i2, int i3) {
        String zzf;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i >= 0 && i <= i3) {
                zzf = (i2 < 0 || i2 > i3) ? zzf(i2, i3, "end index") : zzdu.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            } else {
                zzf = zzf(i, i3, "start index");
            }
            throw new IndexOutOfBoundsException(zzf);
        }
    }

    public static void zze(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzf(int i, int i2, String str) {
        return i < 0 ? zzdu.zza("%s (%s) must not be negative", str, Integer.valueOf(i)) : zzdu.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
