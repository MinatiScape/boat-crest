package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public final Intent f8488a;
    public final BroadcastReceiver.PendingResult b;
    public boolean c = false;
    public final ScheduledFuture<?> d;

    public h(Intent intent, BroadcastReceiver.PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.f8488a = intent;
        this.b = pendingResult;
        this.d = scheduledExecutorService.schedule(new i(this, intent), 9500L, TimeUnit.MILLISECONDS);
    }

    public final synchronized void a() {
        if (!this.c) {
            this.b.finish();
            this.d.cancel(false);
            this.c = true;
        }
    }
}
