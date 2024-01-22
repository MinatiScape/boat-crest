package rx.internal.util;

import java.io.PrintStream;
import java.util.Queue;
import rx.Observer;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes13.dex */
public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    public Queue<Object> h;
    public final int i;
    public volatile Object terminalState;

    static {
        int i = PlatformDependent.isAndroid() ? 16 : 128;
        String property = System.getProperty("rx.ring-buffer.size");
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException e) {
                PrintStream printStream = System.err;
                printStream.println("Failed to set 'rx.buffer.size' with value " + property + " => " + e.getMessage());
            }
        }
        SIZE = i;
    }

    public RxRingBuffer(Queue<Object> queue, int i) {
        this.h = queue;
        this.i = i;
    }

    public static RxRingBuffer getSpmcInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(true, SIZE);
        }
        return new RxRingBuffer();
    }

    public static RxRingBuffer getSpscInstance() {
        if (UnsafeAccess.isUnsafeAvailable()) {
            return new RxRingBuffer(false, SIZE);
        }
        return new RxRingBuffer();
    }

    public boolean accept(Object obj, Observer observer) {
        return NotificationLite.accept(observer, obj);
    }

    public Throwable asError(Object obj) {
        return NotificationLite.getError(obj);
    }

    public int available() {
        return this.i - count();
    }

    public int capacity() {
        return this.i;
    }

    public int count() {
        Queue<Object> queue = this.h;
        if (queue == null) {
            return 0;
        }
        return queue.size();
    }

    public Object getValue(Object obj) {
        return NotificationLite.getValue(obj);
    }

    public boolean isCompleted(Object obj) {
        return NotificationLite.isCompleted(obj);
    }

    public boolean isEmpty() {
        Queue<Object> queue = this.h;
        return queue == null || queue.isEmpty();
    }

    public boolean isError(Object obj) {
        return NotificationLite.isError(obj);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.h == null;
    }

    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = NotificationLite.completed();
        }
    }

    public void onError(Throwable th) {
        if (this.terminalState == null) {
            this.terminalState = NotificationLite.error(th);
        }
    }

    public void onNext(Object obj) throws MissingBackpressureException {
        boolean z;
        boolean z2;
        synchronized (this) {
            Queue<Object> queue = this.h;
            z = true;
            z2 = false;
            if (queue != null) {
                z = false;
                z2 = !queue.offer(NotificationLite.next(obj));
            }
        }
        if (z) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        }
        if (z2) {
            throw new MissingBackpressureException();
        }
    }

    public Object peek() {
        synchronized (this) {
            Queue<Object> queue = this.h;
            if (queue == null) {
                return null;
            }
            Object peek = queue.peek();
            Object obj = this.terminalState;
            if (peek == null && obj != null && queue.peek() == null) {
                peek = obj;
            }
            return peek;
        }
    }

    public Object poll() {
        synchronized (this) {
            Queue<Object> queue = this.h;
            if (queue == null) {
                return null;
            }
            Object poll = queue.poll();
            Object obj = this.terminalState;
            if (poll == null && obj != null && queue.peek() == null) {
                this.terminalState = null;
                poll = obj;
            }
            return poll;
        }
    }

    public synchronized void release() {
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        release();
    }

    public RxRingBuffer(boolean z, int i) {
        this.h = z ? new SpmcArrayQueue<>(i) : new SpscArrayQueue<>(i);
        this.i = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RxRingBuffer() {
        /*
            r2 = this;
            rx.internal.util.atomic.SpscAtomicArrayQueue r0 = new rx.internal.util.atomic.SpscAtomicArrayQueue
            int r1 = rx.internal.util.RxRingBuffer.SIZE
            r0.<init>(r1)
            r2.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.RxRingBuffer.<init>():void");
    }
}
