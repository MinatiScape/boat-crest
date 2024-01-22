package io.reactivex.rxjava3.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class b extends Scheduler {
    public final Handler i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a extends Scheduler.Worker {
        public final Handler h;
        public final boolean i;
        public volatile boolean j;

        public a(Handler handler, boolean z) {
            this.h = handler;
            this.i = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j = true;
            this.h.removeCallbacksAndMessages(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @SuppressLint({"NewApi"})
        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.j) {
                return Disposable.disposed();
            }
            RunnableC0800b runnableC0800b = new RunnableC0800b(this.h, RxJavaPlugins.onSchedule(runnable));
            Message obtain = Message.obtain(this.h, runnableC0800b);
            obtain.obj = this;
            if (this.i) {
                obtain.setAsynchronous(true);
            }
            this.h.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            if (this.j) {
                this.h.removeCallbacks(runnableC0800b);
                return Disposable.disposed();
            }
            return runnableC0800b;
        }
    }

    /* renamed from: io.reactivex.rxjava3.android.schedulers.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class RunnableC0800b implements Runnable, Disposable {
        public final Handler h;
        public final Runnable i;
        public volatile boolean j;

        public RunnableC0800b(Handler handler, Runnable runnable) {
            this.h = handler;
            this.i = runnable;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.h.removeCallbacks(this);
            this.j = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.i.run();
            } catch (Throwable th) {
                RxJavaPlugins.onError(th);
            }
        }
    }

    public b(Handler handler, boolean z) {
        this.i = handler;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new a(this.i, this.j);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @SuppressLint({"NewApi"})
    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0800b runnableC0800b = new RunnableC0800b(this.i, RxJavaPlugins.onSchedule(runnable));
        Message obtain = Message.obtain(this.i, runnableC0800b);
        if (this.j) {
            obtain.setAsynchronous(true);
        }
        this.i.sendMessageDelayed(obtain, timeUnit.toMillis(j));
        return runnableC0800b;
    }
}
