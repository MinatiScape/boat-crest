package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.functions.Functions;
import rx.internal.util.RxRingBuffer;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OperatorZip<R> implements Observable.Operator<R, Observable<?>[]> {
    public final FuncN<? extends R> h;

    /* loaded from: classes13.dex */
    public static final class a<R> extends AtomicLong {
        public static final int THRESHOLD = (int) (RxRingBuffer.SIZE * 0.7d);
        private static final long serialVersionUID = 5995274816189928317L;
        public final Observer<? super R> child;
        private final CompositeSubscription childSubscription;
        public int emitted;
        private AtomicLong requested;
        private volatile Object[] subscribers;
        private final FuncN<? extends R> zipFunction;

        /* renamed from: rx.internal.operators.OperatorZip$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0961a extends Subscriber {
            public final RxRingBuffer l = RxRingBuffer.getSpmcInstance();

            public C0961a() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.onCompleted();
                a.this.tick();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.child.onError(th);
            }

            @Override // rx.Observer
            public void onNext(Object obj) {
                try {
                    this.l.onNext(obj);
                } catch (MissingBackpressureException e) {
                    onError(e);
                }
                a.this.tick();
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                request(RxRingBuffer.SIZE);
            }

            public void requestMore(long j) {
                request(j);
            }
        }

        public a(Subscriber<? super R> subscriber, FuncN<? extends R> funcN) {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.childSubscription = compositeSubscription;
            this.child = subscriber;
            this.zipFunction = funcN;
            subscriber.add(compositeSubscription);
        }

        public void start(Observable[] observableArr, AtomicLong atomicLong) {
            Object[] objArr = new Object[observableArr.length];
            for (int i = 0; i < observableArr.length; i++) {
                C0961a c0961a = new C0961a();
                objArr[i] = c0961a;
                this.childSubscription.add(c0961a);
            }
            this.requested = atomicLong;
            this.subscribers = objArr;
            for (int i2 = 0; i2 < observableArr.length; i2++) {
                observableArr[i2].unsafeSubscribe((C0961a) objArr[i2]);
            }
        }

        public void tick() {
            Object[] objArr = this.subscribers;
            if (objArr == null || getAndIncrement() != 0) {
                return;
            }
            int length = objArr.length;
            Observer<? super R> observer = this.child;
            AtomicLong atomicLong = this.requested;
            while (true) {
                Object[] objArr2 = new Object[length];
                boolean z = true;
                for (int i = 0; i < length; i++) {
                    RxRingBuffer rxRingBuffer = ((C0961a) objArr[i]).l;
                    Object peek = rxRingBuffer.peek();
                    if (peek == null) {
                        z = false;
                    } else if (rxRingBuffer.isCompleted(peek)) {
                        observer.onCompleted();
                        this.childSubscription.unsubscribe();
                        return;
                    } else {
                        objArr2[i] = rxRingBuffer.getValue(peek);
                    }
                }
                if (z && atomicLong.get() > 0) {
                    try {
                        observer.onNext((R) this.zipFunction.call(objArr2));
                        atomicLong.decrementAndGet();
                        this.emitted++;
                        for (Object obj : objArr) {
                            RxRingBuffer rxRingBuffer2 = ((C0961a) obj).l;
                            rxRingBuffer2.poll();
                            if (rxRingBuffer2.isCompleted(rxRingBuffer2.peek())) {
                                observer.onCompleted();
                                this.childSubscription.unsubscribe();
                                return;
                            }
                        }
                        if (this.emitted > THRESHOLD) {
                            for (Object obj2 : objArr) {
                                ((C0961a) obj2).requestMore(this.emitted);
                            }
                            this.emitted = 0;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, observer, objArr2);
                        return;
                    }
                } else if (decrementAndGet() <= 0) {
                    return;
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<R> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1216676403723546796L;
        public final a<R> zipper;

        public b(a<R> aVar) {
            this.zipper = aVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            BackpressureUtils.getAndAddRequest(this, j);
            this.zipper.tick();
        }
    }

    /* loaded from: classes13.dex */
    public final class c extends Subscriber<Observable[]> {
        public final Subscriber<? super R> l;
        public final a<R> m;
        public final b<R> n;
        public boolean o;

        public c(OperatorZip operatorZip, Subscriber<? super R> subscriber, a<R> aVar, b<R> bVar) {
            this.l = subscriber;
            this.m = aVar;
            this.n = bVar;
        }

        @Override // rx.Observer
        /* renamed from: b */
        public void onNext(Observable[] observableArr) {
            if (observableArr != null && observableArr.length != 0) {
                this.o = true;
                this.m.start(observableArr, this.n);
                return;
            }
            this.l.onCompleted();
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
            this.l.onError(th);
        }
    }

    public OperatorZip(FuncN<? extends R> funcN) {
        this.h = funcN;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Observable[]> call(Subscriber<? super R> subscriber) {
        a aVar = new a(subscriber, this.h);
        b bVar = new b(aVar);
        c cVar = new c(this, subscriber, aVar, bVar);
        subscriber.add(cVar);
        subscriber.setProducer(bVar);
        return cVar;
    }

    public OperatorZip(Func2 func2) {
        this.h = Functions.fromFunc(func2);
    }

    public OperatorZip(Func3 func3) {
        this.h = Functions.fromFunc(func3);
    }

    public OperatorZip(Func4 func4) {
        this.h = Functions.fromFunc(func4);
    }

    public OperatorZip(Func5 func5) {
        this.h = Functions.fromFunc(func5);
    }

    public OperatorZip(Func6 func6) {
        this.h = Functions.fromFunc(func6);
    }

    public OperatorZip(Func7 func7) {
        this.h = Functions.fromFunc(func7);
    }

    public OperatorZip(Func8 func8) {
        this.h = Functions.fromFunc(func8);
    }

    public OperatorZip(Func9 func9) {
        this.h = Functions.fromFunc(func9);
    }
}
