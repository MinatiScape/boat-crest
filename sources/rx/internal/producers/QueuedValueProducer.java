package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes13.dex */
public final class QueuedValueProducer<T> extends AtomicLong implements Producer {
    public static final Object NULL_SENTINEL = new Object();
    private static final long serialVersionUID = 7277121710709137047L;
    public final Subscriber<? super T> child;
    public final Queue<Object> queue;
    public final AtomicInteger wip;

    public QueuedValueProducer(Subscriber<? super T> subscriber) {
        this(subscriber, UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue() : new SpscLinkedAtomicQueue());
    }

    private void drain() {
        Object poll;
        if (this.wip.getAndIncrement() == 0) {
            Subscriber<? super T> subscriber = this.child;
            Queue<Object> queue = this.queue;
            while (!subscriber.isUnsubscribed()) {
                this.wip.lazySet(1);
                long j = get();
                long j2 = 0;
                while (j != 0 && (poll = queue.poll()) != null) {
                    try {
                        if (poll == NULL_SENTINEL) {
                            subscriber.onNext(null);
                        } else {
                            subscriber.onNext(poll);
                        }
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        j--;
                        j2++;
                    } catch (Throwable th) {
                        if (poll == NULL_SENTINEL) {
                            poll = null;
                        }
                        Exceptions.throwOrReport(th, subscriber, poll);
                        return;
                    }
                }
                if (j2 != 0 && get() != Long.MAX_VALUE) {
                    addAndGet(-j2);
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    public boolean offer(T t) {
        if (t == null) {
            if (!this.queue.offer(NULL_SENTINEL)) {
                return false;
            }
        } else if (!this.queue.offer(t)) {
            return false;
        }
        drain();
        return true;
    }

    @Override // rx.Producer
    public void request(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (i > 0) {
            BackpressureUtils.getAndAddRequest(this, j);
            drain();
        }
    }

    public QueuedValueProducer(Subscriber<? super T> subscriber, Queue<Object> queue) {
        this.child = subscriber;
        this.queue = queue;
        this.wip = new AtomicInteger();
    }
}
