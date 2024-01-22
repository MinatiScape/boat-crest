package io.reactivex.internal.functions;

import io.reactivex.Notification;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import io.reactivex.functions.Function8;
import io.reactivex.functions.Function9;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class Functions {

    /* renamed from: a  reason: collision with root package name */
    public static final Function<Object, Object> f13898a = new w();
    public static final Runnable EMPTY_RUNNABLE = new q();
    public static final Action EMPTY_ACTION = new n();
    public static final Consumer<Object> b = new o();
    public static final Consumer<Throwable> ERROR_CONSUMER = new s();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new g0();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new p();
    public static final Predicate<Object> c = new l0();
    public static final Predicate<Object> d = new t();
    public static final Callable<Object> e = new f0();
    public static final Comparator<Object> f = new b0();
    public static final Consumer<Subscription> REQUEST_MAX = new z();

    /* loaded from: classes12.dex */
    public static class BoundedConsumer implements Consumer<Subscription> {
        public final int h;

        public BoundedConsumer(int i) {
            this.h = i;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Subscription subscription) throws Exception {
            subscription.request(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class a<T> implements Consumer<T> {
        public final Action h;

        public a(Action action) {
            this.h = action;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.h.run();
        }
    }

    /* loaded from: classes12.dex */
    public enum a0 implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T1, T2, R> implements Function<Object[], R> {
        public final BiFunction<? super T1, ? super T2, ? extends R> h;

        public b(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
            this.h = biFunction;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return this.h.apply(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b0 implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T1, T2, T3, R> implements Function<Object[], R> {
        public final Function3<T1, T2, T3, R> h;

        public c(Function3<T1, T2, T3, R> function3) {
            this.h = function3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c0<T> implements Action {
        public final Consumer<? super Notification<T>> h;

        public c0(Consumer<? super Notification<T>> consumer) {
            this.h = consumer;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.h.accept(Notification.createOnComplete());
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T1, T2, T3, T4, R> implements Function<Object[], R> {
        public final Function4<T1, T2, T3, T4, R> h;

        public d(Function4<T1, T2, T3, T4, R> function4) {
            this.h = function4;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 4) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
            throw new IllegalArgumentException("Array of size 4 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d0<T> implements Consumer<Throwable> {
        public final Consumer<? super Notification<T>> h;

        public d0(Consumer<? super Notification<T>> consumer) {
            this.h = consumer;
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.h.accept(Notification.createOnError(th));
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<T1, T2, T3, T4, T5, R> implements Function<Object[], R> {
        public final Function5<T1, T2, T3, T4, T5, R> h;

        public e(Function5<T1, T2, T3, T4, T5, R> function5) {
            this.h = function5;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 5) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
            throw new IllegalArgumentException("Array of size 5 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class e0<T> implements Consumer<T> {
        public final Consumer<? super Notification<T>> h;

        public e0(Consumer<? super Notification<T>> consumer) {
            this.h = consumer;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.h.accept(Notification.createOnNext(t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T1, T2, T3, T4, T5, T6, R> implements Function<Object[], R> {
        public final Function6<T1, T2, T3, T4, T5, T6, R> h;

        public f(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
            this.h = function6;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 6) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
            throw new IllegalArgumentException("Array of size 6 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class f0 implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T1, T2, T3, T4, T5, T6, T7, R> implements Function<Object[], R> {
        public final Function7<T1, T2, T3, T4, T5, T6, T7, R> h;

        public g(Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
            this.h = function7;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 7) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
            throw new IllegalArgumentException("Array of size 7 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class g0 implements Consumer<Throwable> {
        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T1, T2, T3, T4, T5, T6, T7, T8, R> implements Function<Object[], R> {
        public final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> h;

        public h(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
            this.h = function8;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 8) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
            throw new IllegalArgumentException("Array of size 8 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class h0<T> implements Function<T, Timed<T>> {
        public final TimeUnit h;
        public final Scheduler i;

        public h0(TimeUnit timeUnit, Scheduler scheduler) {
            this.h = timeUnit;
            this.i = scheduler;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Timed<T> apply(T t) throws Exception {
            return new Timed<>(t, this.i.now(this.h), this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Function<Object[], R> {
        public final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> h;

        public i(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
            this.h = function9;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 9) {
                return (R) this.h.apply(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
            throw new IllegalArgumentException("Array of size 9 expected but got " + objArr.length);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i0<K, T> implements BiConsumer<Map<K, T>, T> {

        /* renamed from: a  reason: collision with root package name */
        public final Function<? super T, ? extends K> f13899a;

        public i0(Function<? super T, ? extends K> function) {
            this.f13899a = function;
        }

        @Override // io.reactivex.functions.BiConsumer
        /* renamed from: a */
        public void accept(Map<K, T> map, T t) throws Exception {
            map.put(this.f13899a.apply(t), t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> implements Callable<List<T>> {
        public final int h;

        public j(int i) {
            this.h = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<T> call() throws Exception {
            return new ArrayList(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j0<K, V, T> implements BiConsumer<Map<K, V>, T> {

        /* renamed from: a  reason: collision with root package name */
        public final Function<? super T, ? extends V> f13900a;
        public final Function<? super T, ? extends K> b;

        public j0(Function<? super T, ? extends V> function, Function<? super T, ? extends K> function2) {
            this.f13900a = function;
            this.b = function2;
        }

        @Override // io.reactivex.functions.BiConsumer
        /* renamed from: a */
        public void accept(Map<K, V> map, T t) throws Exception {
            map.put(this.b.apply(t), this.f13900a.apply(t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> implements Predicate<T> {
        public final BooleanSupplier h;

        public k(BooleanSupplier booleanSupplier) {
            this.h = booleanSupplier;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return !this.h.getAsBoolean();
        }
    }

    /* loaded from: classes12.dex */
    public static final class k0<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {

        /* renamed from: a  reason: collision with root package name */
        public final Function<? super K, ? extends Collection<? super V>> f13901a;
        public final Function<? super T, ? extends V> b;
        public final Function<? super T, ? extends K> c;

        public k0(Function<? super K, ? extends Collection<? super V>> function, Function<? super T, ? extends V> function2, Function<? super T, ? extends K> function3) {
            this.f13901a = function;
            this.b = function2;
            this.c = function3;
        }

        @Override // io.reactivex.functions.BiConsumer
        /* renamed from: a */
        public void accept(Map<K, Collection<V>> map, T t) throws Exception {
            K apply = this.c.apply(t);
            Collection<? super V> collection = map.get(apply);
            if (collection == null) {
                collection = this.f13901a.apply(apply);
                map.put(apply, collection);
            }
            collection.add(this.b.apply(t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T, U> implements Function<T, U> {
        public final Class<U> h;

        public l(Class<U> cls) {
            this.h = cls;
        }

        @Override // io.reactivex.functions.Function
        public U apply(T t) throws Exception {
            return this.h.cast(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class l0 implements Predicate<Object> {
        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) {
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T, U> implements Predicate<T> {
        public final Class<U> h;

        public m(Class<U> cls) {
            this.h = cls;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return this.h.isInstance(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class n implements Action {
        @Override // io.reactivex.functions.Action
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* loaded from: classes12.dex */
    public static final class o implements Consumer<Object> {
        @Override // io.reactivex.functions.Consumer
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* loaded from: classes12.dex */
    public static final class p implements LongConsumer {
        @Override // io.reactivex.functions.LongConsumer
        public void accept(long j) {
        }
    }

    /* loaded from: classes12.dex */
    public static final class q implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    /* loaded from: classes12.dex */
    public static final class r<T> implements Predicate<T> {
        public final T h;

        public r(T t) {
            this.h = t;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(T t) throws Exception {
            return ObjectHelper.equals(t, this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class s implements Consumer<Throwable> {
        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class t implements Predicate<Object> {
        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) {
            return false;
        }
    }

    /* loaded from: classes12.dex */
    public static final class u implements Action {
        public final Future<?> h;

        public u(Future<?> future) {
            this.h = future;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.h.get();
        }
    }

    /* loaded from: classes12.dex */
    public enum v implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    /* loaded from: classes12.dex */
    public static final class w implements Function<Object, Object> {
        @Override // io.reactivex.functions.Function
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* loaded from: classes12.dex */
    public static final class x<T, U> implements Callable<U>, Function<T, U> {
        public final U h;

        public x(U u) {
            this.h = u;
        }

        @Override // io.reactivex.functions.Function
        public U apply(T t) throws Exception {
            return this.h;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.h;
        }
    }

    /* loaded from: classes12.dex */
    public static final class y<T> implements Function<List<T>, List<T>> {
        public final Comparator<? super T> h;

        public y(Comparator<? super T> comparator) {
            this.h = comparator;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public List<T> apply(List<T> list) {
            Collections.sort(list, this.h);
            return list;
        }
    }

    /* loaded from: classes12.dex */
    public static final class z implements Consumer<Subscription> {
        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Consumer<T> actionConsumer(Action action) {
        return new a(action);
    }

    public static <T> Predicate<T> alwaysFalse() {
        return (Predicate<T>) d;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>) c;
    }

    public static <T> Consumer<T> boundedConsumer(int i2) {
        return new BoundedConsumer(i2);
    }

    public static <T, U> Function<T, U> castFunction(Class<U> cls) {
        return new l(cls);
    }

    public static <T> Callable<List<T>> createArrayList(int i2) {
        return new j(i2);
    }

    public static <T> Callable<Set<T>> createHashSet() {
        return v.INSTANCE;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return (Consumer<T>) b;
    }

    public static <T> Predicate<T> equalsWith(T t2) {
        return new r(t2);
    }

    public static Action futureAction(Future<?> future) {
        return new u(future);
    }

    public static <T> Function<T, T> identity() {
        return (Function<T, T>) f13898a;
    }

    public static <T, U> Predicate<T> isInstanceOf(Class<U> cls) {
        return new m(cls);
    }

    public static <T> Callable<T> justCallable(T t2) {
        return new x(t2);
    }

    public static <T, U> Function<T, U> justFunction(U u2) {
        return new x(u2);
    }

    public static <T> Function<List<T>, List<T>> listSorter(Comparator<? super T> comparator) {
        return new y(comparator);
    }

    public static <T> Comparator<T> naturalComparator() {
        return a0.INSTANCE;
    }

    public static <T> Comparator<T> naturalOrder() {
        return (Comparator<T>) f;
    }

    public static <T> Action notificationOnComplete(Consumer<? super Notification<T>> consumer) {
        return new c0(consumer);
    }

    public static <T> Consumer<Throwable> notificationOnError(Consumer<? super Notification<T>> consumer) {
        return new d0(consumer);
    }

    public static <T> Consumer<T> notificationOnNext(Consumer<? super Notification<T>> consumer) {
        return new e0(consumer);
    }

    public static <T> Callable<T> nullSupplier() {
        return (Callable<T>) e;
    }

    public static <T> Predicate<T> predicateReverseFor(BooleanSupplier booleanSupplier) {
        return new k(booleanSupplier);
    }

    public static <T> Function<T, Timed<T>> timestampWith(TimeUnit timeUnit, Scheduler scheduler) {
        return new h0(timeUnit, scheduler);
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new b(biFunction);
    }

    public static <T, K> BiConsumer<Map<K, T>, T> toMapKeySelector(Function<? super T, ? extends K> function) {
        return new i0(function);
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2) {
        return new j0(function2, function);
    }

    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> toMultimapKeyValueSelector(Function<? super T, ? extends K> function, Function<? super T, ? extends V> function2, Function<? super K, ? extends Collection<? super V>> function3) {
        return new k0(function3, function2, function);
    }

    public static <T1, T2, T3, R> Function<Object[], R> toFunction(Function3<T1, T2, T3, R> function3) {
        ObjectHelper.requireNonNull(function3, "f is null");
        return new c(function3);
    }

    public static <T1, T2, T3, T4, R> Function<Object[], R> toFunction(Function4<T1, T2, T3, T4, R> function4) {
        ObjectHelper.requireNonNull(function4, "f is null");
        return new d(function4);
    }

    public static <T1, T2, T3, T4, T5, R> Function<Object[], R> toFunction(Function5<T1, T2, T3, T4, T5, R> function5) {
        ObjectHelper.requireNonNull(function5, "f is null");
        return new e(function5);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Function<Object[], R> toFunction(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        ObjectHelper.requireNonNull(function6, "f is null");
        return new f(function6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Function<Object[], R> toFunction(Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
        ObjectHelper.requireNonNull(function7, "f is null");
        return new g(function7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function<Object[], R> toFunction(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
        ObjectHelper.requireNonNull(function8, "f is null");
        return new h(function8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<Object[], R> toFunction(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
        ObjectHelper.requireNonNull(function9, "f is null");
        return new i(function9);
    }
}
