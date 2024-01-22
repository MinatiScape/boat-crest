package org.greenrobot.greendao.rx;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.annotation.apihint.Experimental;
import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.Query;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
@Experimental
/* loaded from: classes13.dex */
public class RxQuery<T> extends org.greenrobot.greendao.rx.a {

    /* renamed from: a  reason: collision with root package name */
    public final Query<T> f15492a;

    /* loaded from: classes13.dex */
    public class a implements Callable<List<T>> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<T> call() throws Exception {
            return RxQuery.this.f15492a.forCurrentThread().list();
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Callable<T> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            return RxQuery.this.f15492a.forCurrentThread().unique();
        }
    }

    /* loaded from: classes13.dex */
    public class c implements Observable.OnSubscribe<T> {
        public c() {
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            try {
                LazyList<T> listLazyUncached = RxQuery.this.f15492a.forCurrentThread().listLazyUncached();
                Iterator<T> it = listLazyUncached.iterator();
                while (it.hasNext()) {
                    Object obj = (T) it.next();
                    if (subscriber.isUnsubscribed()) {
                        break;
                    }
                    subscriber.onNext(obj);
                }
                listLazyUncached.close();
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                subscriber.onError(th);
            }
        }
    }

    public RxQuery(Query<T> query) {
        this.f15492a = query;
    }

    @Override // org.greenrobot.greendao.rx.a
    @Experimental
    public /* bridge */ /* synthetic */ Scheduler getScheduler() {
        return super.getScheduler();
    }

    @Experimental
    public Observable<List<T>> list() {
        return (Observable<List<T>>) wrap(new a());
    }

    public Observable<T> oneByOne() {
        return (Observable<T>) wrap(Observable.create(new c()));
    }

    @Experimental
    public Observable<T> unique() {
        return (Observable<T>) wrap(new b());
    }

    public RxQuery(Query<T> query, Scheduler scheduler) {
        super(scheduler);
        this.f15492a = query;
    }
}
