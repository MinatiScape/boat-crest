package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;
/* loaded from: classes13.dex */
public final class OperatorWindowWithObservable<T, U> implements Observable.Operator<Observable<T>, T> {
    public static final Object i = new Object();
    public final Observable<U> h;

    /* loaded from: classes13.dex */
    public static final class a<T, U> extends Subscriber<U> {
        public final b<T> l;

        public a(b<T> bVar) {
            this.l = bVar;
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
            this.l.g();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final Subscriber<? super Observable<T>> l;
        public final Object m = new Object();
        public Observer<T> n;
        public Observable<T> o;
        public boolean p;
        public List<Object> q;

        public b(Subscriber<? super Observable<T>> subscriber) {
            this.l = new SerializedSubscriber(subscriber);
        }

        public void b() {
            UnicastSubject create = UnicastSubject.create();
            this.n = create;
            this.o = create;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void c(List<Object> list) {
            if (list == null) {
                return;
            }
            for (Object obj : list) {
                if (obj == OperatorWindowWithObservable.i) {
                    f();
                } else if (NotificationLite.isError(obj)) {
                    e(NotificationLite.getError(obj));
                    return;
                } else if (NotificationLite.isCompleted(obj)) {
                    complete();
                    return;
                } else {
                    d(obj);
                }
            }
        }

        public void complete() {
            Observer<T> observer = this.n;
            this.n = null;
            this.o = null;
            if (observer != null) {
                observer.onCompleted();
            }
            this.l.onCompleted();
            unsubscribe();
        }

        public void d(T t) {
            Observer<T> observer = this.n;
            if (observer != null) {
                observer.onNext(t);
            }
        }

        public void e(Throwable th) {
            Observer<T> observer = this.n;
            this.n = null;
            this.o = null;
            if (observer != null) {
                observer.onError(th);
            }
            this.l.onError(th);
            unsubscribe();
        }

        public void f() {
            Observer<T> observer = this.n;
            if (observer != null) {
                observer.onCompleted();
            }
            b();
            this.l.onNext(this.o);
        }

        public void g() {
            synchronized (this.m) {
                if (this.p) {
                    if (this.q == null) {
                        this.q = new ArrayList();
                    }
                    this.q.add(OperatorWindowWithObservable.i);
                    return;
                }
                List<Object> list = this.q;
                this.q = null;
                boolean z = true;
                this.p = true;
                boolean z2 = true;
                while (true) {
                    try {
                        c(list);
                        if (z2) {
                            f();
                            z2 = false;
                        }
                        try {
                            synchronized (this.m) {
                                try {
                                    List<Object> list2 = this.q;
                                    this.q = null;
                                    if (list2 == null) {
                                        this.p = false;
                                        return;
                                    } else if (this.l.isUnsubscribed()) {
                                        synchronized (this.m) {
                                            this.p = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    z = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.m) {
                                                this.p = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            synchronized (this.m) {
                if (this.p) {
                    if (this.q == null) {
                        this.q = new ArrayList();
                    }
                    this.q.add(NotificationLite.completed());
                    return;
                }
                List<Object> list = this.q;
                this.q = null;
                this.p = true;
                try {
                    c(list);
                    complete();
                } catch (Throwable th) {
                    e(th);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this.m) {
                if (this.p) {
                    this.q = Collections.singletonList(NotificationLite.error(th));
                    return;
                }
                this.q = null;
                this.p = true;
                e(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this.m) {
                if (this.p) {
                    if (this.q == null) {
                        this.q = new ArrayList();
                    }
                    this.q.add(t);
                    return;
                }
                List<Object> list = this.q;
                this.q = null;
                boolean z = true;
                this.p = true;
                boolean z2 = true;
                while (true) {
                    try {
                        c(list);
                        if (z2) {
                            d(t);
                            z2 = false;
                        }
                        try {
                            synchronized (this.m) {
                                try {
                                    List<Object> list2 = this.q;
                                    this.q = null;
                                    if (list2 == null) {
                                        this.p = false;
                                        return;
                                    } else if (this.l.isUnsubscribed()) {
                                        synchronized (this.m) {
                                            this.p = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    z = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.m) {
                                                this.p = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorWindowWithObservable(Observable<U> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        b bVar = new b(subscriber);
        a aVar = new a(bVar);
        subscriber.add(bVar);
        subscriber.add(aVar);
        bVar.g();
        this.h.unsafeSubscribe(aVar);
        return bVar;
    }
}
