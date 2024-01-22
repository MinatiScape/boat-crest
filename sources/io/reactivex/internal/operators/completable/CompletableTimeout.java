package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class CompletableTimeout extends Completable {
    public final CompletableSource h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final CompletableSource l;

    /* loaded from: classes12.dex */
    public final class a implements Runnable {
        public final AtomicBoolean h;
        public final CompositeDisposable i;
        public final CompletableObserver j;

        /* renamed from: io.reactivex.internal.operators.completable.CompletableTimeout$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0739a implements CompletableObserver {
            public C0739a() {
            }

            @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
            public void onComplete() {
                a.this.i.dispose();
                a.this.j.onComplete();
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                a.this.i.dispose();
                a.this.j.onError(th);
            }

            @Override // io.reactivex.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                a.this.i.add(disposable);
            }
        }

        public a(AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, CompletableObserver completableObserver) {
            this.h = atomicBoolean;
            this.i = compositeDisposable;
            this.j = completableObserver;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.compareAndSet(false, true)) {
                this.i.clear();
                CompletableSource completableSource = CompletableTimeout.this.l;
                if (completableSource == null) {
                    CompletableObserver completableObserver = this.j;
                    CompletableTimeout completableTimeout = CompletableTimeout.this;
                    completableObserver.onError(new TimeoutException(ExceptionHelper.timeoutMessage(completableTimeout.i, completableTimeout.j)));
                    return;
                }
                completableSource.subscribe(new C0739a());
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements CompletableObserver {
        public final CompositeDisposable h;
        public final AtomicBoolean i;
        public final CompletableObserver j;

        public b(CompositeDisposable compositeDisposable, AtomicBoolean atomicBoolean, CompletableObserver completableObserver) {
            this.h = compositeDisposable;
            this.i = atomicBoolean;
            this.j = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            if (this.i.compareAndSet(false, true)) {
                this.h.dispose();
                this.j.onComplete();
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.i.compareAndSet(false, true)) {
                this.h.dispose();
                this.j.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.h.add(disposable);
        }
    }

    public CompletableTimeout(CompletableSource completableSource, long j, TimeUnit timeUnit, Scheduler scheduler, CompletableSource completableSource2) {
        this.h = completableSource;
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = completableSource2;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        completableObserver.onSubscribe(compositeDisposable);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        compositeDisposable.add(this.k.scheduleDirect(new a(atomicBoolean, compositeDisposable, completableObserver), this.i, this.j));
        this.h.subscribe(new b(compositeDisposable, atomicBoolean, completableObserver));
    }
}