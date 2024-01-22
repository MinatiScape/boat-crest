package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.messaging.z0;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class z0 implements ServiceConnection {
    public final Context h;
    public final Intent i;
    public final ScheduledExecutorService j;
    public final Queue<a> k;
    @Nullable
    public w0 l;
    @GuardedBy("this")
    public boolean m;

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Intent f11373a;
        public final TaskCompletionSource<Void> b = new TaskCompletionSource<>();

        public a(Intent intent) {
            this.f11373a = intent;
        }

        public void a(ScheduledExecutorService scheduledExecutorService) {
            final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.firebase.messaging.x0
                public final z0.a h;

                {
                    this.h = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.h.d();
                }
            }, 9000L, TimeUnit.MILLISECONDS);
            c().addOnCompleteListener(scheduledExecutorService, new OnCompleteListener(schedule) { // from class: com.google.firebase.messaging.y0

                /* renamed from: a  reason: collision with root package name */
                public final ScheduledFuture f11371a;

                {
                    this.f11371a = schedule;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task task) {
                    this.f11371a.cancel(false);
                }
            });
        }

        public void b() {
            this.b.trySetResult(null);
        }

        public Task<Void> c() {
            return this.b.getTask();
        }

        public final /* synthetic */ void d() {
            String action = this.f11373a.getAction();
            StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
            sb.append("Service took too long to process intent: ");
            sb.append(action);
            sb.append(" App may get closed.");
            Log.w(Constants.TAG, sb.toString());
            b();
        }
    }

    public z0(Context context, String str) {
        this(context, com.google.firebase.iid.ServiceStarter.ACTION_MESSAGING_EVENT, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    @GuardedBy("this")
    public final void a() {
        while (!this.k.isEmpty()) {
            this.k.poll().b();
        }
    }

    public final synchronized void b() {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "flush queue called");
        }
        while (!this.k.isEmpty()) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "found intent to be delivered");
            }
            w0 w0Var = this.l;
            if (w0Var != null && w0Var.isBinderAlive()) {
                if (Log.isLoggable(Constants.TAG, 3)) {
                    Log.d(Constants.TAG, "binder is alive, sending the intent.");
                }
                this.l.b(this.k.poll());
            } else {
                d();
                return;
            }
        }
    }

    public synchronized Task<Void> c(Intent intent) {
        a aVar;
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "new intent queued in the bind-strategy delivery");
        }
        aVar = new a(intent);
        aVar.a(this.j);
        this.k.add(aVar);
        b();
        return aVar.c();
    }

    @GuardedBy("this")
    public final void d() {
        if (Log.isLoggable(Constants.TAG, 3)) {
            boolean z = this.m;
            StringBuilder sb = new StringBuilder(39);
            sb.append("binder is dead. start connection? ");
            sb.append(!z);
            Log.d(Constants.TAG, sb.toString());
        }
        if (this.m) {
            return;
        }
        this.m = true;
        try {
        } catch (SecurityException e) {
            Log.e(Constants.TAG, "Exception while binding the service", e);
        }
        if (ConnectionTracker.getInstance().bindService(this.h, this.i, this, 65)) {
            return;
        }
        Log.e(Constants.TAG, "binding to the service failed");
        this.m = false;
        a();
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(valueOf.length() + 20);
            sb.append("onServiceConnected: ");
            sb.append(valueOf);
            Log.d(Constants.TAG, sb.toString());
        }
        this.m = false;
        if (!(iBinder instanceof w0)) {
            String valueOf2 = String.valueOf(iBinder);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 28);
            sb2.append("Invalid service connection: ");
            sb2.append(valueOf2);
            Log.e(Constants.TAG, sb2.toString());
            a();
            return;
        }
        this.l = (w0) iBinder;
        b();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d(Constants.TAG, sb.toString());
        }
        b();
    }

    @VisibleForTesting
    public z0(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.k = new ArrayDeque();
        this.m = false;
        Context applicationContext = context.getApplicationContext();
        this.h = applicationContext;
        this.i = new Intent(com.google.firebase.iid.ServiceStarter.ACTION_MESSAGING_EVENT).setPackage(applicationContext.getPackageName());
        this.j = scheduledExecutorService;
    }
}
