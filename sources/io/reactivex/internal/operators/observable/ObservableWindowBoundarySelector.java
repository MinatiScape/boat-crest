package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableWindowBoundarySelector<T, B, V> extends io.reactivex.internal.operators.observable.a<T, Observable<T>> {
    public final ObservableSource<B> h;
    public final Function<? super B, ? extends ObservableSource<V>> i;
    public final int j;

    /* loaded from: classes12.dex */
    public static final class a<T, V> extends DisposableObserver<V> {
        public final c<T, ?, V> i;
        public final UnicastSubject<T> j;
        public boolean k;

        public a(c<T, ?, V> cVar, UnicastSubject<T> unicastSubject) {
            this.i = cVar;
            this.j = unicastSubject;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            this.i.a(this);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.i.d(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(V v) {
            dispose();
            onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, B> extends DisposableObserver<B> {
        public final c<T, B, ?> i;

        public b(c<T, B, ?> cVar) {
            this.i = cVar;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.i.d(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(B b) {
            this.i.e(b);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, B, V> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        public final ObservableSource<B> i;
        public final Function<? super B, ? extends ObservableSource<V>> j;
        public final int k;
        public final CompositeDisposable l;
        public Disposable m;
        public final AtomicReference<Disposable> n;
        public final List<UnicastSubject<T>> o;
        public final AtomicLong p;
        public final AtomicBoolean q;

        public c(Observer<? super Observable<T>> observer, ObservableSource<B> observableSource, Function<? super B, ? extends ObservableSource<V>> function, int i) {
            super(observer, new MpscLinkedQueue());
            this.n = new AtomicReference<>();
            AtomicLong atomicLong = new AtomicLong();
            this.p = atomicLong;
            this.q = new AtomicBoolean();
            this.i = observableSource;
            this.j = function;
            this.k = i;
            this.l = new CompositeDisposable();
            this.o = new ArrayList();
            atomicLong.lazySet(1L);
        }

        public void a(a<T, V> aVar) {
            this.l.delete(aVar);
            this.queue.offer(new d(aVar.j, null));
            if (enter()) {
                c();
            }
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        public void accept(Observer<? super Observable<T>> observer, Object obj) {
        }

        public void b() {
            this.l.dispose();
            DisposableHelper.dispose(this.n);
        }

        public void c() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.queue;
            Observer<? super V> observer = this.downstream;
            List<UnicastSubject<T>> list = this.o;
            int i = 1;
            while (true) {
                boolean z = this.done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    b();
                    Throwable th = this.error;
                    if (th != null) {
                        for (UnicastSubject<T> unicastSubject : list) {
                            unicastSubject.onError(th);
                        }
                    } else {
                        for (UnicastSubject<T> unicastSubject2 : list) {
                            unicastSubject2.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof d) {
                    d dVar = (d) poll;
                    UnicastSubject<T> unicastSubject3 = dVar.f13912a;
                    if (unicastSubject3 != null) {
                        if (list.remove(unicastSubject3)) {
                            dVar.f13912a.onComplete();
                            if (this.p.decrementAndGet() == 0) {
                                b();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.q.get()) {
                        UnicastSubject<T> create = UnicastSubject.create(this.k);
                        list.add(create);
                        observer.onNext(create);
                        try {
                            ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.j.apply((B) dVar.b), "The ObservableSource supplied is null");
                            a aVar = new a(this, create);
                            if (this.l.add(aVar)) {
                                this.p.getAndIncrement();
                                observableSource.subscribe(aVar);
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            this.q.set(true);
                            observer.onError(th2);
                        }
                    }
                } else {
                    for (UnicastSubject<T> unicastSubject4 : list) {
                        unicastSubject4.onNext((T) NotificationLite.getValue(poll));
                    }
                }
            }
        }

        public void d(Throwable th) {
            this.m.dispose();
            this.l.dispose();
            onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.q.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.n);
                if (this.p.decrementAndGet() == 0) {
                    this.m.dispose();
                }
            }
        }

        public void e(B b) {
            this.queue.offer(new d(null, b));
            if (enter()) {
                c();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.q.get();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (enter()) {
                c();
            }
            if (this.p.decrementAndGet() == 0) {
                this.l.dispose();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            if (enter()) {
                c();
            }
            if (this.p.decrementAndGet() == 0) {
                this.l.dispose();
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastSubject<T> unicastSubject : this.o) {
                    unicastSubject.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            c();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                this.downstream.onSubscribe(this);
                if (this.q.get()) {
                    return;
                }
                b bVar = new b(this);
                if (this.n.compareAndSet(null, bVar)) {
                    this.i.subscribe(bVar);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T, B> {

        /* renamed from: a  reason: collision with root package name */
        public final UnicastSubject<T> f13912a;
        public final B b;

        public d(UnicastSubject<T> unicastSubject, B b) {
            this.f13912a = unicastSubject;
            this.b = b;
        }
    }

    public ObservableWindowBoundarySelector(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Function<? super B, ? extends ObservableSource<V>> function, int i) {
        super(observableSource);
        this.h = observableSource2;
        this.i = function;
        this.j = i;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        this.source.subscribe(new c(new SerializedObserver(observer), this.h, this.i, this.j));
    }
}
