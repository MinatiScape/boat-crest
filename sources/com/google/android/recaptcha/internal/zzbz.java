package com.google.android.recaptcha.internal;

import kotlin.UInt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final /* synthetic */ class zzbz {
    @NotNull
    public static String zza(zzca zzcaVar, @NotNull String str, byte b) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) UInt.m158constructorimpl(UInt.m158constructorimpl(str.charAt(i)) ^ UInt.m158constructorimpl(b)));
        }
        return sb.toString();
    }

    public static void zzb(zzca zzcaVar, int i, int i2) throws zzs {
        if (i != i2) {
            throw new zzs(4, 24, null);
        }
    }
}
