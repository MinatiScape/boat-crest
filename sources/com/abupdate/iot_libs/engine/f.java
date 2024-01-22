package com.abupdate.iot_libs.engine;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class f {
    public static volatile f c;

    /* renamed from: a  reason: collision with root package name */
    public ThreadFactory f1895a = null;
    public ThreadPoolExecutor b = null;

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(1);

        public a(f fVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "FOTA—THREAD_" + this.h.getAndIncrement());
        }
    }

    public static f a() {
        if (c == null) {
            synchronized (f.class) {
                c = new f();
            }
        }
        return c;
    }

    public void b() {
        if (this.b == null) {
            if (this.f1895a == null) {
                this.f1895a = new a(this);
            }
            this.b = new ThreadPoolExecutor(10, 60, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(1024), this.f1895a);
        }
    }

    public <T> FutureTask<T> a(Callable<T> callable) {
        b();
        FutureTask<T> futureTask = new FutureTask<>(callable);
        this.b.submit(futureTask);
        return futureTask;
    }

    public void a(Runnable runnable) {
        b();
        this.b.execute(runnable);
    }
}
