package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OnSubscribeAmb<T> implements Observable.OnSubscribe<T> {
    public final Iterable<? extends Observable<? extends T>> h;

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ d h;

        public a(OnSubscribeAmb onSubscribeAmb, d dVar) {
            this.h = dVar;
        }

        @Override // rx.functions.Action0
        public void call() {
            c<T> cVar = this.h.get();
            if (cVar != null) {
                cVar.unsubscribe();
            }
            OnSubscribeAmb.a(this.h.ambSubscribers);
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Producer {
        public final /* synthetic */ d h;

        public b(OnSubscribeAmb onSubscribeAmb, d dVar) {
            this.h = dVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            c<T> cVar = this.h.get();
            if (cVar != null) {
                cVar.requestMore(j);
                return;
            }
            for (c<T> cVar2 : this.h.ambSubscribers) {
                if (!cVar2.isUnsubscribed()) {
                    if (this.h.get() == cVar2) {
                        cVar2.requestMore(j);
                        return;
                    }
                    cVar2.requestMore(j);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final d<T> m;
        public boolean n;

        public c(long j, Subscriber<? super T> subscriber, d<T> dVar) {
            this.l = subscriber;
            this.m = dVar;
            request(j);
        }

        public final boolean c() {
            if (this.n) {
                return true;
            }
            if (this.m.get() == this) {
                this.n = true;
                return true;
            } else if (this.m.compareAndSet(null, this)) {
                this.m.unsubscribeOthers(this);
                this.n = true;
                return true;
            } else {
                this.m.unsubscribeLosers();
                return false;
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (c()) {
                this.l.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (c()) {
                this.l.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (c()) {
                this.l.onNext(t);
            }
        }

        public final void requestMore(long j) {
            request(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> extends AtomicReference<c<T>> {
        public final Collection<c<T>> ambSubscribers = new ConcurrentLinkedQueue();

        public void unsubscribeLosers() {
            c<T> cVar = get();
            if (cVar != null) {
                unsubscribeOthers(cVar);
            }
        }

        public void unsubscribeOthers(c<T> cVar) {
            for (c<T> cVar2 : this.ambSubscribers) {
                if (cVar2 != cVar) {
                    cVar2.unsubscribe();
                }
            }
            this.ambSubscribers.clear();
        }
    }

    public OnSubscribeAmb(Iterable<? extends Observable<? extends T>> iterable) {
        this.h = iterable;
    }

    public static <T> void a(Collection<c<T>> collection) {
        if (collection.isEmpty()) {
            return;
        }
        for (c<T> cVar : collection) {
            cVar.unsubscribe();
        }
        collection.clear();
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        return amb(arrayList);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        d dVar = new d();
        subscriber.add(Subscriptions.create(new a(this, dVar)));
        for (Observable<? extends T> observable : this.h) {
            if (subscriber.isUnsubscribed()) {
                break;
            }
            c<T> cVar = new c<>(0L, subscriber, dVar);
            dVar.ambSubscribers.add(cVar);
            c<T> cVar2 = dVar.get();
            if (cVar2 != null) {
                dVar.unsubscribeOthers(cVar2);
                return;
            }
            observable.unsafeSubscribe(cVar);
        }
        if (subscriber.isUnsubscribed()) {
            a(dVar.ambSubscribers);
        }
        subscriber.setProducer(new b(this, dVar));
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4, Observable<? extends T> observable5) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        arrayList.add(observable5);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4, Observable<? extends T> observable5, Observable<? extends T> observable6) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        arrayList.add(observable5);
        arrayList.add(observable6);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4, Observable<? extends T> observable5, Observable<? extends T> observable6, Observable<? extends T> observable7) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        arrayList.add(observable5);
        arrayList.add(observable6);
        arrayList.add(observable7);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4, Observable<? extends T> observable5, Observable<? extends T> observable6, Observable<? extends T> observable7, Observable<? extends T> observable8) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        arrayList.add(observable5);
        arrayList.add(observable6);
        arrayList.add(observable7);
        arrayList.add(observable8);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Observable<? extends T> observable, Observable<? extends T> observable2, Observable<? extends T> observable3, Observable<? extends T> observable4, Observable<? extends T> observable5, Observable<? extends T> observable6, Observable<? extends T> observable7, Observable<? extends T> observable8, Observable<? extends T> observable9) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(observable);
        arrayList.add(observable2);
        arrayList.add(observable3);
        arrayList.add(observable4);
        arrayList.add(observable5);
        arrayList.add(observable6);
        arrayList.add(observable7);
        arrayList.add(observable8);
        arrayList.add(observable9);
        return amb(arrayList);
    }

    public static <T> Observable.OnSubscribe<T> amb(Iterable<? extends Observable<? extends T>> iterable) {
        return new OnSubscribeAmb(iterable);
    }
}
