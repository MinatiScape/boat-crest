package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OperatorWindowWithStartEndObservable<T, U, V> implements Observable.Operator<Observable<T>, T> {
    public final Observable<? extends U> h;
    public final Func1<? super U, ? extends Observable<? extends V>> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<U> {
        public final /* synthetic */ c l;

        public a(OperatorWindowWithStartEndObservable operatorWindowWithStartEndObservable, c cVar) {
            this.l = cVar;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(U u) {
            this.l.b(u);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Observer<T> f15676a;
        public final Observable<T> b;

        public b(Observer<T> observer, Observable<T> observable) {
            this.f15676a = new SerializedObserver(observer);
            this.b = observable;
        }
    }

    /* loaded from: classes13.dex */
    public final class c extends Subscriber<T> {
        public final Subscriber<? super Observable<T>> l;
        public final CompositeSubscription m;
        public final Object n = new Object();
        public final List<b<T>> o = new LinkedList();
        public boolean p;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<V> {
            public boolean l = true;
            public final /* synthetic */ b m;

            public a(b bVar) {
                this.m = bVar;
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.l) {
                    this.l = false;
                    c.this.d(this.m);
                    c.this.m.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                c.this.onError(th);
            }

            @Override // rx.Observer
            public void onNext(V v) {
                onCompleted();
            }
        }

        public c(Subscriber<? super Observable<T>> subscriber, CompositeSubscription compositeSubscription) {
            this.l = new SerializedSubscriber(subscriber);
            this.m = compositeSubscription;
        }

        public void b(U u) {
            b<T> c = c();
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                this.o.add(c);
                this.l.onNext(c.b);
                try {
                    Observable<? extends V> call = OperatorWindowWithStartEndObservable.this.i.call(u);
                    a aVar = new a(c);
                    this.m.add(aVar);
                    call.unsafeSubscribe(aVar);
                } catch (Throwable th) {
                    onError(th);
                }
            }
        }

        public b<T> c() {
            UnicastSubject create = UnicastSubject.create();
            return new b<>(create, create);
        }

        public void d(b<T> bVar) {
            boolean z;
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                Iterator<b<T>> it = this.o.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == bVar) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    bVar.f15676a.onCompleted();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this.n) {
                    if (this.p) {
                        return;
                    }
                    this.p = true;
                    ArrayList<b> arrayList = new ArrayList(this.o);
                    this.o.clear();
                    for (b bVar : arrayList) {
                        bVar.f15676a.onCompleted();
                    }
                    this.l.onCompleted();
                }
            } finally {
                this.m.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                synchronized (this.n) {
                    if (this.p) {
                        return;
                    }
                    this.p = true;
                    ArrayList<b> arrayList = new ArrayList(this.o);
                    this.o.clear();
                    for (b bVar : arrayList) {
                        bVar.f15676a.onError(th);
                    }
                    this.l.onError(th);
                }
            } finally {
                this.m.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                for (b bVar : new ArrayList(this.o)) {
                    bVar.f15676a.onNext(t);
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorWindowWithStartEndObservable(Observable<? extends U> observable, Func1<? super U, ? extends Observable<? extends V>> func1) {
        this.h = observable;
        this.i = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        subscriber.add(compositeSubscription);
        c cVar = new c(subscriber, compositeSubscription);
        a aVar = new a(this, cVar);
        compositeSubscription.add(cVar);
        compositeSubscription.add(aVar);
        this.h.unsafeSubscribe(aVar);
        return cVar;
    }
}
