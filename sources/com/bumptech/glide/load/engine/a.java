package com.bumptech.glide.load.engine;

import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.l;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f2350a;
    public final Executor b;
    @VisibleForTesting
    public final Map<Key, d> c;
    public final ReferenceQueue<l<?>> d;
    public l.a e;
    public volatile boolean f;
    @Nullable
    public volatile c g;

    /* renamed from: com.bumptech.glide.load.engine.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class ThreadFactoryC0212a implements ThreadFactory {

        /* renamed from: com.bumptech.glide.load.engine.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public class RunnableC0213a implements Runnable {
            public final /* synthetic */ Runnable h;

            public RunnableC0213a(ThreadFactoryC0212a threadFactoryC0212a, Runnable runnable) {
                this.h = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(10);
                this.h.run();
            }
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(new RunnableC0213a(this, runnable), "glide-active-resources");
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.b();
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public interface c {
        void a();
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class d extends WeakReference<l<?>> {

        /* renamed from: a  reason: collision with root package name */
        public final Key f2351a;
        public final boolean b;
        @Nullable
        public Resource<?> c;

        public d(@NonNull Key key, @NonNull l<?> lVar, @NonNull ReferenceQueue<? super l<?>> referenceQueue, boolean z) {
            super(lVar, referenceQueue);
            this.f2351a = (Key) Preconditions.checkNotNull(key);
            this.c = (lVar.c() && z) ? (Resource) Preconditions.checkNotNull(lVar.b()) : null;
            this.b = lVar.c();
        }

        public void a() {
            this.c = null;
            clear();
        }
    }

    public a(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactoryC0212a()));
    }

    public synchronized void a(Key key, l<?> lVar) {
        d put = this.c.put(key, new d(key, lVar, this.d, this.f2350a));
        if (put != null) {
            put.a();
        }
    }

    public void b() {
        while (!this.f) {
            try {
                c((d) this.d.remove());
                c cVar = this.g;
                if (cVar != null) {
                    cVar.a();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void c(@NonNull d dVar) {
        Resource<?> resource;
        synchronized (this) {
            this.c.remove(dVar.f2351a);
            if (dVar.b && (resource = dVar.c) != null) {
                this.e.onResourceReleased(dVar.f2351a, new l<>(resource, true, false, dVar.f2351a, this.e));
            }
        }
    }

    public synchronized void d(Key key) {
        d remove = this.c.remove(key);
        if (remove != null) {
            remove.a();
        }
    }

    @Nullable
    public synchronized l<?> e(Key key) {
        d dVar = this.c.get(key);
        if (dVar == null) {
            return null;
        }
        l<?> lVar = dVar.get();
        if (lVar == null) {
            c(dVar);
        }
        return lVar;
    }

    public void f(l.a aVar) {
        synchronized (aVar) {
            synchronized (this) {
                this.e = aVar;
            }
        }
    }

    @VisibleForTesting
    public void g() {
        this.f = true;
        Executor executor = this.b;
        if (executor instanceof ExecutorService) {
            com.bumptech.glide.util.Executors.shutdownAndAwaitTermination((ExecutorService) executor);
        }
    }

    @VisibleForTesting
    public a(boolean z, Executor executor) {
        this.c = new HashMap();
        this.d = new ReferenceQueue<>();
        this.f2350a = z;
        this.b = executor;
        executor.execute(new b());
    }
}
