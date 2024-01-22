package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class CompositeSubscription implements Subscription {
    public Set<Subscription> h;
    public volatile boolean i;

    public CompositeSubscription() {
    }

    public static void a(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Subscription subscription : collection) {
            try {
                subscription.unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        Exceptions.throwIfAny(arrayList);
    }

    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    if (this.h == null) {
                        this.h = new HashSet(4);
                    }
                    this.h.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void addAll(Subscription... subscriptionArr) {
        int i = 0;
        if (!this.i) {
            synchronized (this) {
                if (!this.i) {
                    if (this.h == null) {
                        this.h = new HashSet(subscriptionArr.length);
                    }
                    int length = subscriptionArr.length;
                    while (i < length) {
                        Subscription subscription = subscriptionArr[i];
                        if (!subscription.isUnsubscribed()) {
                            this.h.add(subscription);
                        }
                        i++;
                    }
                    return;
                }
            }
        }
        int length2 = subscriptionArr.length;
        while (i < length2) {
            subscriptionArr[i].unsubscribe();
            i++;
        }
    }

    public void clear() {
        Set<Subscription> set;
        if (this.i) {
            return;
        }
        synchronized (this) {
            if (!this.i && (set = this.h) != null) {
                this.h = null;
                a(set);
            }
        }
    }

    public boolean hasSubscriptions() {
        Set<Subscription> set;
        boolean z = false;
        if (this.i) {
            return false;
        }
        synchronized (this) {
            if (!this.i && (set = this.h) != null && !set.isEmpty()) {
                z = true;
            }
        }
        return z;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.i;
    }

    public void remove(Subscription subscription) {
        Set<Subscription> set;
        if (this.i) {
            return;
        }
        synchronized (this) {
            if (!this.i && (set = this.h) != null) {
                boolean remove = set.remove(subscription);
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (this.i) {
            return;
        }
        synchronized (this) {
            if (this.i) {
                return;
            }
            this.i = true;
            Set<Subscription> set = this.h;
            this.h = null;
            a(set);
        }
    }

    public CompositeSubscription(Subscription... subscriptionArr) {
        this.h = new HashSet(Arrays.asList(subscriptionArr));
    }
}
