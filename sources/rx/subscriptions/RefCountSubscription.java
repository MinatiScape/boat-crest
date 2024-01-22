package rx.subscriptions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
/* loaded from: classes13.dex */
public final class RefCountSubscription implements Subscription {
    public static final b j = new b(false, 0);
    public final Subscription h;
    public final AtomicReference<b> i = new AtomicReference<>(j);

    /* loaded from: classes13.dex */
    public static final class a extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 7005765588239987643L;
        public final RefCountSubscription parent;

        public a(RefCountSubscription refCountSubscription) {
            this.parent = refCountSubscription;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() != 0;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(0, 1)) {
                this.parent.a();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f15706a;
        public final int b;

        public b(boolean z, int i) {
            this.f15706a = z;
            this.b = i;
        }

        public b a() {
            return new b(this.f15706a, this.b + 1);
        }

        public b b() {
            return new b(this.f15706a, this.b - 1);
        }

        public b c() {
            return new b(true, this.b);
        }
    }

    public RefCountSubscription(Subscription subscription) {
        if (subscription != null) {
            this.h = subscription;
            return;
        }
        throw new IllegalArgumentException("s");
    }

    public void a() {
        b bVar;
        b b2;
        AtomicReference<b> atomicReference = this.i;
        do {
            bVar = atomicReference.get();
            b2 = bVar.b();
        } while (!atomicReference.compareAndSet(bVar, b2));
        b(b2);
    }

    public final void b(b bVar) {
        if (bVar.f15706a && bVar.b == 0) {
            this.h.unsubscribe();
        }
    }

    public Subscription get() {
        b bVar;
        AtomicReference<b> atomicReference = this.i;
        do {
            bVar = atomicReference.get();
            if (bVar.f15706a) {
                return Subscriptions.unsubscribed();
            }
        } while (!atomicReference.compareAndSet(bVar, bVar.a()));
        return new a(this);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.i.get().f15706a;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        b bVar;
        b c;
        AtomicReference<b> atomicReference = this.i;
        do {
            bVar = atomicReference.get();
            if (bVar.f15706a) {
                return;
            }
            c = bVar.c();
        } while (!atomicReference.compareAndSet(bVar, c));
        b(c);
    }
}
