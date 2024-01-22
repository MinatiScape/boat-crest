package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OnSubscribeToMultimap<T, K, V> implements Observable.OnSubscribe<Map<K, Collection<V>>>, Func0<Map<K, Collection<V>>> {
    public final Func1<? super T, ? extends K> h;
    public final Func1<? super T, ? extends V> i;
    public final Func0<? extends Map<K, Collection<V>>> j;
    public final Func1<? super K, ? extends Collection<V>> k;
    public final Observable<T> l;

    /* loaded from: classes13.dex */
    public static final class a<K, V> implements Func1<K, Collection<V>> {
        public static final a<Object, Object> h = new a<>();

        public static <K, V> a<K, V> b() {
            return (a<K, V>) h;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Collection<V> call(K k) {
            return new ArrayList();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, Collection<V>>> {
        public final Func1<? super T, ? extends K> m;
        public final Func1<? super T, ? extends V> n;
        public final Func1<? super K, ? extends Collection<V>> o;

        /* JADX WARN: Multi-variable type inference failed */
        public b(Subscriber<? super Map<K, Collection<V>>> subscriber, Map<K, Collection<V>> map, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func1<? super K, ? extends Collection<V>> func13) {
            super(subscriber);
            this.value = map;
            this.hasValue = true;
            this.m = func1;
            this.n = func12;
            this.o = func13;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                K call = this.m.call(t);
                V call2 = this.n.call(t);
                Collection<V> collection = (Collection) ((Map) this.value).get(call);
                if (collection == null) {
                    collection = this.o.call(call);
                    ((Map) this.value).put(call, collection);
                }
                collection.add(call2);
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

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(observable, func1, func12, null, a.b());
    }

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0) {
        this(observable, func1, func12, func0, a.b());
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeToMultimap(Observable<T> observable, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func0<? extends Map<K, Collection<V>>> func0, Func1<? super K, ? extends Collection<V>> func13) {
        this.l = observable;
        this.h = func1;
        this.i = func12;
        if (func0 == null) {
            this.j = this;
        } else {
            this.j = func0;
        }
        this.k = func13;
    }

    @Override // rx.functions.Func0, java.util.concurrent.Callable
    public Map<K, Collection<V>> call() {
        return new HashMap();
    }

    public void call(Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            new b(subscriber, this.j.call(), this.h, this.i, this.k).subscribeTo(this.l);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }
}
