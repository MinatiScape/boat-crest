package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.stats.WakeLock;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public final class t0 {

    /* renamed from: a  reason: collision with root package name */
    public static final long f11363a = TimeUnit.MINUTES.toMillis(1);
    public static final Object b = new Object();
    @GuardedBy("WakeLockHolder.syncObject")
    public static WakeLock c;

    @GuardedBy("WakeLockHolder.syncObject")
    public static void a(Context context) {
        if (c == null) {
            WakeLock wakeLock = new WakeLock(context, 1, "wake:com.google.firebase.iid.WakeLockHolder");
            c = wakeLock;
            wakeLock.setReferenceCounted(true);
        }
    }

    public static void b(@NonNull Intent intent) {
        synchronized (b) {
            if (c != null && c(intent)) {
                d(intent, false);
                c.release();
            }
        }
    }

    @VisibleForTesting
    public static boolean c(@NonNull Intent intent) {
        return intent.getBooleanExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", false);
    }

    public static void d(@NonNull Intent intent, boolean z) {
        intent.putExtra("com.google.firebase.iid.WakeLockHolder.wakefulintent", z);
    }

    public static ComponentName e(@NonNull Context context, @NonNull Intent intent) {
        synchronized (b) {
            a(context);
            boolean c2 = c(intent);
            d(intent, true);
            ComponentName startService = context.startService(intent);
            if (startService == null) {
                return null;
            }
            if (!c2) {
                c.acquire(f11363a);
            }
            return startService;
        }
    }
}
