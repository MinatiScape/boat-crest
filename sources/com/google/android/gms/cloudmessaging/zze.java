package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.cloudmessaging.zzf;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zze {
    @Nullable
    @GuardedBy("MessengerIpcClient.class")
    public static zze e;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8232a;
    public final ScheduledExecutorService b;
    @GuardedBy("this")
    public c c = new c(this);
    @GuardedBy("this")
    public int d = 1;

    @VisibleForTesting
    public zze(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.b = scheduledExecutorService;
        this.f8232a = context.getApplicationContext();
    }

    public static synchronized zze zza(Context context) {
        zze zzeVar;
        synchronized (zze.class) {
            if (e == null) {
                e = new zze(context, com.google.android.gms.internal.cloudmessaging.zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), zzf.zzb));
            }
            zzeVar = e;
        }
        return zzeVar;
    }

    public final synchronized int a() {
        int i;
        i = this.d;
        this.d = i + 1;
        return i;
    }

    public final synchronized <T> Task<T> c(m<T> mVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(mVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.c.e(mVar)) {
            c cVar = new c(this);
            this.c = cVar;
            cVar.e(mVar);
        }
        return mVar.b.getTask();
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return c(new n(a(), 1, bundle));
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return c(new k(a(), 2, bundle));
    }
}
