package com.google.android.gms.maps.internal;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class zza {
    public static byte zza(@Nullable Boolean bool) {
        if (bool != null) {
            return !bool.booleanValue() ? (byte) 0 : (byte) 1;
        }
        return (byte) -1;
    }

    @Nullable
    public static Boolean zzb(byte b) {
        if (b != 0) {
            if (b != 1) {
                return null;
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
