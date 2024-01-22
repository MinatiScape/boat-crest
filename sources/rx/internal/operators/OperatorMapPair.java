package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorMapPair<T, U, R> implements Observable.Operator<Observable<? extends R>, T> {
    public final Func1<? super T, ? extends Observable<? extends U>> h;
    public final Func2<? super T, ? super U, ? extends R> i;

    /* loaded from: classes13.dex */
    public static class a implements Func1<T, Observable<U>> {
        public final /* synthetic */ Func1 h;

        public a(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<U> call(T t) {
            return Observable.from((Iterable) this.h.call(t));
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, U, R> extends Subscriber<T> {
        public final Subscriber<? super Observable<? extends R>> l;
        public final Func1<? super T, ? extends Observable<? extends U>> m;
        public final Func2<? super T, ? super U, ? extends R> n;
        public boolean o;

        public b(Subscriber<? super Observable<? extends R>> subscriber, Func1<? super T, ? extends Observable<? extends U>> func1, Func2<? super T, ? super U, ? extends R> func2) {
            this.l = subscriber;
            this.m = func1;
            this.n = func2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.o) {
                return;
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.o) {
                RxJavaHooks.onError(th);
                return;
            }
            this.o = true;
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.l.onNext(this.m.call(t).map(new c(t, this.n)));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.l.setProducer(producer);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T, U, R> implements Func1<U, R> {
        public final T h;
        public final Func2<? super T, ? super U, ? extends R> i;

        public c(T t, Func2<? super T, ? super U, ? extends R> func2) {
            this.h = t;
            this.i = func2;
        }

        @Override // rx.functions.Func1
        public R call(U u) {
            return this.i.call((T) this.h, u);
        }
    }

    public OperatorMapPair(Func1<? super T, ? extends Observable<? extends U>> func1, Func2<? super T, ? super U, ? extends R> func2) {
        this.h = func1;
        this.i = func2;
    }

    public static <T, U> Func1<T, Observable<U>> convertSelector(Func1<? super T, ? extends Iterable<? extends U>> func1) {
        return new a(func1);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<? extends R>> subscriber) {
        b bVar = new b(subscriber, this.h, this.i);
        subscriber.add(bVar);
        return bVar;
    }
}
