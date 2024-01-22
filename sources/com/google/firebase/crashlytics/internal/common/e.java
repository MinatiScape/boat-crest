package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11148a;
    public Task<Void> b = Tasks.forResult(null);
    public final Object c = new Object();
    public final ThreadLocal<Boolean> d = new ThreadLocal<>();

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.d.set(Boolean.TRUE);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ Runnable h;

        public b(e eVar, Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            this.h.run();
            return null;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class c<T> implements Continuation<Void, T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callable f11149a;

        public c(e eVar, Callable callable) {
            this.f11149a = callable;
        }

        @Override // com.google.android.gms.tasks.Continuation
        public T then(@NonNull Task<Void> task) throws Exception {
            return (T) this.f11149a.call();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class d<T> implements Continuation<T, Void> {
        public d(e eVar) {
        }

        @Override // com.google.android.gms.tasks.Continuation
        /* renamed from: a */
        public Void then(@NonNull Task<T> task) throws Exception {
            return null;
        }
    }

    public e(Executor executor) {
        this.f11148a = executor;
        executor.execute(new a());
    }

    public void b() {
        if (!e()) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
    }

    public Executor c() {
        return this.f11148a;
    }

    public final <T> Task<Void> d(Task<T> task) {
        return task.continueWith(this.f11148a, new d(this));
    }

    public final boolean e() {
        return Boolean.TRUE.equals(this.d.get());
    }

    public final <T> Continuation<Void, T> f(Callable<T> callable) {
        return new c(this, callable);
    }

    public Task<Void> g(Runnable runnable) {
        return h(new b(this, runnable));
    }

    public <T> Task<T> h(Callable<T> callable) {
        Task<T> continueWith;
        synchronized (this.c) {
            continueWith = this.b.continueWith(this.f11148a, f(callable));
            this.b = d(continueWith);
        }
        return continueWith;
    }

    public <T> Task<T> i(Callable<Task<T>> callable) {
        Task<T> continueWithTask;
        synchronized (this.c) {
            continueWithTask = this.b.continueWithTask(this.f11148a, f(callable));
            this.b = d(continueWithTask);
        }
        return continueWithTask;
    }
}
