package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeReduce<T> implements Observable.OnSubscribe<T> {
    public final Observable<T> h;
    public final Func2<T, T, T> i;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OnSubscribeReduce onSubscribeReduce, b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.b(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public static final Object p = new Object();
        public final Subscriber<? super T> l;
        public final Func2<T, T, T> m;
        public T n = (T) p;
        public boolean o;

        public b(Subscriber<? super T> subscriber, Func2<T, T, T> func2) {
            this.l = subscriber;
            this.m = func2;
            request(0L);
        }

        public void b(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i != 0) {
                    request(Long.MAX_VALUE);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.o) {
                return;
            }
            this.o = true;
            T t = this.n;
            if (t != p) {
                this.l.onNext(t);
                this.l.onCompleted();
                return;
            }
            this.l.onError(new NoSuchElementException());
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.o) {
                this.o = true;
                this.l.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.o) {
                return;
            }
            T t2 = this.n;
            if (t2 == p) {
                this.n = t;
                return;
            }
            try {
                this.n = this.m.call(t2, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }

    public OnSubscribeReduce(Observable<T> observable, Func2<T, T, T> func2) {
        this.h = observable;
        this.i = func2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, this.i);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        this.h.unsafeSubscribe(bVar);
    }
}
