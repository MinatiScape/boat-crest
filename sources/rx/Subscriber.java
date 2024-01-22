package rx;

import rx.internal.util.SubscriptionList;
/* loaded from: classes13.dex */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    public final SubscriptionList h;
    public final Subscriber<?> i;
    public Producer j;
    public long k;

    public Subscriber() {
        this(null, false);
    }

    public final void a(long j) {
        long j2 = this.k;
        if (j2 == Long.MIN_VALUE) {
            this.k = j;
            return;
        }
        long j3 = j2 + j;
        if (j3 < 0) {
            this.k = Long.MAX_VALUE;
        } else {
            this.k = j3;
        }
    }

    public final void add(Subscription subscription) {
        this.h.add(subscription);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.h.isUnsubscribed();
    }

    public void onStart() {
    }

    public final void request(long j) {
        if (j >= 0) {
            synchronized (this) {
                Producer producer = this.j;
                if (producer != null) {
                    producer.request(j);
                    return;
                } else {
                    a(j);
                    return;
                }
            }
        }
        throw new IllegalArgumentException("number requested cannot be negative: " + j);
    }

    public void setProducer(Producer producer) {
        long j;
        Subscriber<?> subscriber;
        boolean z;
        synchronized (this) {
            j = this.k;
            this.j = producer;
            subscriber = this.i;
            z = subscriber != null && j == Long.MIN_VALUE;
        }
        if (z) {
            subscriber.setProducer(producer);
        } else if (j == Long.MIN_VALUE) {
            producer.request(Long.MAX_VALUE);
        } else {
            producer.request(j);
        }
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.h.unsubscribe();
    }

    public Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    public Subscriber(Subscriber<?> subscriber, boolean z) {
        this.k = Long.MIN_VALUE;
        this.i = subscriber;
        this.h = (!z || subscriber == null) ? new SubscriptionList() : subscriber.h;
    }
}
