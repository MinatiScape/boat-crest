package com.touchgui.sdk.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class i3 {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13776a;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ThreadFactory d = c();
    public ThreadPoolExecutor e;
    public ThreadPoolExecutor f;

    public i3(a0 a0Var) {
        this.f13776a = a0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(d dVar) {
        h3 h3Var;
        ThreadPoolExecutor threadPoolExecutor;
        synchronized (this) {
            if (2 == dVar.d.c()) {
                h3Var = new h3(this, dVar, dVar.d.c());
                this.c.add(h3Var);
                threadPoolExecutor = (ThreadPoolExecutor) b();
            } else {
                h3Var = new h3(this, dVar, dVar.d.c());
                this.c.add(h3Var);
                threadPoolExecutor = (ThreadPoolExecutor) a();
            }
            threadPoolExecutor.execute(h3Var);
        }
    }

    public final synchronized void a(d dVar) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            h3 h3Var = (h3) it.next();
            if (h3Var.f13769a == dVar) {
                h3Var.a(true);
            }
        }
    }

    public final void b(final d dVar) {
        h3 h3Var;
        ThreadPoolExecutor threadPoolExecutor;
        if ("TouchGUI-dispatcher".equals(Thread.currentThread().getName())) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.ic
                @Override // java.lang.Runnable
                public final void run() {
                    i3.this.c(dVar);
                }
            });
            return;
        }
        synchronized (this) {
            if (2 == dVar.d.c()) {
                h3Var = new h3(this, dVar, dVar.d.c());
                this.c.add(h3Var);
                threadPoolExecutor = (ThreadPoolExecutor) b();
            } else {
                h3Var = new h3(this, dVar, dVar.d.c());
                this.c.add(h3Var);
                threadPoolExecutor = (ThreadPoolExecutor) a();
            }
            threadPoolExecutor.execute(h3Var);
        }
    }

    public final synchronized ExecutorService a() {
        if (this.e == null) {
            this.e = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), this.d);
        }
        return this.e;
    }

    public static /* synthetic */ Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable, "TouchGUI-dispatcher");
        thread.setDaemon(false);
        return thread;
    }

    public final synchronized void a(h3 h3Var) {
        this.c.remove(h3Var);
    }

    public static ThreadFactory c() {
        return new ThreadFactory() { // from class: com.touchgui.sdk.internal.jc
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return i3.a(runnable);
            }
        };
    }

    public final synchronized ExecutorService b() {
        if (this.f == null) {
            this.f = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), this.d);
        }
        return this.f;
    }
}
