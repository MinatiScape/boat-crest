package rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.RefCountSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements Observable.OnSubscribe<R> {
    public final Observable<T1> h;
    public final Observable<T2> i;
    public final Func1<? super T1, ? extends Observable<D1>> j;
    public final Func1<? super T2, ? extends Observable<D2>> k;
    public final Func2<? super T1, ? super Observable<T2>, ? extends R> l;

    /* loaded from: classes13.dex */
    public final class a extends HashMap<Integer, Observer<T2>> implements Subscription {
        private static final long serialVersionUID = -3035156013812425335L;
        public final RefCountSubscription cancel;
        public final CompositeSubscription group;
        public boolean leftDone;
        public int leftIds;
        public boolean rightDone;
        public int rightIds;
        public final Map<Integer, T2> rightMap = new HashMap();
        public final Subscriber<? super R> subscriber;

        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0939a extends Subscriber<D1> {
            public final int l;
            public boolean m = true;

            public C0939a(int i) {
                this.l = i;
            }

            @Override // rx.Observer
            public void onCompleted() {
                Observer<T2> remove;
                if (this.m) {
                    this.m = false;
                    synchronized (a.this) {
                        remove = a.this.leftMap().remove(Integer.valueOf(this.l));
                    }
                    if (remove != null) {
                        remove.onCompleted();
                    }
                    a.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.errorMain(th);
            }

            @Override // rx.Observer
            public void onNext(D1 d1) {
                onCompleted();
            }
        }

        /* loaded from: classes13.dex */
        public final class b extends Subscriber<T1> {
            public b() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (a.this) {
                    a aVar = a.this;
                    aVar.leftDone = true;
                    if (aVar.rightDone) {
                        arrayList = new ArrayList(a.this.leftMap().values());
                        a.this.leftMap().clear();
                        a.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                a.this.complete(arrayList);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.errorAll(th);
            }

            @Override // rx.Observer
            public void onNext(T1 t1) {
                int i;
                ArrayList<Object> arrayList;
                try {
                    PublishSubject create = PublishSubject.create();
                    SerializedObserver serializedObserver = new SerializedObserver(create);
                    synchronized (a.this) {
                        a aVar = a.this;
                        i = aVar.leftIds;
                        aVar.leftIds = i + 1;
                        aVar.leftMap().put(Integer.valueOf(i), serializedObserver);
                    }
                    Observable unsafeCreate = Observable.unsafeCreate(new b(create, a.this.cancel));
                    C0939a c0939a = new C0939a(i);
                    a.this.group.add(c0939a);
                    OnSubscribeGroupJoin.this.j.call(t1).unsafeSubscribe(c0939a);
                    R call = OnSubscribeGroupJoin.this.l.call(t1, unsafeCreate);
                    synchronized (a.this) {
                        arrayList = new ArrayList(a.this.rightMap.values());
                    }
                    a.this.subscriber.onNext(call);
                    for (Object obj : arrayList) {
                        serializedObserver.onNext(obj);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        /* loaded from: classes13.dex */
        public final class c extends Subscriber<D2> {
            public final int l;
            public boolean m = true;

            public c(int i) {
                this.l = i;
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.m) {
                    this.m = false;
                    synchronized (a.this) {
                        a.this.rightMap.remove(Integer.valueOf(this.l));
                    }
                    a.this.group.remove(this);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.errorMain(th);
            }

            @Override // rx.Observer
            public void onNext(D2 d2) {
                onCompleted();
            }
        }

        /* loaded from: classes13.dex */
        public final class d extends Subscriber<T2> {
            public d() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (a.this) {
                    a aVar = a.this;
                    aVar.rightDone = true;
                    if (aVar.leftDone) {
                        arrayList = new ArrayList(a.this.leftMap().values());
                        a.this.leftMap().clear();
                        a.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                a.this.complete(arrayList);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.errorAll(th);
            }

            @Override // rx.Observer
            public void onNext(T2 t2) {
                int i;
                ArrayList<Observer> arrayList;
                try {
                    synchronized (a.this) {
                        a aVar = a.this;
                        i = aVar.rightIds;
                        aVar.rightIds = i + 1;
                        aVar.rightMap.put(Integer.valueOf(i), t2);
                    }
                    c cVar = new c(i);
                    a.this.group.add(cVar);
                    OnSubscribeGroupJoin.this.k.call(t2).unsafeSubscribe(cVar);
                    synchronized (a.this) {
                        arrayList = new ArrayList(a.this.leftMap().values());
                    }
                    for (Observer observer : arrayList) {
                        observer.onNext(t2);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        public a(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.group = compositeSubscription;
            this.cancel = new RefCountSubscription(compositeSubscription);
        }

        public void complete(List<Observer<T2>> list) {
            if (list != null) {
                for (Observer<T2> observer : list) {
                    observer.onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }

        public void errorAll(Throwable th) {
            ArrayList<Observer> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(leftMap().values());
                leftMap().clear();
                this.rightMap.clear();
            }
            for (Observer observer : arrayList) {
                observer.onError(th);
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        public void errorMain(Throwable th) {
            synchronized (this) {
                leftMap().clear();
                this.rightMap.clear();
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        public void init() {
            b bVar = new b();
            d dVar = new d();
            this.group.add(bVar);
            this.group.add(dVar);
            OnSubscribeGroupJoin.this.h.unsafeSubscribe(bVar);
            OnSubscribeGroupJoin.this.i.unsafeSubscribe(dVar);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }

        public Map<Integer, Observer<T2>> leftMap() {
            return this;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> implements Observable.OnSubscribe<T> {
        public final RefCountSubscription h;
        public final Observable<T> i;

        /* loaded from: classes13.dex */
        public final class a extends Subscriber<T> {
            public final Subscriber<? super T> l;
            public final Subscription m;

            public a(b bVar, Subscriber<? super T> subscriber, Subscription subscription) {
                super(subscriber);
                this.l = subscriber;
                this.m = subscription;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.onCompleted();
                this.m.unsubscribe();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
                this.m.unsubscribe();
            }

            @Override // rx.Observer
            public void onNext(T t) {
                this.l.onNext(t);
            }
        }

        public b(Observable<T> observable, RefCountSubscription refCountSubscription) {
            this.h = refCountSubscription;
            this.i = observable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            Subscription subscription = this.h.get();
            a aVar = new a(this, subscriber, subscription);
            aVar.add(subscription);
            this.i.unsafeSubscribe(aVar);
        }
    }

    public OnSubscribeGroupJoin(Observable<T1> observable, Observable<T2> observable2, Func1<? super T1, ? extends Observable<D1>> func1, Func1<? super T2, ? extends Observable<D2>> func12, Func2<? super T1, ? super Observable<T2>, ? extends R> func2) {
        this.h = observable;
        this.i = observable2;
        this.j = func1;
        this.k = func12;
        this.l = func2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        a aVar = new a(new SerializedSubscriber(subscriber));
        subscriber.add(aVar);
        aVar.init();
    }
}
