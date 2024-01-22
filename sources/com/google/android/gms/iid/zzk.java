package com.google.android.gms.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
/* loaded from: classes6.dex */
public final class zzk implements ServiceConnection {
    public final Context h;
    public final Intent i;
    public final ScheduledExecutorService j;
    public final Queue<h> k;
    public zzi l;
    @GuardedBy("this")
    public boolean m;

    public zzk(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("EnhancedIntentService")));
    }

    public final synchronized void a() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "flush queue called");
        }
        while (!this.k.isEmpty()) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "found intent to be delivered");
            }
            zzi zziVar = this.l;
            if (zziVar != null && zziVar.isBinderAlive()) {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    Log.d("EnhancedIntentService", "binder is alive, sending the intent.");
                }
                this.l.zzd(this.k.poll());
            } else {
                if (Log.isLoggable("EnhancedIntentService", 3)) {
                    boolean z = !this.m;
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("binder is dead. start connection? ");
                    sb.append(z);
                    Log.d("EnhancedIntentService", sb.toString());
                }
                if (!this.m) {
                    this.m = true;
                    try {
                    } catch (SecurityException e) {
                        Log.e("EnhancedIntentService", "Exception while binding the service", e);
                    }
                    if (ConnectionTracker.getInstance().bindService(this.h, this.i, this, 65)) {
                        return;
                    }
                    Log.e("EnhancedIntentService", "binding to the service failed");
                    this.m = false;
                    b();
                }
                return;
            }
        }
    }

    @GuardedBy("this")
    public final void b() {
        while (!this.k.isEmpty()) {
            this.k.poll().a();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this) {
            this.m = false;
            this.l = (zzi) iBinder;
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                String valueOf = String.valueOf(componentName);
                StringBuilder sb = new StringBuilder(valueOf.length() + 20);
                sb.append("onServiceConnected: ");
                sb.append(valueOf);
                Log.d("EnhancedIntentService", sb.toString());
            }
            if (iBinder == null) {
                Log.e("EnhancedIntentService", "Null service connection");
                b();
            } else {
                a();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d("EnhancedIntentService", sb.toString());
        }
        a();
    }

    public final synchronized void zzd(Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "new intent queued in the bind-strategy delivery");
        }
        this.k.add(new h(intent, pendingResult, this.j));
        a();
    }

    @VisibleForTesting
    public zzk(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.k = new ArrayDeque();
        this.m = false;
        Context applicationContext = context.getApplicationContext();
        this.h = applicationContext;
        this.i = new Intent(str).setPackage(applicationContext.getPackageName());
        this.j = scheduledExecutorService;
    }
}
