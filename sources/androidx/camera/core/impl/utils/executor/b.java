package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class b extends AbstractExecutorService implements ScheduledExecutorService {
    public static ThreadLocal<ScheduledExecutorService> i = new a();
    public final Handler h;

    /* loaded from: classes.dex */
    public class a extends ThreadLocal<ScheduledExecutorService> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public ScheduledExecutorService initialValue() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return CameraXExecutors.mainThreadExecutor();
            }
            if (Looper.myLooper() != null) {
                return new b(new Handler(Looper.myLooper()));
            }
            return null;
        }
    }

    /* renamed from: androidx.camera.core.impl.utils.executor.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class CallableC0116b implements Callable<Void> {
        public final /* synthetic */ Runnable h;

        public CallableC0116b(b bVar, Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            this.h.run();
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class c<V> implements RunnableScheduledFuture<V> {
        public final AtomicReference<CallbackToFutureAdapter.Completer<V>> h = new AtomicReference<>(null);
        public final long i;
        public final Callable<V> j;
        public final ListenableFuture<V> k;

        /* loaded from: classes.dex */
        public class a implements CallbackToFutureAdapter.Resolver<V> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Handler f743a;
            public final /* synthetic */ Callable b;

            /* renamed from: androidx.camera.core.impl.utils.executor.b$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public class RunnableC0117a implements Runnable {
                public RunnableC0117a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.h.getAndSet(null) != null) {
                        a aVar = a.this;
                        aVar.f743a.removeCallbacks(c.this);
                    }
                }
            }

            public a(Handler handler, Callable callable) {
                this.f743a = handler;
                this.b = callable;
            }

            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<V> completer) throws RejectedExecutionException {
                completer.addCancellationListener(new RunnableC0117a(), CameraXExecutors.directExecutor());
                c.this.h.set(completer);
                return "HandlerScheduledFuture-" + this.b.toString();
            }
        }

        public c(Handler handler, long j, Callable<V> callable) {
            this.i = j;
            this.j = callable;
            this.k = CallbackToFutureAdapter.getFuture(new a(handler, callable));
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(Delayed delayed) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            return Long.compare(getDelay(timeUnit), delayed.getDelay(timeUnit));
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            return this.k.cancel(z);
        }

        @Override // java.util.concurrent.Future
        public V get() throws ExecutionException, InterruptedException {
            return this.k.get();
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return timeUnit.convert(this.i - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.k.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.k.isDone();
        }

        @Override // java.util.concurrent.RunnableScheduledFuture
        public boolean isPeriodic() {
            return false;
        }

        @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            CallbackToFutureAdapter.Completer<V> andSet = this.h.getAndSet(null);
            if (andSet != null) {
                try {
                    andSet.set(this.j.call());
                } catch (Exception e) {
                    andSet.setException(e);
                }
            }
        }

        @Override // java.util.concurrent.Future
        public V get(long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return this.k.get(j, timeUnit);
        }
    }

    public b(@NonNull Handler handler) {
        this.h = handler;
    }

    public static ScheduledExecutorService b() {
        ScheduledExecutorService scheduledExecutorService = i.get();
        if (scheduledExecutorService == null) {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                b bVar = new b(new Handler(myLooper));
                i.set(bVar);
                return bVar;
            }
            throw new IllegalStateException("Current thread has no looper!");
        }
        return scheduledExecutorService;
    }

    public final RejectedExecutionException a() {
        return new RejectedExecutionException(this.h + " is shutting down");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException(b.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        if (!this.h.post(runnable)) {
            throw a();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        return schedule(new CallableC0116b(this, runnable), j, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NonNull
    public ScheduledFuture<?> scheduleAtFixedRate(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException(b.class.getSimpleName() + " does not yet support fixed-rate scheduling.");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NonNull
    public ScheduledFuture<?> scheduleWithFixedDelay(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException(b.class.getSimpleName() + " does not yet support fixed-delay scheduling.");
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException(b.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.ExecutorService
    @NonNull
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException(b.class.getSimpleName() + " cannot be shut down. Use Looper.quitSafely().");
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    @NonNull
    public <V> ScheduledFuture<V> schedule(@NonNull Callable<V> callable, long j, @NonNull TimeUnit timeUnit) {
        long uptimeMillis = SystemClock.uptimeMillis() + TimeUnit.MILLISECONDS.convert(j, timeUnit);
        c cVar = new c(this.h, uptimeMillis, callable);
        return this.h.postAtTime(cVar, uptimeMillis) ? cVar : Futures.immediateFailedScheduledFuture(a());
    }
}
