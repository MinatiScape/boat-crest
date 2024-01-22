package rx.internal.producers;

import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class ProducerObserverArbiter<T> implements Producer, Observer<T> {
    public static final Producer q = new a();
    public final Subscriber<? super T> h;
    public boolean i;
    public List<T> j;
    public Producer k;
    public long l;
    public long m;
    public Producer n;
    public Object o;
    public volatile boolean p;

    /* loaded from: classes13.dex */
    public static class a implements Producer {
        @Override // rx.Producer
        public void request(long j) {
        }
    }

    public ProducerObserverArbiter(Subscriber<? super T> subscriber) {
        this.h = subscriber;
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x0009, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.producers.ProducerObserverArbiter.a():void");
    }

    @Override // rx.Observer
    public void onCompleted() {
        synchronized (this) {
            if (this.i) {
                this.o = Boolean.TRUE;
                return;
            }
            this.i = true;
            this.h.onCompleted();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        boolean z;
        synchronized (this) {
            if (this.i) {
                this.o = th;
                z = false;
            } else {
                this.i = true;
                z = true;
            }
        }
        if (z) {
            this.h.onError(th);
        } else {
            this.p = true;
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        synchronized (this) {
            if (this.i) {
                List list = this.j;
                if (list == null) {
                    list = new ArrayList(4);
                    this.j = list;
                }
                list.add(t);
                return;
            }
            this.i = true;
            try {
                this.h.onNext(t);
                long j = this.l;
                if (j != Long.MAX_VALUE) {
                    this.l = j - 1;
                }
                a();
            } catch (Throwable th) {
                synchronized (this) {
                    this.i = false;
                    throw th;
                }
            }
        }
    }

    @Override // rx.Producer
    public void request(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (i == 0) {
            return;
        }
        synchronized (this) {
            if (this.i) {
                this.m += j;
                return;
            }
            this.i = true;
            Producer producer = this.k;
            try {
                long j2 = this.l + j;
                if (j2 < 0) {
                    j2 = Long.MAX_VALUE;
                }
                this.l = j2;
                a();
                if (producer != null) {
                    producer.request(j);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.i = false;
                    throw th;
                }
            }
        }
    }

    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.i) {
                if (producer == null) {
                    producer = q;
                }
                this.n = producer;
                return;
            }
            this.i = true;
            this.k = producer;
            long j = this.l;
            try {
                a();
                if (producer == null || j == 0) {
                    return;
                }
                producer.request(j);
            } catch (Throwable th) {
                synchronized (this) {
                    this.i = false;
                    throw th;
                }
            }
        }
    }
}
