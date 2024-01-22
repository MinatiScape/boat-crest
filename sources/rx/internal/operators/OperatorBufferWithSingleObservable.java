package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OperatorBufferWithSingleObservable<T, TClosing> implements Observable.Operator<List<T>, T> {
    public final Func0<? extends Observable<? extends TClosing>> h;
    public final int i;

    /* loaded from: classes13.dex */
    public class a implements Func0<Observable<? extends TClosing>> {
        public final /* synthetic */ Observable h;

        public a(OperatorBufferWithSingleObservable operatorBufferWithSingleObservable, Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public Observable<? extends TClosing> call() {
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<TClosing> {
        public final /* synthetic */ c l;

        public b(OperatorBufferWithSingleObservable operatorBufferWithSingleObservable, c cVar) {
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
        public void onNext(TClosing tclosing) {
            this.l.b();
        }
    }

    /* loaded from: classes13.dex */
    public final class c extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public List<T> m;
        public boolean n;

        public c(Subscriber<? super List<T>> subscriber) {
            this.l = subscriber;
            this.m = new ArrayList(OperatorBufferWithSingleObservable.this.i);
        }

        public void b() {
            synchronized (this) {
                if (this.n) {
                    return;
                }
                List<T> list = this.m;
                this.m = new ArrayList(OperatorBufferWithSingleObservable.this.i);
                try {
                    this.l.onNext(list);
                } catch (Throwable th) {
                    unsubscribe();
                    synchronized (this) {
                        if (this.n) {
                            return;
                        }
                        this.n = true;
                        Exceptions.throwOrReport(th, this.l);
                    }
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
                    List<T> list = this.m;
                    this.m = null;
                    this.l.onNext(list);
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
                this.m = null;
                this.l.onError(th);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                if (this.n) {
                    return;
                }
                this.m.add(t);
            }
        }
    }

    public OperatorBufferWithSingleObservable(Func0<? extends Observable<? extends TClosing>> func0, int i) {
        this.h = func0;
        this.i = i;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        try {
            Observable<? extends TClosing> call = this.h.call();
            c cVar = new c(new SerializedSubscriber(subscriber));
            b bVar = new b(this, cVar);
            subscriber.add(bVar);
            subscriber.add(cVar);
            call.unsafeSubscribe(bVar);
            return cVar;
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            return Subscribers.empty();
        }
    }

    public OperatorBufferWithSingleObservable(Observable<? extends TClosing> observable, int i) {
        this.h = new a(this, observable);
        this.i = i;
    }
}
