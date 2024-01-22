package com.google.android.gms.location;
/* loaded from: classes10.dex */
public final class zzai {
    public static String zza(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return "THROTTLE_NEVER";
                }
                throw new IllegalArgumentException();
            }
            return "THROTTLE_ALWAYS";
        }
        return "THROTTLE_BACKGROUND";
    }
}
