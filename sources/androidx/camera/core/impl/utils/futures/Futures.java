package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.c;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.clevertap.android.sdk.Constants;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
/* loaded from: classes.dex */
public final class Futures {

    /* renamed from: a  reason: collision with root package name */
    public static final Function<?, ?> f747a = new b();

    /* JADX INFO: Add missing generic type declarations: [I, O] */
    /* loaded from: classes.dex */
    public class a<I, O> implements AsyncFunction<I, O> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function f748a;

        public a(Function function) {
            this.f748a = function;
        }

        @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
        public ListenableFuture<O> apply(I i) {
            return Futures.immediateFuture(this.f748a.apply(i));
        }
    }

    /* loaded from: classes.dex */
    public class b implements Function<Object, Object> {
        @Override // androidx.arch.core.util.Function
        public Object apply(Object obj) {
            return obj;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* loaded from: classes.dex */
    public class c<I> implements FutureCallback<I> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f749a;
        public final /* synthetic */ Function b;

        public c(CallbackToFutureAdapter.Completer completer, Function function) {
            this.f749a = completer;
            this.b = function;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            this.f749a.setException(th);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onSuccess(@Nullable I i) {
            try {
                this.f749a.set(this.b.apply(i));
            } catch (Throwable th) {
                this.f749a.setException(th);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        public final /* synthetic */ ListenableFuture h;

        public d(ListenableFuture listenableFuture) {
            this.h = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.cancel(true);
        }
    }

    /* loaded from: classes.dex */
    public static final class e<V> implements Runnable {
        public final Future<V> h;
        public final FutureCallback<? super V> i;

        public e(Future<V> future, FutureCallback<? super V> futureCallback) {
            this.h = future;
            this.i = futureCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
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
            return e.class.getSimpleName() + Constants.SEPARATOR_COMMA + this.i;
        }
    }

    public static <V> void addCallback(@NonNull ListenableFuture<V> listenableFuture, @NonNull FutureCallback<? super V> futureCallback, @NonNull Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new e(listenableFuture, futureCallback), executor);
    }

    @NonNull
    public static <V> ListenableFuture<List<V>> allAsList(@NonNull Collection<? extends ListenableFuture<? extends V>> collection) {
        return new androidx.camera.core.impl.utils.futures.d(new ArrayList(collection), true, CameraXExecutors.directExecutor());
    }

    public static /* synthetic */ Object b(ListenableFuture listenableFuture, CallbackToFutureAdapter.Completer completer) throws Exception {
        c(false, listenableFuture, f747a, completer, CameraXExecutors.directExecutor());
        return "nonCancellationPropagating[" + listenableFuture + "]";
    }

    public static <I, O> void c(boolean z, @NonNull ListenableFuture<I> listenableFuture, @NonNull Function<? super I, ? extends O> function, @NonNull CallbackToFutureAdapter.Completer<O> completer, @NonNull Executor executor) {
        Preconditions.checkNotNull(listenableFuture);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(completer);
        Preconditions.checkNotNull(executor);
        addCallback(listenableFuture, new c(completer, function), executor);
        if (z) {
            completer.addCancellationListener(new d(listenableFuture), CameraXExecutors.directExecutor());
        }
    }

    @Nullable
    public static <V> V getDone(@NonNull Future<V> future) throws ExecutionException {
        boolean isDone = future.isDone();
        Preconditions.checkState(isDone, "Future was expected to be done, " + future);
        return (V) getUninterruptibly(future);
    }

    @Nullable
    public static <V> V getUninterruptibly(@NonNull Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    @NonNull
    public static <V> ListenableFuture<V> immediateFailedFuture(@NonNull Throwable th) {
        return new c.a(th);
    }

    @NonNull
    public static <V> ScheduledFuture<V> immediateFailedScheduledFuture(@NonNull Throwable th) {
        return new c.b(th);
    }

    @NonNull
    public static <V> ListenableFuture<V> immediateFuture(@Nullable V v) {
        if (v == null) {
            return androidx.camera.core.impl.utils.futures.c.a();
        }
        return new c.C0119c(v);
    }

    @NonNull
    public static <V> ListenableFuture<V> nonCancellationPropagating(@NonNull final ListenableFuture<V> listenableFuture) {
        Preconditions.checkNotNull(listenableFuture);
        return listenableFuture.isDone() ? listenableFuture : CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.utils.futures.b
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object b2;
                b2 = Futures.b(ListenableFuture.this, completer);
                return b2;
            }
        });
    }

    public static <V> void propagate(@NonNull ListenableFuture<V> listenableFuture, @NonNull CallbackToFutureAdapter.Completer<V> completer) {
        propagateTransform(listenableFuture, f747a, completer, CameraXExecutors.directExecutor());
    }

    public static <I, O> void propagateTransform(@NonNull ListenableFuture<I> listenableFuture, @NonNull Function<? super I, ? extends O> function, @NonNull CallbackToFutureAdapter.Completer<O> completer, @NonNull Executor executor) {
        c(true, listenableFuture, function, completer, executor);
    }

    @NonNull
    public static <V> ListenableFuture<List<V>> successfulAsList(@NonNull Collection<? extends ListenableFuture<? extends V>> collection) {
        return new androidx.camera.core.impl.utils.futures.d(new ArrayList(collection), false, CameraXExecutors.directExecutor());
    }

    @NonNull
    public static <I, O> ListenableFuture<O> transform(@NonNull ListenableFuture<I> listenableFuture, @NonNull Function<? super I, ? extends O> function, @NonNull Executor executor) {
        Preconditions.checkNotNull(function);
        return transformAsync(listenableFuture, new a(function), executor);
    }

    @NonNull
    public static <I, O> ListenableFuture<O> transformAsync(@NonNull ListenableFuture<I> listenableFuture, @NonNull AsyncFunction<? super I, ? extends O> asyncFunction, @NonNull Executor executor) {
        androidx.camera.core.impl.utils.futures.a aVar = new androidx.camera.core.impl.utils.futures.a(asyncFunction, listenableFuture);
        listenableFuture.addListener(aVar, executor);
        return aVar;
    }
}
