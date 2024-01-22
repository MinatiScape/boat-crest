package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OnSubscribeToMap<T, K, V> implements Observable.OnSubscribe<Map<K, V>>, Func0<Map<K, V>> {
    public final Observable<T> h;
    public final Func1<? super T, ? extends K> i;
    public final Func1<? super T, ? extends V> j;
    public final Func0<? extends Map<K, V>> k;

    /* loaded from: classes13.dex */
    public static final class a<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, V>> {
        public final Func1<? super T, ? extends K> m;
        public final Func1<? super T, ? extends V> n;

        /* JADX WARN: Multi-variable type inference failed */
        public a(Subscriber<? super Map<K, V>> subscriber, Map<K, V> map, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
            super(subscriber);
            this.value = map;
            this.hasValue = true;
            this.m = func1;
            this.n = func12;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                ((Map) this.value).put(this.m.call(t), this.n.call(t));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OnSubscribeToMap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(observable, func1, func12, null);
    }

    public OnSubscribeToMap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, V>> func0) {
        this.h = observable;
        this.i = func1;
        this.j = func12;
        if (func0 == null) {
            this.k = this;
        } else {
            this.k = func0;
        }
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    @Override // rx.functions.Func0, java.util.concurrent.Callable
    public Map<K, V> call() {
        return new HashMap();
    }

    public void call(Subscriber<? super Map<K, V>> subscriber) {
        try {
            new a(subscriber, this.k.call(), this.i, this.j).subscribeTo(this.h);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
