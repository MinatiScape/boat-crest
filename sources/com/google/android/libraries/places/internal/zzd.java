package com.google.android.libraries.places.internal;

import android.os.Build;
import android.os.SystemClock;
/* loaded from: classes10.dex */
public final class zzd implements zzb {
    private static final boolean zza = zzc();

    private static boolean zzc() {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                SystemClock.elapsedRealtimeNanos();
                return true;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzb
    public final long zza() {
        return SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.libraries.places.internal.zzb
    public final long zzb() {
        if (zza) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.elapsedRealtime() * 1000000;
    }
}
