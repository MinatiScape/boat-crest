package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public abstract class c<V> implements ListenableFuture<V> {

    /* loaded from: classes.dex */
    public static class a<V> extends c<V> {
        @NonNull
        public final Throwable h;

        public a(@NonNull Throwable th) {
            this.h = th;
        }

        @Override // androidx.camera.core.impl.utils.futures.c, java.util.concurrent.Future
        @Nullable
        public V get() throws ExecutionException {
            throw new ExecutionException(this.h);
        }

        @NonNull
        public String toString() {
            return super.toString() + "[status=FAILURE, cause=[" + this.h + "]]";
        }
    }

    /* loaded from: classes.dex */
    public static final class b<V> extends a<V> implements ScheduledFuture<V> {
        public b(@NonNull Throwable th) {
            super(th);
        }

        @Override // java.lang.Comparable
        /* renamed from: b */
        public int compareTo(@NonNull Delayed delayed) {
            return -1;
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(@NonNull TimeUnit timeUnit) {
            return 0L;
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.futures.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0119c<V> extends c<V> {
        public static final c<Object> i = new C0119c(null);
        @Nullable
        public final V h;

        public C0119c(@Nullable V v) {
            this.h = v;
        }

        @Override // androidx.camera.core.impl.utils.futures.c, java.util.concurrent.Future
        @Nullable
        public V get() {
            return this.h;
        }

        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + this.h + "]]";
        }
    }

    public static <V> ListenableFuture<V> a() {
        return C0119c.i;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(executor);
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger.e("ImmediateFuture", "Experienced RuntimeException while attempting to notify " + runnable + " on Executor " + executor, e);
        }
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    @Nullable
    public abstract V get() throws ExecutionException;

    @Override // java.util.concurrent.Future
    @Nullable
    public V get(long j, @NonNull TimeUnit timeUnit) throws ExecutionException {
        Preconditions.checkNotNull(timeUnit);
        return get();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }
}
