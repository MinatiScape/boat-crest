package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableZip<T, R> extends Observable<R> {
    public final ObservableSource<? extends T>[] h;
    public final Iterable<? extends ObservableSource<? extends T>> i;
    public final Function<? super Object[], ? extends R> j;
    public final int k;
    public final boolean l;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2983708048395377667L;
        public volatile boolean cancelled;
        public final boolean delayError;
        public final Observer<? super R> downstream;
        public final b<T, R>[] observers;
        public final T[] row;
        public final Function<? super Object[], ? extends R> zipper;

        public a(Observer<? super R> observer, Function<? super Object[], ? extends R> function, int i, boolean z) {
            this.downstream = observer;
            this.zipper = function;
            this.observers = new b[i];
            this.row = (T[]) new Object[i];
            this.delayError = z;
        }

        public void cancel() {
            clear();
            cancelSources();
        }

        public void cancelSources() {
            for (b<T, R> bVar : this.observers) {
                bVar.a();
            }
        }

        public boolean checkTerminated(boolean z, boolean z2, Observer<? super R> observer, boolean z3, b<?, ?> bVar) {
            if (this.cancelled) {
                cancel();
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = bVar.k;
                        this.cancelled = true;
                        cancel();
                        if (th != null) {
                            observer.onError(th);
                        } else {
                            observer.onComplete();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = bVar.k;
                if (th2 != null) {
                    this.cancelled = true;
                    cancel();
                    observer.onError(th2);
                    return true;
                } else if (z2) {
                    this.cancelled = true;
                    cancel();
                    observer.onComplete();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        public void clear() {
            for (b<T, R> bVar : this.observers) {
                bVar.i.clear();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelSources();
            if (getAndIncrement() == 0) {
                clear();
            }
        }

        public void drain() {
            Throwable th;
            if (getAndIncrement() != 0) {
                return;
            }
            b<T, R>[] bVarArr = this.observers;
            Observer<? super R> observer = this.downstream;
            T[] tArr = this.row;
            boolean z = this.delayError;
            int i = 1;
            while (true) {
                int i2 = 0;
                int i3 = 0;
                for (b<T, R> bVar : bVarArr) {
                    if (tArr[i3] == null) {
                        boolean z2 = bVar.j;
                        T poll = bVar.i.poll();
                        boolean z3 = poll == null;
                        if (checkTerminated(z2, z3, observer, z, bVar)) {
                            return;
                        }
                        if (z3) {
                            i2++;
                        } else {
                            tArr[i3] = poll;
                        }
                    } else if (bVar.j && !z && (th = bVar.k) != null) {
                        this.cancelled = true;
                        cancel();
                        observer.onError(th);
                        return;
                    }
                    i3++;
                }
                if (i2 != 0) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    try {
                        observer.onNext((Object) ObjectHelper.requireNonNull(this.zipper.apply(tArr.clone()), "The zipper returned a null value"));
                        Arrays.fill(tArr, (Object) null);
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        cancel();
                        observer.onError(th2);
                        return;
                    }
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        public void subscribe(ObservableSource<? extends T>[] observableSourceArr, int i) {
            b<T, R>[] bVarArr = this.observers;
            int length = bVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bVarArr[i2] = new b<>(this, i);
            }
            lazySet(0);
            this.downstream.onSubscribe(this);
            for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
                observableSourceArr[i3].subscribe(bVarArr[i3]);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, R> implements Observer<T> {
        public final a<T, R> h;
        public final SpscLinkedArrayQueue<T> i;
        public volatile boolean j;
        public Throwable k;
        public final AtomicReference<Disposable> l = new AtomicReference<>();

        public b(a<T, R> aVar, int i) {
            this.h = aVar;
            this.i = new SpscLinkedArrayQueue<>(i);
        }

        public void a() {
            DisposableHelper.dispose(this.l);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.j = true;
            this.h.drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.k = th;
            this.j = true;
            this.h.drain();
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.i.offer(t);
            this.h.drain();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.l, disposable);
        }
    }

    public ObservableZip(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.h = observableSourceArr;
        this.i = iterable;
        this.j = function;
        this.k = i;
        this.l = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.h;
        if (observableSourceArr == null) {
            observableSourceArr = new ObservableSource[8];
            length = 0;
            for (ObservableSource<? extends T> observableSource : this.i) {
                if (length == observableSourceArr.length) {
                    ObservableSource<? extends T>[] observableSourceArr2 = new ObservableSource[(length >> 2) + length];
                    System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                    observableSourceArr = observableSourceArr2;
                }
                observableSourceArr[length] = observableSource;
                length++;
            }
        } else {
            length = observableSourceArr.length;
        }
        if (length == 0) {
            EmptyDisposable.complete(observer);
        } else {
            new a(observer, this.j, length, this.l).subscribe(observableSourceArr, this.k);
        }
    }
}
