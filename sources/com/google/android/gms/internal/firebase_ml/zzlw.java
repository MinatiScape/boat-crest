package com.google.android.gms.internal.firebase_ml;

import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes7.dex */
public abstract class zzlw {
    public static String a(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static zzlw zza(char c) {
        return new q1(',');
    }

    public abstract boolean zzb(char c);

    public int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        zzml.zza(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zzb(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
