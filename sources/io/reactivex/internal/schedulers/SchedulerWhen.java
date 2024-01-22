package io.reactivex.internal.schedulers;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.UnicastProcessor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public class SchedulerWhen extends Scheduler implements Disposable {
    public static final Disposable l = new g();
    public static final Disposable m = Disposables.disposed();
    public final Scheduler i;
    public final FlowableProcessor<Flowable<Completable>> j;
    public Disposable k;

    /* loaded from: classes12.dex */
    public static final class a implements Function<f, Completable> {
        public final Scheduler.Worker h;

        /* renamed from: io.reactivex.internal.schedulers.SchedulerWhen$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0799a extends Completable {
            public final f h;

            public C0799a(f fVar) {
                this.h = fVar;
            }

            @Override // io.reactivex.Completable
            public void subscribeActual(CompletableObserver completableObserver) {
                completableObserver.onSubscribe(this.h);
                this.h.call(a.this.h, completableObserver);
            }
        }

        public a(Scheduler.Worker worker) {
            this.h = worker;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Completable apply(f fVar) {
            return new C0799a(fVar);
        }
    }

    /* loaded from: classes12.dex */
    public static class b extends f {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        public b(Runnable runnable, long j, TimeUnit timeUnit) {
            this.action = runnable;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.internal.schedulers.SchedulerWhen.f
        public Disposable callActual(Scheduler.Worker worker, CompletableObserver completableObserver) {
            return worker.schedule(new d(this.action, completableObserver), this.delayTime, this.unit);
        }
    }

    /* loaded from: classes12.dex */
    public static class c extends f {
        private final Runnable action;

        public c(Runnable runnable) {
            this.action = runnable;
        }

        @Override // io.reactivex.internal.schedulers.SchedulerWhen.f
        public Disposable callActual(Scheduler.Worker worker, CompletableObserver completableObserver) {
            return worker.schedule(new d(this.action, completableObserver));
        }
    }

    /* loaded from: classes12.dex */
    public static class d implements Runnable {
        public final CompletableObserver h;
        public final Runnable i;

        public d(Runnable runnable, CompletableObserver completableObserver) {
            this.i = runnable;
            this.h = completableObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.i.run();
            } finally {
                this.h.onComplete();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class f extends AtomicReference<Disposable> implements Disposable {
        public f() {
            super(SchedulerWhen.l);
        }

        public void call(Scheduler.Worker worker, CompletableObserver completableObserver) {
            Disposable disposable;
            Disposable disposable2 = get();
            if (disposable2 != SchedulerWhen.m && disposable2 == (disposable = SchedulerWhen.l)) {
                Disposable callActual = callActual(worker, completableObserver);
                if (compareAndSet(disposable, callActual)) {
                    return;
                }
                callActual.dispose();
            }
        }

        public abstract Disposable callActual(Scheduler.Worker worker, CompletableObserver completableObserver);

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            Disposable disposable;
            Disposable disposable2 = SchedulerWhen.m;
            do {
                disposable = get();
                if (disposable == SchedulerWhen.m) {
                    return;
                }
            } while (!compareAndSet(disposable, disposable2));
            if (disposable != SchedulerWhen.l) {
                disposable.dispose();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get().isDisposed();
        }
    }

    /* loaded from: classes12.dex */
    public static final class g implements Disposable {
        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SchedulerWhen(Function<Flowable<Flowable<Completable>>, Completable> function, Scheduler scheduler) {
        this.i = scheduler;
        FlowableProcessor serialized = UnicastProcessor.create().toSerialized();
        this.j = serialized;
        try {
            this.k = ((Completable) function.apply(serialized)).subscribe();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        Scheduler.Worker createWorker = this.i.createWorker();
        FlowableProcessor<T> serialized = UnicastProcessor.create().toSerialized();
        Flowable<Completable> map = serialized.map(new a(createWorker));
        e eVar = new e(serialized, createWorker);
        this.j.onNext(map);
        return eVar;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        this.k.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.k.isDisposed();
    }

    /* loaded from: classes12.dex */
    public static final class e extends Scheduler.Worker {
        public final AtomicBoolean h = new AtomicBoolean();
        public final FlowableProcessor<f> i;
        public final Scheduler.Worker j;

        public e(FlowableProcessor<f> flowableProcessor, Scheduler.Worker worker) {
            this.i = flowableProcessor;
            this.j = worker;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.h.compareAndSet(false, true)) {
                this.i.onComplete();
                this.j.dispose();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.h.get();
        }

        @Override // io.reactivex.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
            b bVar = new b(runnable, j, timeUnit);
            this.i.onNext(bVar);
            return bVar;
        }

        @Override // io.reactivex.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            c cVar = new c(runnable);
            this.i.onNext(cVar);
            return cVar;
        }
    }
}
