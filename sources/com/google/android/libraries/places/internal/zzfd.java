package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes10.dex */
public abstract class zzfd {
    public static zzfd zza(char c) {
        return new zzff('.');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzd(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public abstract boolean zzb(char c);

    public int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        zzft.zza(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zzb(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
