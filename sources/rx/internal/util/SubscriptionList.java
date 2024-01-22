package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class SubscriptionList implements Subscription {
    public List<Subscription> h;
    public volatile boolean i;

    public SubscriptionList() {
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
                    List list = this.h;
                    if (list == null) {
                        list = new LinkedList();
                        this.h = list;
                    }
                    list.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void clear() {
        List<Subscription> list;
        if (this.i) {
            return;
        }
        synchronized (this) {
            list = this.h;
            this.h = null;
        }
        a(list);
    }

    public boolean hasSubscriptions() {
        List<Subscription> list;
        boolean z = false;
        if (this.i) {
            return false;
        }
        synchronized (this) {
            if (!this.i && (list = this.h) != null && !list.isEmpty()) {
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
        if (this.i) {
            return;
        }
        synchronized (this) {
            List<Subscription> list = this.h;
            if (!this.i && list != null) {
                boolean remove = list.remove(subscription);
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
            List<Subscription> list = this.h;
            this.h = null;
            a(list);
        }
    }

    public SubscriptionList(Subscription... subscriptionArr) {
        this.h = new LinkedList(Arrays.asList(subscriptionArr));
    }

    public SubscriptionList(Subscription subscription) {
        LinkedList linkedList = new LinkedList();
        this.h = linkedList;
        linkedList.add(subscription);
    }
}
