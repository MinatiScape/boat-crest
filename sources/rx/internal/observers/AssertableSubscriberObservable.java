package rx.internal.observers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.AssertableSubscriber;
import rx.observers.TestSubscriber;
/* loaded from: classes13.dex */
public class AssertableSubscriberObservable<T> extends Subscriber<T> implements AssertableSubscriber<T> {
    public final TestSubscriber<T> l;

    public AssertableSubscriberObservable(TestSubscriber<T> testSubscriber) {
        this.l = testSubscriber;
    }

    public static <T> AssertableSubscriberObservable<T> create(long j) {
        TestSubscriber testSubscriber = new TestSubscriber(j);
        AssertableSubscriberObservable<T> assertableSubscriberObservable = new AssertableSubscriberObservable<>(testSubscriber);
        assertableSubscriberObservable.add(testSubscriber);
        return assertableSubscriberObservable;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertCompleted() {
        this.l.assertCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Class<? extends Throwable> cls) {
        this.l.assertError(cls);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailure(Class<? extends Throwable> cls, T... tArr) {
        this.l.assertValues(tArr);
        this.l.assertError(cls);
        this.l.assertNotCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailureAndMessage(Class<? extends Throwable> cls, String str, T... tArr) {
        this.l.assertValues(tArr);
        this.l.assertError(cls);
        this.l.assertNotCompleted();
        String message = this.l.getOnErrorEvents().get(0).getMessage();
        if (message == str || (str != null && str.equals(message))) {
            return this;
        }
        throw new AssertionError("Error message differs. Expected: '" + str + "', Received: '" + message + "'");
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoErrors() {
        this.l.assertNoErrors();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoTerminalEvent() {
        this.l.assertNoTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoValues() {
        this.l.assertNoValues();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNotCompleted() {
        this.l.assertNotCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertReceivedOnNext(List<T> list) {
        this.l.assertReceivedOnNext(list);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertResult(T... tArr) {
        this.l.assertValues(tArr);
        this.l.assertNoErrors();
        this.l.assertCompleted();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertTerminalEvent() {
        this.l.assertTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertUnsubscribed() {
        this.l.assertUnsubscribed();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValue(T t) {
        this.l.assertValue(t);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValueCount(int i) {
        this.l.assertValueCount(i);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValues(T... tArr) {
        this.l.assertValues(tArr);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertValuesAndClear(T t, T... tArr) {
        this.l.assertValuesAndClear(t, tArr);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent() {
        this.l.awaitTerminalEvent();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        this.l.awaitTerminalEventAndUnsubscribeOnTimeout(j, timeUnit);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> awaitValueCount(int i, long j, TimeUnit timeUnit) {
        if (this.l.awaitValueCount(i, j, timeUnit)) {
            return this;
        }
        throw new AssertionError("Did not receive enough values in time. Expected: " + i + ", Actual: " + this.l.getValueCount());
    }

    @Override // rx.observers.AssertableSubscriber
    public final int getCompletions() {
        return this.l.getCompletions();
    }

    @Override // rx.observers.AssertableSubscriber
    public Thread getLastSeenThread() {
        return this.l.getLastSeenThread();
    }

    @Override // rx.observers.AssertableSubscriber
    public List<Throwable> getOnErrorEvents() {
        return this.l.getOnErrorEvents();
    }

    @Override // rx.observers.AssertableSubscriber
    public List<T> getOnNextEvents() {
        return this.l.getOnNextEvents();
    }

    @Override // rx.observers.AssertableSubscriber
    public final int getValueCount() {
        return this.l.getValueCount();
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
    public void onNext(T t) {
        this.l.onNext(t);
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public void onStart() {
        this.l.onStart();
    }

    @Override // rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> perform(Action0 action0) {
        action0.call();
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> requestMore(long j) {
        this.l.requestMore(j);
        return this;
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public void setProducer(Producer producer) {
        this.l.setProducer(producer);
    }

    public String toString() {
        return this.l.toString();
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Throwable th) {
        this.l.assertError(th);
        return this;
    }

    @Override // rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent(long j, TimeUnit timeUnit) {
        this.l.awaitTerminalEvent(j, timeUnit);
        return this;
    }
}
