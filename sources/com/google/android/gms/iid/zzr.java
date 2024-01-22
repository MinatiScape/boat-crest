package com.google.android.gms.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.gcm.zzg;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zzr {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8496a;
    public final ScheduledExecutorService b;
    @GuardedBy("this")
    public p c;
    @GuardedBy("this")
    public int d;

    public zzr(Context context) {
        this(context, zzg.zzaa().zze(1, new NamedThreadFactory("MessengerIpcClient"), 9));
    }

    public final synchronized <T> Task<T> b(w<T> wVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(wVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.c.c(wVar)) {
            p pVar = new p(this);
            this.c = pVar;
            pVar.c(wVar);
        }
        return wVar.b.getTask();
    }

    public final synchronized int d() {
        int i;
        i = this.d;
        this.d = i + 1;
        return i;
    }

    public final Task<Bundle> zzd(int i, Bundle bundle) {
        return b(new a(d(), 1, bundle));
    }

    @VisibleForTesting
    public zzr(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.c = new p(this);
        this.d = 1;
        this.f8496a = context.getApplicationContext();
        this.b = scheduledExecutorService;
    }
}
