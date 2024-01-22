package rx.observers;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
/* loaded from: classes13.dex */
public class TestSubscriber<T> extends Subscriber<T> {
    public static final Observer<Object> s = new a();
    public final Observer<T> l;
    public final List<T> m;
    public final List<Throwable> n;
    public int o;
    public final CountDownLatch p;
    public volatile int q;
    public volatile Thread r;

    /* loaded from: classes13.dex */
    public static class a implements Observer<Object> {
        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
        }
    }

    public TestSubscriber(long j) {
        this(s, j);
    }

    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    public void assertCompleted() {
        int i = this.o;
        if (i == 0) {
            c("Not completed!");
        } else if (i > 1) {
            c("Completed multiple times: " + i);
        }
    }

    public void assertError(Class<? extends Throwable> cls) {
        List<Throwable> list = this.n;
        if (list.isEmpty()) {
            c("No errors");
        } else if (list.size() <= 1) {
            if (cls.isInstance(list.get(0))) {
                return;
            }
            AssertionError assertionError = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + list.get(0));
            assertionError.initCause(list.get(0));
            throw assertionError;
        } else {
            AssertionError assertionError2 = new AssertionError("Multiple errors: " + list.size());
            assertionError2.initCause(new CompositeException(list));
            throw assertionError2;
        }
    }

    public void assertNoErrors() {
        if (getOnErrorEvents().isEmpty()) {
            return;
        }
        c("Unexpected onError events");
    }

    public void assertNoTerminalEvent() {
        List<Throwable> list = this.n;
        int i = this.o;
        if (!list.isEmpty() || i > 0) {
            if (list.isEmpty()) {
                c("Found " + list.size() + " errors and " + i + " completion events instead of none");
            } else if (list.size() == 1) {
                c("Found " + list.size() + " errors and " + i + " completion events instead of none");
            } else {
                c("Found " + list.size() + " errors and " + i + " completion events instead of none");
            }
        }
    }

    public void assertNoValues() {
        int size = this.m.size();
        if (size != 0) {
            c("No onNext events expected yet some received: " + size);
        }
    }

    public void assertNotCompleted() {
        int i = this.o;
        if (i == 1) {
            c("Completed!");
        } else if (i > 1) {
            c("Completed multiple times: " + i);
        }
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.m.size() != list.size()) {
            c("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.m.size() + ".\nProvided values: " + list + "\nActual values: " + this.m + "\n");
        }
        for (int i = 0; i < list.size(); i++) {
            b(list.get(i), i);
        }
    }

    public void assertTerminalEvent() {
        if (this.n.size() > 1) {
            c("Too many onError events: " + this.n.size());
        }
        if (this.o > 1) {
            c("Too many onCompleted events: " + this.o);
        }
        if (this.o == 1 && this.n.size() == 1) {
            c("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.o == 0 && this.n.isEmpty()) {
            c("No terminal events received.");
        }
    }

    public void assertUnsubscribed() {
        if (isUnsubscribed()) {
            return;
        }
        c("Not unsubscribed.");
    }

    public void assertValue(T t) {
        assertReceivedOnNext(Collections.singletonList(t));
    }

    public void assertValueCount(int i) {
        int size = this.m.size();
        if (size != i) {
            c("Number of onNext events differ; expected: " + i + ", actual: " + size);
        }
    }

    public void assertValues(T... tArr) {
        assertReceivedOnNext(Arrays.asList(tArr));
    }

    public final void assertValuesAndClear(T t, T... tArr) {
        assertValueCount(tArr.length + 1);
        int i = 0;
        b(t, 0);
        while (i < tArr.length) {
            T t2 = tArr[i];
            i++;
            b(t2, i);
        }
        this.m.clear();
    }

    public void awaitTerminalEvent() {
        try {
            this.p.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        try {
            if (this.p.await(j, timeUnit)) {
                return;
            }
            unsubscribe();
        } catch (InterruptedException unused) {
            unsubscribe();
        }
    }

    public final boolean awaitValueCount(int i, long j, TimeUnit timeUnit) {
        while (j != 0 && this.q < i) {
            try {
                timeUnit.sleep(1L);
                j--;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Interrupted", e);
            }
        }
        return this.q >= i;
    }

    public final void b(T t, int i) {
        T t2 = this.m.get(i);
        if (t == null) {
            if (t2 != null) {
                c("Value at index: " + i + " expected: [null] but was: [" + t2 + "]\n");
            }
        } else if (t.equals(t2)) {
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Value at index: ");
            sb.append(i);
            sb.append(" expected: [");
            sb.append(t);
            sb.append("] (");
            sb.append(t.getClass().getSimpleName());
            sb.append(") but was: [");
            sb.append(t2);
            sb.append("] (");
            sb.append(t2 != null ? t2.getClass().getSimpleName() : "null");
            sb.append(")\n");
            c(sb.toString());
        }
    }

    public final void c(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int i = this.o;
        sb.append(i);
        sb.append(" completion");
        if (i != 1) {
            sb.append('s');
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        if (!this.n.isEmpty()) {
            int size = this.n.size();
            sb.append(" (+");
            sb.append(size);
            sb.append(" error");
            if (size != 1) {
                sb.append('s');
            }
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.n.isEmpty()) {
            if (this.n.size() == 1) {
                assertionError.initCause(this.n.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.n));
            }
        }
        throw assertionError;
    }

    public final int getCompletions() {
        return this.o;
    }

    public Thread getLastSeenThread() {
        return this.r;
    }

    @Deprecated
    public List<Notification<T>> getOnCompletedEvents() {
        int i = this.o;
        ArrayList arrayList = new ArrayList(i != 0 ? i : 1);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Notification.createOnCompleted());
        }
        return arrayList;
    }

    public List<Throwable> getOnErrorEvents() {
        return this.n;
    }

    public List<T> getOnNextEvents() {
        return this.m;
    }

    public final int getValueCount() {
        return this.q;
    }

    @Override // rx.Observer
    public void onCompleted() {
        try {
            this.o++;
            this.r = Thread.currentThread();
            this.l.onCompleted();
        } finally {
            this.p.countDown();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        try {
            this.r = Thread.currentThread();
            this.n.add(th);
            this.l.onError(th);
        } finally {
            this.p.countDown();
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.r = Thread.currentThread();
        this.m.add(t);
        this.q = this.m.size();
        this.l.onNext(t);
    }

    public void requestMore(long j) {
        request(j);
    }

    public TestSubscriber(Observer<T> observer, long j) {
        this.p = new CountDownLatch(1);
        Objects.requireNonNull(observer);
        this.l = observer;
        if (j >= 0) {
            request(j);
        }
        this.m = new ArrayList();
        this.n = new ArrayList();
    }

    public static <T> TestSubscriber<T> create(long j) {
        return new TestSubscriber<>(j);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer, long j) {
        return new TestSubscriber<>(observer, j);
    }

    public void awaitTerminalEvent(long j, TimeUnit timeUnit) {
        try {
            this.p.await(j, timeUnit);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public static <T> TestSubscriber<T> create(Subscriber<T> subscriber) {
        return new TestSubscriber<>((Subscriber) subscriber);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer) {
        return new TestSubscriber<>(observer);
    }

    public TestSubscriber(Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }

    public TestSubscriber(Observer<T> observer) {
        this(observer, -1L);
    }

    public TestSubscriber() {
        this(-1L);
    }

    public void assertError(Throwable th) {
        List<Throwable> list = this.n;
        if (list.isEmpty()) {
            c("No errors");
        } else if (list.size() > 1) {
            c("Multiple errors");
        } else if (th.equals(list.get(0))) {
        } else {
            c("Exceptions differ; expected: " + th + ", actual: " + list.get(0));
        }
    }
}
