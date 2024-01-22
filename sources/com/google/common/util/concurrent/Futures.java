package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.e;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import com.google.common.util.concurrent.n;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Futures extends m {

    @Beta
    @GwtCompatible
    @CanIgnoreReturnValue
    /* loaded from: classes10.dex */
    public static final class FutureCombiner<V> {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f10790a;
        public final ImmutableList<ListenableFuture<? extends V>> b;

        /* loaded from: classes10.dex */
        public class a implements Callable<Void> {
            public final /* synthetic */ Runnable h;

            public a(FutureCombiner futureCombiner, Runnable runnable) {
                this.h = runnable;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Void call() throws Exception {
                this.h.run();
                return null;
            }
        }

        public /* synthetic */ FutureCombiner(boolean z, ImmutableList immutableList, a aVar) {
            this(z, immutableList);
        }

        @CanIgnoreReturnValue
        public <C> ListenableFuture<C> call(Callable<C> callable, Executor executor) {
            return new com.google.common.util.concurrent.f(this.b, this.f10790a, executor, callable);
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> asyncCallable, Executor executor) {
            return new com.google.common.util.concurrent.f(this.b, this.f10790a, executor, asyncCallable);
        }

        public ListenableFuture<?> run(Runnable runnable, Executor executor) {
            return call(new a(this, runnable), executor);
        }

        public FutureCombiner(boolean z, ImmutableList<ListenableFuture<? extends V>> immutableList) {
            this.f10790a = z;
            this.b = immutableList;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ Future h;

        public a(Future future) {
            this.h = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.cancel(false);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [O] */
    /* loaded from: classes10.dex */
    public class b<O> implements Future<O> {
        public final /* synthetic */ Future h;
        public final /* synthetic */ Function i;

        public b(Future future, Function function) {
            this.h = future;
            this.i = function;
        }

        public final O a(I i) throws ExecutionException {
            try {
                return (O) this.i.apply(i);
            } catch (Throwable th) {
                throw new ExecutionException(th);
            }
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return this.h.cancel(z);
        }

        @Override // java.util.concurrent.Future
        public O get() throws InterruptedException, ExecutionException {
            return a(this.h.get());
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.h.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.h.isDone();
        }

        @Override // java.util.concurrent.Future
        public O get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return a(this.h.get(j, timeUnit));
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Runnable {
        public final /* synthetic */ f h;
        public final /* synthetic */ ImmutableList i;
        public final /* synthetic */ int j;

        public c(f fVar, ImmutableList immutableList, int i) {
            this.h = fVar;
            this.i = immutableList;
            this.j = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.f(this.i, this.j);
        }
    }

    /* loaded from: classes10.dex */
    public static final class d<V> implements Runnable {
        public final Future<V> h;
        public final FutureCallback<? super V> i;

        public d(Future<V> future, FutureCallback<? super V> futureCallback) {
            this.h = future;
            this.i = futureCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable tryInternalFastPathGetFailure;
            Future<V> future = this.h;
            if ((future instanceof InternalFutureFailureAccess) && (tryInternalFastPathGetFailure = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) future)) != null) {
                this.i.onFailure(tryInternalFastPathGetFailure);
                return;
            }
            try {
                this.i.onSuccess(Futures.getDone(this.h));
            } catch (Error e) {
                e = e;
                this.i.onFailure(e);
            } catch (RuntimeException e2) {
                e = e2;
                this.i.onFailure(e);
            } catch (ExecutionException e3) {
                this.i.onFailure(e3.getCause());
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.i).toString();
        }
    }

    /* loaded from: classes10.dex */
    public static final class e<T> extends AbstractFuture<T> {
        public f<T> o;

        public /* synthetic */ e(f fVar, a aVar) {
            this(fVar);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.o = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public boolean cancel(boolean z) {
            f<T> fVar = this.o;
            if (super.cancel(z)) {
                fVar.g(z);
                return true;
            }
            return false;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            f<T> fVar = this.o;
            if (fVar != null) {
                int length = fVar.d.length;
                int i = fVar.c.get();
                StringBuilder sb = new StringBuilder(49);
                sb.append("inputCount=[");
                sb.append(length);
                sb.append("], remaining=[");
                sb.append(i);
                sb.append("]");
                return sb.toString();
            }
            return null;
        }

        public e(f<T> fVar) {
            this.o = fVar;
        }
    }

    /* loaded from: classes10.dex */
    public static final class f<T> {

        /* renamed from: a  reason: collision with root package name */
        public boolean f10791a;
        public boolean b;
        public final AtomicInteger c;
        public final ListenableFuture<? extends T>[] d;
        public volatile int e;

        public /* synthetic */ f(ListenableFuture[] listenableFutureArr, a aVar) {
            this(listenableFutureArr);
        }

        public final void e() {
            ListenableFuture<? extends T>[] listenableFutureArr;
            if (this.c.decrementAndGet() == 0 && this.f10791a) {
                for (ListenableFuture<? extends T> listenableFuture : this.d) {
                    if (listenableFuture != null) {
                        listenableFuture.cancel(this.b);
                    }
                }
            }
        }

        public final void f(ImmutableList<AbstractFuture<T>> immutableList, int i) {
            ListenableFuture<? extends T>[] listenableFutureArr = this.d;
            ListenableFuture<? extends T> listenableFuture = listenableFutureArr[i];
            listenableFutureArr[i] = null;
            for (int i2 = this.e; i2 < immutableList.size(); i2++) {
                if (immutableList.get(i2).setFuture(listenableFuture)) {
                    e();
                    this.e = i2 + 1;
                    return;
                }
            }
            this.e = immutableList.size();
        }

        public final void g(boolean z) {
            this.f10791a = true;
            if (!z) {
                this.b = false;
            }
            e();
        }

        public f(ListenableFuture<? extends T>[] listenableFutureArr) {
            this.f10791a = false;
            this.b = true;
            this.e = 0;
            this.d = listenableFutureArr;
            this.c = new AtomicInteger(listenableFutureArr.length);
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<V> extends AbstractFuture.j<V> implements Runnable {
        public ListenableFuture<V> o;

        public g(ListenableFuture<V> listenableFuture) {
            this.o = listenableFuture;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.o = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            ListenableFuture<V> listenableFuture = this.o;
            if (listenableFuture != null) {
                String valueOf = String.valueOf(listenableFuture);
                StringBuilder sb = new StringBuilder(valueOf.length() + 11);
                sb.append("delegate=[");
                sb.append(valueOf);
                sb.append("]");
                return sb.toString();
            }
            return null;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<V> listenableFuture = this.o;
            if (listenableFuture != null) {
                setFuture(listenableFuture);
            }
        }
    }

    public static void a(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new d(listenableFuture, futureCallback), executor);
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new e.a(ImmutableList.copyOf(listenableFutureArr), true);
    }

    @Beta
    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        return com.google.common.util.concurrent.a.y(listenableFuture, cls, function, executor);
    }

    @Beta
    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        return com.google.common.util.concurrent.a.z(listenableFuture, cls, asyncFunction, executor);
    }

    @Beta
    @GwtIncompatible
    @CanIgnoreReturnValue
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls) throws Exception {
        return (V) k.d(future, cls);
    }

    @CanIgnoreReturnValue
    public static <V> V getDone(Future<V> future) throws ExecutionException {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
        return (V) Uninterruptibles.getUninterruptibly(future);
    }

    @CanIgnoreReturnValue
    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return (V) Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e2) {
            a(e2.getCause());
            throw new AssertionError();
        }
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new n.a();
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new n.b(th);
    }

    public static <V> ListenableFuture<V> immediateFuture(@NullableDecl V v) {
        if (v == null) {
            return (ListenableFuture<V>) n.i;
        }
        return new n(v);
    }

    public static ListenableFuture<Void> immediateVoidFuture() {
        return n.i;
    }

    @Beta
    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        Collection copyOf;
        if (iterable instanceof Collection) {
            copyOf = (Collection) iterable;
        } else {
            copyOf = ImmutableList.copyOf(iterable);
        }
        ListenableFuture[] listenableFutureArr = (ListenableFuture[]) copyOf.toArray(new ListenableFuture[copyOf.size()]);
        f fVar = new f(listenableFutureArr, null);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < listenableFutureArr.length; i++) {
            builder.add((ImmutableList.Builder) new e(fVar, null));
        }
        ImmutableList<ListenableFuture<T>> build = builder.build();
        for (int i2 = 0; i2 < listenableFutureArr.length; i2++) {
            listenableFutureArr[i2].addListener(new c(fVar, build, i2), MoreExecutors.directExecutor());
        }
        return build;
    }

    @Beta
    @GwtIncompatible
    public static <I, O> Future<O> lazyTransform(Future<I> future, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(function);
        return new b(future, function);
    }

    @Beta
    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        g gVar = new g(listenableFuture);
        listenableFuture.addListener(gVar, MoreExecutors.directExecutor());
        return gVar;
    }

    @Beta
    @GwtIncompatible
    public static <O> ListenableFuture<O> scheduleAsync(AsyncCallable<O> asyncCallable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        w y = w.y(asyncCallable);
        y.addListener(new a(scheduledExecutorService.schedule(y, j, timeUnit)), MoreExecutors.directExecutor());
        return y;
    }

    @Beta
    public static <O> ListenableFuture<O> submit(Callable<O> callable, Executor executor) {
        w A = w.A(callable);
        executor.execute(A);
        return A;
    }

    @Beta
    public static <O> ListenableFuture<O> submitAsync(AsyncCallable<O> asyncCallable, Executor executor) {
        w y = w.y(asyncCallable);
        executor.execute(y);
        return y;
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new e.a(ImmutableList.copyOf(listenableFutureArr), false);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        return com.google.common.util.concurrent.b.y(listenableFuture, function, executor);
    }

    @Beta
    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        return com.google.common.util.concurrent.b.z(listenableFuture, asyncFunction, executor);
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(listenableFutureArr), null);
    }

    @SafeVarargs
    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(listenableFutureArr), null);
    }

    @Beta
    @GwtIncompatible
    public static <V> ListenableFuture<V> withTimeout(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return listenableFuture.isDone() ? listenableFuture : v.B(listenableFuture, j, timeUnit, scheduledExecutorService);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new e.a(ImmutableList.copyOf(iterable), true);
    }

    @Beta
    @GwtIncompatible
    @CanIgnoreReturnValue
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls, long j, TimeUnit timeUnit) throws Exception {
        return (V) k.e(future, cls, j, timeUnit);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new e.a(ImmutableList.copyOf(iterable), false);
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllComplete(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(iterable), null);
    }

    @Beta
    public static <V> FutureCombiner<V> whenAllSucceed(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(iterable), null);
    }

    @Beta
    public static ListenableFuture<Void> submit(Runnable runnable, Executor executor) {
        w z = w.z(runnable, null);
        executor.execute(z);
        return z;
    }
}
