package kotlinx.coroutines;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.internal.ConcurrentKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    @NotNull
    public final Executor h;

    public ExecutorCoroutineDispatcherImpl(@NotNull Executor executor) {
        this.h = executor;
        ConcurrentKt.removeFutureOnCancel(getExecutor());
    }

    public final void a(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        JobKt.cancel(coroutineContext, ExceptionsKt.CancellationException("The task was rejected", rejectedExecutionException));
    }

    public final ScheduledFuture<?> b(ScheduledExecutorService scheduledExecutorService, Runnable runnable, CoroutineContext coroutineContext, long j) {
        try {
            return scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            a(coroutineContext, e);
            return null;
        }
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = getExecutor();
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
    }

    @Override // kotlinx.coroutines.Delay
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated without replacement as an internal method never intended for public use")
    @Nullable
    public Object delay(long j, @NotNull Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r1 == null) goto L5;
     */
    @Override // kotlinx.coroutines.CoroutineDispatcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dispatch(@org.jetbrains.annotations.NotNull kotlin.coroutines.CoroutineContext r3, @org.jetbrains.annotations.NotNull java.lang.Runnable r4) {
        /*
            r2 = this;
            java.util.concurrent.Executor r0 = r2.getExecutor()     // Catch: java.util.concurrent.RejectedExecutionException -> L16
            kotlinx.coroutines.AbstractTimeSource r1 = kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()     // Catch: java.util.concurrent.RejectedExecutionException -> L16
            if (r1 != 0) goto Lb
            goto L11
        Lb:
            java.lang.Runnable r1 = r1.wrapTask(r4)     // Catch: java.util.concurrent.RejectedExecutionException -> L16
            if (r1 != 0) goto L12
        L11:
            r1 = r4
        L12:
            r0.execute(r1)     // Catch: java.util.concurrent.RejectedExecutionException -> L16
            goto L2b
        L16:
            r0 = move-exception
            kotlinx.coroutines.AbstractTimeSource r1 = kotlinx.coroutines.AbstractTimeSourceKt.getTimeSource()
            if (r1 != 0) goto L1e
            goto L21
        L1e:
            r1.unTrackTask()
        L21:
            r2.a(r3, r0)
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            r0.dispatch(r3, r4)
        L2b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.ExecutorCoroutineDispatcherImpl.dispatch(kotlin.coroutines.CoroutineContext, java.lang.Runnable):void");
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).getExecutor() == getExecutor();
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this.h;
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }

    @Override // kotlinx.coroutines.Delay
    @NotNull
    public DisposableHandle invokeOnTimeout(long j, @NotNull Runnable runnable, @NotNull CoroutineContext coroutineContext) {
        Executor executor = getExecutor();
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        ScheduledFuture<?> b = scheduledExecutorService != null ? b(scheduledExecutorService, runnable, coroutineContext, j) : null;
        if (b != null) {
            return new m(b);
        }
        return DefaultExecutor.INSTANCE.invokeOnTimeout(j, runnable, coroutineContext);
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, @NotNull CancellableContinuation<? super Unit> cancellableContinuation) {
        Executor executor = getExecutor();
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        ScheduledFuture<?> b = scheduledExecutorService != null ? b(scheduledExecutorService, new a0(this, cancellableContinuation), cancellableContinuation.getContext(), j) : null;
        if (b != null) {
            JobKt.cancelFutureOnCancellation(cancellableContinuation, b);
        } else {
            DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(j, cancellableContinuation);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        return getExecutor().toString();
    }
}
