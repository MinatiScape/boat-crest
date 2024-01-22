package com.google.android.libraries.places.internal;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzdk {
    @Nullable
    @GuardedBy("CrashReporter.class")
    private static DropBoxManager zza;
    @GuardedBy("CrashReporter.class")
    private static final LinkedHashMap<Long, Integer> zzb = new zzdm(16, 0.75f, true);
    @Nullable
    @GuardedBy("CrashReporter.class")
    private static String zzc;

    public static synchronized void zza(Context context, boolean z) {
        synchronized (zzdk.class) {
            if (zza == null) {
                zza = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
                zzc = "com.google.android.libraries.places";
            }
        }
    }

    public static synchronized void zza(Throwable th) {
        synchronized (zzdk.class) {
            long id = Thread.currentThread().getId();
            int hashCode = th.hashCode();
            LinkedHashMap<Long, Integer> linkedHashMap = zzb;
            Integer num = linkedHashMap.get(Long.valueOf(id));
            if (num == null || num.intValue() != hashCode) {
                DropBoxManager dropBoxManager = zza;
                if (dropBoxManager != null && dropBoxManager.isTagEnabled("system_app_crash")) {
                    DropBoxManager dropBoxManager2 = zza;
                    dropBoxManager2.addText("system_app_crash", String.format("Package: %s v%d (%s)\n", zzc, Long.valueOf(zza("2.4.0")), "2.4.0") + String.format("Build: %s\n", Build.FINGERPRINT) + "\n" + Log.getStackTraceString(th));
                    linkedHashMap.put(Long.valueOf(id), Integer.valueOf(hashCode));
                }
            }
        }
    }

    private static long zza(String str) {
        List<String> zza2 = zzfs.zza(zzfd.zza('.')).zza(str);
        if (zza2.size() != 3) {
            return -1L;
        }
        long j = 0;
        for (int i = 0; i < zza2.size(); i++) {
            try {
                j = (j * 100) + Integer.parseInt(zza2.get(i));
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }
        return j;
    }
}
