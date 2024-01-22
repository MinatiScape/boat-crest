package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class FutureChain<V> implements ListenableFuture<V> {
    @NonNull
    public final ListenableFuture<V> h;
    @Nullable
    public CallbackToFutureAdapter.Completer<V> i;

    /* loaded from: classes.dex */
    public class a implements CallbackToFutureAdapter.Resolver<V> {
        public a() {
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<V> completer) {
            Preconditions.checkState(FutureChain.this.i == null, "The result can only set once!");
            FutureChain.this.i = completer;
            return "FutureChain[" + FutureChain.this + "]";
        }
    }

    public FutureChain(@NonNull ListenableFuture<V> listenableFuture) {
        this.h = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    @NonNull
    public static <V> FutureChain<V> from(@NonNull ListenableFuture<V> listenableFuture) {
        return listenableFuture instanceof FutureChain ? (FutureChain) listenableFuture : new FutureChain<>(listenableFuture);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(@Nullable V v) {
        CallbackToFutureAdapter.Completer<V> completer = this.i;
        if (completer != null) {
            return completer.set(v);
        }
        return false;
    }

    public final void addCallback(@NonNull FutureCallback<? super V> futureCallback, @NonNull Executor executor) {
        Futures.addCallback(this, futureCallback, executor);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
        this.h.addListener(runnable, executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(@NonNull Throwable th) {
        CallbackToFutureAdapter.Completer<V> completer = this.i;
        if (completer != null) {
            return completer.setException(th);
        }
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.h.cancel(z);
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get() throws InterruptedException, ExecutionException {
        return this.h.get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.h.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.h.isDone();
    }

    @NonNull
    public final <T> FutureChain<T> transform(@NonNull Function<? super V, T> function, @NonNull Executor executor) {
        return (FutureChain) Futures.transform(this, function, executor);
    }

    @NonNull
    public final <T> FutureChain<T> transformAsync(@NonNull AsyncFunction<? super V, T> asyncFunction, @NonNull Executor executor) {
        return (FutureChain) Futures.transformAsync(this, asyncFunction, executor);
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public V get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.h.get(j, timeUnit);
    }

    public FutureChain() {
        this.h = CallbackToFutureAdapter.getFuture(new a());
    }
}
