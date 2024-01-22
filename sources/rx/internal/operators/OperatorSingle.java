package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Subscriber;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorSingle<T> implements Observable.Operator<T, T> {
    public final boolean h;
    public final T i;

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorSingle<?> f15672a = new OperatorSingle<>();
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final boolean m;
        public final T n;
        public T o;
        public boolean p;
        public boolean q;

        public b(Subscriber<? super T> subscriber, boolean z, T t) {
            this.l = subscriber;
            this.m = z;
            this.n = t;
            request(2L);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.q) {
                return;
            }
            if (this.p) {
                this.l.setProducer(new SingleProducer(this.l, this.o));
            } else if (this.m) {
                this.l.setProducer(new SingleProducer(this.l, this.n));
            } else {
                this.l.onError(new NoSuchElementException("Sequence contains no elements"));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.q) {
                RxJavaHooks.onError(th);
            } else {
                this.l.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.q) {
                return;
            }
            if (this.p) {
                this.q = true;
                this.l.onError(new IllegalArgumentException("Sequence contains too many elements"));
                unsubscribe();
                return;
            }
            this.o = t;
            this.p = true;
        }
    }

    public OperatorSingle() {
        this(false, null);
    }

    public static <T> OperatorSingle<T> instance() {
        return (OperatorSingle<T>) a.f15672a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSingle(T t) {
        this(true, t);
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, this.h, this.i);
        subscriber.add(bVar);
        return bVar;
    }

    public OperatorSingle(boolean z, T t) {
        this.h = z;
        this.i = t;
    }
}
