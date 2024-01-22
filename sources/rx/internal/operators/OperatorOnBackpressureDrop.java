package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public class OperatorOnBackpressureDrop<T> implements Observable.Operator<T, T> {
    public final Action1<? super T> h;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ AtomicLong h;

        public a(OperatorOnBackpressureDrop operatorOnBackpressureDrop, AtomicLong atomicLong) {
            this.h = atomicLong;
        }

        @Override // rx.Producer
        public void request(long j) {
            BackpressureUtils.getAndAddRequest(this.h, j);
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public boolean l;
        public final /* synthetic */ Subscriber m;
        public final /* synthetic */ AtomicLong n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Subscriber subscriber, Subscriber subscriber2, AtomicLong atomicLong) {
            super(subscriber);
            this.m = subscriber2;
            this.n = atomicLong;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.l) {
                this.l = true;
                this.m.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            if (this.n.get() > 0) {
                this.m.onNext(t);
                this.n.decrementAndGet();
                return;
            }
            Action1<? super T> action1 = OperatorOnBackpressureDrop.this.h;
            if (action1 != null) {
                try {
                    action1.call(t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorOnBackpressureDrop<Object> f15668a = new OperatorOnBackpressureDrop<>();
    }

    public OperatorOnBackpressureDrop() {
        this(null);
    }

    public static <T> OperatorOnBackpressureDrop<T> instance() {
        return (OperatorOnBackpressureDrop<T>) c.f15668a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorOnBackpressureDrop(Action1<? super T> action1) {
        this.h = action1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        AtomicLong atomicLong = new AtomicLong();
        subscriber.setProducer(new a(this, atomicLong));
        return new b(subscriber, subscriber, atomicLong);
    }
}
