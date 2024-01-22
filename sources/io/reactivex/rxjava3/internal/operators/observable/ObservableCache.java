package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableCache<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, T> implements Observer<T> {
    public static final a[] q = new a[0];
    public static final a[] r = new a[0];
    public final AtomicBoolean h;
    public final int i;
    public final AtomicReference<a<T>[]> j;
    public volatile long k;
    public final b<T> l;
    public b<T> m;
    public int n;
    public Throwable o;
    public volatile boolean p;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 6770240836423125754L;
        public volatile boolean disposed;
        public final Observer<? super T> downstream;
        public long index;
        public b<T> node;
        public int offset;
        public final ObservableCache<T> parent;

        public a(Observer<? super T> observer, ObservableCache<T> observableCache) {
            this.downstream = observer;
            this.parent = observableCache;
            this.node = observableCache.l;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.disposed) {
                return;
            }
            this.disposed = true;
            this.parent.e(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final T[] f13968a;
        public volatile b<T> b;

        public b(int i) {
            this.f13968a = (T[]) new Object[i];
        }
    }

    public ObservableCache(Observable<T> observable, int i) {
        super(observable);
        this.i = i;
        this.h = new AtomicBoolean();
        b<T> bVar = new b<>(i);
        this.l = bVar;
        this.m = bVar;
        this.j = new AtomicReference<>(q);
    }

    public void d(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.j.get();
            if (aVarArr == r) {
                return;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.j.compareAndSet(aVarArr, aVarArr2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.j.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = q;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.j.compareAndSet(aVarArr, aVarArr2));
    }

    public void f(a<T> aVar) {
        if (aVar.getAndIncrement() != 0) {
            return;
        }
        long j = aVar.index;
        int i = aVar.offset;
        b<T> bVar = aVar.node;
        Observer<? super T> observer = aVar.downstream;
        int i2 = this.i;
        int i3 = 1;
        while (!aVar.disposed) {
            boolean z = this.p;
            boolean z2 = this.k == j;
            if (z && z2) {
                aVar.node = null;
                Throwable th = this.o;
                if (th != null) {
                    observer.onError(th);
                    return;
                } else {
                    observer.onComplete();
                    return;
                }
            } else if (!z2) {
                if (i == i2) {
                    bVar = bVar.b;
                    i = 0;
                }
                observer.onNext((Object) bVar.f13968a[i]);
                i++;
                j++;
            } else {
                aVar.index = j;
                aVar.offset = i;
                aVar.node = bVar;
                i3 = aVar.addAndGet(-i3);
                if (i3 == 0) {
                    return;
                }
            }
        }
        aVar.node = null;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        this.p = true;
        for (a<T> aVar : this.j.getAndSet(r)) {
            f(aVar);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable th) {
        this.o = th;
        this.p = true;
        for (a<T> aVar : this.j.getAndSet(r)) {
            f(aVar);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        int i = this.n;
        if (i == this.i) {
            b<T> bVar = new b<>(i);
            bVar.f13968a[0] = t;
            this.n = 1;
            this.m.b = bVar;
            this.m = bVar;
        } else {
            this.m.f13968a[i] = t;
            this.n = i + 1;
        }
        this.k++;
        for (a<T> aVar : this.j.get()) {
            f(aVar);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable disposable) {
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a<T> aVar = new a<>(observer, this);
        observer.onSubscribe(aVar);
        d(aVar);
        if (!this.h.get() && this.h.compareAndSet(false, true)) {
            this.source.subscribe(this);
        } else {
            f(aVar);
        }
    }
}
