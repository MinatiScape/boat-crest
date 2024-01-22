package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
public final class v {
    public static final Map n = new HashMap();

    /* renamed from: a */
    public final Context f10474a;
    public final k b;
    public boolean g;
    public final Intent h;
    @Nullable
    public ServiceConnection l;
    @Nullable
    public IInterface m;
    public final List d = new ArrayList();
    @GuardedBy("attachedRemoteTasksLock")
    public final Set e = new HashSet();
    public final Object f = new Object();
    public final IBinder.DeathRecipient j = new IBinder.DeathRecipient() { // from class: com.google.android.play.integrity.internal.n
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            v.h(v.this);
        }
    };
    @GuardedBy("attachedRemoteTasksLock")
    public final AtomicInteger k = new AtomicInteger(0);
    public final String c = "IntegrityService";
    public final WeakReference i = new WeakReference(null);

    public v(Context context, k kVar, String str, Intent intent, com.google.android.play.core.integrity.q qVar, @Nullable q qVar2, byte[] bArr) {
        this.f10474a = context;
        this.b = kVar;
        this.h = intent;
    }

    public static /* synthetic */ void h(v vVar) {
        vVar.b.d("reportBinderDeath", new Object[0]);
        q qVar = (q) vVar.i.get();
        if (qVar != null) {
            vVar.b.d("calling onBinderDied", new Object[0]);
            qVar.a();
        } else {
            vVar.b.d("%s : Binder has died.", vVar.c);
            for (l lVar : vVar.d) {
                lVar.a(vVar.s());
            }
            vVar.d.clear();
        }
        vVar.t();
    }

    public static /* bridge */ /* synthetic */ void m(v vVar, l lVar) {
        if (vVar.m != null || vVar.g) {
            if (vVar.g) {
                vVar.b.d("Waiting to bind to the service.", new Object[0]);
                vVar.d.add(lVar);
                return;
            }
            lVar.run();
            return;
        }
        vVar.b.d("Initiate binding to the service.", new Object[0]);
        vVar.d.add(lVar);
        u uVar = new u(vVar, null);
        vVar.l = uVar;
        vVar.g = true;
        if (vVar.f10474a.bindService(vVar.h, uVar, 1)) {
            return;
        }
        vVar.b.d("Failed to bind to the service.", new Object[0]);
        vVar.g = false;
        for (l lVar2 : vVar.d) {
            lVar2.a(new w());
        }
        vVar.d.clear();
    }

    public static /* bridge */ /* synthetic */ void n(v vVar) {
        vVar.b.d("linkToDeath", new Object[0]);
        try {
            vVar.m.asBinder().linkToDeath(vVar.j, 0);
        } catch (RemoteException e) {
            vVar.b.c(e, "linkToDeath failed", new Object[0]);
        }
    }

    public static /* bridge */ /* synthetic */ void o(v vVar) {
        vVar.b.d("unlinkToDeath", new Object[0]);
        vVar.m.asBinder().unlinkToDeath(vVar.j, 0);
    }

    public final Handler c() {
        Handler handler;
        Map map = n;
        synchronized (map) {
            if (!map.containsKey(this.c)) {
                HandlerThread handlerThread = new HandlerThread(this.c, 10);
                handlerThread.start();
                map.put(this.c, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.c);
        }
        return handler;
    }

    @Nullable
    public final IInterface e() {
        return this.m;
    }

    public final void p(l lVar, @Nullable final TaskCompletionSource taskCompletionSource) {
        synchronized (this.f) {
            this.e.add(taskCompletionSource);
            taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.play.integrity.internal.m
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    v.this.q(taskCompletionSource, task);
                }
            });
        }
        synchronized (this.f) {
            if (this.k.getAndIncrement() > 0) {
                this.b.a("Already connected to the service.", new Object[0]);
            }
        }
        c().post(new o(this, lVar.c(), lVar));
    }

    public final /* synthetic */ void q(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.f) {
            this.e.remove(taskCompletionSource);
        }
    }

    public final void r(TaskCompletionSource taskCompletionSource) {
        synchronized (this.f) {
            this.e.remove(taskCompletionSource);
        }
        synchronized (this.f) {
            if (this.k.get() > 0 && this.k.decrementAndGet() > 0) {
                this.b.d("Leaving the connection open for other ongoing calls.", new Object[0]);
                return;
            }
            c().post(new p(this));
        }
    }

    public final RemoteException s() {
        return Build.VERSION.SDK_INT < 15 ? new RemoteException() : new RemoteException(String.valueOf(this.c).concat(" : Binder has died."));
    }

    public final void t() {
        synchronized (this.f) {
            for (TaskCompletionSource taskCompletionSource : this.e) {
                taskCompletionSource.trySetException(s());
            }
            this.e.clear();
        }
    }
}
