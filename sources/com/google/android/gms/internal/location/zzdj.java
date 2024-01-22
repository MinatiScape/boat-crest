package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.clevertap.android.sdk.Constants;
import java.text.SimpleDateFormat;
import java.util.Locale;
/* loaded from: classes8.dex */
public final class zzdj {
    @GuardedBy("sharedStringBuilder")

    /* renamed from: a  reason: collision with root package name */
    public static final StringBuilder f8899a;

    static {
        Locale locale = Locale.ROOT;
        new SimpleDateFormat("MM-dd HH:mm:ss.SSS", locale);
        new SimpleDateFormat("MM-dd HH:mm:ss", locale);
        f8899a = new StringBuilder(33);
    }

    public static String zza(long j) {
        String sb;
        StringBuilder sb2 = f8899a;
        synchronized (sb2) {
            sb2.setLength(0);
            zzb(j, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public static void zzb(long j, StringBuilder sb) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            sb.append("0s");
            return;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z = false;
        if (i < 0) {
            sb.append("-");
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                j = Long.MAX_VALUE;
                z = true;
            }
        }
        if (j >= 86400000) {
            sb.append(j / 86400000);
            sb.append("d");
            j %= 86400000;
        }
        if (true == z) {
            j = 25975808;
        }
        if (j >= 3600000) {
            sb.append(j / 3600000);
            sb.append("h");
            j %= 3600000;
        }
        if (j >= Constants.ONE_MIN_IN_MILLIS) {
            sb.append(j / Constants.ONE_MIN_IN_MILLIS);
            sb.append("m");
            j %= Constants.ONE_MIN_IN_MILLIS;
        }
        if (j >= 1000) {
            sb.append(j / 1000);
            sb.append("s");
            j %= 1000;
        }
        if (j > 0) {
            sb.append(j);
            sb.append("ms");
        }
    }
}
