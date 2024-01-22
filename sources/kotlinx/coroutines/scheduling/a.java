package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class a extends ExecutorCoroutineDispatcher implements TaskContext, Executor {
    public static final /* synthetic */ AtomicIntegerFieldUpdater m = AtomicIntegerFieldUpdater.newUpdater(a.class, "inFlightTasks");
    @NotNull
    public final ExperimentalCoroutineDispatcher h;
    public final int i;
    @Nullable
    public final String j;
    public final int k;
    @NotNull
    public final ConcurrentLinkedQueue<Runnable> l = new ConcurrentLinkedQueue<>();
    @NotNull
    private volatile /* synthetic */ int inFlightTasks = 0;

    public a(@NotNull ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int i, @Nullable String str, int i2) {
        this.h = experimentalCoroutineDispatcher;
        this.i = i;
        this.j = str;
        this.k = i2;
    }

    public final void a(Runnable runnable, boolean z) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = m;
            if (atomicIntegerFieldUpdater.incrementAndGet(this) <= this.i) {
                this.h.dispatchWithContext$kotlinx_coroutines_core(runnable, this, z);
                return;
            }
            this.l.add(runnable);
            if (atomicIntegerFieldUpdater.decrementAndGet(this) >= this.i) {
                return;
            }
            runnable = this.l.poll();
        } while (runnable != null);
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
        Runnable poll = this.l.poll();
        if (poll != null) {
            this.h.dispatchWithContext$kotlinx_coroutines_core(poll, this, true);
            return;
        }
        m.decrementAndGet(this);
        Runnable poll2 = this.l.poll();
        if (poll2 == null) {
            return;
        }
        a(poll2, true);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher".toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        a(runnable, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(@NotNull CoroutineContext coroutineContext, @NotNull Runnable runnable) {
        a(runnable, true);
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable runnable) {
        a(runnable, false);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    @NotNull
    public Executor getExecutor() {
        return this;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.k;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    @NotNull
    public String toString() {
        String str = this.j;
        if (str == null) {
            return super.toString() + "[dispatcher = " + this.h + ']';
        }
        return str;
    }
}
