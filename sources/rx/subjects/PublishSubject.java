package rx.subjects;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.BackpressureUtils;
/* loaded from: classes13.dex */
public final class PublishSubject<T> extends Subject<T, T> {
    public final b<T> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = 6451806817170721536L;
        public final Subscriber<? super T> actual;
        public final b<T> parent;
        public long produced;

        public a(b<T> bVar, Subscriber<? super T> subscriber) {
            this.parent = bVar;
            this.actual = subscriber;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j = get();
            if (j != Long.MIN_VALUE) {
                long j2 = this.produced;
                if (j != j2) {
                    this.produced = j2 + 1;
                    this.actual.onNext(t);
                    return;
                }
                unsubscribe();
                this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            long j2;
            if (BackpressureUtils.validate(j)) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                } while (!compareAndSet(j2, BackpressureUtils.addCap(j2, j)));
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends AtomicReference<a<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        public static final a[] EMPTY = new a[0];
        public static final a[] TERMINATED = new a[0];
        private static final long serialVersionUID = -7568940796666027140L;
        public Throwable error;

        public b() {
            lazySet(EMPTY);
        }

        public boolean add(a<T> aVar) {
            a<T>[] aVarArr;
            a[] aVarArr2;
            do {
                aVarArr = get();
                if (aVarArr == TERMINATED) {
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!compareAndSet(aVarArr, aVarArr2));
            return true;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        @Override // rx.Observer
        public void onCompleted() {
            for (a<T> aVar : getAndSet(TERMINATED)) {
                aVar.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            ArrayList arrayList = null;
            for (a<T> aVar : getAndSet(TERMINATED)) {
                try {
                    aVar.onError(th);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            for (a<T> aVar : get()) {
                aVar.onNext(t);
            }
        }

        public void remove(a<T> aVar) {
            a<T>[] aVarArr;
            a[] aVarArr2;
            do {
                aVarArr = get();
                if (aVarArr == TERMINATED || aVarArr == EMPTY) {
                    return;
                }
                int length = aVarArr.length;
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
                    aVarArr2 = EMPTY;
                } else {
                    a[] aVarArr3 = new a[length - 1];
                    System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                    System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                    aVarArr2 = aVarArr3;
                }
            } while (!compareAndSet(aVarArr, aVarArr2));
        }

        public void call(Subscriber<? super T> subscriber) {
            a<T> aVar = new a<>(this, subscriber);
            subscriber.add(aVar);
            subscriber.setProducer(aVar);
            if (add(aVar)) {
                if (aVar.isUnsubscribed()) {
                    remove(aVar);
                    return;
                }
                return;
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onCompleted();
            }
        }
    }

    public PublishSubject(b<T> bVar) {
        super(bVar);
        this.i = bVar;
    }

    public static <T> PublishSubject<T> create() {
        return new PublishSubject<>(new b());
    }

    public Throwable getThrowable() {
        if (this.i.get() == b.TERMINATED) {
            return this.i.error;
        }
        return null;
    }

    public boolean hasCompleted() {
        return this.i.get() == b.TERMINATED && this.i.error == null;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.i.get() == b.TERMINATED && this.i.error != null;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.i.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.i.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.i.onNext(t);
    }
}
