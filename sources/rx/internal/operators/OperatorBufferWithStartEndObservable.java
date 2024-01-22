package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing> implements Observable.Operator<List<T>, T> {
    public final Observable<? extends TOpening> h;
    public final Func1<? super TOpening, ? extends Observable<? extends TClosing>> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<TOpening> {
        public final /* synthetic */ b l;

        public a(OperatorBufferWithStartEndObservable operatorBufferWithStartEndObservable, b bVar) {
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
        public void onNext(TOpening topening) {
            this.l.c(topening);
        }
    }

    /* loaded from: classes13.dex */
    public final class b extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final List<List<T>> m = new LinkedList();
        public boolean n;
        public final CompositeSubscription o;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<TClosing> {
            public final /* synthetic */ List l;

            public a(List list) {
                this.l = list;
            }

            @Override // rx.Observer
            public void onCompleted() {
                b.this.o.remove(this);
                b.this.b(this.l);
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                b.this.onError(th);
            }

            @Override // rx.Observer
            public void onNext(TClosing tclosing) {
                b.this.o.remove(this);
                b.this.b(this.l);
            }
        }

        public b(Subscriber<? super List<T>> subscriber) {
            this.l = subscriber;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.o = compositeSubscription;
            add(compositeSubscription);
        }

        public void b(List<T> list) {
            boolean z;
            synchronized (this) {
                if (this.n) {
                    return;
                }
                Iterator<List<T>> it = this.m.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == list) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    this.l.onNext(list);
                }
            }
        }

        public void c(TOpening topening) {
            ArrayList arrayList = new ArrayList();
            synchronized (this) {
                if (this.n) {
                    return;
                }
                this.m.add(arrayList);
                try {
                    Observable<? extends TClosing> call = OperatorBufferWithStartEndObservable.this.i.call(topening);
                    a aVar = new a(arrayList);
                    this.o.add(aVar);
                    call.unsafeSubscribe(aVar);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.n) {
                        return;
                    }
                    this.n = true;
                    LinkedList<List> linkedList = new LinkedList(this.m);
                    this.m.clear();
                    for (List list : linkedList) {
                        this.l.onNext(list);
                    }
                    this.l.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.l);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.n) {
                    return;
                }
                this.n = true;
                this.m.clear();
                this.l.onError(th);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (List<T> list : this.m) {
                    list.add(t);
                }
            }
        }
    }

    public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> observable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1) {
        this.h = observable;
        this.i = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        b bVar = new b(new SerializedSubscriber(subscriber));
        a aVar = new a(this, bVar);
        subscriber.add(aVar);
        subscriber.add(bVar);
        this.h.unsafeSubscribe(aVar);
        return bVar;
    }
}
