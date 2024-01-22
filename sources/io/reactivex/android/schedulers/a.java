package io.reactivex.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class a extends Scheduler {
    public final Handler i;
    public final boolean j;

    /* renamed from: io.reactivex.android.schedulers.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0735a extends Scheduler.Worker {
        public final Handler h;
        public final boolean i;
        public volatile boolean j;

        public C0735a(Handler handler, boolean z) {
            this.h = handler;
            this.i = z;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j = true;
            this.h.removeCallbacksAndMessages(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j;
        }

        @Override // io.reactivex.Scheduler.Worker
        @SuppressLint({"NewApi"})
        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.j) {
                return Disposables.disposed();
            }
            b bVar = new b(this.h, RxJavaPlugins.onSchedule(runnable));
            Message obtain = Message.obtain(this.h, bVar);
            obtain.obj = this;
            if (this.i) {
                obtain.setAsynchronous(true);
            }
            this.h.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            if (this.j) {
                this.h.removeCallbacks(bVar);
                return Disposables.disposed();
            }
            return bVar;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements Runnable, Disposable {
        public final Handler h;
        public final Runnable i;
        public volatile boolean j;

        public b(Handler handler, Runnable runnable) {
            this.h = handler;
            this.i = runnable;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.h.removeCallbacks(this);
            this.j = true;
        }

        @Override // io.reactivex.disposables.Disposable
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

    public a(Handler handler, boolean z) {
        this.i = handler;
        this.j = z;
    }

    @Override // io.reactivex.Scheduler
    public Scheduler.Worker createWorker() {
        return new C0735a(this.i, this.j);
    }

    @Override // io.reactivex.Scheduler
    @SuppressLint({"NewApi"})
    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        b bVar = new b(this.i, RxJavaPlugins.onSchedule(runnable));
        Message obtain = Message.obtain(this.i, bVar);
        if (this.j) {
            obtain.setAsynchronous(true);
        }
        this.i.sendMessageDelayed(obtain, timeUnit.toMillis(j));
        return bVar;
    }
}
