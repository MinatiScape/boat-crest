package androidx.room;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class c<T> extends LiveData<T> {
    public final RoomDatabase l;
    public final boolean m;
    public final Callable<T> n;
    public final androidx.room.a o;
    public final InvalidationTracker.Observer p;
    public final AtomicBoolean q = new AtomicBoolean(true);
    public final AtomicBoolean r = new AtomicBoolean(false);
    public final AtomicBoolean s = new AtomicBoolean(false);
    public final Runnable t = new a();
    public final Runnable u = new b();

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            boolean z;
            if (c.this.s.compareAndSet(false, true)) {
                c.this.l.getInvalidationTracker().addWeakObserver(c.this.p);
            }
            do {
                if (c.this.r.compareAndSet(false, true)) {
                    T t = null;
                    z = false;
                    while (c.this.q.compareAndSet(true, false)) {
                        try {
                            try {
                                t = c.this.n.call();
                                z = true;
                            } catch (Exception e) {
                                throw new RuntimeException("Exception while computing database live data.", e);
                            }
                        } finally {
                            c.this.r.set(false);
                        }
                    }
                    if (z) {
                        c.this.postValue(t);
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    return;
                }
            } while (c.this.q.get());
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean hasActiveObservers = c.this.hasActiveObservers();
            if (c.this.q.compareAndSet(false, true) && hasActiveObservers) {
                c.this.g().execute(c.this.t);
            }
        }
    }

    /* renamed from: androidx.room.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0181c extends InvalidationTracker.Observer {
        public C0181c(String[] strArr) {
            super(strArr);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(@NonNull Set<String> set) {
            ArchTaskExecutor.getInstance().executeOnMainThread(c.this.u);
        }
    }

    @SuppressLint({"RestrictedApi"})
    public c(RoomDatabase roomDatabase, androidx.room.a aVar, boolean z, Callable<T> callable, String[] strArr) {
        this.l = roomDatabase;
        this.m = z;
        this.n = callable;
        this.o = aVar;
        this.p = new C0181c(strArr);
    }

    public Executor g() {
        if (this.m) {
            return this.l.getTransactionExecutor();
        }
        return this.l.getQueryExecutor();
    }

    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        super.onActive();
        this.o.b(this);
        g().execute(this.t);
    }

    @Override // androidx.lifecycle.LiveData
    public void onInactive() {
        super.onInactive();
        this.o.c(this);
    }
}
