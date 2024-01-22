package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OnSubscribeFromArray<T> implements Observable.OnSubscribe<T> {
    public final T[] h;

    /* loaded from: classes13.dex */
    public static final class a<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = 3534218984725836979L;
        public final T[] array;
        public final Subscriber<? super T> child;
        public int index;

        public a(Subscriber<? super T> subscriber, T[] tArr) {
            this.child = subscriber;
            this.array = tArr;
        }

        public void fastPath() {
            Subscriber<? super T> subscriber = this.child;
            for (T t : this.array) {
                Object obj = (Object) t;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(obj);
            }
            if (subscriber.isUnsubscribed()) {
                return;
            }
            subscriber.onCompleted();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j == Long.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, j) == 0) {
                    fastPath();
                }
            } else if (i == 0 || BackpressureUtils.getAndAddRequest(this, j) != 0) {
            } else {
                slowPath(j);
            }
        }

        public void slowPath(long j) {
            Subscriber<? super T> subscriber = this.child;
            T[] tArr = this.array;
            int length = tArr.length;
            int i = this.index;
            do {
                long j2 = 0;
                while (true) {
                    if (j != 0 && i != length) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onNext((Object) tArr[i]);
                        i++;
                        if (i == length) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            subscriber.onCompleted();
                            return;
                        }
                        j--;
                        j2--;
                    } else {
                        j = get() + j2;
                        if (j == 0) {
                            this.index = i;
                            j = addAndGet(j2);
                        }
                    }
                }
            } while (j != 0);
        }
    }

    public OnSubscribeFromArray(T[] tArr) {
        this.h = tArr;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.setProducer(new a(subscriber, this.h));
    }
}
