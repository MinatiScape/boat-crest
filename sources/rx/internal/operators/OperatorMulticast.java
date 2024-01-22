package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorMulticast<T, R> extends ConnectableObservable<R> {
    public final Observable<? extends T> i;
    public final Object j;
    public final Func0<? extends Subject<? super T, ? extends R>> k;
    public final AtomicReference<Subject<? super T, ? extends R>> l;
    public final List<Subscriber<? super R>> m;
    public Subscriber<T> n;
    public Subscription o;

    /* loaded from: classes13.dex */
    public class a implements Observable.OnSubscribe<R> {
        public final /* synthetic */ Object h;
        public final /* synthetic */ AtomicReference i;
        public final /* synthetic */ List j;

        public a(Object obj, AtomicReference atomicReference, List list) {
            this.h = obj;
            this.i = atomicReference;
            this.j = list;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            synchronized (this.h) {
                if (this.i.get() == null) {
                    this.j.add(subscriber);
                } else {
                    ((Subject) this.i.get()).unsafeSubscribe(subscriber);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Action0 {
        public final /* synthetic */ AtomicReference h;

        public b(AtomicReference atomicReference) {
            this.h = atomicReference;
        }

        @Override // rx.functions.Action0
        public void call() {
            synchronized (OperatorMulticast.this.j) {
                if (OperatorMulticast.this.o == this.h.get()) {
                    OperatorMulticast operatorMulticast = OperatorMulticast.this;
                    Subscriber<T> subscriber = operatorMulticast.n;
                    operatorMulticast.n = null;
                    operatorMulticast.o = null;
                    operatorMulticast.l.set(null);
                    if (subscriber != null) {
                        subscriber.unsubscribe();
                    }
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class c extends Subscriber<R> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(OperatorMulticast operatorMulticast, Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.l = subscriber2;
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
        public void onNext(R r) {
            this.l.onNext(r);
        }
    }

    public OperatorMulticast(Observable<? extends T> observable, Func0<? extends Subject<? super T, ? extends R>> func0) {
        this(new Object(), new AtomicReference(), new ArrayList(), observable, func0);
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> action1) {
        Subscriber<T> subscriber;
        synchronized (this.j) {
            if (this.n != null) {
                action1.call(this.o);
                return;
            }
            Subject<? super T, ? extends R> call = this.k.call();
            this.n = Subscribers.from(call);
            AtomicReference atomicReference = new AtomicReference();
            atomicReference.set(Subscriptions.create(new b(atomicReference)));
            this.o = (Subscription) atomicReference.get();
            for (Subscriber<? super R> subscriber2 : this.m) {
                call.unsafeSubscribe(new c(this, subscriber2, subscriber2));
            }
            this.m.clear();
            this.l.set(call);
            action1.call(this.o);
            synchronized (this.j) {
                subscriber = this.n;
            }
            if (subscriber != null) {
                this.i.subscribe((Subscriber<? super Object>) subscriber);
            }
        }
    }

    public OperatorMulticast(Object obj, AtomicReference<Subject<? super T, ? extends R>> atomicReference, List<Subscriber<? super R>> list, Observable<? extends T> observable, Func0<? extends Subject<? super T, ? extends R>> func0) {
        super(new a(obj, atomicReference, list));
        this.j = obj;
        this.l = atomicReference;
        this.m = list;
        this.i = observable;
        this.k = func0;
    }
}
